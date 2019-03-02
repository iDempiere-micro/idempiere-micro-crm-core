package company.bigger.idempiere.service

import org.compiere.bo.X_C_ContactActivity
import org.compiere.model.I_C_ContactActivity
import org.compiere.model.I_C_Opportunity
import software.hsharp.core.models.EnvironmentService
import java.sql.Timestamp

class ContactActivityService (
    private val environmentService: EnvironmentService
) {
    fun createContactActivity(opportunity: I_C_Opportunity, startDate: Timestamp, description: String, contactActivityType: String) : I_C_ContactActivity {
        val activity = X_C_ContactActivity(environmentService.context, 0)
        with (activity) {
            setC_Opportunity(opportunity)
            this.startDate = startDate
            this.description = description
            this.contactActivityType = contactActivityType
            save()
        }
        return activity
    }
}