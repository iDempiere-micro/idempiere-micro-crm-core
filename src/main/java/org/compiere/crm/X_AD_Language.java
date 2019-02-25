package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_Language;
import org.compiere.orm.BasePOName;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_Language
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Language extends BasePOName implements I_AD_Language {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_Language(Properties ctx, int AD_Language_ID) {
        super(ctx, AD_Language_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_Language(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_Language(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 4 - System
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_Language[" + getId() + "]";
    }

    /**
     * Get Language.
     *
     * @return Language for this entity
     */
    public String getLanguage() {
        return (String) getValue(COLUMNNAME_AD_Language);
    }

    /**
     * Set Language.
     *
     * @param AD_Language Language for this entity
     */
    public void setLanguage(String AD_Language) {
        set_ValueNoCheck(COLUMNNAME_AD_Language, AD_Language);
    }

    /**
     * Get Language ID.
     *
     * @return Language ID
     */
    public int getLanguageId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_Language_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Language ID.
     *
     * @param AD_Language_ID Language ID
     */
    public void setLanguageId(int AD_Language_ID) {
        if (AD_Language_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Language_ID, null);
        else set_ValueNoCheck(COLUMNNAME_AD_Language_ID, Integer.valueOf(AD_Language_ID));
    }

    /**
     * Get ISO Country Code.
     *
     * @return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 -
     * http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
     */
    public String getCountryCode() {
        return (String) getValue(COLUMNNAME_CountryCode);
    }

    /**
     * Set ISO Country Code.
     *
     * @param CountryCode Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1
     *                    - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
     */
    public void setCountryCode(String CountryCode) {
        set_Value(COLUMNNAME_CountryCode, CountryCode);
    }

    /**
     * Get Date Pattern.
     *
     * @return Java Date Pattern
     */
    public String getDatePattern() {
        return (String) getValue(COLUMNNAME_DatePattern);
    }

    /**
     * Set Base Language.
     *
     * @param IsBaseLanguage The system information is maintained in this language
     */
    public void setIsBaseLanguage(boolean IsBaseLanguage) {
        set_ValueNoCheck(COLUMNNAME_IsBaseLanguage, Boolean.valueOf(IsBaseLanguage));
    }

    /**
     * Get Base Language.
     *
     * @return The system information is maintained in this language
     */
    public boolean isBaseLanguage() {
        return charToBoolean(getValue(COLUMNNAME_IsBaseLanguage));
    }

    /**
     * Set System Language.
     *
     * @param IsSystemLanguage The screens, etc. are maintained in this Language
     */
    public void setIsSystemLanguage(boolean IsSystemLanguage) {
        set_Value(COLUMNNAME_IsSystemLanguage, Boolean.valueOf(IsSystemLanguage));
    }

    /**
     * Get System Language.
     *
     * @return The screens, etc. are maintained in this Language
     */
    public boolean isSystemLanguage() {
        return charToBoolean(getValue(COLUMNNAME_IsSystemLanguage));
    }

    /**
     * Get ISO Language Code.
     *
     * @return Lower-case two-letter ISO-3166 code -
     * http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
     */
    public String getLanguageISO() {
        return (String) getValue(COLUMNNAME_LanguageISO);
    }

    /**
     * Set ISO Language Code.
     *
     * @param LanguageISO Lower-case two-letter ISO-3166 code -
     *                    http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
     */
    public void setLanguageISO(String LanguageISO) {
        set_Value(COLUMNNAME_LanguageISO, LanguageISO);
    }

    @Override
    public int getTableId() {
        return I_AD_Language.Table_ID;
    }
}
