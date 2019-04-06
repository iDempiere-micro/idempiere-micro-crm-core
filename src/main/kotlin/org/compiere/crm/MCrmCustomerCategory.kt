package org.compiere.crm

import kotliquery.Row
import org.compiere.model.I_C_BPartner
import org.compiere.orm.BasePOName

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
            return MBPartner(getValue(I_C_BPartner.COLUMNNAME_C_BPartner_ID) as Int)
        }
        set(bp) {
            setValueNoCheck(I_C_BPartner.COLUMNNAME_C_BPartner_ID, bp.businessPartnerId)
        }

    var category: MCrmCategory
        get() {
            return MCrmCategory(getValue("Crm_Category_ID") as Int)
        }
        set(cat) {
            setValueNoCheck("Crm_Category_ID", cat.id)
        }
}
