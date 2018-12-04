package org.compiere.crm

import company.bigger.test.support.BaseTest
import org.compiere.orm.DefaultModelFactory
import org.compiere.orm.IModelFactory
import org.idempiere.icommon.model.IPO
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

open class BaseCrmTest : BaseTest() {
    fun <T : IPO> getById(id: Int, tableName: String): T {
        val modelFactory: IModelFactory = DefaultModelFactory()
        val result = modelFactory.getPO(tableName, id, null)
        println(result)
        assertNotNull(result)
        val obj = result as T
        assertNotNull(obj)
        assertEquals(id, obj.id)
        return obj
    }
}