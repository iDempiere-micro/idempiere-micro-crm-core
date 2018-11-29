package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.compiere.orm.BasePOName
import org.idempiere.orm.I_Persistent
import org.idempiere.orm.POInfo
import java.sql.ResultSet
import java.util.Properties

class MCrmCustomerCategory : BasePOName, I_Persistent {
    companion object {
        const val Table_ID = 1000001
        const val Table_Name = "Crm_Customer_Category"
    }

    override val tableId: Int
        get() = Table_ID

    override fun getAccessLevel(): Int {
        return 3 // AccessLevel = 3 - Client - Org
    }

    constructor(ctx: Properties, ID: Int, trxName: String?) : super(ctx, ID, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?) : super(ctx, rs, trxName)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, trxName, a)

    var bPartner: I_C_BPartner
        get() {
            return MBPartner(ctx, get_Value(I_C_BPartner.COLUMNNAME_C_BPartner_ID) as Int, null)
        }
        set(bp) {
            set_ValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, bp.c_BPartner_ID)
        }

    var category: MCrmCategory
        get() {
            return MCrmCategory(ctx, get_Value("Crm_Category_ID") as Int, null)
        }
        set(cat) {
            set_ValueNoCheck("Crm_Category_ID", cat.id)
        }
}
