package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.idempiere.common.util.EnvironmentServiceImpl
import org.junit.Test
import software.hsharp.core.orm.BaseSimpleModelFactory
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
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
internal const val userId = 104
internal const val orgId = 0

internal val environmentService = EnvironmentServiceImpl(clientId, orgId, userId)
internal val businessPartnerService = BusinessPartnerServiceImpl(environmentService)
internal val countryService = CountryServiceImpl(environmentService)
internal val categoryService = CategoryServiceImpl(environmentService)
internal val baseModule =
    CrmModuleImpl(
        environmentService = environmentService,
        modelFactory = BaseSimpleModelFactory(
            simpleMapperId, simpleMapperRow
        ),
        businessPartnerService = businessPartnerService
    )
internal val environment = Environment(baseModule)

class BPartnerTest : BaseCrmTest() {
    @Test
    fun `loading saving finding business partner work`() {
        environment.run {
            DB.run {
                val id = 118
                val partner = MBPartner.get(id)

                assertEquals(id, partner.businessPartnerId)
                assertEquals("JoeBlock", partner.searchKey)
                assertEquals("Joe Block", partner.name)

                val partner2: I_C_BPartner = partner as I_C_BPartner

                val newValue = "JoeBlock*"
                partner2.searchKey = (newValue)
                partner2.save()

                val partner3 = MBPartner.get(id)

                assertEquals(id, partner3.businessPartnerId)
                assertEquals(newValue, partner3.searchKey)
                assertEquals("Joe Block", partner3.name)

                partner2.searchKey = ("JoeBlock")
                partner2.save()

                val newPartner = environment.module.businessPartnerService.getTemplate()
                val name = "Test " + randomString(10)
                newPartner.name = name
                val value = "t-" + randomString(5)
                newPartner.searchKey = (value)
                newPartner.save()

                val defaultCountry = getDefaultCountry()
                val location = MLocation(defaultCountry, defaultRegion)
                location.address1 = "Address 1"
                location.address2 = "Address 2"
                location.city = "City"
                location.save()
                val partnerLocation = MBPartnerLocation(newPartner)
                partnerLocation.locationId = location.locationId
                partnerLocation.save()

                val newPartner2 = MBPartner.get(newPartner.businessPartnerId)
                assertEquals(1, newPartner2.locations.count())

                newPartner.delete(true)

                location.delete(true)
            }
        }
    }

    @Test
    fun `get contacts and locations of all the business partners from client 11`() {
        environment.run {
            DB.run {
                val businessPartners = businessPartnerService.getAll()
                businessPartners.map {
                    Triple(it, it.contacts, it.locations)
                }
            }
        }
    }

    @Test
    fun `create a business partner through service works`() {
        environment.run {
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
}