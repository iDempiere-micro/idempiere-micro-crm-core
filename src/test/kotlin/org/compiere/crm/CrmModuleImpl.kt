package org.compiere.crm

import org.compiere.orm.ModelFactory
import software.hsharp.core.modules.BaseModuleImpl
import software.hsharp.core.services.EnvironmentService
import software.hsharp.services.BusinessPartnerService

/**
 * A Module with the [BusinessPartnerService]
 */
class CrmModuleImpl(
    environmentService: EnvironmentService,
    modelFactory: ModelFactory,
    val businessPartnerService: BusinessPartnerService
) : BaseModuleImpl(environmentService, modelFactory)