package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_BP_Group;
import org.compiere.orm.BasePOName;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_BP_Group
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BP_Group extends BasePOName implements I_C_BP_Group {

    /**
     * Same = S
     */
    public static final String PRIORITYBASE_Same = "S";
    /**
     * Lower = L
     */
    public static final String PRIORITYBASE_Lower = "L";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_BP_Group(Properties ctx, int C_BP_Group_ID) {
        super(ctx, C_BP_Group_ID);
        /**
         * if (C_BP_Group_ID == 0) { setC_BP_Group_ID (0); setIsConfidentialInfo (false); // N
         * setIsDefault (false); setName (null); setValue (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_BP_Group(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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

    /**
     * Get Business Partner Group.
     *
     * @return Business Partner Group
     */
    public int getC_BP_Group_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Dunning.
     *
     * @return Dunning Rules for overdue invoices
     */
    public int getC_Dunning_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_Dunning_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Credit Watch %.
     *
     * @return Credit Watch - Percent of Credit Limit when OK switches to Watch
     */
    public BigDecimal getCreditWatchPercent() {
        BigDecimal bd = (BigDecimal) getValue(COLUMNNAME_CreditWatchPercent);
        if (bd == null) return Env.ZERO;
        return bd;
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
     * Set Default.
     *
     * @param IsDefault Default value
     */
    public void setIsDefault(boolean IsDefault) {
        set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
    }

    /**
     * Get Discount Schema.
     *
     * @return Schema to calculate the trade discount percentage
     */
    public int getM_DiscountSchema_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_M_DiscountSchema_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Price List.
     *
     * @return Unique identifier of a Price List
     */
    public int getM_PriceList_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_M_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get PO Discount Schema.
     *
     * @return Schema to calculate the purchase trade discount percentage
     */
    public int getPO_DiscountSchema_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_PO_DiscountSchema_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Purchase Pricelist.
     *
     * @return Price List used by this Business Partner
     */
    public int getPO_PriceList_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_PO_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Price Match Tolerance.
     *
     * @return PO-Invoice Match Price Tolerance in percent of the purchase price
     */
    public BigDecimal getPriceMatchTolerance() {
        BigDecimal bd = (BigDecimal) getValue(COLUMNNAME_PriceMatchTolerance);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Get Priority Base.
     *
     * @return Base of Priority
     */
    public String getPriorityBase() {
        return (String) getValue(COLUMNNAME_PriorityBase);
    }

    /**
     * Set Priority Base.
     *
     * @param PriorityBase Base of Priority
     */
    public void setPriorityBase(String PriorityBase) {

        set_Value(COLUMNNAME_PriorityBase, PriorityBase);
    }

    /**
     * Set Search Key.
     *
     * @param Value Search key for the record in the format required - must be unique
     */
    public void setValue(String Value) {
        set_Value(COLUMNNAME_Value, Value);
    }

    @Override
    public int getTableId() {
        return I_C_BP_Group.Table_ID;
    }
}
