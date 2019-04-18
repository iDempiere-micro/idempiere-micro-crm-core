package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BP_Group;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.orm.MTree_Base;
import org.compiere.orm.Query;
import org.idempiere.common.util.Env;
import software.hsharp.core.util.DBKt;
import software.hsharp.core.util.Environment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

public class MBPartner extends MBaseBPartner implements I_C_BPartner {
    /**
     *
     */
    private static final long serialVersionUID = -803727877324075871L;
    /**
     * Prim Address
     */
    private Integer m_primaryC_BPartner_Location_ID = null;

    /**
     * ************************************************************************ Constructor for new
     * BPartner from Template
     */
    public MBPartner() {
        this(-1);
    } //	MBPartner

    /**
     * Default Constructor
     */
    public MBPartner(Row row) {
        super(row);
    } //	MBPartner


    /**
     * Default Constructor
     *
     * @param C_BPartner_ID partner or 0 or -1 (load from template)
     */
    public MBPartner(int C_BPartner_ID) {
        super(C_BPartner_ID);
        //
        if (C_BPartner_ID == -1) {
            initTemplate(Environment.Companion.getCurrent().getClientId());
            C_BPartner_ID = 0;
        }
        if (C_BPartner_ID == 0) {
            //
            setIsCustomer(true);
            setIsProspect(true);
            //
            setSendEMail(false);
            setIsOneTime(false);
            setIsVendor(false);
            setIsSummary(false);
            setIsEmployee(false);
            setIsSalesRep(false);
            setIsTaxExempt(false);
            setIsPOTaxExempt(false);
            setIsDiscountPrinted(false);
            //
            setSalesOrderCreditLimit(Env.ZERO);
            setSalesOrderCreditUsed(Env.ZERO);
            setTotalOpenBalance(Env.ZERO);
            setSOCreditStatus(SOCREDITSTATUS_NoCreditCheck);
            //
            setFirstSale(null);
            setActualLifeTimeValue(Env.ZERO);
            setPotentialLifeTimeValue(Env.ZERO);
            setAcqusitionCost(Env.ZERO);
            setShareOfCustomer(0);
            setSalesVolume(0);
        }
        if (log.isLoggable(Level.FINE)) log.fine(toString());
    } //	MBPartner

    /**
     * Import Constructor
     *
     * @param impBP import
     */
    public MBPartner(X_I_BPartner impBP) {
        this(0);
        setClientOrg(impBP);
        setUpdatedBy(impBP.getUpdatedBy());
        //
        String value = impBP.getValue();
        if (value == null || value.length() == 0) value = impBP.getEMail();
        if (value == null || value.length() == 0) value = impBP.getContactName();
        setSearchKey(value);
        String name = impBP.getName();
        if (name == null || name.length() == 0) name = impBP.getContactName();
        if (name == null || name.length() == 0) name = impBP.getEMail();
        setName(name);
        setName2(impBP.getName2());
        setDescription(impBP.getDescription());
        //	setHelp(impBP.getHelp());
        setDUNS(impBP.getDUNS());
        setTaxID(impBP.getTaxID());
        setNAICS(impBP.getNAICS());
        setBPGroupId(impBP.getBPGroupId());
    } //	MBPartner

    /**
     * Get Empty Template Business Partner
     *
     * @param AD_Client_ID client
     * @return Template Business Partner or null
     */
    public static I_C_BPartner getTemplate(int AD_Client_ID) {
        I_C_BPartner template = getBPartnerCashTrx(AD_Client_ID);
        if (template == null) template = new MBPartner(0);
        //	Reset
        template.setValueNoCheck("C_BPartner_ID", 0);
        template.setValueNoCheck("C_BPartner_UU", null);
        template.setSearchKey("");
        template.setName("");
        template.setName2(null);
        template.setDUNS("");
        template.setFirstSale(null);
        //
        template.setSalesOrderCreditLimit(Env.ZERO);
        template.setSalesOrderCreditUsed(Env.ZERO);
        template.setTotalOpenBalance(Env.ZERO);
        //	s_template.setRating(null);
        //
        template.setActualLifeTimeValue(Env.ZERO);
        template.setPotentialLifeTimeValue(Env.ZERO);
        template.setAcqusitionCost(Env.ZERO);
        template.setShareOfCustomer(0);
        template.setSalesVolume(0);
        // Reset Created, Updated to current system time ( teo_sarca )
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        template.setValueNoCheck("Created", ts);
        template.setValueNoCheck("Updated", ts);
        template.setValueNoCheck("CreatedBy", Env.getUserId());
        template.setValueNoCheck("UpdatedBy", Env.getUserId());
        return template;
    } //	getTemplate

    /**
     * Get Cash Trx Business Partner
     *
     * @param AD_Client_ID client
     * @return Cash Trx Business Partner or null
     */
    public static I_C_BPartner getBPartnerCashTrx(int AD_Client_ID) {
        return MClientInfoKt.getClientInfo(AD_Client_ID).getBPartnerCashTrx();
    } //	getBPartnerCashTrx

