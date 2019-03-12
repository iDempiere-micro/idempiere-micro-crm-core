package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.compiere.model.CreateBusinessPartnerInput
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.services.BusinessPartnerService

class BusinessPartnerServiceImpl(
    private val environmentService: EnvironmentService
) : BaseDataServiceImpl<I_C_BPartner>(environmentService, I_C_BPartner.Table_Name, false), BusinessPartnerService {

    override fun createBusinessPartner(businessPartner: CreateBusinessPartnerInput): I_C_BPartner {
        val (name: String, searchKey: String) = Pair(businessPartner.legalName, businessPartner.searchKey)
        val ctx = environmentService.context
        val newPartner = MBPartner.getTemplate(ctx, environmentService.clientId)
        newPartner.setName(name)
        newPartner.setSearchKey(searchKey)
        newPartner.save()

        val defaultCountry = MCountry.getDefault(ctx)
        val defaultRegion = MRegion.getDefault(ctx)
        val location = MLocation(defaultCountry, defaultRegion)
        location.save()
        val partnerLocation = MBPartnerLocation(newPartner)
        partnerLocation.locationId = location.locationId
        partnerLocation.save()

        return newPartner
    }
}