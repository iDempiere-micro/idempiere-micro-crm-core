package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_JobCategory;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_JobCategory
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_JobCategory extends BasePOName implements I_C_JobCategory, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_JobCategory(Properties ctx, int C_JobCategory_ID, String trxName) {
    super(ctx, C_JobCategory_ID, trxName);
  }

  /** Load Constructor */
  public X_C_JobCategory(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_C_JobCategory[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Position Category.
   *
   * @return Job Position Category
   */
  public int getC_JobCategory_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_JobCategory_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Position Category.
   *
   * @param C_JobCategory_ID Job Position Category
   */
  public void setC_JobCategory_ID(int C_JobCategory_ID) {
    if (C_JobCategory_ID < 1) set_ValueNoCheck(COLUMNNAME_C_JobCategory_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_JobCategory_ID, Integer.valueOf(C_JobCategory_ID));
  }

  /**
   * Get C_JobCategory_UU.
   *
   * @return C_JobCategory_UU
   */
  public String getC_JobCategory_UU() {
    return (String) get_Value(COLUMNNAME_C_JobCategory_UU);
  }

  /**
   * Set C_JobCategory_UU.
   *
   * @param C_JobCategory_UU C_JobCategory_UU
   */
  public void setC_JobCategory_UU(String C_JobCategory_UU) {
    set_Value(COLUMNNAME_C_JobCategory_UU, C_JobCategory_UU);
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
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(COLUMNNAME_Description, Description);
  }

  /**
   * Get Comment/Help.
   *
   * @return Comment or Hint
   */
  public String getHelp() {
    return (String) get_Value(COLUMNNAME_Help);
  }

  /**
   * Set Comment/Help.
   *
   * @param Help Comment or Hint
   */
  public void setHelp(String Help) {
    set_Value(COLUMNNAME_Help, Help);
  }

  @Override
  public int getTableId() {
    return I_C_JobCategory.Table_ID;
  }
}
