package org.compiere.crm

fun get(AD_Client_ID: Int): MClientInfo? {
    return org.compiere.orm.get(AD_Client_ID, { row -> MClientInfo(row) }, { it as MClientInfo? }) as MClientInfo?
}
