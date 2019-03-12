package org.compiere.crm

import org.compiere.bo.X_C_ContactActivity
import org.compiere.model.I_AD_User
import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_ContactActivity
import org.compiere.model.I_C_Opportunity
import org.idempiere.common.util.Env.ZERO
import software.hsharp.core.models.EnvironmentService
import software.hsharp.services.BusinessOpportunityService
import software.hsharp.services.CurrencyService
import software.hsharp.services.SalesStageService
import java.sql.Timestamp
import java.time.Instant

class ContactActivityService(
    private val environmentService: EnvironmentService,
    private val businessOpportunityService: BusinessOpportunityService,
    private val salesStageService: SalesStageService,
    private val currencyService: CurrencyService
) {
    fun createContactActivity(
        businessPartner: I_C_BPartner,
        startDate: Timestamp,
        description: String,
        contactActivityType: String
    ): I_C_ContactActivity {
        val now = Timestamp.from(Instant.now())
        val salesStage = salesStageService.ensureSalesStage("Planning", 0.toBigDecimal(), "planning")
        val opportunity = businessOpportunityService.getBusinessOpportunityForBusinessPartner(
            businessPartner, now, currencyService.EUR, salesStage, ZERO, ZERO
        )
        return createContactActivity(
            opportunity, startDate, description, contactActivityType, environmentService.userId
        )
    }

    fun createContactActivity(
        opportunity: I_C_Opportunity,
        startDate: Timestamp,
        description: String,
        contactActivityType: String,
        salesRepresentativeId: Int?
    ): I_C_ContactActivity {
        val activity = X_C_ContactActivity(environmentService.context, 0)
        with(activity) {
            setC_Opportunity(opportunity)
            this.startDate = startDate
            this.description = description
            this.contactActivityType = contactActivityType
            this.salesRepresentativeId = salesRepresentativeId ?: 0
            this.setUserId(this.salesRepresentativeId)
            save()
        }
        return activity
    }

    fun createContactActivity(
        opportunity: I_C_Opportunity,
        startDate: Timestamp,
        description: String,
        contactActivityType: String,
        salesRepresentative: I_AD_User?
    ): I_C_ContactActivity {
        return createContactActivity(opportunity, startDate, description, contactActivityType, salesRepresentative?.userId)
    }
}