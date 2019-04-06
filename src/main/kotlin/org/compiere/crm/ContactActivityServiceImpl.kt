package org.compiere.crm

import org.compiere.bo.X_C_ContactActivity
import org.compiere.model.I_AD_User
import org.compiere.model.I_C_BPartner
import org.compiere.model.I_C_ContactActivity
import org.compiere.model.I_C_Opportunity
import org.compiere.model.BusinessOpportunity
import org.compiere.model.I_C_Currency
import org.compiere.model.I_C_SalesStage
import org.idempiere.common.util.Env.ZERO
import software.hsharp.core.services.EnvironmentService
import software.hsharp.services.BusinessOpportunityService
import software.hsharp.services.ContactActivityService
import software.hsharp.services.CurrencyService
import software.hsharp.services.SalesStageService
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.Instant

class ContactActivityServiceImpl(
    private val environmentService: EnvironmentService,
    private val businessOpportunityService: BusinessOpportunityService,
    private val salesStageService: SalesStageService,
    private val currencyService: CurrencyService
) : ContactActivityService {
    override fun createContactActivity(
        businessPartner: I_C_BPartner,
        startDate: Timestamp,
        description: String,
        contactActivityType: String
    ): I_C_ContactActivity {
        val now = Timestamp.from(Instant.now())
        val salesStage = salesStageService.ensureSalesStage("Planning", 0.toBigDecimal(), "planning")
        val opportunityParams = object : BusinessOpportunity {
            override val expectedCloseDate: Timestamp
                get() = now
            override val currency: I_C_Currency?
                get() = currencyService.EUR
            override val salesStage: I_C_SalesStage?
                get() = salesStage
            override val probability: BigDecimal
                get() = ZERO
            override val amount: BigDecimal
                get() = ZERO
        }

        val opportunity = businessOpportunityService.getBusinessOpportunityForBusinessPartner(
            businessPartner, opportunityParams
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
        val activity = X_C_ContactActivity(0)
        with(activity) {
            setOpportunity(opportunity)
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