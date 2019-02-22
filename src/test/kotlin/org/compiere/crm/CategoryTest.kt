package org.compiere.crm

import company.bigger.test.support.randomString
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.util.DB
import software.hsharp.core.util.HikariCPI
import kotlin.test.assertNotNull

class CategoryTest : BaseCrmTest() {

    init {
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")
    }

    @Test
    fun `create crm category, business partner and assign them`() {
        DB.run {
            val ctx = Env.getCtx()
            val category = MCrmCategory(ctx, 0)
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

            val bpartnerInCategory = MCrmCustomerCategory(ctx, 0)
            bpartnerInCategory.bPartner = newPartner
            bpartnerInCategory.name = randomString(10)
            bpartnerInCategory.category = cat

            bpartnerInCategory.save()

            val bpInCat: MCrmCustomerCategory = getById(bpartnerInCategory.id, MCrmCustomerCategory.Table_Name)
            assertNotNull(bpInCat)
        }
    }
}