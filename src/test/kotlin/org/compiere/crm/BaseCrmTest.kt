package org.compiere.crm

import org.compiere.orm.ModelFactory
import org.flywaydb.core.Flyway
import org.idempiere.icommon.model.PersistentObject
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.orm.BaseSimpleModelFactory
import software.hsharp.core.util.HikariCPI
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal val sessionUrl =
    System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere?autosave=conservative"

abstract class BaseCrmTest {
    init {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")

        // Create the Flyway instance and point it to the database
        val flyway =
            Flyway
                .configure()
                .dataSource(sessionUrl, "adempiere", "adempiere")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load()

        // Start the migration
        flyway.migrate()
    }

    fun <T : PersistentObject> getById(id: Int, tableName: String): T {
        val modelFactory: ModelFactory = BaseSimpleModelFactory(simpleMapperId, simpleMapperRow)
        val result: T = modelFactory.getPO(tableName, id)
        println(result)
        assertNotNull(result)
        assertEquals(id, result.id)
        return result
    }
}