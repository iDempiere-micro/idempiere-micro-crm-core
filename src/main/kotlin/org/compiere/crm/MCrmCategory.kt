package org.compiere.crm

import org.compiere.orm.BasePONameValue
import org.idempiere.orm.I_Persistent
import org.idempiere.orm.POInfo
import java.sql.ResultSet
import java.util.Properties

class MCrmCategory : BasePONameValue, I_Persistent {
    override val tableId: Int
        get() = Table_ID

    companion object {
        const val Table_ID = 1000000
        const val Table_Name = "Crm_Category"
    }

    override fun getAccessLevel(): Int {
        return 3 // AccessLevel = 3 - Client - Org
    }

    constructor(ctx: Properties, ID: Int, trxName: String?) : super(ctx, ID, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, trxName, a)
}
