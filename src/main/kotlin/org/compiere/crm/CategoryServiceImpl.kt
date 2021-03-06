package org.compiere.crm

import software.hsharp.models.CrmCategory
import software.hsharp.core.services.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.CategoryService

class CategoryServiceImpl(
    private val environmentService: EnvironmentService
) : BaseDataServiceImpl<CrmCategory>(environmentService, MCrmCategory.Table_Name, false), CategoryService {
    override fun createCategory(name: String, searchKey: String): CrmCategory {
        val result = MCrmCategory(0)
        with(result) {
            this.name = name
            this.searchKey = searchKey
            save()
        }
        return result
    }
}