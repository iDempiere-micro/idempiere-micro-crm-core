package org.compiere.crm

import kotliquery.queryOf
import org.compiere.model.I_C_BPartner_Location
import software.hsharp.core.util.DB
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.ArrayList
import java.util.Properties
import java.util.logging.Level

open class MBaseBPartner : X_C_BPartner {
    constructor(ctx: Properties, rs: ResultSet, trxName: String) : super(ctx, rs, trxName)
    constructor(ctx: Properties, id: Int, trxName: String) : super(ctx, id, trxName)

    /** Users  */
    private val m_contacts: MutableList<MUser> = mutableListOf()
    /** Addressed  */
    private val m_locations: MutableList<I_C_BPartner_Location> = mutableListOf()

    /**
     * Load Default BPartner
     *
     * @param AD_Client_ID client
     * @return true if loaded
     */
    protected fun initTemplate(AD_Client_ID: Int): Boolean {
        if (AD_Client_ID == 0) throw IllegalArgumentException("Client_ID=0")

        var success = true
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
    } //	getLocations
}