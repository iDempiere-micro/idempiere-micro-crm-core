package org.compiere.crm

import org.compiere.orm.DefaultModelFactory
import org.compiere.orm.IModelFactory
import org.flywaydb.core.Flyway
import org.idempiere.icommon.model.IPO
import org.slf4j.impl.SimpleLogger
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

    fun <T : IPO> getById(id: Int, tableName: String): T {
        val modelFactory: IModelFactory = DefaultModelFactory()
        val result = modelFactory.getPO(tableName, id)
        println(result)
        assertNotNull(result)
        @Suppress("UNCHECKED_CAST")
        val obj = result as T
        assertNotNull(obj)
        assertEquals(id, obj.id)
        return obj
    }
}