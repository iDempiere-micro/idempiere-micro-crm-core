package org.compiere.crm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_BP_Group;
import org.compiere.orm.BasePOName;
import org.compiere.orm.MTable;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_BP_Group
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BP_Group extends BasePOName implements I_C_BP_Group, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_BP_Group(Properties ctx, int C_BP_Group_ID, String trxName) {
    super(ctx, C_BP_Group_ID, trxName);
    /**
     * if (C_BP_Group_ID == 0) { setC_BP_Group_ID (0); setIsConfidentialInfo (false); // N
     * setIsDefault (false); setName (null); setValue (null); }
     */
  }

  /** Load Constructor */
  public X_C_BP_Group(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public X_C_BP_Group(Properties ctx, Row row) {
    super(ctx, row);
  } //	MBPGroup

  /**
   * AccessLevel
   *
   * @return 3 - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    return "X_C_BP_Group[" + getId() + "]";
  }

  public org.compiere.model.I_AD_PrintColor getAD_PrintColor() throws RuntimeException {
    return (org.compiere.model.I_AD_PrintColor)
        MTable.get(getCtx(), org.compiere.model.I_AD_PrintColor.Table_Name)
            .getPO(getAD_PrintColor_ID(), get_TrxName());
  }

  /**
   * Set Print Color.
   *
   * @param AD_PrintColor_ID Color used for printing and display
   */
  public void setAD_PrintColor_ID(int AD_PrintColor_ID) {
    if (AD_PrintColor_ID < 1) set_Value(COLUMNNAME_AD_PrintColor_ID, null);
    else set_Value(COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
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
   * Set Business Partner Group.
   *
   * @param C_BP_Group_ID Business Partner Group
   */
  public void setC_BP_Group_ID(int C_BP_Group_ID) {
    if (C_BP_Group_ID < 1) set_ValueNoCheck(COLUMNNAME_C_BP_Group_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
  }

  /**
   * Get Business Partner Group.
   *
   * @return Business Partner Group
   */
  public int getC_BP_Group_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BP_Group_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set C_BP_Group_UU.
   *
   * @param C_BP_Group_UU C_BP_Group_UU
   */
  public void setC_BP_Group_UU(String C_BP_Group_UU) {
    set_Value(COLUMNNAME_C_BP_Group_UU, C_BP_Group_UU);
  }

  /**
   * Get C_BP_Group_UU.
   *
   * @return C_BP_Group_UU
   */
  public String getC_BP_Group_UU() {
    return (String) get_Value(COLUMNNAME_C_BP_Group_UU);
  }

  public org.compiere.model.I_C_Dunning getC_Dunning() throws RuntimeException {
    return (org.compiere.model.I_C_Dunning)
        MTable.get(getCtx(), org.compiere.model.I_C_Dunning.Table_Name)
            .getPO(getC_Dunning_ID(), get_TrxName());
  }

  /**
   * Set Dunning.
   *
   * @param C_Dunning_ID Dunning Rules for overdue invoices
   */
  public void setC_Dunning_ID(int C_Dunning_ID) {
    if (C_Dunning_ID < 1) set_Value(COLUMNNAME_C_Dunning_ID, null);
    else set_Value(COLUMNNAME_C_Dunning_ID, Integer.valueOf(C_Dunning_ID));
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
   * Set Credit Watch %.
   *
   * @param CreditWatchPercent Credit Watch - Percent of Credit Limit when OK switches to Watch
   */
  public void setCreditWatchPercent(BigDecimal CreditWatchPercent) {
    set_Value(COLUMNNAME_CreditWatchPercent, CreditWatchPercent);
  }

  /**
   * Get Credit Watch %.
   *
   * @return Credit Watch - Percent of Credit Limit when OK switches to Watch
   */
  public BigDecimal getCreditWatchPercent() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_CreditWatchPercent);
    if (bd == null) return Env.ZERO;
    return bd;
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
   * Set Confidential Info.
   *
   * @param IsConfidentialInfo Can enter confidential information
   */
  public void setIsConfidentialInfo(boolean IsConfidentialInfo) {
    set_Value(COLUMNNAME_IsConfidentialInfo, Boolean.valueOf(IsConfidentialInfo));
  }

  /**
   * Get Confidential Info.
   *
   * @return Can enter confidential information
   */
  public boolean isConfidentialInfo() {
    return charToBoolean(get_Value(COLUMNNAME_IsConfidentialInfo));
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

  public org.compiere.model.I_M_DiscountSchema getM_DiscountSchema() throws RuntimeException {
    return (org.compiere.model.I_M_DiscountSchema)
        MTable.get(getCtx(), org.compiere.model.I_M_DiscountSchema.Table_Name)
            .getPO(getM_DiscountSchema_ID(), get_TrxName());
  }

  /**
   * Set Discount Schema.
   *
   * @param M_DiscountSchema_ID Schema to calculate the trade discount percentage
   */
  public void setM_DiscountSchema_ID(int M_DiscountSchema_ID) {
    if (M_DiscountSchema_ID < 1) set_Value(COLUMNNAME_M_DiscountSchema_ID, null);
    else set_Value(COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
  }

  /**
   * Get Discount Schema.
   *
   * @return Schema to calculate the trade discount percentage
   */
  public int getM_DiscountSchema_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_M_DiscountSchema_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_M_PriceList getM_PriceList() throws RuntimeException {
    return (org.compiere.model.I_M_PriceList)
        MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
            .getPO(getM_PriceList_ID(), get_TrxName());
  }

  /**
   * Set Price List.
   *
   * @param M_PriceList_ID Unique identifier of a Price List
   */
  public void setM_PriceList_ID(int M_PriceList_ID) {
    if (M_PriceList_ID < 1) set_Value(COLUMNNAME_M_PriceList_ID, null);
    else set_Value(COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
  }

  /**
   * Get Price List.
   *
   * @return Unique identifier of a Price List
   */
  public int getM_PriceList_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_M_PriceList_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_M_DiscountSchema getPO_DiscountSchema() throws RuntimeException {
    return (org.compiere.model.I_M_DiscountSchema)
        MTable.get(getCtx(), org.compiere.model.I_M_DiscountSchema.Table_Name)
            .getPO(getPO_DiscountSchema_ID(), get_TrxName());
  }

  /**
   * Set PO Discount Schema.
   *
   * @param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage
   */
  public void setPO_DiscountSchema_ID(int PO_DiscountSchema_ID) {
    if (PO_DiscountSchema_ID < 1) set_Value(COLUMNNAME_PO_DiscountSchema_ID, null);
    else set_Value(COLUMNNAME_PO_DiscountSchema_ID, PO_DiscountSchema_ID);
  }

  /**
   * Get PO Discount Schema.
   *
   * @return Schema to calculate the purchase trade discount percentage
   */
  public int getPO_DiscountSchema_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_PO_DiscountSchema_ID);
    if (ii == null) return 0;
    return ii;
  }

  public org.compiere.model.I_M_PriceList getPO_PriceList() throws RuntimeException {
    return (org.compiere.model.I_M_PriceList)
        MTable.get(getCtx(), org.compiere.model.I_M_PriceList.Table_Name)
            .getPO(getPO_PriceList_ID(), get_TrxName());
  }

  /**
   * Set Purchase Pricelist.
   *
   * @param PO_PriceList_ID Price List used by this Business Partner
   */
  public void setPO_PriceList_ID(int PO_PriceList_ID) {
    if (PO_PriceList_ID < 1) set_Value(COLUMNNAME_PO_PriceList_ID, null);
    else set_Value(COLUMNNAME_PO_PriceList_ID, PO_PriceList_ID);
  }

  /**
   * Get Purchase Pricelist.
   *
   * @return Price List used by this Business Partner
   */
  public int getPO_PriceList_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_PO_PriceList_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Price Match Tolerance.
   *
   * @param PriceMatchTolerance PO-Invoice Match Price Tolerance in percent of the purchase price
   */
  public void setPriceMatchTolerance(BigDecimal PriceMatchTolerance) {
    set_Value(COLUMNNAME_PriceMatchTolerance, PriceMatchTolerance);
  }

  /**
   * Get Price Match Tolerance.
   *
   * @return PO-Invoice Match Price Tolerance in percent of the purchase price
   */
  public BigDecimal getPriceMatchTolerance() {
    BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_PriceMatchTolerance);
    if (bd == null) return Env.ZERO;
    return bd;
  }

  /** PriorityBase AD_Reference_ID=350 */
  public static final int PRIORITYBASE_AD_Reference_ID = 350;
  /** Same = S */
  public static final String PRIORITYBASE_Same = "S";
  /** Lower = L */
  public static final String PRIORITYBASE_Lower = "L";
  /** Higher = H */
  public static final String PRIORITYBASE_Higher = "H";
  /**
   * Set Priority Base.
   *
   * @param PriorityBase Base of Priority
   */
  public void setPriorityBase(String PriorityBase) {

    set_Value(COLUMNNAME_PriorityBase, PriorityBase);
  }

  /**
   * Get Priority Base.
   *
   * @return Base of Priority
   */
  public String getPriorityBase() {
    return (String) get_Value(COLUMNNAME_PriorityBase);
  }

  /**
   * Set Search Key.
   *
   * @param Value Search key for the record in the format required - must be unique
   */
  public void setValue(String Value) {
    set_Value(COLUMNNAME_Value, Value);
  }

  /**
   * Get Search Key.
   *
   * @return Search key for the record in the format required - must be unique
   */
  public String getValue() {
    return (String) get_Value(COLUMNNAME_Value);
  }

  @Override
  public int getTableId() {
    return I_C_BP_Group.Table_ID;
  }
}