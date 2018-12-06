package org.compiere.crm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.HasName;
import org.compiere.model.HasName2;
import org.compiere.model.I_I_BPartner;
import org.compiere.orm.BasePOUser;
import org.compiere.orm.MTable;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for I_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_I_BPartner extends BasePOUser implements I_I_BPartner, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_I_BPartner(Properties ctx, int I_BPartner_ID, String trxName) {
    super(ctx, I_BPartner_ID, trxName);
  }

  /** Load Constructor */
  public X_I_BPartner(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 2 - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    return "X_I_BPartner[" + getId() + "]";
  }

  /**
   * Set Address 1.
   *
   * @param Address1 Address line 1 for this location
   */
  public void setAddress1(String Address1) {
    set_Value(COLUMNNAME_Address1, Address1);
  }

  /**
   * Get Address 1.
   *
   * @return Address line 1 for this location
   */
  public String getAddress1() {
    return (String) get_Value(COLUMNNAME_Address1);
  }

  /**
   * Set Address 2.
   *
   * @param Address2 Address line 2 for this location
   */
  public void setAddress2(String Address2) {
    set_Value(COLUMNNAME_Address2, Address2);
  }

  /**
   * Get Address 2.
   *
   * @return Address line 2 for this location
   */
  public String getAddress2() {
    return (String) get_Value(COLUMNNAME_Address2);
  }

  /**
   * Set Birthday.
   *
   * @param Birthday Birthday or Anniversary day
   */
  public void setBirthday(Timestamp Birthday) {
    set_Value(COLUMNNAME_Birthday, Birthday);
  }

  /**
   * Get Birthday.
   *
   * @return Birthday or Anniversary day
   */
  public Timestamp getBirthday() {
    return (Timestamp) get_Value(COLUMNNAME_Birthday);
  }

  /**
   * Set BP Contact Greeting.
   *
   * @param BPContactGreeting Greeting for Business Partner Contact
   */
  public void setBPContactGreeting(String BPContactGreeting) {
    set_Value(COLUMNNAME_BPContactGreeting, BPContactGreeting);
  }

  /**
   * Get BP Contact Greeting.
   *
   * @return Greeting for Business Partner Contact
   */
  public String getBPContactGreeting() {
    return (String) get_Value(COLUMNNAME_BPContactGreeting);
  }

  public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException {
    return (org.compiere.model.I_C_BPartner)
        MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
            .getPO(getC_BPartner_ID(), get_TrxName());
  }

  /**
   * Set Business Partner .
   *
   * @param C_BPartner_ID Identifies a Business Partner
   */
  public void setC_BPartner_ID(int C_BPartner_ID) {
    if (C_BPartner_ID < 1) set_Value(COLUMNNAME_C_BPartner_ID, null);
    else set_Value(COLUMNNAME_C_BPartner_ID, C_BPartner_ID);
  }

  /**
   * Get Business Partner .
   *
   * @return Identifies a Business Partner
   */
  public int getC_BPartner_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException {
    return (org.compiere.model.I_C_BPartner_Location)
        MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
            .getPO(getC_BPartner_Location_ID(), get_TrxName());
  }

  /**
   * Set Partner Location.
   *
   * @param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner
   */
  public void setC_BPartner_Location_ID(int C_BPartner_Location_ID) {
    if (C_BPartner_Location_ID < 1) set_Value(COLUMNNAME_C_BPartner_Location_ID, null);
    else set_Value(COLUMNNAME_C_BPartner_Location_ID, C_BPartner_Location_ID);
  }

  /**
   * Get Partner Location.
   *
   * @return Identifies the (ship to) address for this Business Partner
   */
  public int getC_BPartner_Location_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BPartner_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException {
    return (org.compiere.model.I_C_BP_Group)
        MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
            .getPO(getC_BP_Group_ID(), get_TrxName());
  }

  /**
   * Set Business Partner Group.
   *
   * @param C_BP_Group_ID Business Partner Group
   */
  public void setC_BP_Group_ID(int C_BP_Group_ID) {
    if (C_BP_Group_ID < 1) set_Value(COLUMNNAME_C_BP_Group_ID, null);
    else set_Value(COLUMNNAME_C_BP_Group_ID, C_BP_Group_ID);
  }

  /**
   * Get Business Partner Group.
   *
   * @return Business Partner Group
   */
  public int getC_BP_Group_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BP_Group_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_C_Country getC_Country() throws RuntimeException {
    return (org.compiere.model.I_C_Country)
        MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
            .getPO(getC_Country_ID(), get_TrxName());
  }

  /**
   * Set Country.
   *
   * @param C_Country_ID Country
   */
  public void setC_Country_ID(int C_Country_ID) {
    if (C_Country_ID < 1) set_Value(COLUMNNAME_C_Country_ID, null);
    else set_Value(COLUMNNAME_C_Country_ID, C_Country_ID);
  }

  /**
   * Get Country.
   *
   * @return Country
   */
  public int getC_Country_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Country_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException {
    return (org.compiere.model.I_C_Greeting)
        MTable.get(getCtx(), org.compiere.model.I_C_Greeting.Table_Name)
            .getPO(getC_Greeting_ID(), get_TrxName());
  }

  /**
   * Set Greeting.
   *
   * @param C_Greeting_ID Greeting to print on correspondence
   */
  public void setC_Greeting_ID(int C_Greeting_ID) {
    if (C_Greeting_ID < 1) set_Value(COLUMNNAME_C_Greeting_ID, null);
    else set_Value(COLUMNNAME_C_Greeting_ID, C_Greeting_ID);
  }

  /**
   * Get Greeting.
   *
   * @return Greeting to print on correspondence
   */
  public int getC_Greeting_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Greeting_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set City.
   *
   * @param City Identifies a City
   */
  public void setCity(String City) {
    set_Value(COLUMNNAME_City, City);
  }

  /**
   * Get City.
   *
   * @return Identifies a City
   */
  public String getCity() {
    return (String) get_Value(COLUMNNAME_City);
  }

  /**
   * Set Comments.
   *
   * @param Comments Comments or additional information
   */
  public void setComments(String Comments) {
    set_Value(COLUMNNAME_Comments, Comments);
  }

  /**
   * Get Comments.
   *
   * @return Comments or additional information
   */
  public String getComments() {
    return (String) get_Value(COLUMNNAME_Comments);
  }

  /**
   * Set Contact Description.
   *
   * @param ContactDescription Description of Contact
   */
  public void setContactDescription(String ContactDescription) {
    set_Value(COLUMNNAME_ContactDescription, ContactDescription);
  }

  /**
   * Get Contact Description.
   *
   * @return Description of Contact
   */
  public String getContactDescription() {
    return (String) get_Value(COLUMNNAME_ContactDescription);
  }

  /**
   * Set Contact Name.
   *
   * @param ContactName Business Partner Contact Name
   */
  public void setContactName(String ContactName) {
    set_Value(COLUMNNAME_ContactName, ContactName);
  }

  /**
   * Get Contact Name.
   *
   * @return Business Partner Contact Name
   */
  public String getContactName() {
    return (String) get_Value(COLUMNNAME_ContactName);
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

  public org.compiere.model.I_C_Region getC_Region() throws RuntimeException {
    return (org.compiere.model.I_C_Region)
        MTable.get(getCtx(), org.compiere.model.I_C_Region.Table_Name)
            .getPO(getC_Region_ID(), get_TrxName());
  }

  /**
   * Set Region.
   *
   * @param C_Region_ID Identifies a geographical Region
   */
  public void setC_Region_ID(int C_Region_ID) {
    if (C_Region_ID < 1) set_Value(COLUMNNAME_C_Region_ID, null);
    else set_Value(COLUMNNAME_C_Region_ID, C_Region_ID);
  }

  /**
   * Get Region.
   *
   * @return Identifies a geographical Region
   */
  public int getC_Region_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Region_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
  }

  /**
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(COLUMNNAME_Description);
  }

  /**
   * Set D-U-N-S.
   *
   * @param DUNS Dun & Bradstreet Number
   */
  public void setDUNS(String DUNS) {
    set_Value(COLUMNNAME_DUNS, DUNS);
  }

  /**
   * Get D-U-N-S.
   *
   * @return Dun & Bradstreet Number
   */
  public String getDUNS() {
    return (String) get_Value(COLUMNNAME_DUNS);
  }

  /**
   * Set EMail Address.
   *
   * @param EMail Electronic Mail Address
   */
  public void setEMail(String EMail) {
    set_Value(COLUMNNAME_EMail, EMail);
  }

  /**
   * Get EMail Address.
   *
   * @return Electronic Mail Address
   */
  public String getEMail() {
    return (String) get_Value(COLUMNNAME_EMail);
  }

  /**
   * Set Fax.
   *
   * @param Fax Facsimile number
   */
  public void setFax(String Fax) {
    set_Value(COLUMNNAME_Fax, Fax);
  }

  /**
   * Get Fax.
   *
   * @return Facsimile number
   */
  public String getFax() {
    return (String) get_Value(COLUMNNAME_Fax);
  }

  /**
   * Set Group Key.
   *
   * @param GroupValue Business Partner Group Key
   */
  public void setGroupValue(String GroupValue) {
    set_Value(COLUMNNAME_GroupValue, GroupValue);
  }

  /**
   * Get Group Key.
   *
   * @return Business Partner Group Key
   */
  public String getGroupValue() {
    return (String) get_Value(COLUMNNAME_GroupValue);
  }

  /**
   * Set Import Business Partner.
   *
   * @param I_BPartner_ID Import Business Partner
   */
  public void setI_BPartner_ID(int I_BPartner_ID) {
    if (I_BPartner_ID < 1) set_ValueNoCheck(COLUMNNAME_I_BPartner_ID, null);
    else set_ValueNoCheck(COLUMNNAME_I_BPartner_ID, I_BPartner_ID);
  }

  /**
   * Get Import Business Partner.
   *
   * @return Import Business Partner
   */
  public int getI_BPartner_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_I_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set I_BPartner_UU.
   *
   * @param I_BPartner_UU I_BPartner_UU
   */
  public void setI_BPartner_UU(String I_BPartner_UU) {
    set_Value(COLUMNNAME_I_BPartner_UU, I_BPartner_UU);
  }

  /**
   * Get I_BPartner_UU.
   *
   * @return I_BPartner_UU
   */
  public String getI_BPartner_UU() {
    return (String) get_Value(COLUMNNAME_I_BPartner_UU);
  }

  /**
   * Set Import Error Message.
   *
   * @param I_ErrorMsg Messages generated from import process
   */
  public void setI_ErrorMsg(String I_ErrorMsg) {
    set_Value(COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
  }

  /**
   * Get Import Error Message.
   *
   * @return Messages generated from import process
   */
  public String getI_ErrorMsg() {
    return (String) get_Value(COLUMNNAME_I_ErrorMsg);
  }

  /**
   * Set Imported.
   *
   * @param I_IsImported Has this import been processed
   */
  public void setI_IsImported(boolean I_IsImported) {
    set_Value(COLUMNNAME_I_IsImported, I_IsImported);
  }

  /**
   * Get Imported.
   *
   * @return Has this import been processed
   */
  public boolean isI_IsImported() {
    return charToBoolean(get_Value(COLUMNNAME_I_IsImported));
  }

  /**
   * Set Interest Area.
   *
   * @param InterestAreaName Name of the Interest Area
   */
  public void setInterestAreaName(String InterestAreaName) {
    set_Value(COLUMNNAME_InterestAreaName, InterestAreaName);
  }

  /**
   * Get Interest Area.
   *
   * @return Name of the Interest Area
   */
  public String getInterestAreaName() {
    return (String) get_Value(COLUMNNAME_InterestAreaName);
  }

  /**
   * Set Customer.
   *
   * @param IsCustomer Indicates if this Business Partner is a Customer
   */
  public void setIsCustomer(boolean IsCustomer) {
    set_Value(COLUMNNAME_IsCustomer, IsCustomer);
  }

  /**
   * Get Customer.
   *
   * @return Indicates if this Business Partner is a Customer
   */
  public boolean isCustomer() {
    return charToBoolean(get_Value(COLUMNNAME_IsCustomer));
  }

  /**
   * Set Employee.
   *
   * @param IsEmployee Indicates if this Business Partner is an employee
   */
  public void setIsEmployee(boolean IsEmployee) {
    set_Value(COLUMNNAME_IsEmployee, IsEmployee);
  }

  /**
   * Get Employee.
   *
   * @return Indicates if this Business Partner is an employee
   */
  public boolean isEmployee() {
    return charToBoolean(get_Value(COLUMNNAME_IsEmployee));
  }

  /**
   * Set Vendor.
   *
   * @param IsVendor Indicates if this Business Partner is a Vendor
   */
  public void setIsVendor(boolean IsVendor) {
    set_Value(COLUMNNAME_IsVendor, IsVendor);
  }

  /**
   * Get Vendor.
   *
   * @return Indicates if this Business Partner is a Vendor
   */
  public boolean isVendor() {
    return charToBoolean(get_Value(COLUMNNAME_IsVendor));
  }

  /**
   * Set NAICS/SIC.
   *
   * @param NAICS Standard Industry Code or its successor NAIC -
   *     http://www.osha.gov/oshstats/sicser.html
   */
  public void setNAICS(String NAICS) {
    set_Value(COLUMNNAME_NAICS, NAICS);
  }

  /**
   * Get NAICS/SIC.
   *
   * @return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
   */
  public String getNAICS() {
    return (String) get_Value(COLUMNNAME_NAICS);
  }

  /**
   * Set Name.
   *
   * @param Name Alphanumeric identifier of the entity
   */
  public void setName(String Name) {
    set_Value(HasName.Companion.getCOLUMNNAME_Name(), Name);
  }

  /**
   * Get Name.
   *
   * @return Alphanumeric identifier of the entity
   */
  public String getName() {
    return (String) get_Value(HasName.Companion.getCOLUMNNAME_Name());
  }

  /**
   * Set Name 2.
   *
   * @param Name2 Additional Name
   */
  public void setName2(String Name2) {
    set_Value(HasName2.Companion.getCOLUMNNAME_Name2(), Name2);
  }

  /**
   * Get Name 2.
   *
   * @return Additional Name
   */
  public String getName2() {
    return (String) get_Value(HasName2.Companion.getCOLUMNNAME_Name2());
  }

  /**
   * Set Password.
   *
   * @param Password Password of any length (case sensitive)
   */
  public void setPassword(String Password) {
    set_Value(COLUMNNAME_Password, Password);
  }

  /**
   * Get Password.
   *
   * @return Password of any length (case sensitive)
   */
  public String getPassword() {
    return (String) get_Value(COLUMNNAME_Password);
  }

  /**
   * Set Phone.
   *
   * @param Phone Identifies a telephone number
   */
  public void setPhone(String Phone) {
    set_Value(COLUMNNAME_Phone, Phone);
  }

  /**
   * Get Phone.
   *
   * @return Identifies a telephone number
   */
  public String getPhone() {
    return (String) get_Value(COLUMNNAME_Phone);
  }

  /**
   * Set 2nd Phone.
   *
   * @param Phone2 Identifies an alternate telephone number.
   */
  public void setPhone2(String Phone2) {
    set_Value(COLUMNNAME_Phone2, Phone2);
  }

  /**
   * Get 2nd Phone.
   *
   * @return Identifies an alternate telephone number.
   */
  public String getPhone2() {
    return (String) get_Value(COLUMNNAME_Phone2);
  }

  /**
   * Set ZIP.
   *
   * @param Postal Postal code
   */
  public void setPostal(String Postal) {
    set_Value(COLUMNNAME_Postal, Postal);
  }

  /**
   * Get ZIP.
   *
   * @return Postal code
   */
  public String getPostal() {
    return (String) get_Value(COLUMNNAME_Postal);
  }

  /**
   * Set Additional Zip.
   *
   * @param Postal_Add Additional ZIP or Postal code
   */
  public void setPostal_Add(String Postal_Add) {
    set_Value(COLUMNNAME_Postal_Add, Postal_Add);
  }

  /**
   * Get Additional Zip.
   *
   * @return Additional ZIP or Postal code
   */
  public String getPostal_Add() {
    return (String) get_Value(COLUMNNAME_Postal_Add);
  }

  /**
   * Set Processed.
   *
   * @param Processed The document has been processed
   */
  public void setProcessed(boolean Processed) {
    set_ValueNoCheck(COLUMNNAME_Processed, Processed);
  }

  /**
   * Get Processed.
   *
   * @return The document has been processed
   */
  public boolean isProcessed() {
    return charToBoolean(get_Value(COLUMNNAME_Processed));
  }

  /**
   * Set Process Now.
   *
   * @param Processing Process Now
   */
  public void setProcessing(boolean Processing) {
    set_Value(COLUMNNAME_Processing, Processing);
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
   * Set Region.
   *
   * @param RegionName Name of the Region
   */
  public void setRegionName(String RegionName) {
    set_Value(COLUMNNAME_RegionName, RegionName);
  }

  /**
   * Get Region.
   *
   * @return Name of the Region
   */
  public String getRegionName() {
    return (String) get_Value(COLUMNNAME_RegionName);
  }

  public org.compiere.model.I_R_InterestArea getR_InterestArea() throws RuntimeException {
    return (org.compiere.model.I_R_InterestArea)
        MTable.get(getCtx(), org.compiere.model.I_R_InterestArea.Table_Name)
            .getPO(getR_InterestArea_ID(), get_TrxName());
  }

  /**
   * Set Interest Area.
   *
   * @param R_InterestArea_ID Interest Area or Topic
   */
  public void setR_InterestArea_ID(int R_InterestArea_ID) {
    if (R_InterestArea_ID < 1) set_Value(COLUMNNAME_R_InterestArea_ID, null);
    else set_Value(COLUMNNAME_R_InterestArea_ID, Integer.valueOf(R_InterestArea_ID));
  }

  /**
   * Get Interest Area.
   *
   * @return Interest Area or Topic
   */
  public int getR_InterestArea_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_R_InterestArea_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Tax ID.
   *
   * @param TaxID Tax Identification
   */
  public void setTaxID(String TaxID) {
    set_Value(COLUMNNAME_TaxID, TaxID);
  }

  /**
   * Get Tax ID.
   *
   * @return Tax Identification
   */
  public String getTaxID() {
    return (String) get_Value(COLUMNNAME_TaxID);
  }

  /**
   * Set Title.
   *
   * @param Title Name this entity is referred to as
   */
  public void setTitle(String Title) {
    set_Value(COLUMNNAME_Title, Title);
  }

  /**
   * Get Title.
   *
   * @return Name this entity is referred to as
   */
  public String getTitle() {
    return (String) get_Value(COLUMNNAME_Title);
  }

  /**
   * Set Search Key.
   *
   * @param Value Search key for the record in the format required - must be unique
   */
  public void setValue(String Value) {
    set_Value(COLUMNNAME_Value, Value);
  }

  /**
   * Get Search Key.
   *
   * @return Search key for the record in the format required - must be unique
   */
  public String getValue() {
    return (String) get_Value(COLUMNNAME_Value);
  }

  @Override
  public void setIsShipTo(boolean IsShipTo) {
    set_Value(COLUMNNAME_I_IsShipTo, IsShipTo);
  }

  @Override
  public boolean isShipTo() {
    return charToBoolean(get_Value(COLUMNNAME_I_IsShipTo));
  }

  @Override
  public void setIsBillTo(boolean IsBillTo) {
    set_Value(COLUMNNAME_I_IsBillTo, IsBillTo);
  }

  @Override
  public boolean isBillTo() {
    return charToBoolean(get_Value(COLUMNNAME_I_IsBillTo));
  }

  /**
   * Get Record ID/ColumnName
   *
   * @return ID/ColumnName pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), getValue());
  }

  @Override
  public int getTableId() {
    return I_I_BPartner.Table_ID;
  }
}