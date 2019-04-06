package org.compiere.bo

import kotliquery.Row

class MOpportunity : MBaseOpportunity {

    constructor(C_Opportunity_ID: Int) : super(C_Opportunity_ID)
    constructor (row: Row) : super(row)

    override fun beforeSave(newRecord: Boolean): Boolean {
        if (orderId > 0) {
            val order = order
            if (order != null)
                this.amount = order.grandTotal
        }
        return true
    }
}