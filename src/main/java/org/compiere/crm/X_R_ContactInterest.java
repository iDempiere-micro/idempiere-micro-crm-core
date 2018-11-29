package org.compiere.crm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.I_R_ContactInterest;
import org.compiere.orm.BasePOUser;
import org.compiere.orm.MTable;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

/**
 * Generated Model for R_ContactInterest
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_R_ContactInterest extends BasePOUser implements I_R_ContactInterest, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_R_ContactInterest(Properties ctx, int R_ContactInterest_ID, String trxName) {
    super(ctx, R_ContactInterest_ID, trxName);
    /**
     * if (R_ContactInterest_ID == 0) { setAD_User_ID (0); // @AD_User_ID@ setR_InterestArea_ID (0);
     * }
     */
  }

  /** Load Constructor */
  public X_R_ContactInterest(Properties ctx, ResultSet rs, String trxName) {
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

  public String toString() {
    StringBuffer sb = new StringBuffer("X_R_ContactInterest[").append(getId()).append("]");
    return sb.toString();
  }

  /**
   * Get Record ID/ColumnName
   *
   * @return ID/ColumnName pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), String.valueOf(getAD_User_ID()));
  }

  /**
   * Set Opt-out Date.
   *
   * @param OptOutDate Date the contact opted out
   */
  public void setOptOutDate(Timestamp OptOutDate) {
    set_Value(COLUMNNAME_OptOutDate, OptOutDate);
  }

  /**
   * Get Opt-out Date.
   *
   * @return Date the contact opted out
   */
  public Timestamp getOptOutDate() {
    return (Timestamp) get_Value(COLUMNNAME_OptOutDate);
  }

  /**
   * Set R_ContactInterest_UU.
   *
   * @param R_ContactInterest_UU R_ContactInterest_UU
   */
  public void setR_ContactInterest_UU(String R_ContactInterest_UU) {
    set_Value(COLUMNNAME_R_ContactInterest_UU, R_ContactInterest_UU);
  }

  /**
   * Get R_ContactInterest_UU.
   *
   * @return R_ContactInterest_UU
   */
  public String getR_ContactInterest_UU() {
    return (String) get_Value(COLUMNNAME_R_ContactInterest_UU);
  }

  public org.compiere.model.I_R_InterestArea getR_InterestArea() throws RuntimeException {
    return (org.compiere.model.I_R_InterestArea)
        MTable.get(getCtx(), org.compiere.model.I_R_InterestArea.Table_Name)
            .getPO(getR_InterestArea_ID(), get_TrxName());
  }

  /**
   * Set Interest Area.
   *
   * @param R_InterestArea_ID Interest Area or Topic
   */
  public void setR_InterestArea_ID(int R_InterestArea_ID) {
    if (R_InterestArea_ID < 1) set_ValueNoCheck(COLUMNNAME_R_InterestArea_ID, null);
    else set_ValueNoCheck(COLUMNNAME_R_InterestArea_ID, R_InterestArea_ID);
  }

  /**
   * Get Interest Area.
   *
   * @return Interest Area or Topic
   */
  public int getR_InterestArea_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_R_InterestArea_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Subscribe Date.
   *
   * @param SubscribeDate Date the contact actively subscribed
   */
  public void setSubscribeDate(Timestamp SubscribeDate) {
    set_Value(COLUMNNAME_SubscribeDate, SubscribeDate);
  }

  /**
   * Get Subscribe Date.
   *
   * @return Date the contact actively subscribed
   */
  public Timestamp getSubscribeDate() {
    return (Timestamp) get_Value(COLUMNNAME_SubscribeDate);
  }

  @Override
  public int getTableId() {
    return I_R_ContactInterest.Table_ID;
  }
}
