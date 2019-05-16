package org.compiere.crm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_C_BPartner
import org.compiere.orm.MClient

import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.orm.getTable
import software.hsharp.core.util.DB

private fun doLoadClientInfo(clientId: Int): MClientInfo {
    val sql = "SELECT * FROM AD_ClientInfo WHERE AD_Client_ID=?"
    val loadQuery = queryOf(sql, clientId).map { row -> MClientInfo(row) }.asSingle
    return DB.current.run(loadQuery) ?: throw AdempiereSystemError("Client info not found for client $clientId")
}

private val clientInfoFactory = factory { doLoadClientInfo(it) }

/**
 * Get Client Info
 *
 * @param AD_Client_ID id
 * @return Client Info
 */
fun getClientInfo(id: Int) = id loadUsing clientInfoFactory

class MClientInfo : org.compiere.orm.MClientInfo {

    val bPartnerCashTrx: I_C_BPartner?
        get() = getTable(I_C_BPartner.Table_Name).getPO(bPartnerCashTrxId)

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row)

    constructor(
        client: MClient,
        AD_Tree_Org_ID: Int,
        AD_Tree_BPartner_ID: Int,
        AD_Tree_Project_ID: Int,
        AD_Tree_SalesRegion_ID: Int,
        AD_Tree_Product_ID: Int,
        AD_Tree_Campaign_ID: Int,
        AD_Tree_Activity_ID: Int
    ) : super(
            client,
            AD_Tree_Org_ID,
            AD_Tree_BPartner_ID,
            AD_Tree_Project_ID,
            AD_Tree_SalesRegion_ID,
            AD_Tree_Product_ID,
            AD_Tree_Campaign_ID,
            AD_Tree_Activity_ID
    )
    /**
     * Overwrite Save
     *
     * @return true if saved
     * @overwrite
     */
    override fun save(): Boolean {
        if (orgId != 0) setOrgId(0)
        return if (createNew) super.save() else saveUpdate()
    } // 	save

    companion object {
        private val serialVersionUID = 4861006368856890116L
    }
} // 	MClientInfo
