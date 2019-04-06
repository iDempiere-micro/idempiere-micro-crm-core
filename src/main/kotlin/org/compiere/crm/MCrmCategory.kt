package org.compiere.crm

import kotliquery.Row
import software.hsharp.models.CrmCategory
import org.compiere.orm.BasePONameValue

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

    constructor(row: Row) : super(row)
    constructor(Id: Int) : super(Id)
}
