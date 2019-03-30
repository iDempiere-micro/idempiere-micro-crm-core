package org.compiere.crm

import kotliquery.Row
import org.compiere.model.I_AD_User
import org.compiere.model.I_C_BPartner_Location
import org.idempiere.common.util.Env
import software.hsharp.core.orm.I_ZERO
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf
import software.hsharp.models.CrmCategory
import software.hsharp.models.IHasCategories
import java.math.BigDecimal
import java.util.Properties

open class MBaseBPartner : X_C_BPartner, IHasCategories {
    constructor(ctx: Properties, row: Row) : super(ctx, row)
    constructor(ctx: Properties, id: Int) : super(ctx, id)

    /** Users  */
    private val m_contacts: MutableList<MUser> = mutableListOf()
    /** Addressed  */
    private val m_locations: MutableList<I_C_BPartner_Location> = mutableListOf()
    /** BP Group  */
    private var m_group: MBPGroup? = null

    /**
     * Load Default BPartner
     *
     * @param AD_Client_ID client
     * @return true if loaded
     */
    protected fun initTemplate(AD_Client_ID: Int): Boolean {
        if (AD_Client_ID == 0) throw IllegalArgumentException("Client_ID=0")

        val success = true
        val sql =
            "SELECT * FROM C_BPartner " + "WHERE C_BPartner_ID IN (SELECT C_BPartnerCashTrx_ID FROM AD_ClientInfo WHERE AD_Client_ID=?)"
        val loadQuery = queryOf(sql, listOf(AD_Client_ID)).map { row -> load(row) }.asSingle
        val loaded = DB.current.run(loadQuery)

        if (loaded == null) {
            load(0)
        }

        setStandardDefaults()
        // 	Reset
        setValueNoCheck("C_BPartner_ID", I_ZERO)
        searchKey = ""
        name = ""
        setName2(null)
        setValueNoCheck("C_BPartner_UU", "")
        return success
    } // 	getTemplate

    /**
     * Get All Contacts
     *
     * @param reload if true users will be requeried
     * @return contacts
     */
    fun getContacts(reload: Boolean): List<I_AD_User> {
        if (reload || m_contacts.size == 0) {
            //
            val sql = "SELECT * FROM AD_User WHERE C_BPartner_ID=? ORDER BY AD_User_ID"
            val loadQuery = queryOf(sql, listOf(businessPartnerId)).map { MUser(ctx, it) }.asList
            val result = DB.current.run(loadQuery)

            m_contacts.clear()
            m_contacts.addAll(result)
        }
        return m_contacts
    } // 	getContacts

    /**
     * Get All Locations (only active)
     *
     * @param reload if true locations will be requeried
     * @return locations
     */
    fun getLocations(reload: Boolean): List<I_C_BPartner_Location> {
        if (reload || m_locations.size == 0) {

            val sql =
                "SELECT * FROM C_BPartner_Location WHERE C_BPartner_ID=? AND IsActive='Y'" + " ORDER BY C_BPartner_Location_ID"
            val loadQuery = queryOf(sql, listOf(businessPartnerId)).map { row -> MBPartnerLocation(ctx, row) }.asList
            val locations = DB.current.run(loadQuery)

            m_locations.clear()
            m_locations.addAll(locations)
        }
        return m_locations
    } // 	getLocations

    /**
     * Get BP Group
     *
     * @return group
     */
    fun getBPGroup(): MBPGroup? {
        if (m_group == null) {
            if (bpGroupId == 0)
                m_group = MBPGroup.getDefault(ctx)
            else
                m_group = MBPGroup.get(ctx, bpGroupId)
        }
        return m_group
    } // 	getBPGroup

    /**
     * Get BP Group
     *
     * @param group group
     */
    fun setBPGroup(group: MBPGroup?) {
        m_group = group
        if (group == null) return
        bpGroupId = group.bpGroupId
        if (group.dunningId != 0) setDunningId(group.dunningId)
        if (group.priceListId != 0) priceListId = group.priceListId
        if (group.purchaseOrderPriceListId != 0) purchaseOrderPriceListId = group.purchaseOrderPriceListId
        if (group.discountSchemaId != 0)
            setDiscountSchemaId(group.discountSchemaId)
        if (group.poDiscountSchemaId != 0)
            setPODiscountSchemaId(group.poDiscountSchemaId)
    } // 	setBPGroup

