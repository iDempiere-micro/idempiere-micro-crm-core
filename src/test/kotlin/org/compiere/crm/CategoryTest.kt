package org.compiere.crm

import company.bigger.test.support.randomString
import kotliquery.HikariCP
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.orm.DummyEventManager
import kotlin.test.assertNotNull

class CategoryTest : BaseCrmTest() {

    init {
        HikariCP.default(sessionUrl, "adempiere", "adempiere")
        DummyEventManager()
    }

    @Test
    fun `create crm category, business partner and assign them`() {
        val ctx = Env.getCtx()
        val category = MCrmCategory(ctx, 0, null)
        val catName = "Test-${randomString(10)}"
        category.name = catName
        category.setValue(catName)
        category.save()
        val cat: MCrmCategory = getById(category.id, MCrmCategory.Table_Name)
        assertNotNull(cat)

        val newPartner = MBPartner.getTemplate(ctx, 11)
        val name = "Test " + randomString(10)
        newPartner.setName(name)
        val value = "t-" + randomString(5)
        newPartner.setValue(value)
        newPartner.save()

        val bp: MBPartner = getById(newPartner.id, MBPartner.Table_Name)
        assertNotNull(bp)

        val bpartnerInCategory = MCrmCustomerCategory(ctx, 0, null)
        bpartnerInCategory.bPartner = newPartner
        bpartnerInCategory.name = randomString(10)
        bpartnerInCategory.category = cat

        bpartnerInCategory.save()

        val bpInCat: MCrmCustomerCategory = getById(bpartnerInCategory.id, MCrmCustomerCategory.Table_Name)
        assertNotNull(bpInCat)
    }
}