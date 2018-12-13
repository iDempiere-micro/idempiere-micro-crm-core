package org.compiere.crm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_C_BPartner_Location
import org.idempiere.common.util.Env
import software.hsharp.core.orm.I_ZERO
import software.hsharp.core.util.DB
import java.math.BigDecimal
import java.sql.ResultSet
import java.util.*

open class MBaseBPartner : X_C_BPartner {
    constructor(ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor(ctx: Properties, row: Row) : super(ctx, row)
    constructor(ctx: Properties, id: Int, trxName: String?) : super(ctx, id, trxName)

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
        val loadQuery = queryOf(sql, AD_Client_ID).map { row -> load(row) }.asSingle
        val loaded = DB.current.run(loadQuery)

        if (loaded == null) {
            load(0, null)
        }

        setStandardDefaults()
        // 	Reset
        set_ValueNoCheck("C_BPartner_ID", I_ZERO)
        setValue("")
        setName("")
        name2 = null
        set_ValueNoCheck("C_BPartner_UU", "")
        return success
    } // 	getTemplate

    /**
     * Get All Contacts
     *
     * @param reload if true users will be requeried
     * @return contacts
     */
    fun getContacts(reload: Boolean): Array<MUser> {
        if (reload || m_contacts.size == 0) {
            //
            val sql = "SELECT * FROM AD_User WHERE C_BPartner_ID=? ORDER BY AD_User_ID"
            val loadQuery = queryOf(sql, c_BPartner_ID).map { MUser(ctx, it) }.asList
            val result = DB.current.run(loadQuery)

            m_contacts.clear()
            m_contacts.addAll(result)
        }
        return m_contacts.toTypedArray()
    } // 	getContacts

    /**
     * Get All Locations (only active)
     *
     * @param reload if true locations will be requeried
     * @return locations
     */
    fun getLocations(reload: Boolean): Array<I_C_BPartner_Location> {
        if (reload || m_locations.size == 0) {

            val sql =
                "SELECT * FROM C_BPartner_Location WHERE C_BPartner_ID=? AND IsActive='Y'" + " ORDER BY C_BPartner_Location_ID"
            val loadQuery = queryOf(sql, c_BPartner_ID).map { row -> MBPartnerLocation(ctx, row) }.asList
            val locations = DB.current.run(loadQuery)

            m_locations.clear()
            m_locations.addAll(locations)
        }
        return m_locations.toTypedArray()
    } // 	getLocations

    /**
     * Get BP Group
     *
     * @return group
     */
    fun getBPGroup(): MBPGroup? {
        if (m_group == null) {
            if (c_BP_Group_ID == 0)
                m_group = MBPGroup.getDefault(ctx)
            else
                m_group = MBPGroup.get(ctx, c_BP_Group_ID, _TrxName)
        }
        return m_group
    } //	getBPGroup

    /**
     * Get BP Group
     *
     * @param group group
     */
    fun setBPGroup(group: MBPGroup?) {
        m_group = group
        if (group == null) return
        c_BP_Group_ID = group.getC_BP_Group_ID()
        if (group.c_Dunning_ID != 0) c_Dunning_ID = group.c_Dunning_ID
        if (group.m_PriceList_ID != 0) m_PriceList_ID = group.m_PriceList_ID
        if (group.pO_PriceList_ID != 0) pO_PriceList_ID = group.pO_PriceList_ID
        if (group.m_DiscountSchema_ID != 0)
            m_DiscountSchema_ID = group.m_DiscountSchema_ID
        if (group.getPO_DiscountSchema_ID() != 0)
            pO_DiscountSchema_ID = group.pO_DiscountSchema_ID
    } //	setBPGroup


    /**
     * Get Credit Watch Ratio
     *
     * @return BP Group ratio or 0.9
     */
    fun getCreditWatchRatio(): BigDecimal? {
        return getBPGroup()?.getCreditWatchRatio()
    } //	getCreditWatchRatio

    /** Set Credit Status  */
    fun setSOCreditStatus() {
        val creditLimit = sO_CreditLimit
        //	Nothing to do
        if (X_C_BPartner.SOCREDITSTATUS_NoCreditCheck == soCreditStatus
            || X_C_BPartner.SOCREDITSTATUS_CreditStop == soCreditStatus
            || Env.ZERO.compareTo(creditLimit) == 0
        )
            return

        //	Above Credit Limit
        if (creditLimit < totalOpenBalance)
            soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditHold
        else {
            //	Above Watch Limit
            val watchAmt = creditLimit.multiply(getCreditWatchRatio())
            if (watchAmt < totalOpenBalance)
                soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditWatch
            else
            //	is OK
                soCreditStatus = X_C_BPartner.SOCREDITSTATUS_CreditOK
        }
    } //	setSOCreditStatus

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
        val loadQuery = queryOf(sql, c_BPartner_ID).map { row -> Pair(row.bigDecimal(1), row.bigDecimal(2)) }.asSingle
        val result = DB.current.run(loadQuery)
        if (result == null) return
        val (SO_CreditUsed, TotalOpenBalance) = result
        super.setSO_CreditUsed(SO_CreditUsed)
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
        val loadQuery = queryOf(sql, c_BPartner_ID).map { row -> row.bigDecimalOrNull(1)}.asSingle
        val result = DB.current.run(loadQuery)
        if (result != null) super.setActualLifeTimeValue(result)
    }
}