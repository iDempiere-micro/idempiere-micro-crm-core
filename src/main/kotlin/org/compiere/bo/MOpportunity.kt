package org.compiere.bo

import kotliquery.Row
import java.sql.ResultSet
import java.util.Properties

class MOpportunity : X_C_Opportunity {

    constructor(ctx: Properties, C_Opportunity_ID: Int) : super(ctx, C_Opportunity_ID)
    constructor (ctx: Properties, row: Row) : super(ctx, row)

    override fun beforeSave(newRecord: Boolean): Boolean {
        if (orderId > 0) {
            val order = order
            if (order != null)
                this.amount = order.grandTotal
        }
        return true
    }
}