    /**
     * Get Credit Watch Ratio
     *
     * @return BP Group ratio or 0.9
     */
    fun getCreditWatchRatio(): BigDecimal? {
        return getBPGroup()?.getCreditWatchRatio()
    } // 	getCreditWatchRatio

    /** Set Credit Status  */
    fun setSOCreditStatus() {
        val creditLimit = salesOrderCreditLimit
        // 	Nothing to do
        if (X_C_BPartner.SOCREDITSTATUS_NoCreditCheck == soCreditStatus ||
            X_C_BPartner.SOCREDITSTATUS_CreditStop == soCreditStatus ||
            Env.ZERO.compareTo(creditLimit) == 0
        )
            return

        // 	Above Credit Limit
        if (creditLimit < totalOpenBalance)
            soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditHold
        else {
            // 	Above Watch Limit
            val watchAmt = creditLimit.multiply(getCreditWatchRatio())
            if (watchAmt < totalOpenBalance)
                soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditWatch
            else
            // 	is OK
                soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditOK
        }
    } // 	setSOCreditStatus

    fun setTotalOpenBalance() {
        val sql = """
            SELECT
            COALESCE((SELECT SUM(currencyBase(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID,i.DateInvoiced, i.AD_Client_ID,i.AD_Org_ID)) FROM C_Invoice_v i
            WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y' AND i.IsPaid='N' AND i.DocStatus IN ('CO','CL')),0),
            COALESCE((SELECT SUM(currencyBase(invoiceOpen(i.C_Invoice_ID,i.C_InvoicePaySchedule_ID),i.C_Currency_ID,i.DateInvoiced, i.AD_Client_ID,i.AD_Org_ID)*i.MultiplierAP) FROM C_Invoice_v i
            WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsPaid='N' AND i.DocStatus IN ('CO','CL')),0) -
            COALESCE((SELECT SUM(currencyBase(Paymentavailable(p.C_Payment_ID),p.C_Currency_ID,p.DateTrx,p.AD_Client_ID,p.AD_Org_ID)) FROM C_Payment_v p
            WHERE p.C_BPartner_ID=bp.C_BPartner_ID AND p.IsAllocated='N'
            AND p.C_Charge_ID IS NULL AND p.DocStatus IN ('CO','CL')),0)
            FROM C_BPartner bp
            WHERE C_BPartner_ID=?
        """.trimIndent()
        val loadQuery =
            queryOf(sql, listOf(businessPartnerId)).map { row -> Pair(row.bigDecimal(1), row.bigDecimal(2)) }.asSingle
        val result = DB.current.run(loadQuery) ?: return
        val (SO_CreditUsed, TotalOpenBalance) = result
        super.setSalesOrderCreditUsed(SO_CreditUsed)
        super.setTotalOpenBalance(TotalOpenBalance)
        setSOCreditStatus()
    }

    fun setActualLifeTimeValue() {
        val sql = """
            SELECT
            COALESCE ((SELECT SUM(currencyBase(i.GrandTotal,i.C_Currency_ID,i.DateInvoiced, i.AD_Client_ID,i.AD_Org_ID)) FROM C_Invoice_v i
            WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y' AND i.DocStatus IN ('CO','CL')),0)
            FROM C_BPartner bp
            WHERE C_BPartner_ID=?
        """.trimIndent()
        val loadQuery = queryOf(sql, listOf(businessPartnerId)).map { row -> row.bigDecimalOrNull(1) }.asSingle
        val result = DB.current.run(loadQuery)
        if (result != null) super.setActualLifeTimeValue(result)
    }

    override val categories: List<CrmCategory>
        get() {
            return "/sql/getBusinessPartnerCategories.sql".asResource { sql ->
                val loadQuery = queryOf(sql, listOf(businessPartnerId)).map { row -> MCrmCategory(ctx, row) }.asList
                DB.current.run(loadQuery)
            }
        }

}