package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_Location;
import org.compiere.orm.MTable;
import org.compiere.orm.PO;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Location
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Location extends PO implements I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Location(Properties ctx, int C_Location_ID, String trxName) {
    super(ctx, C_Location_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Location(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_C_Location(Properties ctx, Row row) {
    super(ctx, row);
  }

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return I_C_Location.accessLevel.intValue();
  }

  public String toString() {
    return "X_C_Location[" + getId() + "]";
  }

  /**
   * Get Address 1.
   *
   * @return Address line 1 for this location
   */
  public String getAddress1() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Address1);
  }

  /**
   * Set Address 1.
   *
   * @param Address1 Address line 1 for this location
   */
  public void setAddress1(String Address1) {
    set_Value(I_C_Location.COLUMNNAME_Address1, Address1);
  }

  /**
   * Get Address 2.
   *
   * @return Address line 2 for this location
   */
  public String getAddress2() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Address2);
  }

  /**
   * Set Address 2.
   *
   * @param Address2 Address line 2 for this location
   */
  public void setAddress2(String Address2) {
    set_Value(I_C_Location.COLUMNNAME_Address2, Address2);
  }

  /**
   * Get Address 3.
   *
   * @return Address Line 3 for the location
   */
  public String getAddress3() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Address3);
  }

  /**
   * Set Address 3.
   *
   * @param Address3 Address Line 3 for the location
   */
  public void setAddress3(String Address3) {
    set_Value(I_C_Location.COLUMNNAME_Address3, Address3);
  }

  /**
   * Get Address 4.
   *
   * @return Address Line 4 for the location
   */
  public String getAddress4() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Address4);
  }

  /**
   * Set Address 4.
   *
   * @param Address4 Address Line 4 for the location
   */
  public void setAddress4(String Address4) {
    set_Value(I_C_Location.COLUMNNAME_Address4, Address4);
  }

  /**
   * Get Address 5.
   *
   * @return Address Line 5 for the location
   */
  public String getAddress5() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Address5);
  }

  /**
   * Set Address 5.
   *
   * @param Address5 Address Line 5 for the location
   */
  public void setAddress5(String Address5) {
    set_Value(I_C_Location.COLUMNNAME_Address5, Address5);
  }

  public org.compiere.model.I_C_AddressValidation getC_AddressValidation() throws RuntimeException {
    return (org.compiere.model.I_C_AddressValidation)
        MTable.get(getCtx(), org.compiere.model.I_C_AddressValidation.Table_Name)
            .getPO(getC_AddressValidation_ID(), null);
  }

  /**
   * Get Address Validation.
   *
   * @return Address Validation
   */
  public int getC_AddressValidation_ID() {
    Integer ii = (Integer) get_Value(I_C_Location.COLUMNNAME_C_AddressValidation_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Address Validation.
   *
   * @param C_AddressValidation_ID Address Validation
   */
  public void setC_AddressValidation_ID(int C_AddressValidation_ID) {
    if (C_AddressValidation_ID < 1)
      set_ValueNoCheck(I_C_Location.COLUMNNAME_C_AddressValidation_ID, null);
    else
      set_ValueNoCheck(
          I_C_Location.COLUMNNAME_C_AddressValidation_ID, Integer.valueOf(C_AddressValidation_ID));
  }

  public org.compiere.model.I_C_City getC_City() throws RuntimeException {
    return (org.compiere.model.I_C_City)
        MTable.get(getCtx(), org.compiere.model.I_C_City.Table_Name).getPO(getC_City_ID(), null);
  }

  /**
   * Get City.
   *
   * @return City
   */
  public int getC_City_ID() {
    Integer ii = (Integer) get_Value(I_C_Location.COLUMNNAME_C_City_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set City.
   *
   * @param C_City_ID City
   */
  public void setC_City_ID(int C_City_ID) {
    if (C_City_ID < 1) set_Value(I_C_Location.COLUMNNAME_C_City_ID, null);
    else set_Value(I_C_Location.COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
  }

  public org.compiere.model.I_C_Country getC_Country() throws RuntimeException {
    return (org.compiere.model.I_C_Country)
        MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
            .getPO(getC_Country_ID(), null);
  }

  /**
   * Get Country.
   *
   * @return Country
   */
  public int getC_Country_ID() {
    Integer ii = (Integer) get_Value(I_C_Location.COLUMNNAME_C_Country_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Country.
   *
   * @param C_Country_ID Country
   */
  public void setC_Country_ID(int C_Country_ID) {
    if (C_Country_ID < 1) set_Value(I_C_Location.COLUMNNAME_C_Country_ID, null);
    else set_Value(I_C_Location.COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
  }

  /**
   * Get City.
   *
   * @return Identifies a City
   */
  public String getCity() {
    return (String) get_Value(I_C_Location.COLUMNNAME_City);
  }

  /**
   * Set City.
   *
   * @param City Identifies a City
   */
  public void setCity(String City) {
    set_Value(I_C_Location.COLUMNNAME_City, City);
  }

  /**
   * Get Record ID/I_C_Location.COLUMNNAME
   *
   * @return ID/I_C_Location.COLUMNNAME pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), getCity());
  }

  /**
   * Get Address.
   *
   * @return Location or Address
   */
  public int getC_Location_ID() {
    Integer ii = (Integer) get_Value(I_C_Location.COLUMNNAME_C_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Address.
   *
   * @param C_Location_ID Location or Address
   */
  public void setC_Location_ID(int C_Location_ID) {
    if (C_Location_ID < 1) set_ValueNoCheck(I_C_Location.COLUMNNAME_C_Location_ID, null);
    else set_ValueNoCheck(I_C_Location.COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
  }

  /**
   * Get C_Location_UU.
   *
   * @return C_Location_UU
   */
  public String getC_Location_UU() {
    return (String) get_Value(I_C_Location.COLUMNNAME_C_Location_UU);
  }

  /**
   * Set C_Location_UU.
   *
   * @param C_Location_UU C_Location_UU
   */
  public void setC_Location_UU(String C_Location_UU) {
    set_Value(I_C_Location.COLUMNNAME_C_Location_UU, C_Location_UU);
  }

  /**
   * Get Comments.
   *
   * @return Comments or additional information
   */
  public String getComments() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Comments);
  }

  /**
   * Set Comments.
   *
   * @param Comments Comments or additional information
   */
  public void setComments(String Comments) {
    set_Value(I_C_Location.COLUMNNAME_Comments, Comments);
  }

  public org.compiere.model.I_C_Region getC_Region() throws RuntimeException {
    return (org.compiere.model.I_C_Region)
        MTable.get(getCtx(), org.compiere.model.I_C_Region.Table_Name)
            .getPO(getC_Region_ID(), null);
  }

  /**
   * Get Region.
   *
   * @return Identifies a geographical Region
   */
  public int getC_Region_ID() {
    Integer ii = (Integer) get_Value(I_C_Location.COLUMNNAME_C_Region_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Region.
   *
   * @param C_Region_ID Identifies a geographical Region
   */
  public void setC_Region_ID(int C_Region_ID) {
    if (C_Region_ID < 1) set_Value(I_C_Location.COLUMNNAME_C_Region_ID, null);
    else set_Value(I_C_Location.COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
  }

  /**
   * Set Valid.
   *
   * @param IsValid Element is valid
   */
  public void setIsValid(boolean IsValid) {
    set_ValueNoCheck(I_C_Location.COLUMNNAME_IsValid, Boolean.valueOf(IsValid));
  }

  /**
   * Get Valid.
   *
   * @return Element is valid
   */
  public boolean isValid() {
    return charToBoolean(get_Value(I_C_Location.COLUMNNAME_IsValid));
  }

  /**
   * Get ZIP.
   *
   * @return Postal code
   */
  public String getPostal() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Postal);
  }

  /**
   * Set ZIP.
   *
   * @param Postal Postal code
   */
  public void setPostal(String Postal) {
    set_Value(I_C_Location.COLUMNNAME_Postal, Postal);
  }

  /**
   * Get Additional Zip.
   *
   * @return Additional ZIP or Postal code
   */
  public String getPostal_Add() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Postal_Add);
  }

  /**
   * Set Additional Zip.
   *
   * @param Postal_Add Additional ZIP or Postal code
   */
  public void setPostal_Add(String Postal_Add) {
    set_Value(I_C_Location.COLUMNNAME_Postal_Add, Postal_Add);
  }

  /**
   * Get Region.
   *
   * @return Name of the Region
   */
  public String getRegionName() {
    return (String) get_Value(I_C_Location.COLUMNNAME_RegionName);
  }

  /**
   * Set Region.
   *
   * @param RegionName Name of the Region
   */
  public void setRegionName(String RegionName) {
    set_Value(I_C_Location.COLUMNNAME_RegionName, RegionName);
  }

  /**
   * Get Result.
   *
   * @return Result of the action taken
   */
  public String getResult() {
    return (String) get_Value(I_C_Location.COLUMNNAME_Result);
  }

  /**
   * Set Result.
   *
   * @param Result Result of the action taken
   */
  public void setResult(String Result) {
    set_ValueNoCheck(I_C_Location.COLUMNNAME_Result, Result);
  }

  /**
   * Get Validate Address.
   *
   * @return Validate Address
   */
  public String getValidateAddress() {
    return (String) get_Value(I_C_Location.COLUMNNAME_ValidateAddress);
  }

  /**
   * Set Validate Address.
   *
   * @param ValidateAddress Validate Address
   */
  public void setValidateAddress(String ValidateAddress) {
    set_Value(I_C_Location.COLUMNNAME_ValidateAddress, ValidateAddress);
  }

  @Override
  public int getTableId() {
    return I_C_Location.Table_ID;
  }
}
