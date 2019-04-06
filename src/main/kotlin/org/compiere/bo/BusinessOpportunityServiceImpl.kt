package org.compiere.bo

import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_Opportunity
import org.compiere.model.BusinessOpportunity
import org.compiere.orm.Query
import software.hsharp.core.services.EnvironmentService
import software.hsharp.services.BusinessOpportunityService

class BusinessOpportunityServiceImpl(
    private val environmentService: EnvironmentService
) : BusinessOpportunityService {
    override fun getBusinessOpportunityForBusinessPartner(
        forBusinessPartner: I_C_BPartner,
        newBusinessOpportunity: BusinessOpportunity
    ): I_C_Opportunity {
        val opportunities: List<I_C_Opportunity> =
            Query(I_C_Opportunity.Table_Name, "AD_Client_ID=? AND C_BPartner_ID=?")
                .setParameters(environmentService.clientId, forBusinessPartner.businessPartnerId)
                .list()
        return if (opportunities.isEmpty()) {
            val opportunity = MOpportunity(0)
            with(opportunity) {
                businessPartner = forBusinessPartner
                expectedCloseDate = newBusinessOpportunity.expectedCloseDate
                amount = newBusinessOpportunity.amount
                currency = newBusinessOpportunity.currency
                salesStage = newBusinessOpportunity.salesStage
                probability = newBusinessOpportunity.probability
                save()
            }
            opportunity
        } else opportunities.first()
    }
}