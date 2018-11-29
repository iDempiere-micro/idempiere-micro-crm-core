package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Job;
import org.compiere.orm.BasePOName;
import org.compiere.orm.MTable;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Job
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Job extends BasePOName implements I_C_Job, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Job(Properties ctx, int C_Job_ID, String trxName) {
    super(ctx, C_Job_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Job(Properties ctx, ResultSet rs, String trxName) {
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
    return "X_C_Job[" + getId() + "]";
  }

  public org.compiere.model.I_C_JobCategory getC_JobCategory() throws RuntimeException {
    return (org.compiere.model.I_C_JobCategory)
        MTable.get(getCtx(), org.compiere.model.I_C_JobCategory.Table_Name)
            .getPO(getC_JobCategory_ID(), get_TrxName());
  }

  /**
   * Set Position Category.
   *
   * @param C_JobCategory_ID Job Position Category
   */
  public void setC_JobCategory_ID(int C_JobCategory_ID) {
    if (C_JobCategory_ID < 1) set_Value(COLUMNNAME_C_JobCategory_ID, null);
    else set_Value(COLUMNNAME_C_JobCategory_ID, Integer.valueOf(C_JobCategory_ID));
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
   * Set Position.
   *
   * @param C_Job_ID Job Position
   */
  public void setC_Job_ID(int C_Job_ID) {
    if (C_Job_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Job_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_Job_ID, Integer.valueOf(C_Job_ID));
  }

  /**
   * Get Position.
   *
   * @return Job Position
   */
  public int getC_Job_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Job_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set C_Job_UU.
   *
   * @param C_Job_UU C_Job_UU
   */
  public void setC_Job_UU(String C_Job_UU) {
    set_Value(COLUMNNAME_C_Job_UU, C_Job_UU);
  }

  /**
   * Get C_Job_UU.
   *
   * @return C_Job_UU
   */
  public String getC_Job_UU() {
    return (String) get_Value(COLUMNNAME_C_Job_UU);
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
   * Set Comment/Help.
   *
   * @param Help Comment or Hint
   */
  public void setHelp(String Help) {
    set_Value(COLUMNNAME_Help, Help);
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
   * Set Employee.
   *
   * @param IsEmployee Indicates if this Business Partner is an employee
   */
  public void setIsEmployee(boolean IsEmployee) {
    set_Value(COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
  }

  /**
   * Get Employee.
   *
   * @return Indicates if this Business Partner is an employee
   */
  public boolean isEmployee() {
    Object oo = get_Value(COLUMNNAME_IsEmployee);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  @Override
  public int getTableId() {
    return I_C_Job.Table_ID;
  }
}
