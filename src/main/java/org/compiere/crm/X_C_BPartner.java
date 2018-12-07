package org.compiere.crm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.HasName2;
import org.compiere.model.I_AD_PrintFormat;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_1099Box;
import org.compiere.model.I_C_BP_Group;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Dunning;
import org.compiere.model.I_C_Greeting;
import org.compiere.model.I_C_InvoiceSchedule;
import org.compiere.model.I_C_PaymentTerm;
import org.compiere.model.I_M_DiscountSchema;
import org.compiere.model.I_M_PriceList;
import org.compiere.orm.BasePONameValue;
import org.compiere.orm.MTable;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BPartner extends BasePONameValue implements I_Persistent {

  /** AD_Language AD_Reference_ID=327 */
  public static final int AD_LANGUAGE_AD_Reference_ID = 327;
  /** DeliveryRule AD_Reference_ID=151 */
  public static final int DELIVERYRULE_AD_Reference_ID = 151;
  /** After Receipt = R */
  public static final String DELIVERYRULE_AfterReceipt = "R";
  /** Availability = A */
  public static final String DELIVERYRULE_Availability = "A";
  /** Complete Line = L */
  public static final String DELIVERYRULE_CompleteLine = "L";
  /** Complete Order = O */
  public static final String DELIVERYRULE_CompleteOrder = "O";
  /** Force = F */
  public static final String DELIVERYRULE_Force = "F";
  /** Manual = M */
  public static final String DELIVERYRULE_Manual = "M";
  /** DeliveryViaRule AD_Reference_ID=152 */
  public static final int DELIVERYVIARULE_AD_Reference_ID = 152;
  /** Pickup = P */
  public static final String DELIVERYVIARULE_Pickup = "P";
  /** Delivery = D */
  public static final String DELIVERYVIARULE_Delivery = "D";
  /** Shipper = S */
  public static final String DELIVERYVIARULE_Shipper = "S";
  /** FreightCostRule AD_Reference_ID=153 */
  public static final int FREIGHTCOSTRULE_AD_Reference_ID = 153;
  /** Freight included = I */
  public static final String FREIGHTCOSTRULE_FreightIncluded = "I";
  /** Fix price = F */
  public static final String FREIGHTCOSTRULE_FixPrice = "F";
  /** Calculated = C */
  public static final String FREIGHTCOSTRULE_Calculated = "C";
  /** Line = L */
  public static final String FREIGHTCOSTRULE_Line = "L";
  /** InvoiceRule AD_Reference_ID=150 */
  public static final int INVOICERULE_AD_Reference_ID = 150;
  /** After Order delivered = O */
  public static final String INVOICERULE_AfterOrderDelivered = "O";
  /** After Delivery = D */
  public static final String INVOICERULE_AfterDelivery = "D";
  /** Customer Schedule after Delivery = S */
  public static final String INVOICERULE_CustomerScheduleAfterDelivery = "S";
  /** Immediate = I */
  public static final String INVOICERULE_Immediate = "I";
  /** PaymentRule AD_Reference_ID=195 */
  public static final int PAYMENTRULE_AD_Reference_ID = 195;
  /** Cash = B */
  public static final String PAYMENTRULE_Cash = "B";
  /** Credit Card = K */
  public static final String PAYMENTRULE_CreditCard = "K";
  /** Direct Deposit = T */
  public static final String PAYMENTRULE_DirectDeposit = "T";
  /** Check = S */
  public static final String PAYMENTRULE_Check = "S";
  /** On Credit = P */
  public static final String PAYMENTRULE_OnCredit = "P";
  /** Direct Debit = D */
  public static final String PAYMENTRULE_DirectDebit = "D";
  /** Mixed POS Payment = M */
  public static final String PAYMENTRULE_MixedPOSPayment = "M";
  /** PaymentRulePO AD_Reference_ID=195 */
  public static final int PAYMENTRULEPO_AD_Reference_ID = 195;
  /** Cash = B */
  public static final String PAYMENTRULEPO_Cash = "B";
  /** Credit Card = K */
  public static final String PAYMENTRULEPO_CreditCard = "K";
  /** Direct Deposit = T */
  public static final String PAYMENTRULEPO_DirectDeposit = "T";
  /** Check = S */
  public static final String PAYMENTRULEPO_Check = "S";
  /** On Credit = P */
  public static final String PAYMENTRULEPO_OnCredit = "P";
  /** Direct Debit = D */
  public static final String PAYMENTRULEPO_DirectDebit = "D";
  /** Mixed POS Payment = M */
  public static final String PAYMENTRULEPO_MixedPOSPayment = "M";
  /** SOCreditStatus AD_Reference_ID=289 */
  public static final int SOCREDITSTATUS_AD_Reference_ID = 289;
  /** Credit Stop = S */
  public static final String SOCREDITSTATUS_CreditStop = "S";
  /** Credit Hold = H */
  public static final String SOCREDITSTATUS_CreditHold = "H";
  /** Credit Watch = W */
  public static final String SOCREDITSTATUS_CreditWatch = "W";
  /** No Credit Check = X */
  public static final String SOCREDITSTATUS_NoCreditCheck = "X";
  /** Credit OK = O */
  public static final String SOCREDITSTATUS_CreditOK = "O";
  /** */
  private static final long serialVersionUID = 20171031L;
  /** Standard Constructor */
  public X_C_BPartner(Properties ctx, int C_BPartner_ID, String trxName) {
    super(ctx, C_BPartner_ID, trxName);
    /**
     * if (C_BPartner_ID == 0) { setC_BPartner_ID (0); setC_BP_Group_ID (0); setIs1099Vendor
     * (false); // N setIsCustomer (false); setIsEmployee (false); setIsOneTime (false);
     * setIsPOTaxExempt (false); // N setIsProspect (false); // N setIsSalesRep (false);
     * setIsSummary (false); setIsVendor (false); setName (null); setSendEMail (false);
     * setSO_CreditLimit (Env.ZERO); setSO_CreditUsed (Env.ZERO); setValue (null); }
     */
  }
  /** Load Constructor */
  public X_C_BPartner(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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
   * Get Acquisition Cost.
   *
   * @return The cost of gaining the prospect as a customer
   */
  public BigDecimal getAcqusitionCost() {
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_AcqusitionCost);
    if (bd == null) return Env.ZERO;
    return bd;
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
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_ActualLifeTimeValue);
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
    return (String) get_Value(I_C_BPartner.COLUMNNAME_AD_Language);
  }

  /**
   * Set Language.
   *
   * @param AD_Language Language for this entity
   */
  public void setADLanguage(String AD_Language) {

    set_Value(I_C_BPartner.COLUMNNAME_AD_Language, AD_Language);
  }

  /**
   * Get Linked Organization.
   *
   * @return The Business Partner is another Organization for explicit Inter-Org transactions
   */
  public String getAD_OrgBP_ID() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID);
  }

  /**
   * Set Linked Organization.
   *
   * @param AD_OrgBP_ID The Business Partner is another Organization for explicit Inter-Org
   *     transactions
   */
  public void setAD_OrgBP_ID(String AD_OrgBP_ID) {
    set_Value(I_C_BPartner.COLUMNNAME_AD_OrgBP_ID, AD_OrgBP_ID);
  }

  /**
   * Get Partner Parent.
   *
   * @return Business Partner Parent
   */
  public int getBPartner_Parent_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_BPartner_Parent_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Partner Parent.
   *
   * @param BPartner_Parent_ID Business Partner Parent
   */
  public void setBPartner_Parent_ID(int BPartner_Parent_ID) {
    if (BPartner_Parent_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_BPartner_Parent_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_BPartner_Parent_ID, Integer.valueOf(BPartner_Parent_ID));
  }

  /**
   * Get Business Partner .
   *
   * @return Identifies a Business Partner
   */
  public int getC_BPartner_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Business Partner .
   *
   * @param C_BPartner_ID Identifies a Business Partner
   */
  public void setC_BPartner_ID(int C_BPartner_ID) {
    if (C_BPartner_ID < 1) set_ValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, null);
    else set_ValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
  }

  /**
   * Get C_BPartner_UU.
   *
   * @return C_BPartner_UU
   */
  public String getC_BPartner_UU() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_C_BPartner_UU);
  }

  /**
   * Set C_BPartner_UU.
   *
   * @param C_BPartner_UU C_BPartner_UU
   */
  public void setC_BPartner_UU(String C_BPartner_UU) {
    set_Value(I_C_BPartner.COLUMNNAME_C_BPartner_UU, C_BPartner_UU);
  }

  public I_C_BP_Group getC_BP_Group() throws RuntimeException {
    return (I_C_BP_Group)
        MTable.get(getCtx(), I_C_BP_Group.Table_Name).getPO(getC_BP_Group_ID(), get_TrxName());
  }

  /**
   * Get Business Partner Group.
   *
   * @return Business Partner Group
   */
  public int getC_BP_Group_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_BP_Group_ID);
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

  public I_C_Dunning getC_Dunning() throws RuntimeException {
    return (I_C_Dunning)
        MTable.get(getCtx(), I_C_Dunning.Table_Name).getPO(getC_Dunning_ID(), get_TrxName());
  }

  /**
   * Get Dunning.
   *
   * @return Dunning Rules for overdue invoices
   */
  public int getC_Dunning_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_Dunning_ID);
    if (ii == null) return 0;
    return ii;
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

  public I_C_Greeting getC_Greeting() throws RuntimeException {
    return (I_C_Greeting)
        MTable.get(getCtx(), I_C_Greeting.Table_Name).getPO(getC_Greeting_ID(), get_TrxName());
  }

  /**
   * Get Greeting.
   *
   * @return Greeting to print on correspondence
   */
  public int getC_Greeting_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_Greeting_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Greeting.
   *
   * @param C_Greeting_ID Greeting to print on correspondence
   */
  public void setC_Greeting_ID(int C_Greeting_ID) {
    if (C_Greeting_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_Greeting_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
  }

  public I_C_InvoiceSchedule getC_InvoiceSchedule() throws RuntimeException {
    return (I_C_InvoiceSchedule)
        MTable.get(getCtx(), I_C_InvoiceSchedule.Table_Name)
            .getPO(getC_InvoiceSchedule_ID(), get_TrxName());
  }

  /**
   * Get Invoice Schedule.
   *
   * @return Schedule for generating Invoices
   */
  public int getC_InvoiceSchedule_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_InvoiceSchedule_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Invoice Schedule.
   *
   * @param C_InvoiceSchedule_ID Schedule for generating Invoices
   */
  public void setC_InvoiceSchedule_ID(int C_InvoiceSchedule_ID) {
    if (C_InvoiceSchedule_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_InvoiceSchedule_ID, null);
    else
      set_Value(
          I_C_BPartner.COLUMNNAME_C_InvoiceSchedule_ID, Integer.valueOf(C_InvoiceSchedule_ID));
  }

  public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException {
    return (I_C_PaymentTerm)
        MTable.get(getCtx(), I_C_PaymentTerm.Table_Name)
            .getPO(getC_PaymentTerm_ID(), get_TrxName());
  }

  /**
   * Get Payment Term.
   *
   * @return The terms of Payment (timing, discount)
   */
  public int getC_PaymentTerm_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_PaymentTerm_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Payment Term.
   *
   * @param C_PaymentTerm_ID The terms of Payment (timing, discount)
   */
  public void setC_PaymentTerm_ID(int C_PaymentTerm_ID) {
    if (C_PaymentTerm_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_PaymentTerm_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
  }

  public org.eevolution.model.I_C_TaxGroup getC_TaxGroup() throws RuntimeException {
    return (org.eevolution.model.I_C_TaxGroup)
        MTable.get(getCtx(), org.eevolution.model.I_C_TaxGroup.Table_Name)
            .getPO(getC_TaxGroup_ID(), get_TrxName());
  }

  /**
   * Get Tax Group.
   *
   * @return Tax Group
   */
  public int getC_TaxGroup_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_C_TaxGroup_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Tax Group.
   *
   * @param C_TaxGroup_ID Tax Group
   */
  public void setC_TaxGroup_ID(int C_TaxGroup_ID) {
    if (C_TaxGroup_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_C_TaxGroup_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_C_TaxGroup_ID, Integer.valueOf(C_TaxGroup_ID));
  }

  /**
   * Get Customer Profile ID.
   *
   * @return Customer Profile ID
   */
  public String getCustomerProfileID() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_CustomerProfileID);
  }

  /**
   * Set Customer Profile ID.
   *
   * @param CustomerProfileID Customer Profile ID
   */
  public void setCustomerProfileID(String CustomerProfileID) {
    set_Value(I_C_BPartner.COLUMNNAME_CustomerProfileID, CustomerProfileID);
  }

  public I_C_1099Box getDefault1099Box() throws RuntimeException {
    return (I_C_1099Box)
        MTable.get(getCtx(), I_C_1099Box.Table_Name).getPO(getDefault1099Box_ID(), get_TrxName());
  }

  /**
   * Get Default 1099 Box.
   *
   * @return Default 1099 Box
   */
  public int getDefault1099Box_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_Default1099Box_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Default 1099 Box.
   *
   * @param Default1099Box_ID Default 1099 Box
   */
  public void setDefault1099Box_ID(int Default1099Box_ID) {
    if (Default1099Box_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_Default1099Box_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_Default1099Box_ID, Integer.valueOf(Default1099Box_ID));
  }

  /**
   * Get Delivery Rule.
   *
   * @return Defines the timing of Delivery
   */
  public String getDeliveryRule() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_DeliveryRule);
  }

  /**
   * Set Delivery Rule.
   *
   * @param DeliveryRule Defines the timing of Delivery
   */
  public void setDeliveryRule(String DeliveryRule) {

    set_Value(I_C_BPartner.COLUMNNAME_DeliveryRule, DeliveryRule);
  }

  /**
   * Get Delivery Via.
   *
   * @return How the order will be delivered
   */
  public String getDeliveryViaRule() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_DeliveryViaRule);
  }

  /**
   * Set Delivery Via.
   *
   * @param DeliveryViaRule How the order will be delivered
   */
  public void setDeliveryViaRule(String DeliveryViaRule) {

    set_Value(I_C_BPartner.COLUMNNAME_DeliveryViaRule, DeliveryViaRule);
  }

  /**
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_Description);
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
   * Get Document Copies.
   *
   * @return Number of copies to be printed
   */
  public int getDocumentCopies() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_DocumentCopies);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Document Copies.
   *
   * @param DocumentCopies Number of copies to be printed
   */
  public void setDocumentCopies(int DocumentCopies) {
    set_Value(I_C_BPartner.COLUMNNAME_DocumentCopies, Integer.valueOf(DocumentCopies));
  }

  /**
   * Get Dunning Grace Date.
   *
   * @return Dunning Grace Date
   */
  public Timestamp getDunningGrace() {
    return (Timestamp) get_Value(I_C_BPartner.COLUMNNAME_DunningGrace);
  }

  /**
   * Set Dunning Grace Date.
   *
   * @param DunningGrace Dunning Grace Date
   */
  public void setDunningGrace(Timestamp DunningGrace) {
    set_Value(I_C_BPartner.COLUMNNAME_DunningGrace, DunningGrace);
  }

  /**
   * Get D-U-N-S.
   *
   * @return Dun & Bradstreet Number
   */
  public String getDUNS() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_DUNS);
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
    return (Timestamp) get_Value(I_C_BPartner.COLUMNNAME_FirstSale);
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
   * Get Flat Discount %.
   *
   * @return Flat discount percentage
   */
  public BigDecimal getFlatDiscount() {
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_FlatDiscount);
    if (bd == null) return Env.ZERO;
    return bd;
  }

  /**
   * Set Flat Discount %.
   *
   * @param FlatDiscount Flat discount percentage
   */
  public void setFlatDiscount(BigDecimal FlatDiscount) {
    set_Value(I_C_BPartner.COLUMNNAME_FlatDiscount, FlatDiscount);
  }

  /**
   * Get Freight Cost Rule.
   *
   * @return Method for charging Freight
   */
  public String getFreightCostRule() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_FreightCostRule);
  }

  /**
   * Set Freight Cost Rule.
   *
   * @param FreightCostRule Method for charging Freight
   */
  public void setFreightCostRule(String FreightCostRule) {

    set_Value(I_C_BPartner.COLUMNNAME_FreightCostRule, FreightCostRule);
  }

  public I_AD_PrintFormat getInvoice_PrintFormat() throws RuntimeException {
    return (I_AD_PrintFormat)
        MTable.get(getCtx(), I_AD_PrintFormat.Table_Name)
            .getPO(getInvoice_PrintFormat_ID(), get_TrxName());
  }

  /**
   * Get Invoice Print Format.
   *
   * @return Print Format for printing Invoices
   */
  public int getInvoice_PrintFormat_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_Invoice_PrintFormat_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Invoice Print Format.
   *
   * @param Invoice_PrintFormat_ID Print Format for printing Invoices
   */
  public void setInvoice_PrintFormat_ID(int Invoice_PrintFormat_ID) {
    if (Invoice_PrintFormat_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_Invoice_PrintFormat_ID, null);
    else
      set_Value(
          I_C_BPartner.COLUMNNAME_Invoice_PrintFormat_ID, Integer.valueOf(Invoice_PrintFormat_ID));
  }

  /**
   * Get Invoice Rule.
   *
   * @return Frequency and method of invoicing
   */
  public String getInvoiceRule() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_InvoiceRule);
  }

  /**
   * Set Invoice Rule.
   *
   * @param InvoiceRule Frequency and method of invoicing
   */
  public void setInvoiceRule(String InvoiceRule) {

    set_Value(I_C_BPartner.COLUMNNAME_InvoiceRule, InvoiceRule);
  }

  /**
   * Set 1099 Vendor.
   *
   * @param Is1099Vendor 1099 Vendor
   */
  public void setIs1099Vendor(boolean Is1099Vendor) {
    set_Value(I_C_BPartner.COLUMNNAME_Is1099Vendor, Boolean.valueOf(Is1099Vendor));
  }

  /**
   * Get 1099 Vendor.
   *
   * @return 1099 Vendor
   */
  public boolean is1099Vendor() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_Is1099Vendor);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
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
    set_Value(I_C_BPartner.COLUMNNAME_IsCustomer, Boolean.valueOf(IsCustomer));
  }

  /**
   * Get Customer.
   *
   * @return Indicates if this Business Partner is a Customer
   */
  public boolean isCustomer() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsCustomer);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Discount Printed.
   *
   * @return Print Discount on Invoice and Order
   */
  public boolean isDiscountPrinted() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsDiscountPrinted);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Employee.
   *
   * @return Indicates if this Business Partner is an employee
   */
  public boolean isEmployee() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsEmployee);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Set Is Manufacturer.
   *
   * @param IsManufacturer Indicate role of this Business partner as Manufacturer
   */
  public void setIsManufacturer(boolean IsManufacturer) {
    set_Value(I_C_BPartner.COLUMNNAME_IsManufacturer, Boolean.valueOf(IsManufacturer));
  }

  /**
   * Get Is Manufacturer.
   *
   * @return Indicate role of this Business partner as Manufacturer
   */
  public boolean isManufacturer() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsManufacturer);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get One time transaction.
   *
   * @return One time transaction
   */
  public boolean isOneTime() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsOneTime);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get PO Tax exempt.
   *
   * @return Business partner is exempt from tax on purchases
   */
  public boolean isPOTaxExempt() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsPOTaxExempt);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Prospect.
   *
   * @return Indicates this is a Prospect
   */
  public boolean isProspect() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsProspect);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Sales Representative.
   *
   * @return Indicates if the business partner is a sales representative or company agent
   */
  public boolean isSalesRep() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsSalesRep);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Summary Level.
   *
   * @return This is a summary entity
   */
  public boolean isSummary() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsSummary);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get SO Tax exempt.
   *
   * @return Business partner is exempt from tax on sales
   */
  public boolean isTaxExempt() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsTaxExempt);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Vendor.
   *
   * @return Indicates if this Business Partner is a Vendor
   */
  public boolean isVendor() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_IsVendor);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
  }

  /**
   * Get Logo.
   *
   * @return Logo
   */
  public int getLogo_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_Logo_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Logo.
   *
   * @param Logo_ID Logo
   */
  public void setLogo_ID(int Logo_ID) {
    if (Logo_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_Logo_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
  }

  public I_M_DiscountSchema getM_DiscountSchema() throws RuntimeException {
    return (I_M_DiscountSchema)
        MTable.get(getCtx(), I_M_DiscountSchema.Table_Name)
            .getPO(getM_DiscountSchema_ID(), get_TrxName());
  }

  /**
   * Get Discount Schema.
   *
   * @return Schema to calculate the trade discount percentage
   */
  public int getM_DiscountSchema_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_M_DiscountSchema_ID);
    if (ii == null) return 0;
    return ii;
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

  public I_M_PriceList getM_PriceList() throws RuntimeException {
    return (I_M_PriceList)
        MTable.get(getCtx(), I_M_PriceList.Table_Name).getPO(getM_PriceList_ID(), get_TrxName());
  }

  /**
   * Get Price List.
   *
   * @return Unique identifier of a Price List
   */
  public int getM_PriceList_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_M_PriceList_ID);
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
   * Get NAICS/SIC.
   *
   * @return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
   */
  public String getNAICS() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_NAICS);
  }

  /**
   * Set NAICS/SIC.
   *
   * @param NAICS Standard Industry Code or its successor NAIC -
   *     http://www.osha.gov/oshstats/sicser.html
   */
  public void setNAICS(String NAICS) {
    set_Value(I_C_BPartner.COLUMNNAME_NAICS, NAICS);
  }

  /**
   * Get Name 2.
   *
   * @return Additional Name
   */
  public String getName2() {
    return (String) get_Value(HasName2.Companion.getCOLUMNNAME_Name2());
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
   * Get Employees.
   *
   * @return Number of employees
   */
  public int getNumberEmployees() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_NumberEmployees);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Employees.
   *
   * @param NumberEmployees Number of employees
   */
  public void setNumberEmployees(int NumberEmployees) {
    set_Value(I_C_BPartner.COLUMNNAME_NumberEmployees, Integer.valueOf(NumberEmployees));
  }

  /**
   * Get Payment Rule.
   *
   * @return How you pay the invoice
   */
  public String getPaymentRule() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_PaymentRule);
  }

  /**
   * Set Payment Rule.
   *
   * @param PaymentRule How you pay the invoice
   */
  public void setPaymentRule(String PaymentRule) {

    set_Value(I_C_BPartner.COLUMNNAME_PaymentRule, PaymentRule);
  }

  /**
   * Get Payment Rule.
   *
   * @return Purchase payment option
   */
  public String getPaymentRulePO() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_PaymentRulePO);
  }

  /**
   * Set Payment Rule.
   *
   * @param PaymentRulePO Purchase payment option
   */
  public void setPaymentRulePO(String PaymentRulePO) {

    set_Value(I_C_BPartner.COLUMNNAME_PaymentRulePO, PaymentRulePO);
  }

  public I_M_DiscountSchema getPO_DiscountSchema() throws RuntimeException {
    return (I_M_DiscountSchema)
        MTable.get(getCtx(), I_M_DiscountSchema.Table_Name)
            .getPO(getPO_DiscountSchema_ID(), get_TrxName());
  }

  /**
   * Get PO Discount Schema.
   *
   * @return Schema to calculate the purchase trade discount percentage
   */
  public int getPO_DiscountSchema_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_PO_DiscountSchema_ID);
    if (ii == null) return 0;
    return ii;
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

  public I_C_PaymentTerm getPO_PaymentTerm() throws RuntimeException {
    return (I_C_PaymentTerm)
        MTable.get(getCtx(), I_C_PaymentTerm.Table_Name)
            .getPO(getPO_PaymentTerm_ID(), get_TrxName());
  }

  /**
   * Get PO Payment Term.
   *
   * @return Payment rules for a purchase order
   */
  public int getPO_PaymentTerm_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_PO_PaymentTerm_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set PO Payment Term.
   *
   * @param PO_PaymentTerm_ID Payment rules for a purchase order
   */
  public void setPO_PaymentTerm_ID(int PO_PaymentTerm_ID) {
    if (PO_PaymentTerm_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_PO_PaymentTerm_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_PO_PaymentTerm_ID, Integer.valueOf(PO_PaymentTerm_ID));
  }

  public I_M_PriceList getPO_PriceList() throws RuntimeException {
    return (I_M_PriceList)
        MTable.get(getCtx(), I_M_PriceList.Table_Name).getPO(getPO_PriceList_ID(), get_TrxName());
  }

  /**
   * Get Purchase Pricelist.
   *
   * @return Price List used by this Business Partner
   */
  public int getPO_PriceList_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_PO_PriceList_ID);
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
   * Get Order Reference.
   *
   * @return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
   */
  public String getPOReference() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_POReference);
  }

  /**
   * Set Order Reference.
   *
   * @param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business
   *     Partner
   */
  public void setPOReference(String POReference) {
    set_Value(I_C_BPartner.COLUMNNAME_POReference, POReference);
  }

  /**
   * Get Potential Life Time Value.
   *
   * @return Total Revenue expected
   */
  public BigDecimal getPotentialLifeTimeValue() {
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_PotentialLifeTimeValue);
    if (bd == null) return Env.ZERO;
    return bd;
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
   * Get Rating.
   *
   * @return Classification or Importance
   */
  public String getRating() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_Rating);
  }

  /**
   * Set Rating.
   *
   * @param Rating Classification or Importance
   */
  public void setRating(String Rating) {
    set_Value(I_C_BPartner.COLUMNNAME_Rating, Rating);
  }

  /**
   * Get Reference No.
   *
   * @return Your customer or vendor number at the Business Partner's site
   */
  public String getReferenceNo() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_ReferenceNo);
  }

  /**
   * Set Reference No.
   *
   * @param ReferenceNo Your customer or vendor number at the Business Partner's site
   */
  public void setReferenceNo(String ReferenceNo) {
    set_Value(I_C_BPartner.COLUMNNAME_ReferenceNo, ReferenceNo);
  }

  public I_AD_User getSalesRep() throws RuntimeException {
    return (I_AD_User)
        MTable.get(getCtx(), I_AD_User.Table_Name).getPO(getSalesRep_ID(), get_TrxName());
  }

  /**
   * Get Sales Representative.
   *
   * @return Sales Representative or Company Agent
   */
  public int getSalesRep_ID() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_SalesRep_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Sales Representative.
   *
   * @param SalesRep_ID Sales Representative or Company Agent
   */
  public void setSalesRep_ID(int SalesRep_ID) {
    if (SalesRep_ID < 1) set_Value(I_C_BPartner.COLUMNNAME_SalesRep_ID, null);
    else set_Value(I_C_BPartner.COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
  }

  /**
   * Get Sales Volume in 1.000.
   *
   * @return Total Volume of Sales in Thousands of Currency
   */
  public int getSalesVolume() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_SalesVolume);
    if (ii == null) return 0;
    return ii;
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
   * Get Send EMail.
   *
   * @return Enable sending Document EMail
   */
  public boolean isSendEMail() {
    Object oo = get_Value(I_C_BPartner.COLUMNNAME_SendEMail);
    if (oo != null) {
      if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
      return "Y".equals(oo);
    }
    return false;
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
   * Get Share.
   *
   * @return Share of Customer's business as a percentage
   */
  public int getShareOfCustomer() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_ShareOfCustomer);
    if (ii == null) return 0;
    return ii;
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
   * Get Min Shelf Life %.
   *
   * @return Minimum Shelf Life in percent based on Product Instance Guarantee Date
   */
  public int getShelfLifeMinPct() {
    Integer ii = (Integer) get_Value(I_C_BPartner.COLUMNNAME_ShelfLifeMinPct);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set Min Shelf Life %.
   *
   * @param ShelfLifeMinPct Minimum Shelf Life in percent based on Product Instance Guarantee Date
   */
  public void setShelfLifeMinPct(int ShelfLifeMinPct) {
    set_Value(I_C_BPartner.COLUMNNAME_ShelfLifeMinPct, Integer.valueOf(ShelfLifeMinPct));
  }

  /**
   * Get Credit Limit.
   *
   * @return Total outstanding invoice amounts allowed
   */
  public BigDecimal getSO_CreditLimit() {
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_SO_CreditLimit);
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
    return (String) get_Value(I_C_BPartner.COLUMNNAME_SOCreditStatus);
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
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_SO_CreditUsed);
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
   * Get Order Description.
   *
   * @return Description to be used on orders
   */
  public String getSO_Description() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_SO_Description);
  }

  /**
   * Set Order Description.
   *
   * @param SO_Description Description to be used on orders
   */
  public void setSO_Description(String SO_Description) {
    set_Value(I_C_BPartner.COLUMNNAME_SO_Description, SO_Description);
  }

  /**
   * Get Tax ID.
   *
   * @return Tax Identification
   */
  public String getTaxID() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_TaxID);
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
    BigDecimal bd = (BigDecimal) get_Value(I_C_BPartner.COLUMNNAME_TotalOpenBalance);
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

  /**
   * Get URL.
   *
   * @return Full URL address - e.g. http://www.idempiere.org
   */
  public String getURL() {
    return (String) get_Value(I_C_BPartner.COLUMNNAME_URL);
  }

  /**
   * Set URL.
   *
   * @param URL Full URL address - e.g. http://www.idempiere.org
   */
  public void setURL(String URL) {
    set_Value(I_C_BPartner.COLUMNNAME_URL, URL);
  }

  @Override
  public int getTableId() {
    return I_C_BPartner.Table_ID;
  }
}