    /**
     * Get BPartner with Value in a transaction
     *
     * @param Value value
     * @return BPartner or null
     */
    public static I_C_BPartner get(String Value) {
        if (Value == null || Value.length() == 0) return null;
        final String whereClause = "Value=? AND AD_Client_ID=?";
        return new Query<I_C_BPartner>(I_C_BPartner.Table_Name, whereClause)
                .setParameters(Value, Env.getClientId())
                .firstOnly();
    } //	get

    /**
     * Get BPartner with Value in a transaction
     *
     * @return BPartner or null
     */
    public static I_C_BPartner get(int C_BPartner_ID) {
        final String whereClause = "C_BPartner_ID=? AND AD_Client_ID=?";
        return new Query<I_C_BPartner>(I_C_BPartner.Table_Name, whereClause)
                .setParameters(C_BPartner_ID, Env.getClientId())
                .firstOnly();
    } //	get

    /**
     * Get Not Invoiced Shipment Value
     *
     * @param C_BPartner_ID partner
     * @return value in accounting currency
     */
    public static BigDecimal getNotInvoicedAmt(int C_BPartner_ID) {
        String sql =
                "SELECT COALESCE(SUM(COALESCE("
                        + "currencyBase((ol.QtyDelivered-ol.QtyInvoiced)*ol.PriceActual,o.C_Currency_ID,o.DateOrdered, o.AD_Client_ID,o.AD_Org_ID) ,0)),0) "
                        + "FROM C_OrderLine ol"
                        + " INNER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID) "
                        + "WHERE o.IsSOTrx='Y' AND Bill_BPartner_ID=?";
        return DBKt.getSQLValueBD(sql, C_BPartner_ID);
    } //	getNotInvoicedAmt

    public List<I_AD_User> getContacts() {
        return getContacts(true);
    }

    @Override
    public List<I_C_BPartner_Location> getLocations() {
        return getLocations(true);
    }

    /**
     * ************************************************************************ String Representation
     *
     * @return info
     */
    public String toString() {
        return "MBPartner[ID=" +
                getId() +
                ",Value=" +
                getSearchKey() +
                ",Name=" +
                getName() +
                ",Open=" +
                getTotalOpenBalance() +
                "]";
    } //	toString

    /**
     * Get Linked Organization. (is Button) The Business Partner is another Organization for explicit
     * Inter-Org transactions
     *
     * @return AD_Org_ID if BP
     */
    public int getLinkedOrganizationId() {
        String org = super.getLinkedOrganizationIdAsString();
        if (org == null) return 0;
        int AD_OrgBP_ID = 0;
        try {
            AD_OrgBP_ID = Integer.parseInt(org);
        } catch (Exception ex) {
            log.log(Level.SEVERE, org, ex);
        }
        return AD_OrgBP_ID;
    } //	getAD_OrgBP_ID_Int

    /**
     * Set Linked Organization. (is Button)
     *
     * @param AD_OrgBP_ID
     */
    public void setLinkedOrganizationId(int AD_OrgBP_ID) {
        if (AD_OrgBP_ID == 0) super.setLinkedOrganizationId(null);
        else super.setValue("AD_OrgBP_ID", AD_OrgBP_ID);
    } //	setAD_OrgBP_ID

    /**
     * Get Primary C_BPartner_Location_ID
     *
     * @return C_BPartner_Location_ID
     */
    public int getPrimaryBPartnerLocationId() {
        if (m_primaryC_BPartner_Location_ID == null) {
            List<I_C_BPartner_Location> locs = getLocations(false);
            for (int i = 0; m_primaryC_BPartner_Location_ID == null && i < locs.size(); i++) {
                if (locs.get(i).getIsBillTo()) {
                    setPrimaryBPartnerLocationId(locs.get(i).getBusinessPartnerLocationId());
                    break;
                }
            }
            //	get first
            if (m_primaryC_BPartner_Location_ID == null && locs.size() > 0)
                setPrimaryBPartnerLocationId(locs.get(0).getBusinessPartnerLocationId());
        }
        if (m_primaryC_BPartner_Location_ID == null) return 0;
        return m_primaryC_BPartner_Location_ID;
    } //	getPrimaryC_BPartner_Location_ID

    /**
     * Set Primary C_BPartner_Location_ID
     *
     * @param C_BPartner_Location_ID id
     */
    public void setPrimaryBPartnerLocationId(int C_BPartner_Location_ID) {
        m_primaryC_BPartner_Location_ID = C_BPartner_Location_ID;
    } //	setPrimaryC_BPartner_Location_ID

