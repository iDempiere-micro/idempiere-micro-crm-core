package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.HasName2;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.BasePONameValue;
import org.idempiere.common.util.Env;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Generated Model for C_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BPartner extends BasePONameValue implements HasName2 {

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
    public X_C_BPartner(int C_BPartner_ID) {
        super(C_BPartner_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_BPartner(Row row) {
        super(row);
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
        setValue(I_C_BPartner.COLUMNNAME_AcqusitionCost, AcqusitionCost);
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
        setValue(I_C_BPartner.COLUMNNAME_ActualLifeTimeValue, ActualLifeTimeValue);
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
    public String getLinkedOrganizationIdAsString() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID);
    }

    /**
     * Set Linked Organization.
     *
     * @param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org
     *                    transactions
     */
    public void setLinkedOrganizationId(String AD_OrgBP_ID) {
        setValue(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID, AD_OrgBP_ID);
    }

    /**
     * Get Business Partner .
     *
     * @return Identifies a Business Partner
     */
    public int getBusinessPartnerId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_BPartner_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Business Partner Group.
     *
     * @return Business Partner Group
     */
    public int getBPGroupId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Business Partner Group.
     *
     * @param C_BP_Group_ID Business Partner Group
     */
    public void setBPGroupId(int C_BP_Group_ID) {
        if (C_BP_Group_ID < 1) setValue(I_C_BPartner.COLUMNNAME_C_BP_Group_ID, null);
        else setValue(I_C_BPartner.COLUMNNAME_C_BP_Group_ID, C_BP_Group_ID);
    }

    /**
     * Set Dunning.
     *
     * @param C_Dunning_ID Dunning Rules for overdue invoices
     */
    public void setDunningId(int C_Dunning_ID) {
        if (C_Dunning_ID < 1) setValue(I_C_BPartner.COLUMNNAME_C_Dunning_ID, null);
        else setValue(I_C_BPartner.COLUMNNAME_C_Dunning_ID, C_Dunning_ID);
    }


    /**
     * Get Invoice Schedule.
     *
     * @return Schedule for generating Invoices
     */
    public int getInvoiceScheduleId() {
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
        setValue(I_C_BPartner.COLUMNNAME_Description, Description);
    }

    /**
     * Set D-U-N-S.
     *
     * @param DUNS Dun & Bradstreet Number
     */
    public void setDUNS(String DUNS) {
        setValue(I_C_BPartner.COLUMNNAME_DUNS, DUNS);
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
        setValue(I_C_BPartner.COLUMNNAME_FirstSale, FirstSale);
    }

    /**
     * Get Customer.
     *
     * @return Indicates if this Business Partner is a Customer
     */
    public boolean getIsCustomer() {
        Object oo = getValue(I_C_BPartner.COLUMNNAME_IsCustomer);
        if (oo != null) {
            if (oo instanceof Boolean)
                return ((Boolean) oo);
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Set Customer.
     *
     * @param IsCustomer Indicates if this Business Partner is a Customer
     */
    public void setIsCustomer(boolean IsCustomer) {
        setValue(I_C_BPartner.COLUMNNAME_IsCustomer, IsCustomer);
    }

    /**
     * Set Discount Printed.
     *
     * @param IsDiscountPrinted Print Discount on Invoice and Order
     */
    public void setIsDiscountPrinted(boolean IsDiscountPrinted) {
        setValue(I_C_BPartner.COLUMNNAME_IsDiscountPrinted, IsDiscountPrinted);
    }

    /**
     * Set Employee.
     *
     * @param IsEmployee Indicates if this Business Partner is an employee
     */
    public void setIsEmployee(boolean IsEmployee) {
        setValue(I_C_BPartner.COLUMNNAME_IsEmployee, IsEmployee);
    }

    /**
     * Set One time transaction.
     *
     * @param IsOneTime One time transaction
     */
    public void setIsOneTime(boolean IsOneTime) {
        setValue(I_C_BPartner.COLUMNNAME_IsOneTime, IsOneTime);
    }

    /**
     * Set PO Tax exempt.
     *
     * @param IsPOTaxExempt Business partner is exempt from tax on purchases
     */
    public void setIsPOTaxExempt(boolean IsPOTaxExempt) {
        setValue(I_C_BPartner.COLUMNNAME_IsPOTaxExempt, IsPOTaxExempt);
    }

    /**
     * Set Prospect.
     *
     * @param IsProspect Indicates this is a Prospect
     */
    public void setIsProspect(boolean IsProspect) {
        setValue(I_C_BPartner.COLUMNNAME_IsProspect, IsProspect);
    }

    /**
     * Set Sales Representative.
     *
     * @param IsSalesRep Indicates if the business partner is a sales representative or company agent
     */
    public void setIsSalesRep(boolean IsSalesRep) {
        setValue(I_C_BPartner.COLUMNNAME_IsSalesRep, IsSalesRep);
    }

    /**
     * Set Summary Level.
     *
     * @param IsSummary This is a summary entity
     */
    public void setIsSummary(boolean IsSummary) {
        setValue(I_C_BPartner.COLUMNNAME_IsSummary, IsSummary);
    }

    /**
     * Set SO Tax exempt.
     *
     * @param IsTaxExempt Business partner is exempt from tax on sales
     */
    public void setIsTaxExempt(boolean IsTaxExempt) {
        setValue(I_C_BPartner.COLUMNNAME_IsTaxExempt, IsTaxExempt);
    }

    /**
     * Set Vendor.
     *
     * @param IsVendor Indicates if this Business Partner is a Vendor
     */
    public void setIsVendor(boolean IsVendor) {
        setValue(I_C_BPartner.COLUMNNAME_IsVendor, IsVendor);
    }

    /**
     * Set Discount Schema.
     *
     * @param M_DiscountSchema_ID Schema to calculate the trade discount percentage
     */
    public void setDiscountSchemaId(int M_DiscountSchema_ID) {
        if (M_DiscountSchema_ID < 1) setValue(I_C_BPartner.COLUMNNAME_M_DiscountSchema_ID, null);
        else
            setValue(I_C_BPartner.COLUMNNAME_M_DiscountSchema_ID, M_DiscountSchema_ID);
    }

    /**
     * Get Price List.
     *
     * @return Unique identifier of a Price List
     */
    public int getPriceListId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_M_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Price List.
     *
     * @param M_PriceList_ID Unique identifier of a Price List
     */
    public void setPriceListId(int M_PriceList_ID) {
        if (M_PriceList_ID < 1) setValue(I_C_BPartner.COLUMNNAME_M_PriceList_ID, null);
        else setValue(I_C_BPartner.COLUMNNAME_M_PriceList_ID, M_PriceList_ID);
    }

    /**
     * Set NAICS/SIC.
     *
     * @param NAICS Standard Industry Code or its successor NAIC -
     *              http://www.osha.gov/oshstats/sicser.html
     */
    public void setNAICS(String NAICS) {
        setValue(I_C_BPartner.COLUMNNAME_NAICS, NAICS);
    }

    /**
     * Get Name 2.
     */
    public String getName2() {
        return (String) getValue(HasName2.COLUMNNAME_Name2);
    }

    /**
     * Set Name 2.
     *
     * @param Name2 Additional Name
     */
    public void setName2(String Name2) {
        setValue(HasName2.COLUMNNAME_Name2, Name2);
    }

    /**
     * Set PO Discount Schema.
     *
     * @param PO_DiscountSchema_ID Schema to calculate the purchase trade discount percentage
     */
    public void setPODiscountSchemaId(int PO_DiscountSchema_ID) {
        if (PO_DiscountSchema_ID < 1) setValue(I_C_BPartner.COLUMNNAME_PO_DiscountSchema_ID, null);
        else
            setValue(
                    I_C_BPartner.COLUMNNAME_PO_DiscountSchema_ID, PO_DiscountSchema_ID);
    }

    /**
     * Get Purchase Pricelist.
     *
     * @return Price List used by this Business Partner
     */
    public int getPurchaseOrderPriceListId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_PO_PriceList_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Purchase Pricelist.
     *
     * @param PO_PriceList_ID Price List used by this Business Partner
     */
    public void setPurchaseOrderPriceListId(int PO_PriceList_ID) {
        if (PO_PriceList_ID < 1) setValue(I_C_BPartner.COLUMNNAME_PO_PriceList_ID, null);
        else setValue(I_C_BPartner.COLUMNNAME_PO_PriceList_ID, PO_PriceList_ID);
    }

    /**
     * Set Potential Life Time Value.
     *
     * @param PotentialLifeTimeValue Total Revenue expected
     */
    public void setPotentialLifeTimeValue(BigDecimal PotentialLifeTimeValue) {
        setValue(I_C_BPartner.COLUMNNAME_PotentialLifeTimeValue, PotentialLifeTimeValue);
    }

    /**
     * Set Sales Volume in 1.000.
     *
     * @param SalesVolume Total Volume of Sales in Thousands of Currency
     */
    public void setSalesVolume(int SalesVolume) {
        setValue(I_C_BPartner.COLUMNNAME_SalesVolume, SalesVolume);
    }

    /**
     * Set Send EMail.
     *
     * @param SendEMail Enable sending Document EMail
     */
    public void setSendEMail(boolean SendEMail) {
        setValue(I_C_BPartner.COLUMNNAME_SendEMail, SendEMail);
    }

    /**
     * Set Share.
     *
     * @param ShareOfCustomer Share of Customer's business as a percentage
     */
    public void setShareOfCustomer(int ShareOfCustomer) {
        setValue(I_C_BPartner.COLUMNNAME_ShareOfCustomer, ShareOfCustomer);
    }

    /**
     * Get Credit Limit.
     *
     * @return Total outstanding invoice amounts allowed
     */
    public BigDecimal getSalesOrderCreditLimit() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_SO_CreditLimit);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Credit Limit.
     *
     * @param SO_CreditLimit Total outstanding invoice amounts allowed
     */
    public void setSalesOrderCreditLimit(BigDecimal SO_CreditLimit) {
        setValue(I_C_BPartner.COLUMNNAME_SO_CreditLimit, SO_CreditLimit);
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

        setValue(I_C_BPartner.COLUMNNAME_SOCreditStatus, SOCreditStatus);
    }

    /**
     * Get Credit Used.
     *
     * @return Current open balance
     */
    public BigDecimal getSalesOrderCreditUsed() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_SO_CreditUsed);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Credit Used.
     *
     * @param SO_CreditUsed Current open balance
     */
    public void setSalesOrderCreditUsed(BigDecimal SO_CreditUsed) {
        setValueNoCheck(I_C_BPartner.COLUMNNAME_SO_CreditUsed, SO_CreditUsed);
    }

    /**
     * Get Tax ID.
     *
     * @return Tax Identification
     */
    public String getTaxID() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_TaxID);
    }

    /**
     * Set Tax ID.
     *
     * @param TaxID Tax Identification
     */
    public void setTaxID(String TaxID) {
        setValue(I_C_BPartner.COLUMNNAME_TaxID, TaxID);
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
        setValue(I_C_BPartner.COLUMNNAME_TotalOpenBalance, TotalOpenBalance);
    }

    @Override
    public int getTableId() {
        return I_C_BPartner.Table_ID;
    }

    /**
     * Get URL.
     *
     * @return Full URL address - e.g. http://www.idempiere.org
     */
    public String getURL() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_URL);
    }

    /**
     * Set URL.
     *
     * @param URL Full URL address - e.g. http://www.idempiere.org
     */
    public void setURL(String URL) {
        setValue(I_C_BPartner.COLUMNNAME_URL, URL);
    }

    /**
     * Get Flat Discount %.
     *
     * @return Flat discount percentage
     */
    public BigDecimal getFlatDiscount() {
        BigDecimal bd = (BigDecimal) getValue(I_C_BPartner.COLUMNNAME_FlatDiscount);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    /**
     * Set Flat Discount %.
     *
     * @param FlatDiscount Flat discount percentage
     */
    public void setFlatDiscount(BigDecimal FlatDiscount) {
        setValue(I_C_BPartner.COLUMNNAME_FlatDiscount, FlatDiscount);
    }


}
