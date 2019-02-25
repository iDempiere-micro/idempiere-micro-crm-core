package org.compiere.bo

import org.compiere.model.I_C_Opportunity
import org.compiere.orm.BasePOUser
import org.compiere.orm.MTable
import org.idempiere.common.util.KeyNamePair
import java.math.BigDecimal
import java.sql.ResultSet
import java.util.*

open class X_C_Opportunity : BasePOUser, I_C_Opportunity {
    override val tableId: Int
        get() = I_C_Opportunity.Table_ID

    constructor(ctx: Properties, C_Opportunity_ID: Int) : super(ctx, C_Opportunity_ID)
    constructor (ctx: Properties, rs: ResultSet) : super(ctx, rs)

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
    override fun getC_BPartner(): org.compiere.model.I_C_BPartner {
        return MTable.get(ctx, org.compiere.model.I_C_BPartner.Table_Name)
            .getPO(c_BPartner_ID) as org.compiere.model.I_C_BPartner
    }

    /** Get Business Partner .
     * @return Identifies a Business Partner
     */
    override fun getC_BPartner_ID(): Int {
        return getValue(I_C_Opportunity.COLUMNNAME_C_BPartner_ID) as Int? ?: return 0
    }

    @Throws(RuntimeException::class)
    override fun getC_Order(): org.compiere.model.I_C_Order? {
        if (c_Order_ID == 0) return null
        return MTable.get(ctx, org.compiere.model.I_C_Order.Table_Name)
            .getPO(c_Order_ID) as org.compiere.model.I_C_Order
    }

    /** Get Order.
     * @return Order
     */
    override fun getC_Order_ID(): Int {
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
        set_Value(I_C_Opportunity.COLUMNNAME_OpportunityAmt, OpportunityAmt)
    }

}
