package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_BP_Group;
import org.compiere.orm.BasePOName;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;

/**
 * Generated Model for C_BP_Group
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public abstract class X_C_BP_Group extends BasePOName implements I_C_BP_Group {

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
    public X_C_BP_Group(int C_BP_Group_ID) {
        super(C_BP_Group_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_BP_Group(Row row) {
        super(row);
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
    public int getBPGroupId() {
        Integer ii = getValue(COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Dunning.
     *
     * @return Dunning Rules for overdue invoices
     */
    public int getDunningId() {
        Integer ii = getValue(COLUMNNAME_C_Dunning_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Credit Watch %.
     *
     * @return Credit Watch - Percent of Credit Limit when OK switches to Watch
     */
    public BigDecimal getCreditWatchPercent() {
        BigDecimal bd = getValue(COLUMNNAME_CreditWatchPercent);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Confidential Info.
     *
     * @param IsConfidentialInfo Can enter confidential information
     */
    public void setIsConfidentialInfo(boolean IsConfidentialInfo) {
        setValue(COLUMNNAME_IsConfidentialInfo, Boolean.valueOf(IsConfidentialInfo));
    }

    /**
     * Set Default.
     *
     * @param IsDefault Default value
     */
    public void setIsDefault(boolean IsDefault) {
        setValue(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
    }

    /**
     * Get Discount Schema.
     *
     * @return Schema to calculate the trade discount percentage
     */
    public int getDiscountSchemaId() {
        Integer ii = getValue(COLUMNNAME_M_DiscountSchema_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Price List.
     *
     * @return Unique identifier of a Price List
     */
    public int getPriceListId() {
        Integer ii = getValue(COLUMNNAME_M_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get PO Discount Schema.
     *
     * @return Schema to calculate the purchase trade discount percentage
     */
    public int getPODiscountSchemaId() {
        Integer ii = getValue(COLUMNNAME_PO_DiscountSchema_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Purchase Pricelist.
     *
     * @return Price List used by this Business Partner
     */
    public int getPurchaseOrderPriceListId() {
        Integer ii = getValue(COLUMNNAME_PO_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Price Match Tolerance.
     *
     * @return PO-Invoice Match Price Tolerance in percent of the purchase price
     */
    public BigDecimal getPriceMatchTolerance() {
        BigDecimal bd = getValue(COLUMNNAME_PriceMatchTolerance);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Get Priority Base.
     *
     * @return Base of Priority
     */
    public String getPriorityBase() {
        return getValue(COLUMNNAME_PriorityBase);
    }

    /**
     * Set Priority Base.
     *
     * @param PriorityBase Base of Priority
     */
    public void setPriorityBase(String PriorityBase) {

        setValue(COLUMNNAME_PriorityBase, PriorityBase);
    }

    /**
     * Set Search Key.
     *
     * @param Value Search key for the record in the format required - must be unique
     */
    public void setValue(String Value) {
        setValue(COLUMNNAME_Value, Value);
    }

    @Override
    public int getTableId() {
        return I_C_BP_Group.Table_ID;
    }
}
