package company.bigger.idempiere.service

import mu.KotlinLogging
import org.compiere.bo.X_C_SalesStage
import org.compiere.model.I_C_SalesStage
import org.compiere.orm.Query
import software.hsharp.core.models.EnvironmentService
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

class SalesStageService(
    private val environmentService: EnvironmentService
) {
    fun ensureSalesStage(name: String, probability: BigDecimal, searchKey: String): I_C_SalesStage {
        val ctx = environmentService.context
        val salesStages: List<I_C_SalesStage> =
            Query(ctx, I_C_SalesStage.Table_Name, "AD_Client_ID=?")
                .setParameters(environmentService.clientId)
                .list()
        return if (salesStages.isEmpty()) {
            logger.trace { "Creating sales stage $name" }

            val salesStage = X_C_SalesStage(ctx, 0)
            salesStage.name = name
            salesStage.probability = probability
            salesStage.searchKey = searchKey
            salesStage.save()
            salesStage
        } else salesStages.first()
    }
}