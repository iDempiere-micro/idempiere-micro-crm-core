package org.compiere.crm

import company.bigger.test.support.BaseTest
import kotliquery.queryOf
import kotliquery.using
import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_BPartner_Location
import org.compiere.orm.DefaultModelFactory
import org.compiere.orm.IModelFactory
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.util.DB
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FactoryTest : BaseCrmTest() {
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
                SELECT adempiere."$tableName".*,
                c_bpartner_location_id, isbillto, isshipto, ispayfrom, isremitto, phone, phone2, fax, isdn, c_salesregion_id, c_location_id, c_bpartner_location_uu, customeraddressid, ispreservecustomname
                FROM adempiere."$tableName", adempiere.c_bpartner_location WHERE ("$tableName".ad_client_id = ? OR "$tableName".ad_client_id=0) AND ("$tableName".ad_org_id = ? OR "$tableName".ad_org_id=0) AND (adempiere."$tableName"."${tableName}_ID"=?) AND (c_bpartner_location.c_bpartner_id = C_BPartner.c_bpartner_id);
                """.trimIndent()
                    ).toLowerCase()
        println("SQL:$sql")

        val modelFactory: IModelFactory = DefaultModelFactory()
        val query =
            queryOf(sql, AD_CLIENT_ID, AD_ORG_ID, id).map {
                Pair(modelFactory.getPO<I_C_BPartner>(tableName, it), modelFactory.getPO<I_C_BPartner_Location>(I_C_BPartner_Location.Table_Name, it))
            }.asSingle

        using(DB.current) { session ->
            val row = session.run(query)
            assertNotNull(row)
            val result = row.first
            val result2 = row.second

            assertEquals(id, result.id)
            assertEquals(113, result2.c_BPartner_Location_ID)
        }
    }
}