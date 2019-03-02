package company.bigger.idempiere.service

import mu.KotlinLogging
import org.compiere.crm.MBPartner
import org.compiere.crm.MBPartnerLocation
import org.compiere.crm.MCountry
import org.compiere.crm.MRegion
import org.compiere.crm.MLocation
import org.compiere.model.I_C_BPartner
import org.compiere.orm.Query
import software.hsharp.core.models.CreateBusinessPartnerInput
import software.hsharp.core.models.EnvironmentService

private val logger = KotlinLogging.logger {}

class BusinessPartnerService(
    private val environmentService: EnvironmentService
) {
    fun getBusinessPartners(): List<I_C_BPartner> {
        logger.trace { "getBusinessPartners, AD_Client_ID=${environmentService.clientId}" }

        return Query(environmentService.context, I_C_BPartner.Table_Name, "AD_Client_ID=?")
            .setParameters(environmentService.clientId)
            .list()
    }

    fun createBusinessPartner(businessPartnerInput: CreateBusinessPartnerInput): I_C_BPartner {
        val (name: String, searchKey: String) = Pair(businessPartnerInput.legalName, businessPartnerInput.searchKey)
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