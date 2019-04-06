package org.compiere.crm

import kotliquery.Row
import software.hsharp.core.util.DB
import software.hsharp.core.util.asResource
import software.hsharp.core.util.queryOf

fun getBPLocation(bPartnerLocationId: Int): MLocation? {
    return "/sql/getBPLocation.sql".asResource { sql ->
        val loadQuery =
            queryOf(sql, listOf(bPartnerLocationId))
                .map { MLocation(it) }
                .asSingle
        DB.current.run(loadQuery)
    }
}

open class MBaseLocation : X_C_Location {
    constructor(Id: Int) : super(Id)
    constructor(rs: Row) : super(rs)
}