package org.compiere.crm

import kotliquery.Row
import org.compiere.model.I_C_BPartner
import org.compiere.orm.BasePOName
import org.idempiere.common.util.AdempiereSystemError

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

    constructor(ID: Int) : super(ID)
    constructor(row: Row) : super(row)

    var bPartner: I_C_BPartner
        get() {
            return MBPartner(getValue<Int>(I_C_BPartner.COLUMNNAME_C_BPartner_ID) ?: throw AdempiereSystemError("Must have business Partner"))
        }
        set(bp) {
            setValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, bp.businessPartnerId)
        }

    var category: MCrmCategory
        get() {
            return MCrmCategory(getValue<Int>("Crm_Category_ID") ?: throw AdempiereSystemError("Must have Category"))
        }
        set(cat) {
            setValueNoCheck("Crm_Category_ID", cat.id)
        }
}
