package org.compiere.crm

import org.compiere.model.CrmCategory
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.CategoryService

class CategoryServiceImpl(
    private val environmentService: EnvironmentService
) : BaseDataServiceImpl<CrmCategory>(environmentService, MCrmCategory.Table_Name, false), CategoryService {
    override fun createCategory(name: String, searchKey: String): CrmCategory {
        val result = MCrmCategory(environmentService.context, 0)
        with(result) {
            this.name = name
            this.searchKey = searchKey
            save()
        }
        return result
    }
}