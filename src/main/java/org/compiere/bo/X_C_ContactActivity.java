package org.compiere.bo;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_C_ContactActivity;
import org.compiere.orm.BasePOUser;
import org.compiere.orm.MTable;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_ContactActivity
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_ContactActivity extends BasePOUser implements I_C_ContactActivity, I_Persistent {

  /** ContactActivityType AD_Reference_ID=53423 */
  public static final int CONTACTACTIVITYTYPE_AD_Reference_ID = 53423;
  /** Email = EM */
  public static final String CONTACTACTIVITYTYPE_Email = "EM";
  /** Phone call = PC */
  public static final String CONTACTACTIVITYTYPE_PhoneCall = "PC";
  /** Meeting = ME */
  public static final String CONTACTACTIVITYTYPE_Meeting = "ME";
  /** Task = TA */
  public static final String CONTACTACTIVITYTYPE_Task = "TA";
  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_ContactActivity(Properties ctx, int C_ContactActivity_ID, String trxName) {
    super(ctx, C_ContactActivity_ID, trxName);
    /**
     * if (C_ContactActivity_ID == 0) { setC_ContactActivity_ID (0); setContactActivityType (null);
     * setDescription (null); setStartDate (new Timestamp( System.currentTimeMillis() ));
     * // @SQL=SELECT SYSDATE AS DefaultValue FROM DUAL }
     */
  }

  /** Load Constructor */
  public X_C_ContactActivity(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /** Load Constructor with rename */
  public X_C_ContactActivity(
      Properties ctx, ResultSet rs, String trxName, String columnNamePrefix) {
    super(ctx, rs, trxName, columnNamePrefix);
  }

  /**
   * AccessLevel
   *
   * @return 3 - Client - Org
   */
  protected int getAccessLevel() {
    return I_C_ContactActivity.accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_C_ContactActivity[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Contact Activity.
   *
   * @return Events, tasks, communications related to a contact
   */
  public int getC_ContactActivity_ID() {
    Integer ii = (Integer) get_Value(I_C_ContactActivity.COLUMNNAME_C_ContactActivity_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Contact Activity.
   *
   * @param C_ContactActivity_ID Events, tasks, communications related to a contact
   */
  public void setC_ContactActivity_ID(int C_ContactActivity_ID) {
    if (C_ContactActivity_ID < 1)
      set_ValueNoCheck(I_C_ContactActivity.COLUMNNAME_C_ContactActivity_ID, null);
    else
      set_ValueNoCheck(
          I_C_ContactActivity.COLUMNNAME_C_ContactActivity_ID,
          Integer.valueOf(C_ContactActivity_ID));
  }

  /**
   * Get C_ContactActivity_UU.
   *
   * @return C_ContactActivity_UU
   */
  public String getC_ContactActivity_UU() {
    return (String) get_Value(I_C_ContactActivity.COLUMNNAME_C_ContactActivity_UU);
  }

  /**
   * Set C_ContactActivity_UU.
   *
   * @param C_ContactActivity_UU C_ContactActivity_UU
   */
  public void setC_ContactActivity_UU(String C_ContactActivity_UU) {
    set_Value(I_C_ContactActivity.COLUMNNAME_C_ContactActivity_UU, C_ContactActivity_UU);
  }

  /**
   * Get Comments.
   *
   * @return Comments or additional information
   */
  public String getComments() {
    return (String) get_Value(I_C_ContactActivity.COLUMNNAME_Comments);
  }

  /**
   * Set Comments.
   *
   * @param Comments Comments or additional information
   */
  public void setComments(String Comments) {
    set_Value(I_C_ContactActivity.COLUMNNAME_Comments, Comments);
  }

  /**
   * Get Activity Type.
   *
   * @return Type of activity, e.g. task, email, phone call
   */
  public String getContactActivityType() {
    return (String) get_Value(I_C_ContactActivity.COLUMNNAME_ContactActivityType);
  }

  /**
   * Set Activity Type.
   *
   * @param ContactActivityType Type of activity, e.g. task, email, phone call
   */
  public void setContactActivityType(String ContactActivityType) {

    set_ValueNoCheck(I_C_ContactActivity.COLUMNNAME_ContactActivityType, ContactActivityType);
  }

  public org.compiere.model.I_C_Opportunity getC_Opportunity() throws RuntimeException {
    return (org.compiere.model.I_C_Opportunity)
        MTable.get(getCtx(), org.compiere.model.I_C_Opportunity.Table_Name)
            .getPO(getC_Opportunity_ID(), null);
  }

  /**
   * Get Sales Opportunity.
   *
   * @return Sales Opportunity
   */
  public int getC_Opportunity_ID() {
    Integer ii = (Integer) get_Value(I_C_ContactActivity.COLUMNNAME_C_Opportunity_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sales Opportunity.
   *
   * @param C_Opportunity_ID Sales Opportunity
   */
  public void setC_Opportunity_ID(int C_Opportunity_ID) {
    if (C_Opportunity_ID < 1) set_Value(I_C_ContactActivity.COLUMNNAME_C_Opportunity_ID, null);
    else
      set_Value(I_C_ContactActivity.COLUMNNAME_C_Opportunity_ID, Integer.valueOf(C_Opportunity_ID));
  }

  /**
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(I_C_ContactActivity.COLUMNNAME_Description);
  }

  /**
   * Set Description.
   *
   * @param Description Optional short description of the record
   */
  public void setDescription(String Description) {
    set_Value(I_C_ContactActivity.COLUMNNAME_Description, Description);
  }

  /**
   * Get Record ID/ColumnName
   *
   * @return ID/ColumnName pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), getDescription());
  }

  /**
   * Get End Date.
   *
   * @return Last effective date (inclusive)
   */
  public Timestamp getEndDate() {
    return (Timestamp) get_Value(I_C_ContactActivity.COLUMNNAME_EndDate);
  }

  /**
   * Set End Date.
   *
   * @param EndDate Last effective date (inclusive)
   */
  public void setEndDate(Timestamp EndDate) {
    set_Value(I_C_ContactActivity.COLUMNNAME_EndDate, EndDate);
  }

  /**
   * Set Complete.
   *
   * @param IsComplete It is complete
   */
  public void setIsComplete(boolean IsComplete) {
    set_Value(I_C_ContactActivity.COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
  }

  /**
   * Get Complete.
   *
   * @return It is complete
   */
  public boolean isComplete() {
    Object oo = get_Value(I_C_ContactActivity.COLUMNNAME_IsComplete);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException {
    return (org.compiere.model.I_AD_User)
        MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
            .getPO(getSalesRep_ID(), null);
  }

  /**
   * Get Sales Representative.
   *
   * @return Sales Representative or Company Agent
   */
  public int getSalesRep_ID() {
    Integer ii = (Integer) get_Value(I_C_ContactActivity.COLUMNNAME_SalesRep_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sales Representative.
   *
   * @param SalesRep_ID Sales Representative or Company Agent
   */
  public void setSalesRep_ID(int SalesRep_ID) {
    if (SalesRep_ID < 1) set_Value(I_C_ContactActivity.COLUMNNAME_SalesRep_ID, null);
    else set_Value(I_C_ContactActivity.COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
  }

  /**
   * Get Start Date.
   *
   * @return First effective day (inclusive)
   */
  public Timestamp getStartDate() {
    return (Timestamp) get_Value(I_C_ContactActivity.COLUMNNAME_StartDate);
  }

  /**
   * Set Start Date.
   *
   * @param StartDate First effective day (inclusive)
   */
  public void setStartDate(Timestamp StartDate) {
    set_Value(I_C_ContactActivity.COLUMNNAME_StartDate, StartDate);
  }

  @Override
  public int getTableId() {
    return I_C_ContactActivity.Table_ID;
  }
}
