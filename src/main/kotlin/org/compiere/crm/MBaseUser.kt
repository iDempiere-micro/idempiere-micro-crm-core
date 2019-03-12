package org.compiere.crm

import kotliquery.Row
import kotliquery.queryOf
import org.compiere.orm.MRole
import software.hsharp.core.util.DB
import java.sql.ResultSet
import java.util.Properties

fun getWithRole(role: MRole): Array<MUser> {
    val sql = """
            SELECT * FROM AD_User u
            WHERE u.IsActive='Y'
            AND EXISTS (SELECT * FROM AD_User_Roles ur
            WHERE ur.AD_User_ID=u.AD_User_ID AND ur.AD_Role_ID=? AND ur.IsActive='Y')
        """.trimIndent()
    val loadQuery =
        queryOf(sql, role.roleId)
            .map { row -> MUser(role.ctx, row) }.asList

    return DB.current.run(loadQuery).toTypedArray()
}

open class MBaseUser : X_AD_User {
    constructor(ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor(ctx: Properties, row: Row) : super(ctx, row)
    constructor(ctx: Properties, id: Int) : super(ctx, id)

    /** Roles of User with Org  */
    private var m_roles: Array<MRole> = arrayOf()
    /** Roles of User with Org  */
    private var m_rolesAD_Org_ID = -1

    fun getRoles(orgId: Int): Array<MRole> {
        if (m_roles.isNotEmpty() && m_rolesAD_Org_ID == orgId) return m_roles

        val sql = """
            SELECT * FROM AD_Role r
            WHERE r.IsActive='Y'
            AND EXISTS (SELECT * FROM AD_User_Roles ur
            WHERE r.AD_Role_ID=ur.AD_Role_ID AND ur.IsActive='Y' AND ur.AD_User_ID=?)
            AND ( ( r.isaccessallorgs = 'Y' ) OR
            (
            r.IsUseUserOrgAccess <> 'Y'
            AND EXISTS (SELECT * FROM AD_Role_OrgAccess ro
            WHERE r.AD_Role_ID=ro.AD_Role_ID AND ro.IsActive='Y' AND ro.AD_Org_ID=?)
            ) OR
            (
            r.IsUseUserOrgAccess = 'Y'
            AND EXISTS (SELECT * FROM AD_User_OrgAccess uo
            WHERE uo.AD_User_ID=? AND uo.IsActive='Y' AND uo.AD_Org_ID=?)
            )
            )
            ORDER BY AD_Role_ID
        """.trimIndent()
        val loadQuery =
            queryOf(sql, userId, orgId, userId, orgId)
                .map { row -> MRole(ctx, row) }.asList

        return DB.current.run(loadQuery).toTypedArray()
    }
}