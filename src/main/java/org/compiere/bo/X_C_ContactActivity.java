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
  public X_C_ContactActivity(Properties ctx, int C_ContactActivity_ID) {
    super(ctx, C_ContactActivity_ID);
    /**
     * if (C_ContactActivity_ID == 0) { setC_ContactActivity_ID (0); setContactActivityType (null);
     * setDescription (null); setStartDate (new Timestamp( System.currentTimeMillis() ));
     * // @SQL=SELECT SYSDATE AS DefaultValue FROM DUAL }
     */
  }

  /** Load Constructor */
  public X_C_ContactActivity(Properties ctx, ResultSet rs) {
    super(ctx, rs);
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

    public org.compiere.model.I_C_Opportunity getC_Opportunity() throws RuntimeException {
    return (org.compiere.model.I_C_Opportunity)
        MTable.get(getCtx(), org.compiere.model.I_C_Opportunity.Table_Name)
            .getPO(getC_Opportunity_ID());
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
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(I_C_ContactActivity.COLUMNNAME_Description);
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
   * Get Complete.
   *
   * @return It is complete
   */
  public boolean isComplete() {
    Object oo = get_Value(I_C_ContactActivity.COLUMNNAME_IsComplete);
    if (oo != null) {
      if (oo instanceof Boolean) return (Boolean) oo;
      return "Y".equals(oo);
    }
    return false;
  }

  public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException {
    return (org.compiere.model.I_AD_User)
        MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name).getPO(getSalesRep_ID());
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
   * Get Start Date.
   *
   * @return First effective day (inclusive)
   */
  public Timestamp getStartDate() {
    return (Timestamp) get_Value(I_C_ContactActivity.COLUMNNAME_StartDate);
  }

    @Override
  public int getTableId() {
    return I_C_ContactActivity.Table_ID;
  }
}
