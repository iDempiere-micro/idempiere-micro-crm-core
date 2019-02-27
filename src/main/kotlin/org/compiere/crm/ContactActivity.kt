package org.compiere.crm

import org.compiere.model.I_C_ContactActivity

data class ContactActivity(
    val Key: Int,
    val name: String,
    val start: Long, // represent the value of java.sql.Timestamp
    val bpartnerName: String,
    val completed: Boolean,
    val activityOwnerName: String
) {
    val ID: String
        get() = "$Key"

    constructor (a: I_C_ContactActivity) :
            this(
                a.contactActivityId,
                a.description,
                a.startDate.time,
                a.opportunity.businessPartner.name,
                a.isComplete,
                a.salesRepresentative.name
            )
}
