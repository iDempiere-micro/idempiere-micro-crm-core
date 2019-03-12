package org.compiere.bo

import kotliquery.Row
import org.compiere.model.I_C_Opportunity
import org.compiere.model.I_C_SalesStage
import org.compiere.orm.BasePOUser
import org.compiere.orm.MTable
import org.idempiere.common.util.Env
import org.idempiere.common.util.KeyNamePair
import java.math.BigDecimal
import java.sql.ResultSet
import java.sql.Timestamp
import java.util.Properties

open class X_C_Opportunity : BasePOUser, I_C_Opportunity {
    override val tableId: Int
        get() = I_C_Opportunity.Table_ID

    constructor(ctx: Properties, C_Opportunity_ID: Int) : super(ctx, C_Opportunity_ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)
    constructor (ctx: Properties, row: Row) : super(ctx, row)

    /** AccessLevel
     * @return 3 - Client - Org
     */
    override fun getAccessLevel(): Int {
        return I_C_Opportunity.accessLevel.toInt()
    }

    override fun toString(): String {
        val sb = StringBuffer("X_C_Opportunity[")
            .append(id).append("]")
        return sb.toString()
    }

    @Throws(RuntimeException::class)
    override fun getBusinessPartner(): org.compiere.model.I_C_BPartner {
        return MTable.get(ctx, org.compiere.model.I_C_BPartner.Table_Name)
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
        return MTable.get(ctx, org.compiere.model.I_C_Order.Table_Name)
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

    /** Set Opportunity Amount.
     * @param OpportunityAmt
     * The estimated value of this opportunity.
     */
    override fun setOpportunityAmt(OpportunityAmt: BigDecimal) {
        setValue(I_C_Opportunity.COLUMNNAME_OpportunityAmt, OpportunityAmt)
    }

    /** Set Expected Close Date.
     * @param ExpectedCloseDate
     * Expected Close Date
     */
    fun setExpectedCloseDate(ExpectedCloseDate: Timestamp) {
        setValue(I_C_Opportunity.COLUMNNAME_ExpectedCloseDate, ExpectedCloseDate)
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

    override fun setSalesStage(stage: I_C_SalesStage?) {
        salesStageId = stage?.id ?: 0
    }

    /** Set Probability.
     * @param Probability Probability
     */
    override fun setProbability(Probability: BigDecimal) {
        setValue(I_C_Opportunity.COLUMNNAME_Probability, Probability)
    }

    /** Get Probability.
     * @return Probability
     */
    override fun getProbability(): BigDecimal {
        return getValue(I_C_Opportunity.COLUMNNAME_Probability) as BigDecimal? ?: return Env.ZERO
    }
}
