package org.compiere.crm

import java.util.*

fun get(ctx: Properties, AD_Client_ID: Int): MClientInfo? {
    return org.compiere.orm.get(AD_Client_ID, { row -> MClientInfo(ctx, row) }, { it as MClientInfo? }) as MClientInfo?
}
