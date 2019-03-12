package org.compiere.crm

import company.bigger.test.support.randomString
import org.compiere.model.I_C_BPartner
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.util.DB
import java.util.Properties
import kotlin.test.assertEquals

private const val clientId = 11

private class FakeEnvironmentService(override val clientId: Int, override val context: Properties) : EnvironmentService {
    override val userId = 0
}

private val environmentService = FakeEnvironmentService(clientId, Env.getCtx())
private val businessPartnerService = BusinessPartnerServiceImpl(environmentService)

class BPartnerTest : BaseCrmTest() {
    private fun login() {
        val ctx = Env.getCtx()
        val AD_CLIENT_ID_s = clientId.toString()
        ctx.setProperty(Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        Env.setContext(ctx, Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        val AD_USER_ID = 104
        val AD_USER_ID_s = AD_USER_ID.toString()
        ctx.setProperty(Env.AD_USER_ID, AD_USER_ID_s)
        Env.setContext(ctx, Env.AD_USER_ID, AD_USER_ID_s)
    }

    @Test
    fun `loading saving finding business partner work`() {
        DB.run {
            val ctx = Env.getCtx()
            login()

            val id = 118
            val partner = MBPartner.get(Env.getCtx(), id)

            assertEquals(id, partner.businessPartnerId)
            assertEquals("JoeBlock", partner.searchKey)
            assertEquals("Joe Block", partner.name)

            val partner2: I_C_BPartner = partner as I_C_BPartner

            val newValue = "JoeBlock*"
            partner2.setSearchKey(newValue)
            partner2.save()

            val partner3 = MBPartner.get(Env.getCtx(), id)

            assertEquals(id, partner3.businessPartnerId)
            assertEquals(newValue, partner3.searchKey)
            assertEquals("Joe Block", partner3.name)

            partner2.setSearchKey("JoeBlock")
            partner2.save()

            val newPartner = MBPartner.getTemplate(ctx, clientId)
            val name = "Test " + randomString(10)
            newPartner.setName(name)
            val value = "t-" + randomString(5)
            newPartner.setSearchKey(value)
            newPartner.save()

            val defaultCountry = MCountry.getDefault(ctx)
            val defaultRegion = MRegion.getDefault(ctx)
            val location = MLocation(defaultCountry, defaultRegion)
            location.address1 = "Address 1"
            location.address2 = "Address 2"
            location.city = "City"
            location.save()
            val partnerLocation = MBPartnerLocation(newPartner)
            partnerLocation.locationId = location.locationId
            partnerLocation.save()

            val newPartner2 = MBPartner.get(Env.getCtx(), newPartner.businessPartnerId)
            assertEquals(1, newPartner2.locations.count())

            newPartner.delete(true)

            location.delete(true)
        }
    }

    @Test
    fun `get contacts and locations of all the business partners from client 11`() {
        DB.run {
            val businessPartners = businessPartnerService.getAll()
            businessPartners.map {
                Triple(it, it.contacts, it.locations)
            }
        }
    }
}