package org.compiere.bo

import mu.KotlinLogging
import org.compiere.model.I_C_SalesStage
import org.compiere.orm.Query
import software.hsharp.core.services.EnvironmentService
import software.hsharp.services.SalesStageService
import java.math.BigDecimal

private val logger = KotlinLogging.logger {}

class SalesStageServiceImpl(
    private val environmentService: EnvironmentService
) : SalesStageService {
    override fun ensureSalesStage(name: String, probability: BigDecimal, searchKey: String): I_C_SalesStage {
        val salesStages =
            Query<I_C_SalesStage>(I_C_SalesStage.Table_Name, "AD_Client_ID=?")
                .setParameters(environmentService.clientId)
                .list()
        return if (salesStages.isEmpty()) {
            logger.trace { "Creating sales stage $name" }

            val salesStage = X_C_SalesStage(0)
            salesStage.name = name
            salesStage.probability = probability
            salesStage.searchKey = searchKey
            salesStage.save()
            salesStage
        } else salesStages.first()
    }
}