    /**
     * Get SO CreditStatus with additional amount
     *
     * @param additionalAmt additional amount in Accounting Currency
     * @return sinulated credit status
     */
    public String getSOCreditStatus(BigDecimal additionalAmt) {
        if (additionalAmt == null || additionalAmt.signum() == 0) return getSOCreditStatus();
        //
        BigDecimal creditLimit = getSalesOrderCreditLimit();
        //	Nothing to do
        if (SOCREDITSTATUS_NoCreditCheck.equals(getSOCreditStatus())
                || SOCREDITSTATUS_CreditStop.equals(getSOCreditStatus())
                || Env.ZERO.compareTo(creditLimit) == 0) return getSOCreditStatus();

        //	Above (reduced) Credit Limit
        creditLimit = creditLimit.subtract(additionalAmt);
        if (creditLimit.compareTo(getTotalOpenBalance()) < 0) return SOCREDITSTATUS_CreditHold;

        //	Above Watch Limit
        BigDecimal watchAmt = creditLimit.multiply(getCreditWatchRatio());
        if (watchAmt.compareTo(getTotalOpenBalance()) < 0) return SOCREDITSTATUS_CreditWatch;

        //	is OK
        return SOCREDITSTATUS_CreditOK;
    } //	getSOCreditStatus

    /**
     * Get PriceList
     *
     * @return price List
     */
    public int getPriceListId() {
        int ii = super.getPriceListId();
        if (ii == 0) ii = getBPGroup().getPriceListId();
        return ii;
    } //	getPriceListId

    /**
     * Get PO PriceList
     *
     * @return price list
     */
    public int getPurchaseOrderPriceListId() {
        int ii = super.getPurchaseOrderPriceListId();
        if (ii == 0) ii = getBPGroup().getPurchaseOrderPriceListId();
        return ii;
    } //

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    protected boolean beforeSave(boolean newRecord) {
        if (newRecord || isValueChanged("C_BP_Group_ID")) {
            I_C_BP_Group grp = getBPGroup();
            setBPGroup(grp); // 	setDefaults
        }
        return true;
    } //	beforeSave

    /**
     * ************************************************************************ After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;
        if (newRecord) {
            //	Trees
            insert_Tree(MTree_Base.TREETYPE_BPartner);
            //	Accounting
            StringBuilder msgacc = new StringBuilder("p.C_BP_Group_ID=").append(getBPGroupId());
            insert_Accounting("C_BP_Customer_Acct", "C_BP_Group_Acct", msgacc.toString());
            insert_Accounting("C_BP_Vendor_Acct", "C_BP_Group_Acct", msgacc.toString());
        }
        if (newRecord || isValueChanged(COLUMNNAME_Value)) update_Tree(MTree_Base.TREETYPE_BPartner);

        //	TODO: Value/Name change
        // if (!newRecord && (isValueChanged("Value") || isValueChanged("Name"))) {
        // TODO: StringBuilder msgacc = new StringBuilder("C_BPartner_ID=").append(getBusinessPartnerId());
        // TODO: MAccount.updateValueDescription(msgacc.toString(), get_TrxName());
        // }
        return success;
    } //	afterSave

    /**
     * After Delete
     *
     * @param success
     * @return deleted
     */
    protected boolean afterDelete(boolean success) {
        if (success) delete_Tree(MTree_Base.TREETYPE_BPartner);
        return success;
    } //	afterDelete

    @Override
    public boolean save() {
        return super.save();
    }

    @Override
    public void saveEx() {
        super.saveEx();
    }

    @Override
    public int getTableId() {
        return I_C_BPartner.Table_ID;
    }

    /**
     * Get Sales Representative.
     *
     * @return Sales Representative or Company Agent
     */
    public int getSalesRepresentativeId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_SalesRep_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Sales Representative.
     *
     * @param SalesRep_ID Sales Representative or Company Agent
     */
    public void setSalesRepresentativeId(int SalesRep_ID) {
        if (SalesRep_ID < 1)
            setValue(COLUMNNAME_SalesRep_ID, null);
        else
            setValue(COLUMNNAME_SalesRep_ID, SalesRep_ID);
    }


    /**
     * Get Payment Term.
     *
     * @return The terms of Payment (timing, discount)
     */
    public int getPaymentTermId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_C_PaymentTerm_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Delivery Rule.
     *
     * @return Defines the timing of Delivery
     */
    public String getDeliveryRule() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_DeliveryRule);
    }

    /**
     * Get Delivery Via.
     *
     * @return How the order will be delivered
     */
    public String getDeliveryViaRule() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_DeliveryViaRule);
    }

    @Override
    public String getDUNS() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_DUNS);
    }

    /**
     * Get Invoice Rule.
     *
     * @return Frequency and method of invoicing
     */
    public String getInvoiceRule() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_InvoiceRule);
    }

    /**
     * Get Payment Rule.
     *
     * @return How you pay the invoice
     */
    public String getPaymentRule() {
        return (String) getValue(I_C_BPartner.COLUMNNAME_PaymentRule);
    }

    /**
     * Get PO Payment Term.
     *
     * @return Payment rules for a purchase order
     */
    public int getPurchaseOrderPaymentTermId() {
        Integer ii = (Integer) getValue(I_C_BPartner.COLUMNNAME_PO_PaymentTerm_ID);
        if (ii == null) return 0;
        return ii;
    }

} //	MBPartner
