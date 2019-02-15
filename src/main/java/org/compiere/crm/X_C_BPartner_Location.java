package org.compiere.crm;

import static org.compiere.model.I_C_BPartner_Location.COLUMNNAME_IsPreserveCustomName;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.orm.BasePOName;
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
  public X_C_BPartner_Location(Properties ctx, int C_BPartner_Location_ID) {
    super(ctx, C_BPartner_Location_ID);
    /**
     * if (C_BPartner_Location_ID == 0) { setC_BPartner_ID (0); setC_BPartner_Location_ID (0);
     * setC_Location_ID (0); setIsBillTo (true); // Y setIsPayFrom (true); // Y setIsRemitTo (true);
     * // Y setIsShipTo (true); // Y setName (null); // . }
     */
  }

  /** Load Constructor */
  public X_C_BPartner_Location(Properties ctx, ResultSet rs) {
    super(ctx, rs);
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

    /**
   * Get Sales Region.
   *
   * @return Sales coverage region
   */
    private int getC_SalesRegion_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner_Location.COLUMNNAME_C_SalesRegion_ID);
    if (ii == null) return 0;
    return ii;
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

    @Override
  public int getTableId() {
    return I_C_BPartner_Location.Table_ID;
  }
}
