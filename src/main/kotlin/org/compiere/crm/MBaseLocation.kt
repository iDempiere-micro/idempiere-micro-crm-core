package org.compiere.crm

import kotliquery.Row
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf
import java.util.Properties

fun getBPLocation(ctx: Properties, bPartnerLocationId: Int): MLocation? {
    return "/sql/getBPLocation.sql".asResource { sql ->
        val loadQuery =
            queryOf(sql, listOf(bPartnerLocationId))
                .map { MLocation(ctx, it) }
                .asSingle
        DB.current.run(loadQuery)
    }
}

open class MBaseLocation : X_C_Location {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor(ctx: Properties, rs: Row) : super(ctx, rs)
}