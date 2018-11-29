package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Region;
import org.compiere.orm.BasePOName;
import org.compiere.orm.MTable;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Region
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Region extends BasePOName implements I_C_Region, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Region(Properties ctx, int C_Region_ID, String trxName) {
    super(ctx, C_Region_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Region(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    return "X_C_Region[" + getId() + "]";
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
    if (C_Country_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Country_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_Country_ID, C_Country_ID);
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

  /**
   * Set Region.
   *
   * @param C_Region_ID Identifies a geographical Region
   */
  public void setC_Region_ID(int C_Region_ID) {
    if (C_Region_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Region_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_Region_ID, C_Region_ID);
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
   * Set C_Region_UU.
   *
   * @param C_Region_UU C_Region_UU
   */
  public void setC_Region_UU(String C_Region_UU) {
    set_Value(COLUMNNAME_C_Region_UU, C_Region_UU);
  }

  /**
   * Get C_Region_UU.
   *
   * @return C_Region_UU
   */
  public String getC_Region_UU() {
    return (String) get_Value(COLUMNNAME_C_Region_UU);
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
   * Set Default.
   *
   * @param IsDefault Default value
   */
  public void setIsDefault(boolean IsDefault) {
    set_Value(COLUMNNAME_IsDefault, IsDefault);
  }

  /**
   * Get Default.
   *
   * @return Default value
   */
  public boolean isDefault() {
    return charToBoolean(get_Value(COLUMNNAME_IsDefault));
  }

  @Override
  public int getTableId() {
    return I_C_Region.Table_ID;
  }
}
