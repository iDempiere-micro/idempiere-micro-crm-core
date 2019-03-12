package org.compiere.crm

import org.compiere.model.CrmCategory
import org.compiere.orm.BasePONameValue
import java.sql.ResultSet
import java.util.Properties

class MCrmCategory : BasePONameValue, CrmCategory {
    override val tableId: Int
        get() = Table_ID

    companion object {
        const val Table_ID = 1000000
        const val Table_Name = "Crm_Category"
    }

    override fun getAccessLevel(): Int {
        return 3 // AccessLevel = 3 - Client - Org
    }

    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, rs: ResultSet, a: String?) : super(ctx, rs, a)
}
