package org.compiere.crm

import org.compiere.model.I_C_Country
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.CountryService

class CountryServiceImpl(
    environmentService: EnvironmentService
) : BaseDataServiceImpl<I_C_Country>(environmentService, I_C_Country.Table_Name, true), CountryService