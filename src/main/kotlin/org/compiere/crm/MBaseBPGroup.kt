package org.compiere.crm

import kotliquery.queryOf
import mu.KotlinLogging
import org.compiere.model.I_C_BP_Group
import org.idempiere.common.util.CCache
import org.idempiere.common.util.Env
import software.hsharp.core.util.DB
import java.util.Properties

/** Default Cache  */
private val cacheDefaultBPGroups = CCache<Int, MBPGroup>(
    I_C_BP_Group.Table_Name, MBPGroup::class.java.name + ".Default", 5
)
private val log = KotlinLogging.logger {}

fun getDefault(ctx: Properties): MBPGroup? {
    val clientId = Env.getClientId(ctx)
    val retValue = cacheDefaultBPGroups[clientId]
    if (retValue != null) return retValue

    val sql = """
        SELECT * FROM C_BP_Group g
        WHERE IsDefault='Y' AND AD_Client_ID=?
         ORDER BY IsActive DESC
    """.trimIndent()
    val loadQuery = queryOf(sql, clientId).map { row -> MBPGroup(ctx, row) }.asSingle
    val loaded = DB.current.run(loadQuery)
    if (loaded == null || loaded.id == 0) {
        log.warn("No Default BP Group for AD_Client_ID=$clientId")
        return null
    }
    cacheDefaultBPGroups[clientId] = loaded
    return loaded
} // 	get

fun getOfBPartner(ctx: Properties, bpartnerId: Int): MBPGroup? {
    val sql = """
        SELECT * FROM C_BP_Group g
        WHERE IsDefault='Y' AND AD_Client_ID=?
         ORDER BY IsActive DESC
    """.trimIndent()
    val loadQuery = queryOf(sql, bpartnerId).map { row -> MBPGroup(ctx, row) }.asSingle
    return DB.current.run(loadQuery)
}