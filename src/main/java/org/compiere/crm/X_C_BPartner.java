package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.HasName2;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.BasePONameValue;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Generated Model for C_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BPartner extends BasePONameValue {

    /**
     * Credit Stop = S
     */
    public static final String SOCREDITSTATUS_CreditStop = "S";
    /**
     * Credit Hold = H
     */
    public static final String SOCREDITSTATUS_CreditHold = "H";
    /**
     * Credit Watch = W
     */
    public static final String SOCREDITSTATUS_CreditWatch = "W";
    /**
     * No Credit Check = X
     */
    public static final String SOCREDITSTATUS_NoCreditCheck = "X";
    /**
     * Credit OK = O
     */
    public static final String SOCREDITSTATUS_CreditOK = "O";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_BPartner(Properties ctx, int C_BPartner_ID) {
        super(ctx, C_BPartner_ID);
        /**
         * if (C_BPartner_ID == 0) { setC_BPartner_ID (0); setC_BP_Group_ID (0); setIs1099Vendor
         * (false); // N setIsCustomer (false); setIsEmployee (false); setIsOneTime (false);
         * setIsPOTaxExempt (false); // N setIsProspect (false); // N setIsSalesRep (false);
         * setIsSummary (false); setIsVendor (false); setName (null); setSendEMail (false);
         * setSO_CreditLimit (Env.ZERO); setSO_CreditUsed (Env.ZERO); setValue (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_BPartner(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_C_BPartner(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 3 - Client - Org
     */
    protected int getAccessLevel() {
        return I_C_BPartner.accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_C_BPartner[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Set Acquisition Cost.
     *
     * @param AcqusitionCost The cost of gaining the prospect as a customer
     */
    public void setAcqusitionCost(BigDecimal AcqusitionCost) {
        set_Value(I_C_BPartner.COLUMNNAME_AcqusitionCost, AcqusitionCost);
    }

    /**
     * Get Actual Life Time Value.
     *
     * @return Actual Life Time Revenue
     */
    public BigDecimal getActualLifeTimeValue() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_ActualLifeTimeValue);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Actual Life Time Value.
     *
     * @param ActualLifeTimeValue Actual Life Time Revenue
     */
    public void setActualLifeTimeValue(BigDecimal ActualLifeTimeValue) {
        set_Value(I_C_BPartner.COLUMNNAME_ActualLifeTimeValue, ActualLifeTimeValue);
    }

    /**
     * Get Language.
     *
     * @return Language for this entity
     */
    public String getADLanguage() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_AD_Language);
    }

    /**
     * Get Linked Organization.
     *
     * @return The Business Partner is another Organization for explicit Inter-Org transactions
     */
    public String getAD_OrgBP_ID() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID);
    }

    /**
     * Set Linked Organization.
     *
     * @param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org
     *                    transactions
     */
    public void setAD_OrgBP_ID(String AD_OrgBP_ID) {
        set_Value(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID, AD_OrgBP_ID);
    }

    /**
     * Get Business Partner .
     *
     * @return Identifies a Business Partner
     */
    public int getC_BPartner_ID() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_BPartner_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Business Partner Group.
     *
     * @return Business Partner Group
     */
    public int getC_BP_Group_ID() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Business Partner Group.
     *
     * @param C_BP_Group_ID Business Partner Group
     */
    public void setC_BP_Group_ID(int C_BP_Group_ID) {
        if (C_BP_Group_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_BP_Group_ID, null);
        else set_Value(I_C_BPartner.COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
    }

    /**
     * Set Dunning.
     *
     * @param C_Dunning_ID Dunning Rules for overdue invoices
     */
    public void setC_Dunning_ID(int C_Dunning_ID) {
        if (C_Dunning_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_Dunning_ID, null);
        else set_Value(I_C_BPartner.COLUMNNAME_C_Dunning_ID, Integer.valueOf(C_Dunning_ID));
    }


    /**
     * Get Invoice Schedule.
     *
     * @return Schedule for generating Invoices
     */
    public int getC_InvoiceSchedule_ID() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_InvoiceSchedule_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_Description);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        set_Value(I_C_BPartner.COLUMNNAME_Description, Description);
    }

    /**
     * Set D-U-N-S.
     *
     * @param DUNS Dun & Bradstreet Number
     */
    public void setDUNS(String DUNS) {
        set_Value(I_C_BPartner.COLUMNNAME_DUNS, DUNS);
    }

    /**
     * Get First Sale.
     *
     * @return Date of First Sale
     */
    public Timestamp getFirstSale() {
        return (Timestamp) getValue(I_C_BPartner.COLUMNNAME_FirstSale);
    }

    /**
     * Set First Sale.
     *
     * @param FirstSale Date of First Sale
     */
    public void setFirstSale(Timestamp FirstSale) {
        set_Value(I_C_BPartner.COLUMNNAME_FirstSale, FirstSale);
    }

    /**
     * Set Customer.
     *
     * @param IsCustomer Indicates if this Business Partner is a Customer
     */
    public void setIsCustomer(boolean IsCustomer) {
        set_Value(I_C_BPartner.COLUMNNAME_IsCustomer, Boolean.valueOf(IsCustomer));
    }

    /**
     * Set Discount Printed.
     *
     * @param IsDiscountPrinted Print Discount on Invoice and Order
     */
    public void setIsDiscountPrinted(boolean IsDiscountPrinted) {
        set_Value(I_C_BPartner.COLUMNNAME_IsDiscountPrinted, Boolean.valueOf(IsDiscountPrinted));
    }

    /**
     * Set Employee.
     *
     * @param IsEmployee Indicates if this Business Partner is an employee
     */
    public void setIsEmployee(boolean IsEmployee) {
        set_Value(I_C_BPartner.COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
    }

    /**
     * Set One time transaction.
     *
     * @param IsOneTime One time transaction
     */
    public void setIsOneTime(boolean IsOneTime) {
        set_Value(I_C_BPartner.COLUMNNAME_IsOneTime, Boolean.valueOf(IsOneTime));
    }

    /**
     * Set PO Tax exempt.
     *
     * @param IsPOTaxExempt Business partner is exempt from tax on purchases
     */
    public void setIsPOTaxExempt(boolean IsPOTaxExempt) {
        set_Value(I_C_BPartner.COLUMNNAME_IsPOTaxExempt, Boolean.valueOf(IsPOTaxExempt));
    }

    /**
     * Set Prospect.
     *
     * @param IsProspect Indicates this is a Prospect
     */
    public void setIsProspect(boolean IsProspect) {
        set_Value(I_C_BPartner.COLUMNNAME_IsProspect, Boolean.valueOf(IsProspect));
    }

    /**
     * Set Sales Representative.
     *
     * @param IsSalesRep Indicates if the business partner is a sales representative or company agent
     */
    public void setIsSalesRep(boolean IsSalesRep) {
        set_Value(I_C_BPartner.COLUMNNAME_IsSalesRep, Boolean.valueOf(IsSalesRep));
    }

    /**
     * Set Summary Level.
     *
     * @param IsSummary This is a summary entity
     */
    public void setIsSummary(boolean IsSummary) {
        set_Value(I_C_BPartner.COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
    }

    /**
     * Set SO Tax exempt.
     *
     * @param IsTaxExempt Business partner is exempt from tax on sales
     */
    public void setIsTaxExempt(boolean IsTaxExempt) {
        set_Value(I_C_BPartner.COLUMNNAME_IsTaxExempt, Boolean.valueOf(IsTaxExempt));
    }

    /**
     * Set Vendor.
     *
     * @param IsVendor Indicates if this Business Partner is a Vendor
     */
    public void setIsVendor(boolean IsVendor) {
        set_Value(I_C_BPartner.COLUMNNAME_IsVendor, Boolean.valueOf(IsVendor));
    }

    /**
     * Set Discount Schema.
     *
     * @param M_DiscountSchema_ID Schema to calculate the trade discount percentage
     */
    public void setM_DiscountSchema_ID(int M_DiscountSchema_ID) {
        if (M_DiscountSchema_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_M_DiscountSchema_ID, null);
        else
            set_Value(I_C_BPartner.COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
    }

    /**
     * Get Price List.
     *
     * @return Unique identifier of a Price List
     */
    public int getM_PriceList_ID() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_M_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Price List.
     *
     * @param M_PriceList_ID Unique identifier of a Price List
     */
    public void setM_PriceList_ID(int M_PriceList_ID) {
        if (M_PriceList_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_M_PriceList_ID, null);
        else set_Value(I_C_BPartner.COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
    }

    /**
     * Set NAICS/SIC.
     *
     * @param NAICS Standard Industry Code or its successor NAIC -
     *              http://www.osha.gov/oshstats/sicser.html
     */
    public void setNAICS(String NAICS) {
        set_Value(I_C_BPartner.COLUMNNAME_NAICS, NAICS);
    }

    /**
     * Set Name 2.
     *
     * @param Name2 Additional Name
     */
    public void setName2(String Name2) {
        set_Value(HasName2.Companion.getCOLUMNNAME_Name2(), Name2);
    }

    /**
     * Set PO Discount Schema.
     *
     * @param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage
     */
    public void setPO_DiscountSchema_ID(int PO_DiscountSchema_ID) {
        if (PO_DiscountSchema_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_PO_DiscountSchema_ID, null);
        else
            set_Value(
                    I_C_BPartner.COLUMNNAME_PO_DiscountSchema_ID, Integer.valueOf(PO_DiscountSchema_ID));
    }

    /**
     * Get Purchase Pricelist.
     *
     * @return Price List used by this Business Partner
     */
    public int getPO_PriceList_ID() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_PO_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Purchase Pricelist.
     *
     * @param PO_PriceList_ID Price List used by this Business Partner
     */
    public void setPO_PriceList_ID(int PO_PriceList_ID) {
        if (PO_PriceList_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_PO_PriceList_ID, null);
        else set_Value(I_C_BPartner.COLUMNNAME_PO_PriceList_ID, Integer.valueOf(PO_PriceList_ID));
    }

    /**
     * Set Potential Life Time Value.
     *
     * @param PotentialLifeTimeValue Total Revenue expected
     */
    public void setPotentialLifeTimeValue(BigDecimal PotentialLifeTimeValue) {
        set_Value(I_C_BPartner.COLUMNNAME_PotentialLifeTimeValue, PotentialLifeTimeValue);
    }

    /**
     * Set Sales Volume in 1.000.
     *
     * @param SalesVolume Total Volume of Sales in Thousands of Currency
     */
    public void setSalesVolume(int SalesVolume) {
        set_Value(I_C_BPartner.COLUMNNAME_SalesVolume, Integer.valueOf(SalesVolume));
    }

    /**
     * Set Send EMail.
     *
     * @param SendEMail Enable sending Document EMail
     */
    public void setSendEMail(boolean SendEMail) {
        set_Value(I_C_BPartner.COLUMNNAME_SendEMail, Boolean.valueOf(SendEMail));
    }

    /**
     * Set Share.
     *
     * @param ShareOfCustomer Share of Customer's business as a percentage
     */
    public void setShareOfCustomer(int ShareOfCustomer) {
        set_Value(I_C_BPartner.COLUMNNAME_ShareOfCustomer, Integer.valueOf(ShareOfCustomer));
    }

    /**
     * Get Credit Limit.
     *
     * @return Total outstanding invoice amounts allowed
     */
    public BigDecimal getSO_CreditLimit() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_SO_CreditLimit);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Credit Limit.
     *
     * @param SO_CreditLimit Total outstanding invoice amounts allowed
     */
    public void setSO_CreditLimit(BigDecimal SO_CreditLimit) {
        set_Value(I_C_BPartner.COLUMNNAME_SO_CreditLimit, SO_CreditLimit);
    }

    /**
     * Get Credit Status.
     *
     * @return Business Partner Credit Status
     */
    public String getSOCreditStatus() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_SOCreditStatus);
    }

    /**
     * Set Credit Status.
     *
     * @param SOCreditStatus Business Partner Credit Status
     */
    public void setSOCreditStatus(String SOCreditStatus) {

        set_Value(I_C_BPartner.COLUMNNAME_SOCreditStatus, SOCreditStatus);
    }

    /**
     * Get Credit Used.
     *
     * @return Current open balance
     */
    public BigDecimal getSO_CreditUsed() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_SO_CreditUsed);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Credit Used.
     *
     * @param SO_CreditUsed Current open balance
     */
    public void setSO_CreditUsed(BigDecimal SO_CreditUsed) {
        set_ValueNoCheck(I_C_BPartner.COLUMNNAME_SO_CreditUsed, SO_CreditUsed);
    }

    /**
     * Set Tax ID.
     *
     * @param TaxID Tax Identification
     */
    public void setTaxID(String TaxID) {
        set_Value(I_C_BPartner.COLUMNNAME_TaxID, TaxID);
    }

    /**
     * Get Open Balance.
     *
     * @return Total Open Balance Amount in primary Accounting Currency
     */
    public BigDecimal getTotalOpenBalance() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_TotalOpenBalance);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Open Balance.
     *
     * @param TotalOpenBalance Total Open Balance Amount in primary Accounting Currency
     */
    public void setTotalOpenBalance(BigDecimal TotalOpenBalance) {
        set_Value(I_C_BPartner.COLUMNNAME_TotalOpenBalance, TotalOpenBalance);
    }

    @Override
    public int getTableId() {
        return I_C_BPartner.Table_ID;
    }
}
