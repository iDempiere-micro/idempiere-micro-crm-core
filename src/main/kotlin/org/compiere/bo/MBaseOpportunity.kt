package org.compiere.bo

import kotliquery.Row
import org.compiere.model.I_C_Currency
import org.compiere.model.I_C_Opportunity
import org.compiere.model.I_C_SalesStage
import org.compiere.orm.BasePOUser
import org.compiere.orm.MTable
import org.idempiere.common.util.Env
import org.idempiere.common.util.KeyNamePair
import java.math.BigDecimal
import java.sql.Timestamp

/**
 * Base functionality for the Business Opportunity
 */
open class MBaseOpportunity : BasePOUser, I_C_Opportunity {
    override var expectedCloseDate: Timestamp
        get() = getValue(I_C_Opportunity.COLUMNNAME_ExpectedCloseDate) as Timestamp
        set(value) { setValue(I_C_Opportunity.COLUMNNAME_ExpectedCloseDate, value) }
    override var currency: I_C_Currency?
        get() = MCurrency(getCurrencyId())
        set(value) { setCurrencyId(value?.id ?: 0) }
    override var salesStage: I_C_SalesStage?
        get() = X_C_SalesStage(salesStageId)
        set(value) { salesStageId = value?.id ?: 0 }
    override var probability: BigDecimal
        get() = getValue(I_C_Opportunity.COLUMNNAME_Probability) as BigDecimal? ?: Env.ZERO
        set(value) { setValue(I_C_Opportunity.COLUMNNAME_Probability, value) }
    override var amount: BigDecimal
        get() {
            val bd = getValue(I_C_Opportunity.COLUMNNAME_OpportunityAmt) as BigDecimal?
            return bd ?: Env.ZERO
        }
        set(value) { setValue(I_C_Opportunity.COLUMNNAME_OpportunityAmt, value) }
    override val tableId: Int
        get() = I_C_Opportunity.Table_ID

    constructor(C_Opportunity_ID: Int) : super(C_Opportunity_ID)
    constructor (row: Row) : super(row)

    /** AccessLevel
     * @return 3 - Client - Org
     */
    override fun getAccessLevel(): Int {
        return I_C_Opportunity.accessLevel.toInt()
    }

    override fun toString(): String {
        val sb = StringBuffer("MBaseOpportunity[")
            .append(id).append("]")
        return sb.toString()
    }

    @Throws(RuntimeException::class)
    override fun getBusinessPartner(): org.compiere.model.I_C_BPartner {
        return MTable.get(org.compiere.model.I_C_BPartner.Table_Name)
            .getPO(businessPartnerId) as org.compiere.model.I_C_BPartner
    }

    override fun setBusinessPartner(bPartner: org.compiere.model.I_C_BPartner) {
        businessPartnerId = bPartner.id
    }

    /** Get Business Partner .
     * @return Identifies a Business Partner
     */
    override fun getBusinessPartnerId(): Int {
        return getValue(I_C_Opportunity.COLUMNNAME_C_BPartner_ID) as Int? ?: return 0
    }

    override fun setBusinessPartnerId(id: Int) {
        setValue(I_C_Opportunity.COLUMNNAME_C_BPartner_ID, id)
    }

    @Throws(RuntimeException::class)
    override fun getOrder(): org.compiere.model.I_C_Order? {
        if (orderId == 0) return null
        return MTable.get(org.compiere.model.I_C_Order.Table_Name)
            .getPO(orderId) as org.compiere.model.I_C_Order
    }

    /** Get Order.
     * @return Order
     */
    override fun getOrderId(): Int {
        return (getValue(I_C_Opportunity.COLUMNNAME_C_Order_ID) as Int?) ?: return 0
    }

    /** Get Document No.
     * @return Document sequence number of the document
     */
    override fun getDocumentNo(): String {
        return getValue(I_C_Opportunity.COLUMNNAME_DocumentNo) as String
    }

    /** Get Record ID/ColumnName
     * @return ID/ColumnName pair
     */
    fun getKeyNamePair(): KeyNamePair {
        return KeyNamePair(id, documentNo)
    }

    /** Set Currency.
     * @param C_Currency_ID
     * The Currency for this record
     */
    override fun setCurrencyId(C_Currency_ID: Int) {
        if (C_Currency_ID < 1)
            setValue(I_C_Opportunity.COLUMNNAME_C_Currency_ID, null)
        else
            setValue(I_C_Opportunity.COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID))
    }

    /** Get Currency.
     * @return The Currency for this record
     */
    fun getCurrencyId(): Int {
        return getValue(I_C_Opportunity.COLUMNNAME_C_Currency_ID) as Int? ?: return 0
    }

    /** Set Sales Stage.
     * @param C_SalesStage_ID
     * Stages of the sales process
     */
    override fun setSalesStageId(C_SalesStage_ID: Int) {
        setValue(I_C_Opportunity.COLUMNNAME_C_SalesStage_ID, if (C_SalesStage_ID < 1) null else C_SalesStage_ID)
    }

    /** Get Sales Stage.
     * @return Stages of the sales process
     */
    override fun getSalesStageId(): Int {
        return getValue(I_C_Opportunity.COLUMNNAME_C_SalesStage_ID) as Int? ?: return 0
    }
}
