package software.hsharp.core.models

interface CreateBusinessPartnerInput {
    val searchKey: String
    val legalName: String
    val categoryName: String?
    val dunsNumber: String?
    val vatNumber: String?
    val email: String?
    val isCustomer: Boolean?
    val note: String?
    val flatDiscount: Int?
    val branchName: String?
    val branchPhone: String?
    val branchStreet: String?
    val branchCity: String?
    val branchZip: String?
    val branchCountry: String?
    val ownerPhone: String?
    val legalStreet: String?
    val legalCity: String?
    val legalZip: String?
    val legalCountry: String?
    val orderContactName: String?
    val orderContactPhone: String?
    val orderContactEmail: String?
    val decisionMakerContactName: String?
    val decisionMakerContactPhone: String?
    val decisionMakerContactEmail: String?
    val invoicingContactName: String?
    val invoicingContactPhone: String?
    val invoicingContactEmail: String?
}