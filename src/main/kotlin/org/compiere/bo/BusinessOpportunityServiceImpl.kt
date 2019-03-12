package org.compiere.bo

import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_Currency
import org.compiere.model.I_C_Opportunity
import org.compiere.model.I_C_SalesStage
import org.compiere.orm.Query
import software.hsharp.core.models.EnvironmentService
import software.hsharp.services.BusinessOpportunityService
import java.math.BigDecimal
import java.sql.Timestamp

class BusinessOpportunityServiceImpl(
    private val environmentService: EnvironmentService
) : BusinessOpportunityService {
    override fun getBusinessOpportunityForBusinessPartner(
        businessPartner: I_C_BPartner,
        expectedCloseData: Timestamp,
        currency: I_C_Currency,
        salesStage: I_C_SalesStage,
        probability: BigDecimal,
        amount: BigDecimal
    ): I_C_Opportunity {
        val ctx = environmentService.context
        val opportunities: List<I_C_Opportunity> =
            Query(ctx, I_C_Opportunity.Table_Name, "AD_Client_ID=? AND C_BPartner_ID=?")
                .setParameters(environmentService.clientId, businessPartner.businessPartnerId)
                .list()
        return if (opportunities.isEmpty()) {
            val opportunity = MOpportunity(ctx, 0)
            with(opportunity) {
                this.businessPartner = businessPartner
                setExpectedCloseDate(expectedCloseData)
                setOpportunityAmt(amount)
                setCurrencyId(currency.currencyId)
                setSalesStage(salesStage)
                this.probability = probability
                save()
            }
            opportunity
        } else opportunities.first()
    }
}