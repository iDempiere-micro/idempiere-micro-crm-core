package org.compiere.crm

import org.compiere.model.I_C_BPartner
import org.compiere.model.CreateBusinessPartnerInput
import org.compiere.model.I_C_BPartner_Location
import org.idempiere.common.util.Env.ZERO
import software.hsharp.core.services.EnvironmentService
import software.hsharp.core.orm.BaseDataServiceImpl
import software.hsharp.core.util.Environment
import software.hsharp.services.BusinessPartnerService

/**
 * Business Partner Service Implementation
 */
class BusinessPartnerServiceImpl(
    private val environmentService: EnvironmentService
) : BaseDataServiceImpl<I_C_BPartner>(environmentService, I_C_BPartner.Table_Name, false), BusinessPartnerService {
    override fun getEmpty(): I_C_BPartner {
        return MBPartner(0)
    }

    override fun getTemplate(): I_C_BPartner {
        return MBPartner.getTemplate(Environment.current.clientId)
    }

    override fun createBusinessPartner(businessPartner: CreateBusinessPartnerInput): I_C_BPartner {
        fun setLocParams(location: I_C_BPartner_Location) {
            with(businessPartner) {
                val locationName = branchName
                if (locationName != null) {
                    location.name = locationName
                }
                val loc = location.location
                if (branchStreet != null) {
                    loc.address1 = branchStreet
                }
                if (branchCity != null) {
                    loc.city = branchCity
                }
                if (branchZip != null) {
                    loc.postal = branchZip
                }
                branchCountryId?.let { loc.countryId = it }
                loc.save()
                location.isBillTo = false
                if (branchPhone != null) {
                    location.phone = branchPhone
                }
                location.locationId = loc.id
            }
        }

        fun setLegalParams(location: I_C_BPartner_Location) {
            with(businessPartner) {
                val loc = location.location
                legalStreet?.let { loc.address1 = it }
                legalCity?.let { loc.city = it }
                legalZip?.let { loc.postal = it }
                legalCountryId?.let { loc.countryId = it }
                loc.save()
                location.isShipTo = false
                ownerPhone.let { location.phone = it }
                location.locationId = loc.id
            }
        }

        return with(businessPartner) {
            val result = MBPartner.getTemplate(environmentService.clientId)
            result.name = legalName
            result.searchKey = searchKey

            result.url = email
            result.description = description
            result.duns = dunsNumber
            result.taxID = vatNumber

            result.isCustomer = isCustomer ?: false
            result.flatDiscount = flatDiscount?.toBigDecimal() ?: ZERO
            result.salesRepresentativeId = salesRepresentativeId

            result.save()

            val branch = result.locations.firstOrNull { it.isShipTo }
            val updatedLocation =
                if (branch != null) {
                    setLocParams(branch)
                    branch
                } else {
                    val newLocation = MBPartnerLocation(result)
                    newLocation.isShipTo = true
                    setLocParams(newLocation)
                    newLocation
                }
            updatedLocation.save()

            val legal = result.locations.firstOrNull { it.isBillTo && !it.isShipTo }
            val updatedLegal =
                if (legal != null) {
                    setLegalParams(legal)
                    legal
                } else {
                    val newLocation = MBPartnerLocation(result)
                    newLocation.isBillTo = true
                    setLegalParams(newLocation)
                    newLocation
                }
            updatedLegal.save()

            val contactPersons = result.contacts
            val orderContact =
                if (contactPersons.count() > 0) {
                    val orderContact = contactPersons[0]
                    orderContact
                } else {
                    val orderContact = MUser(result)
                    orderContact
                }
            with(orderContact) {
                name = "" + orderContactName
                eMail = orderContactEmail
                phone = orderContactPhone
                searchKey = name
                save()
            }

            val decisionMaker =
                if (contactPersons.count() > 1) {
                    val decisionMaker = contactPersons[1]
                    decisionMaker
                } else {
                    val decisionMaker = MUser(result)
                    decisionMaker
                }
            with(decisionMaker) {
                name = "" + decisionMakerContactName
                eMail = decisionMakerContactEmail
                phone = decisionMakerContactPhone
                searchKey = name
                save()
            }

            val invoicingPerson =
                if (contactPersons.count() > 2) {
                    val invoicingPerson = contactPersons[2]
                    invoicingPerson
                } else {
                    val invoicingPerson = MUser(result)
                    invoicingPerson
                }
            with(invoicingPerson) {
                name = "" + invoicingContactName
                eMail = invoicingContactEmail
                phone = invoicingContactPhone
                searchKey = name
                save()
            }

            result
        }
    }
}