package org.compiere.orm

import org.compiere.model.I_AD_User
import java.sql.ResultSet
import java.util.*

abstract class BasePOUser : PO {
    constructor(ctx: Properties, ID: Int) : super(ctx, ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, rs: ResultSet, trxName: String?, a: String?) : super(ctx, rs, a)

    /** Set User/Contact.
     * @param AD_User_ID
     * User within the system - Internal or Business Partner Contact
     */
    fun setAD_User_ID(AD_User_ID: Int) {
        if (AD_User_ID < 1)
            set_Value(I_AD_User.COLUMNNAME_AD_User_ID, null)
        else
            set_Value(I_AD_User.COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID))
    }

    /** Get User/Contact.
     * @return User within the system - Internal or Business Partner Contact
     */
    fun getAD_User_ID(): Int {
        return get_Value(I_AD_User.COLUMNNAME_AD_User_ID) as Int? ?: return 0
    }
}