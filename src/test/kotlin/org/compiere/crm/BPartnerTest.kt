package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.idempiere.common.util.Env
import org.junit.Test
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.util.DB
import java.util.Properties
import java.util.Random
import kotlin.test.assertEquals

/**
 * Generate a random string (small letters)
 */
fun randomString(length: Int): String {
    fun ClosedRange<Char>.randomString(length: Int) =
        (1..length)
            .map { (Random().nextInt(endInclusive.toInt() - start.toInt()) + start.toInt()).toChar() }
            .joinToString("")
    return ('a'..'z').randomString(length)
}

internal const val clientId = 11

internal class FakeEnvironmentService(override val clientId: Int, override val context: Properties) : EnvironmentService {
    override val userId = 0
}

internal val environmentService = FakeEnvironmentService(clientId, Env.getCtx())
internal val businessPartnerService = BusinessPartnerServiceImpl(environmentService)
internal val countryService = CountryServiceImpl(environmentService)
internal val categoryService = CategoryServiceImpl(environmentService)

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
            partner2.searchKey = (newValue)
            partner2.save()

            val partner3 = MBPartner.get(Env.getCtx(), id)

            assertEquals(id, partner3.businessPartnerId)
            assertEquals(newValue, partner3.searchKey)
            assertEquals("Joe Block", partner3.name)

            partner2.searchKey = ("JoeBlock")
            partner2.save()

            val newPartner = MBPartner.getTemplate(ctx, clientId)
            val name = "Test " + randomString(10)
            newPartner.name = name
            val value = "t-" + randomString(5)
            newPartner.searchKey = (value)
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

    @Test
    fun `create a business partner through service works`() {
        val categoryName = DB.run {
            val category = categoryService.getAll().firstOrNull()
            category?.name ?: { categoryService.createCategory(randomString(5), randomString(5)) }.invoke().name
        }
        val countryId = DB.run {
            countryService.getAll().first().countryId
        }

        val source = BusinessPartnerInput(
            searchKey = "v-" + randomString(10),
            legalName = "bp-" + randomString(10),
            categoryName = categoryName,
            dunsNumber = "dunsNumber",
            vatNumber = "vatNumber",
            email = "test@test.com",
            isCustomer = true,
            note = "note",
            flatDiscount = 10,
            branchName = "branchName",
            branchPhone = "branchPhone",
            branchStreet = "branchStreet",
            branchCity = "branchCity",
            branchZip = "branchZip",
            branchCountryId = countryId,
            ownerPhone = "ownerPhone",
            legalStreet = "legalStreet",
            legalCity = "legalCity",
            legalZip = "legalZip",
            legalCountryId = countryId,
            orderContactName = "orderContactName",
            orderContactPhone = "orderContactPhone",
            orderContactEmail = "orderContactEmail@test.com",
            decisionMakerContactName = "decisionMakerContactName",
            decisionMakerContactPhone = "decisionMakerContactPhone",
            decisionMakerContactEmail = "decisionMakerContactEmail@test.com",
            invoicingContactName = "invoicingContactName",
            invoicingContactPhone = "invoicingContactPhone",
            invoicingContactEmail = "invoicingContactEmail@test.com",
            description = "description",
            salesRepresentativeId = 101
        )

        val result = DB.run {
            businessPartnerService.createBusinessPartner(source)
        }

        DB.run {
            assertEquals(source.searchKey, result.searchKey)
            assertEquals(source.legalName, result.name)
            assertEquals(source.flatDiscount?.toBigDecimal() ?: -1, result.flatDiscount)
            assertEquals(source.description, result.description)
            assertEquals(source.isCustomer, result.isCustomer)
            assertEquals(3, result.contacts.count())
            assertEquals(2, result.locations.count())
            // TODO: Test category
        }
    }
}