package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.compiere.orm.BasePOName
import java.sql.ResultSet
import java.util.*

class MCrmCustomerCategory : BasePOName {
    companion object {
        const val Table_ID = 1000001
        const val Table_Name = "Crm_Customer_Category"
    }

    override val tableId: Int
        get() = Table_ID

    override fun getAccessLevel(): Int {
        return 3 // AccessLevel = 3 - Client - Org
    }

    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, rs: ResultSet, a: String?) : super(ctx, rs, a)

    var bPartner: I_C_BPartner
        get() {
            return MBPartner(ctx, getValue(I_C_BPartner.COLUMNNAME_C_BPartner_ID) as Int)
        }
        set(bp) {
            set_ValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, bp.c_BPartner_ID)
        }

    var category: MCrmCategory
        get() {
            return MCrmCategory(ctx, getValue("Crm_Category_ID") as Int)
        }
        set(cat) {
            set_ValueNoCheck("Crm_Category_ID", cat.id)
        }
}
