package org.compiere.crm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.model.I_C_Region
import org.idempiere.common.util.CCache
import software.hsharp.core.util.DB
import java.sql.ResultSet
import java.util.*

internal val regionsCache = CCache<String, MRegion>(I_C_Region.Table_Name, 100)
internal var defaultRegion: MRegion? = null

internal fun loadAllRegions(ctx: Properties) {
    regionsCache.clear()
    val sql = "SELECT * FROM C_Region WHERE IsActive='Y'"
    val loadQuery = queryOf(sql).map { row -> MRegion(ctx, row) }.asList
    val regions = DB.current.run(loadQuery)
    regions.forEach { regionsCache[it.c_Region_ID.toString()] = it }
    defaultRegion = regions.firstOrNull { it.isDefault }
} // 	loadAllRegions

open class MBaseRegion : X_C_Region {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor(ctx: Properties, rs: Row) : super(ctx, rs)
}