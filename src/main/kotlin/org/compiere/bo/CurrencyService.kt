package org.compiere.bo

import org.compiere.model.I_C_Currency
import software.hsharp.core.services.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.CurrencyService

private const val EURId = 102

class CurrencyServiceImpl(
    private val environmentService: EnvironmentService
) : BaseDataServiceImpl<I_C_Currency>(environmentService, I_C_Currency.Table_Name, true), CurrencyService {
    override val EUR get() = MCurrency(EURId)
}