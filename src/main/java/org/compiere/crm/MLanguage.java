package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_Language;
import org.compiere.orm.Query;
import org.compiere.util.Msg;
import org.idempiere.common.util.Language;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * Language Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 * <li>BF [ 2444851 ] MLanguage should throw an exception if there is an error
 * @version $Id: MLanguage.java,v 1.4 2006/07/30 00:58:36 jjanke Exp $
 */
public class MLanguage extends X_AD_Language {
    /**
     *
     */
    private static final long serialVersionUID = 6415602943484245447L;
    /**
     * Locale
     */
    private Locale m_locale = null;
    /**
     * Date Format
     */
    private SimpleDateFormat m_dateFormat = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx            context
     * @param AD_Language_ID id
     * @param trxName        transaction
     */
    public MLanguage(Properties ctx, int AD_Language_ID) {
        super(ctx, AD_Language_ID);
    } //	MLanguage

    //	/**	Logger						*/
    //	private static CLogger		s_log = CLogger.getCLogger (MLanguage.class);

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MLanguage(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MLanguage

    public MLanguage(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Create Language
     *
     * @param ctx         context
     * @param AD_Language language code
     * @param Name        name
     * @param CountryCode country code
     * @param LanguageISO language code
     * @param trxName     transaction
     */
    private MLanguage(
            Properties ctx,
            String AD_Language,
            String Name,
            String CountryCode,
            String LanguageISO,
            String trxName) {
        super(ctx, 0);
        setADLanguage(AD_Language); // 	en_US
        setIsBaseLanguage(false);
        setIsSystemLanguage(false);
        setName(Name);
        setCountryCode(CountryCode); // 	US
        setLanguageISO(LanguageISO); // 	en
    } //	MLanguage

    /**
     * Get Language Model from Language
     *
     * @param ctx  context
     * @param lang language
     * @return language
     */
    public static I_AD_Language get(Properties ctx, Language lang) {
        return get(ctx, lang.getADLanguage());
    } //	getMLanguage

    /**
     * Get Language Model from AD_Language
     *
     * @param ctx         context
     * @param AD_Language language e.g. en_US
     * @return language or null
     */
    public static I_AD_Language get(Properties ctx, String AD_Language) {
        return new Query(
                ctx, I_AD_Language.Table_Name, I_AD_Language.COLUMNNAME_AD_Language + "=?")
                .setParameters(AD_Language)
                .firstOnly();
    } //	get

    /**
     * Load Languages (variants) with Language
     *
     * @param ctx         context
     * @param LanguageISO language (2 letter) e.g. en
     * @return language
     */
    public static MLanguage[] getWithLanguage(Properties ctx, String LanguageISO) {
        List<MLanguage> list =
                new Query(ctx, I_AD_Language.Table_Name, I_AD_Language.COLUMNNAME_LanguageISO + "=?")
                        .setParameters(LanguageISO)
                        .list();
        return list.toArray(new MLanguage[list.size()]);
    } //	get

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder str =
                new StringBuilder("MLanguage[")
                        .append(getADLanguage())
                        .append("-")
                        .append(getName())
                        .append(",Language=")
                        .append(getLanguageISO())
                        .append(",Country=")
                        .append(getCountryCode())
                        .append("]");
        return str.toString();
    } //	toString

    /**
     * Get Locale
     *
     * @return Locale
     */
    public Locale getLocale() {
        if (m_locale == null) m_locale = new Locale(getLanguageISO(), getCountryCode());
        return m_locale;
    } //	getLocale

    /**
     * Get (Short) Date Format. The date format must parseable by org.compiere.grid.ed.MDocDate i.e.
     * leading zero for date and month
     *
     * @return date format MM/dd/yyyy - dd.MM.yyyy
     */
    public SimpleDateFormat getDateFormat() {
        if (m_dateFormat != null) return m_dateFormat;

        if (getDatePattern() != null) {
            m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, getLocale());
            try {
                m_dateFormat.applyPattern(getDatePattern());
            } catch (Exception e) {
                log.severe(getDatePattern() + " - " + e);
                m_dateFormat = null;
            }
        }

        if (m_dateFormat == null) {
            //	Fix Locale Date format
            m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, getLocale());
            String sFormat = m_dateFormat.toPattern();
            //	some short formats have only one M and d (e.g. ths US)
            if (sFormat.indexOf("MM") == -1 && sFormat.indexOf("dd") == -1) {
                StringBuilder nFormat = new StringBuilder();
                for (int i = 0; i < sFormat.length(); i++) {
                    if (sFormat.charAt(i) == 'M') nFormat.append("MM");
                    else if (sFormat.charAt(i) == 'd') nFormat.append("dd");
                    else nFormat.append(sFormat.charAt(i));
                }
                //	System.out.println(sFormat + " => " + nFormat);
                m_dateFormat.applyPattern(nFormat.toString());
            }
            //	Unknown short format => use JDBC
            if (m_dateFormat.toPattern().length() != 8) m_dateFormat.applyPattern("yyyy-MM-dd");

            //	4 digit year
            if (m_dateFormat.toPattern().indexOf("yyyy") == -1) {
                sFormat = m_dateFormat.toPattern();
                StringBuilder nFormat = new StringBuilder();
                for (int i = 0; i < sFormat.length(); i++) {
                    if (sFormat.charAt(i) == 'y') nFormat.append("yy");
                    else nFormat.append(sFormat.charAt(i));
                }
                m_dateFormat.applyPattern(nFormat.toString());
            }
        }
        //
        m_dateFormat.setLenient(true);
        return m_dateFormat;
    } //  getDateFormat

    /**
     * Set AD_Language_ID
     */
    private void setADLanguage_ID() {
        int AD_Language_ID = getAD_Language_ID();
        if (AD_Language_ID == 0) {
            String sql =
                    "SELECT NVL(MAX(AD_Language_ID), 999999) FROM AD_Language WHERE AD_Language_ID > 1000";
            AD_Language_ID = getSQLValue(sql);
            setADLanguage_ID(AD_Language_ID + 1);
        }
    } //	setADLanguage_ID

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true/false
     */
    protected boolean beforeSave(boolean newRecord) {
        String dp = getDatePattern();
        if (is_ValueChanged("DatePattern") && dp != null && dp.length() > 0) {
            if (dp.indexOf("MM") == -1) {
                log.saveError(
                        "Error", Msg.parseTranslation(getCtx(), "@Error@ @DatePattern@ - No Month (MM)"));
                return false;
            }
            if (dp.indexOf("dd") == -1) {
                log.saveError(
                        "Error", Msg.parseTranslation(getCtx(), "@Error@ @DatePattern@ - No Day (dd)"));
                return false;
            }
            if (dp.indexOf("yy") == -1) {
                log.saveError(
                        "Error", Msg.parseTranslation(getCtx(), "@Error@ @DatePattern@ - No Year (yy)"));
                return false;
            }

            m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, getLocale());
            try {
                m_dateFormat.applyPattern(dp);
            } catch (Exception e) {
                log.saveError(
                        "Error", Msg.parseTranslation(getCtx(), "@Error@ @DatePattern@ - " + e.getMessage()));
                m_dateFormat = null;
                return false;
            }
        }
        if (newRecord) setADLanguage_ID();
        return true;
    } //	beforeSae

    /**
     * AfterSave
     *
     * @param newRecord new
     * @param success   success
     * @return true if saved
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        return true;
    } //	afterSave
} //	MLanguage
