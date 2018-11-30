package org.compiere.crm

import company.bigger.test.support.BaseTest
import kotliquery.queryOf
import kotliquery.using
import org.compiere.model.I_C_BPartner
import org.compiere.orm.DefaultModelFactory
import org.compiere.orm.IModelFactory
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.util.DB
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FactoryTest : BaseTest() {
    @Test
    fun `get business partner from complex SQL`() {
        val ctx = Env.getCtx()
        val AD_CLIENT_ID = 11
        val AD_CLIENT_ID_s = AD_CLIENT_ID.toString()
        ctx.setProperty(Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        Env.setContext(ctx, Env.AD_CLIENT_ID, AD_CLIENT_ID_s)

        val tableName = I_C_BPartner.Table_Name
        val AD_ORG_ID = 0
        val id = 118

        val sql =
            ("""
                SELECT * FROM adempiere."$tableName", adempiere.M_PriceList WHERE ("$tableName".ad_client_id = ? OR "$tableName".ad_client_id=0) AND ("$tableName".ad_org_id = ? OR "$tableName".ad_org_id=0) AND ("${tableName}_ID"=?) AND (M_PriceList.M_PriceList_ID = C_BPartner.M_PriceList_ID);
                """.trimIndent()
                    ).toLowerCase()
        println("SQL:$sql")

        val modelFactory: IModelFactory = DefaultModelFactory()
        val query = queryOf(sql, AD_CLIENT_ID, AD_ORG_ID, id).map { modelFactory.getPO<I_C_BPartner>(tableName, it) }.asSingle

        using(DB.current) { session ->
            val result = session.run(query)
            // val result2 = modelFactory.getPO("M_PriceList", rs, null)
            println(result)
            // println(result2)
            assertNotNull(result)
            // assertNotNull(result2)
            assertEquals(id, result.id)
            // assertEquals(101, result2.id)
        }
    }
}