package org.compiere.bo

import java.sql.ResultSet
import java.util.*

class MOpportunity : X_C_Opportunity {

    constructor(ctx: Properties, C_Opportunity_ID: Int) : super(ctx, C_Opportunity_ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)

    override fun beforeSave(newRecord: Boolean): Boolean {
        if (c_Order_ID > 0) {
            val order = c_Order
            if (order != null)
                this.setOpportunityAmt(order.grandTotal)
        }
        return true
    }
}