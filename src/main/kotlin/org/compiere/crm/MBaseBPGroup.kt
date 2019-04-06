package org.compiere.crm

import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.model.I_C_BP_Group
import org.idempiere.common.util.AdempiereSystemError
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment

private val log = KotlinLogging.logger {}

private fun doGetDefaultBusinessPartnerGroup(clientId: Int): I_C_BP_Group {
    val sql = """
        SELECT * FROM C_BP_Group g
        WHERE IsDefault='Y' AND AD_Client_ID=?
         ORDER BY IsActive DESC
    """.trimIndent()
    val loadQuery = queryOf(sql, clientId).map { row -> MBPGroup(row) }.asSingle
    val loaded = DB.current.run(loadQuery)
    if (loaded == null || loaded.id == 0) {
        throw AdempiereSystemError("No Default BP Group for AD_Client_ID=$clientId")
    }
    return loaded
} // 	get

private val businessPartnerGroupFactory = factory { doGetDefaultBusinessPartnerGroup(it) }

fun getDefaultBusinessPartnerGroup(): I_C_BP_Group = Environment.current.clientId loadUsing businessPartnerGroupFactory

fun getOfBPartner(bpartnerId: Int): MBPGroup? {
    val sql = """
        SELECT * FROM C_BP_Group g
        WHERE IsDefault='Y' AND AD_Client_ID=?
         ORDER BY IsActive DESC
    """.trimIndent()
    val loadQuery = queryOf(sql, bpartnerId).map { row -> MBPGroup(row) }.asSingle
    return DB.current.run(loadQuery)
}