package org.compiere.crm

import java.util.Properties

fun get(ctx: Properties, AD_Client_ID: Int, trxName: String?): MClientInfo? {
    return org.compiere.orm.get(ctx, AD_Client_ID, trxName, { row -> MClientInfo(ctx, row) }, { it as MClientInfo? } ) as MClientInfo?
}
