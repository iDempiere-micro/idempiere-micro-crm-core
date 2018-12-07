package org.compiere.crm;

import static org.compiere.model.I_C_BPartner_Location.COLUMNNAME_IsPreserveCustomName;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_C_Location;
import org.compiere.orm.BasePOName;
import org.compiere.orm.MTable;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_BPartner_Location
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BPartner_Location extends BasePOName implements I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_BPartner_Location(Properties ctx, int C_BPartner_Location_ID, String trxName) {
    super(ctx, C_BPartner_Location_ID, trxName);
    /**
     * if (C_BPartner_Location_ID == 0) { setC_BPartner_ID (0); setC_BPartner_Location_ID (0);
     * setC_Location_ID (0); setIsBillTo (true); // Y setIsPayFrom (true); // Y setIsRemitTo (true);
     * // Y setIsShipTo (true); // Y setName (null); // . }
     */
  }

  /** Load Constructor */
  public X_C_BPartner_Location(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_C_BPartner_Location(Properties ctx, Row row) {
    super(ctx, row);
  } // MBPartner_Location

  /**
   * AccessLevel
   *
   * @return 3 - Client - Org
   */
  protected int getAccessLevel() {
    return I_C_BPartner_Location.accessLevel.intValue();
  }

  public String toString() {
    return "X_C_BPartner_Location[" + getId() + "]";
  }

  public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException {
    return (org.compiere.model.I_C_BPartner)
        MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
            .getPO(getC_BPartner_ID(), get_TrxName());
  }

  /**
   * Get Business Partner .
   *
   * @return Identifies a Business Partner
   */
  public int getC_BPartner_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner_Location.COLUMNNAME_C_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Business Partner .
   *
   * @param C_BPartner_ID Identifies a Business Partner
   */
  public void setC_BPartner_ID(int C_BPartner_ID) {
    if (C_BPartner_ID < 1) set_ValueNoCheck(I_C_BPartner_Location.COLUMNNAME_C_BPartner_ID, null);
    else
      set_ValueNoCheck(
          I_C_BPartner_Location.COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
  }

  /**
   * Get Partner Location.
   *
   * @return Identifies the (ship to) address for this Business Partner
   */
  public int getC_BPartner_Location_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Partner Location.
   *
   * @param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner
   */
  public void setC_BPartner_Location_ID(int C_BPartner_Location_ID) {
    if (C_BPartner_Location_ID < 1)
      set_ValueNoCheck(I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_ID, null);
    else
      set_ValueNoCheck(
          I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_ID, C_BPartner_Location_ID);
  }

  /**
   * Get C_BPartner_Location_UU.
   *
   * @return C_BPartner_Location_UU
   */
  public String getC_BPartner_Location_UU() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_UU);
  }

  /**
   * Set C_BPartner_Location_UU.
   *
   * @param C_BPartner_Location_UU C_BPartner_Location_UU
   */
  public void setC_BPartner_Location_UU(String C_BPartner_Location_UU) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_UU, C_BPartner_Location_UU);
  }

  public I_C_Location getC_Location() throws RuntimeException {
    return (I_C_Location)
        MTable.get(getCtx(), I_C_Location.Table_Name).getPO(getC_Location_ID(), get_TrxName());
  }

  /**
   * Get Address.
   *
   * @return Location or Address
   */
  public int getC_Location_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner_Location.COLUMNNAME_C_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Address.
   *
   * @param C_Location_ID Location or Address
   */
  public void setC_Location_ID(int C_Location_ID) {
    if (C_Location_ID < 1) set_Value(I_C_BPartner_Location.COLUMNNAME_C_Location_ID, null);
    else set_Value(I_C_BPartner_Location.COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
  }

  public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException {
    return (org.compiere.model.I_C_SalesRegion)
        MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
            .getPO(getC_SalesRegion_ID(), get_TrxName());
  }

  /**
   * Get Sales Region.
   *
   * @return Sales coverage region
   */
  public int getC_SalesRegion_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner_Location.COLUMNNAME_C_SalesRegion_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sales Region.
   *
   * @param C_SalesRegion_ID Sales coverage region
   */
  public void setC_SalesRegion_ID(int C_SalesRegion_ID) {
    if (C_SalesRegion_ID < 1) set_Value(I_C_BPartner_Location.COLUMNNAME_C_SalesRegion_ID, null);
    else
      set_Value(
          I_C_BPartner_Location.COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
  }

  /**
   * Get Customer Address ID.
   *
   * @return Customer Address ID
   */
  public String getCustomerAddressID() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_CustomerAddressID);
  }

  /**
   * Set Customer Address ID.
   *
   * @param CustomerAddressID Customer Address ID
   */
  public void setCustomerAddressID(String CustomerAddressID) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_CustomerAddressID, CustomerAddressID);
  }

  /**
   * Get Fax.
   *
   * @return Facsimile number
   */
  public String getFax() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_Fax);
  }

  /**
   * Set Fax.
   *
   * @param Fax Facsimile number
   */
  public void setFax(String Fax) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_Fax, Fax);
  }

  /**
   * Set Invoice Address.
   *
   * @param IsBillTo Business Partner Invoice/Bill Address
   */
  public void setIsBillTo(boolean IsBillTo) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_IsBillTo, Boolean.valueOf(IsBillTo));
  }

  /**
   * Get Invoice Address.
   *
   * @return Business Partner Invoice/Bill Address
   */
  public boolean isBillTo() {
    Object oo = get_Value(I_C_BPartner_Location.COLUMNNAME_IsBillTo);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get ISDN.
   *
   * @return ISDN or modem line
   */
  public String getISDN() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_ISDN);
  }

  /**
   * Set ISDN.
   *
   * @param ISDN ISDN or modem line
   */
  public void setISDN(String ISDN) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_ISDN, ISDN);
  }

  /**
   * Set Pay-From Address.
   *
   * @param IsPayFrom Business Partner pays from that address and we'll send dunning letters there
   */
  public void setIsPayFrom(boolean IsPayFrom) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_IsPayFrom, Boolean.valueOf(IsPayFrom));
  }

  /**
   * Get Pay-From Address.
   *
   * @return Business Partner pays from that address and we'll send dunning letters there
   */
  public boolean isPayFrom() {
    return charToBoolean(get_Value(I_C_BPartner_Location.COLUMNNAME_IsPayFrom));
  }

  /**
   * Set Preserve custom name.
   *
   * @param IsPreserveCustomName Preserve custom name
   */
  public void setIsPreserveCustomName(boolean IsPreserveCustomName) {
    set_Value(COLUMNNAME_IsPreserveCustomName, IsPreserveCustomName);
  }

  /**
   * Get Preserve custom name.
   *
   * @return Preserve custom name
   */
  public boolean isPreserveCustomName() {
    return charToBoolean(get_Value(COLUMNNAME_IsPreserveCustomName));
  }

  /**
   * Set Remit-To Address.
   *
   * @param IsRemitTo Business Partner payment address
   */
  public void setIsRemitTo(boolean IsRemitTo) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_IsRemitTo, Boolean.valueOf(IsRemitTo));
  }

  /**
   * Get Remit-To Address.
   *
   * @return Business Partner payment address
   */
  public boolean isRemitTo() {
    return charToBoolean(get_Value(I_C_BPartner_Location.COLUMNNAME_IsRemitTo));
  }

  /**
   * Set Ship Address.
   *
   * @param IsShipTo Business Partner Shipment Address
   */
  public void setIsShipTo(boolean IsShipTo) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_IsShipTo, Boolean.valueOf(IsShipTo));
  }

  /**
   * Get Ship Address.
   *
   * @return Business Partner Shipment Address
   */
  public boolean isShipTo() {
    return charToBoolean(get_Value(I_C_BPartner_Location.COLUMNNAME_IsShipTo));
  }

  /**
   * Get Phone.
   *
   * @return Identifies a telephone number
   */
  public String getPhone() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_Phone);
  }

  /**
   * Set Phone.
   *
   * @param Phone Identifies a telephone number
   */
  public void setPhone(String Phone) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_Phone, Phone);
  }

  /**
   * Get 2nd Phone.
   *
   * @return Identifies an alternate telephone number.
   */
  public String getPhone2() {
    return (String) get_Value(I_C_BPartner_Location.COLUMNNAME_Phone2);
  }

  /**
   * Set 2nd Phone.
   *
   * @param Phone2 Identifies an alternate telephone number.
   */
  public void setPhone2(String Phone2) {
    set_Value(I_C_BPartner_Location.COLUMNNAME_Phone2, Phone2);
  }

  @Override
  public int getTableId() {
    return I_C_BPartner_Location.Table_ID;
  }
}
