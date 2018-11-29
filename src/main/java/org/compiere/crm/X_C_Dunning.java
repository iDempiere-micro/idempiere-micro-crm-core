package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Dunning;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for C_Dunning
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Dunning extends BasePOName implements I_C_Dunning, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Dunning(Properties ctx, int C_Dunning_ID, String trxName) {
    super(ctx, C_Dunning_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Dunning(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 3 - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  /** Load Meta Data */
  protected POInfo initPO(Properties ctx) {
    POInfo poi = POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
    return poi;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_C_Dunning[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Set Dunning.
   *
   * @param C_Dunning_ID Dunning Rules for overdue invoices
   */
  public void setC_Dunning_ID(int C_Dunning_ID) {
    if (C_Dunning_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Dunning_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_Dunning_ID, Integer.valueOf(C_Dunning_ID));
  }

  /**
   * Get Dunning.
   *
   * @return Dunning Rules for overdue invoices
   */
  public int getC_Dunning_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Dunning_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set C_Dunning_UU.
   *
   * @param C_Dunning_UU C_Dunning_UU
   */
  public void setC_Dunning_UU(String C_Dunning_UU) {
    set_Value(COLUMNNAME_C_Dunning_UU, C_Dunning_UU);
  }

  /**
   * Get C_Dunning_UU.
   *
   * @return C_Dunning_UU
   */
  public String getC_Dunning_UU() {
    return (String) get_Value(COLUMNNAME_C_Dunning_UU);
  }

  /**
   * Set Create levels sequentially.
   *
   * @param CreateLevelsSequentially Create Dunning Letter by level sequentially
   */
  public void setCreateLevelsSequentially(boolean CreateLevelsSequentially) {
    set_Value(COLUMNNAME_CreateLevelsSequentially, Boolean.valueOf(CreateLevelsSequentially));
  }

  /**
   * Get Create levels sequentially.
   *
   * @return Create Dunning Letter by level sequentially
   */
  public boolean isCreateLevelsSequentially() {
    Object oo = get_Value(COLUMNNAME_CreateLevelsSequentially);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
    set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
  }

  /**
   * Get Default.
   *
   * @return Default value
   */
  public boolean isDefault() {
    Object oo = get_Value(COLUMNNAME_IsDefault);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Send dunning letters.
   *
   * @param SendDunningLetter Indicates if dunning letters will be sent
   */
  public void setSendDunningLetter(boolean SendDunningLetter) {
    set_Value(COLUMNNAME_SendDunningLetter, Boolean.valueOf(SendDunningLetter));
  }

  /**
   * Get Send dunning letters.
   *
   * @return Indicates if dunning letters will be sent
   */
  public boolean isSendDunningLetter() {
    Object oo = get_Value(COLUMNNAME_SendDunningLetter);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }
}
