package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_Language;
import org.compiere.orm.BasePOName;
import org.compiere.orm.MTable;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_Language
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_Language extends BasePOName implements I_AD_Language, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_Language(Properties ctx, int AD_Language_ID, String trxName) {
    super(ctx, AD_Language_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_Language(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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
   * Set Language.
   *
   * @param AD_Language Language for this entity
   */
  public void setADLanguage(String AD_Language) {
    set_ValueNoCheck(COLUMNNAME_AD_Language, AD_Language);
  }

  /**
   * Get Language.
   *
   * @return Language for this entity
   */
  public String getADLanguage() {
    return (String) get_Value(COLUMNNAME_AD_Language);
  }

  /**
   * Set Language ID.
   *
   * @param AD_Language_ID Language ID
   */
  public void setADLanguage_ID(int AD_Language_ID) {
    if (AD_Language_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_Language_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_Language_ID, Integer.valueOf(AD_Language_ID));
  }

  /**
   * Get Language ID.
   *
   * @return Language ID
   */
  public int getAD_Language_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_Language_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_Language_UU.
   *
   * @param AD_Language_UU AD_Language_UU
   */
  public void setADLanguage_UU(String AD_Language_UU) {
    set_Value(COLUMNNAME_AD_Language_UU, AD_Language_UU);
  }

  /**
   * Get AD_Language_UU.
   *
   * @return AD_Language_UU
   */
  public String getAD_Language_UU() {
    return (String) get_Value(COLUMNNAME_AD_Language_UU);
  }

  public org.compiere.model.I_AD_PrintPaper getAD_PrintPaper() throws RuntimeException {
    return (org.compiere.model.I_AD_PrintPaper)
        MTable.get(getCtx(), org.compiere.model.I_AD_PrintPaper.Table_Name)
            .getPO(getAD_PrintPaper_ID(), get_TrxName());
  }

  /**
   * Set Print Paper.
   *
   * @param AD_PrintPaper_ID Printer paper definition
   */
  public void setAD_PrintPaper_ID(int AD_PrintPaper_ID) {
    if (AD_PrintPaper_ID < 1) set_Value(COLUMNNAME_AD_PrintPaper_ID, null);
    else set_Value(COLUMNNAME_AD_PrintPaper_ID, Integer.valueOf(AD_PrintPaper_ID));
  }

  /**
   * Get Print Paper.
   *
   * @return Printer paper definition
   */
  public int getAD_PrintPaper_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_PrintPaper_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set ISO Country Code.
   *
   * @param CountryCode Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1
   *     - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
   */
  public void setCountryCode(String CountryCode) {
    set_Value(COLUMNNAME_CountryCode, CountryCode);
  }

  /**
   * Get ISO Country Code.
   *
   * @return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 -
   *     http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
   */
  public String getCountryCode() {
    return (String) get_Value(COLUMNNAME_CountryCode);
  }

  /**
   * Set Date Pattern.
   *
   * @param DatePattern Java Date Pattern
   */
  public void setDatePattern(String DatePattern) {
    set_Value(COLUMNNAME_DatePattern, DatePattern);
  }

  /**
   * Get Date Pattern.
   *
   * @return Java Date Pattern
   */
  public String getDatePattern() {
    return (String) get_Value(COLUMNNAME_DatePattern);
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
    return charToBoolean(get_Value(COLUMNNAME_IsBaseLanguage));
  }

  /**
   * Set Decimal Point.
   *
   * @param IsDecimalPoint The number notation has a decimal point (no decimal comma)
   */
  public void setIsDecimalPoint(boolean IsDecimalPoint) {
    set_Value(COLUMNNAME_IsDecimalPoint, Boolean.valueOf(IsDecimalPoint));
  }

  /**
   * Get Decimal Point.
   *
   * @return The number notation has a decimal point (no decimal comma)
   */
  public boolean isDecimalPoint() {
    return charToBoolean(get_Value(COLUMNNAME_IsDecimalPoint));
  }

  /**
   * Set Login Locale.
   *
   * @param IsLoginLocale Login Locale
   */
  public void setIsLoginLocale(boolean IsLoginLocale) {
    set_Value(COLUMNNAME_IsLoginLocale, Boolean.valueOf(IsLoginLocale));
  }

  /**
   * Get Login Locale.
   *
   * @return Login Locale
   */
  public boolean isLoginLocale() {
    return charToBoolean(get_Value(COLUMNNAME_IsLoginLocale));
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
    return charToBoolean(get_Value(COLUMNNAME_IsSystemLanguage));
  }

  /**
   * Set ISO Language Code.
   *
   * @param LanguageISO Lower-case two-letter ISO-3166 code -
   *     http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
   */
  public void setLanguageISO(String LanguageISO) {
    set_Value(COLUMNNAME_LanguageISO, LanguageISO);
  }

  /**
   * Get ISO Language Code.
   *
   * @return Lower-case two-letter ISO-3166 code -
   *     http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt
   */
  public String getLanguageISO() {
    return (String) get_Value(COLUMNNAME_LanguageISO);
  }

  /**
   * Set Print Text.
   *
   * @param PrintName The label text to be printed on a document or correspondence.
   */
  public void setPrintName(String PrintName) {
    set_Value(COLUMNNAME_PrintName, PrintName);
  }

  /**
   * Get Print Text.
   *
   * @return The label text to be printed on a document or correspondence.
   */
  public String getPrintName() {
    return (String) get_Value(COLUMNNAME_PrintName);
  }

  /**
   * Set Process Now.
   *
   * @param Processing Process Now
   */
  public void setProcessing(boolean Processing) {
    set_Value(COLUMNNAME_Processing, Boolean.valueOf(Processing));
  }

  /**
   * Get Process Now.
   *
   * @return Process Now
   */
  public boolean isProcessing() {
    return charToBoolean(get_Value(COLUMNNAME_Processing));
  }

  /**
   * Set Time Pattern.
   *
   * @param TimePattern Java Time Pattern
   */
  public void setTimePattern(String TimePattern) {
    set_Value(COLUMNNAME_TimePattern, TimePattern);
  }

  /**
   * Get Time Pattern.
   *
   * @return Java Time Pattern
   */
  public String getTimePattern() {
    return (String) get_Value(COLUMNNAME_TimePattern);
  }

  @Override
  public int getTableId() {
    return I_AD_Language.Table_ID;
  }
}
