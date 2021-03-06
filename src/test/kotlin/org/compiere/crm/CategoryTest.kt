package org.compiere.crm

import org.junit.Test
import software.hsharp.core.util.DB
import software.hsharp.core.util.HikariCPI
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CategoryTest : BaseCrmTest() {

    init {
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")
    }

    @Test
    fun `create crm category in a service`() {
        environment.run {
            DB.run {
                val name = "Test-${randomString(10)}"
                val searchKey = randomString(10)
                val result = categoryService.createCategory(name = name, searchKey = searchKey)
                assertEquals(name, result.name)
                assertEquals(searchKey, result.searchKey)
            }
        }
    }

    @Test
    fun `create crm category, business partner and assign them`() {
        environment.run {
            DB.run {
                val category = MCrmCategory(0)
                val catName = "Test-${randomString(10)}"
                category.name = catName
                category.searchKey = catName
                category.save()
                val cat: MCrmCategory = getById(category.id, MCrmCategory.Table_Name)
                assertNotNull(cat)

                val newPartner = MBPartner.getTemplate(11)
                val name = "Test " + randomString(10)
                newPartner.name = name
                val value = "t-" + randomString(5)
                newPartner.searchKey = value
                newPartner.save()

                val bp: MBPartner = getById(newPartner.id, MBPartner.Table_Name)
                assertNotNull(bp)

                val bpartnerInCategory = MCrmCustomerCategory(0)
                bpartnerInCategory.bPartner = newPartner
                bpartnerInCategory.name = randomString(10)
                bpartnerInCategory.category = cat

                bpartnerInCategory.save()

                val bpInCat: MCrmCustomerCategory = getById(bpartnerInCategory.id, MCrmCustomerCategory.Table_Name)
                assertNotNull(bpInCat)

                val bpWithCategories: MBPartner = getById(newPartner.id, MBPartner.Table_Name)
                assertNotNull(bpWithCategories)
                assertEquals(1, bpWithCategories.categories.size)
            }
        }
    }
}