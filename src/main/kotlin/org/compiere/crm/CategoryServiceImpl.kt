package org.compiere.crm

import org.compiere.model.CrmCategory
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.CategoryService

class CategoryServiceImpl(
    environmentService: EnvironmentService
) : BaseDataServiceImpl<CrmCategory>(environmentService, MCrmCategory.Table_Name, false), CategoryService