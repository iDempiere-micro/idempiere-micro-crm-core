package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_PrintColor;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for AD_PrintColor
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PrintColor extends BasePOName implements I_AD_PrintColor, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_PrintColor(Properties ctx, int AD_PrintColor_ID, String trxName) {
    super(ctx, AD_PrintColor_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_PrintColor(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_AD_PrintColor[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Set Print Color.
   *
   * @param AD_PrintColor_ID Color used for printing and display
   */
  public void setAD_PrintColor_ID(int AD_PrintColor_ID) {
    if (AD_PrintColor_ID < 1) set_ValueNoCheck(COLUMNNAME_AD_PrintColor_ID, null);
    else set_ValueNoCheck(COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
  }

  /**
   * Get Print Color.
   *
   * @return Color used for printing and display
   */
  public int getAD_PrintColor_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_AD_PrintColor_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set AD_PrintColor_UU.
   *
   * @param AD_PrintColor_UU AD_PrintColor_UU
   */
  public void setAD_PrintColor_UU(String AD_PrintColor_UU) {
    set_Value(COLUMNNAME_AD_PrintColor_UU, AD_PrintColor_UU);
  }

  /**
   * Get AD_PrintColor_UU.
   *
   * @return AD_PrintColor_UU
   */
  public String getAD_PrintColor_UU() {
    return (String) get_Value(COLUMNNAME_AD_PrintColor_UU);
  }

  /**
   * Set Validation code.
   *
   * @param Code Validation Code
   */
  public void setCode(String Code) {
    set_Value(COLUMNNAME_Code, Code);
  }

  /**
   * Get Validation code.
   *
   * @return Validation Code
   */
  public String getCode() {
    return (String) get_Value(COLUMNNAME_Code);
  }

  /**
   * Set Default.
   *
   * @param IsDefault Default value
   */
  public void setIsDefault(boolean IsDefault) {
    set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
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
    return I_AD_PrintColor.Table_ID;
  }
}
