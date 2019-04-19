package org.compiere.crm

import kotliquery.Row
import org.compiere.model.UserBusinessPartnerAccess
import org.compiere.orm.BasePOUser

/**
 * Generated Model for AD_UserBPAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
class UserBusinessPartnerAccessImpl : BasePOUser, UserBusinessPartnerAccess {

    override val tableId: Int
        get() = UserBusinessPartnerAccess.Table_ID

    /**
     * Standard Constructor
     */
    constructor(AD_UserBPAccess_ID: Int) : super(AD_UserBPAccess_ID)

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row)

    /**
     * AccessLevel
     *
     * @return 2 - Client
     */
    override fun getAccessLevel(): Int {
        return UserBusinessPartnerAccess.accessLevel.toInt()
    }

    override fun toString(): String {
        return "UserBusinessPartnerAccessImpl[$id]"
    }

    companion object {
        private const val serialVersionUID = 20171031L
    }
}
