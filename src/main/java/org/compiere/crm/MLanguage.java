package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_Language;
import org.compiere.orm.Query;
import org.compiere.util.MsgKt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
     * @param AD_Language_ID id
     */
    public MLanguage(int AD_Language_ID) {
        super(AD_Language_ID);
    } //	MLanguage

    //	/**	Logger						*/
    //	private static CLogger		s_log = CLogger.getCLogger (MLanguage.class);

    /**
     * Load Constructor
     */
    public MLanguage(Row row) {
        super(row);
    }

    /**
     * Create Language
     *
     * @param AD_Language language code
     * @param Name        name
     * @param CountryCode country code
     * @param LanguageISO language code
     */
    private MLanguage(
            String AD_Language,
            String Name,
            String CountryCode,
            String LanguageISO) {
        super(0);
        setLanguage(AD_Language); // 	en_US
        setIsBaseLanguage(false);
        setIsSystemLanguage(false);
        setName(Name);
        setCountryCode(CountryCode); // 	US
        setLanguageISO(LanguageISO); // 	en
    } //	MLanguage

    /**
     * Get Language Model from AD_Language
     *
     * @param AD_Language language e.g. en_US
     * @return language or null
     */
    public static I_AD_Language get(String AD_Language) {
        return new Query(
                I_AD_Language.Table_Name, I_AD_Language.COLUMNNAME_AD_Language + "=?")
                .setParameters(AD_Language)
                .firstOnly();
    } //	get

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "MLanguage[" +
                getLanguage() +
                "-" +
                getName() +
                ",Language=" +
                getLanguageISO() +
                ",Country=" +
                getCountryCode() +
                "]";
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
     * Set AD_Language_ID
     */
    private void setADLanguageId() {
        int AD_Language_ID = getLanguageId();
        if (AD_Language_ID == 0) {
            String sql =
                    "SELECT NVL(MAX(AD_Language_ID), 999999) FROM AD_Language WHERE AD_Language_ID > 1000";
            AD_Language_ID = getSQLValue(sql);
            setLanguageId(AD_Language_ID + 1);
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
        if (isValueChanged("DatePattern") && dp != null && dp.length() > 0) {
            if (!dp.contains("MM")) {
                log.saveError(
                        "Error", MsgKt.parseTranslation("@Error@ @DatePattern@ - No Month (MM)"));
                return false;
            }
            if (!dp.contains("dd")) {
                log.saveError(
                        "Error", MsgKt.parseTranslation("@Error@ @DatePattern@ - No Day (dd)"));
                return false;
            }
            if (!dp.contains("yy")) {
                log.saveError(
                        "Error", MsgKt.parseTranslation("@Error@ @DatePattern@ - No Year (yy)"));
                return false;
            }

            m_dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, getLocale());
            try {
                m_dateFormat.applyPattern(dp);
            } catch (Exception e) {
                log.saveError(
                        "Error", MsgKt.parseTranslation("@Error@ @DatePattern@ - " + e.getMessage()));
                m_dateFormat = null;
                return false;
            }
        }
        if (newRecord) setADLanguageId();
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
