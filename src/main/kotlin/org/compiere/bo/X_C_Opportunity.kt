/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.idempiere.org/license.html           *
 *****************************************************************************/

package org.compiere.bo

import org.compiere.model.I_C_Opportunity
import org.compiere.orm.BasePOUser
import org.compiere.orm.MTable
import org.idempiere.common.util.KeyNamePair
import org.idempiere.orm.I_Persistent
import java.math.BigDecimal
import java.sql.ResultSet
import java.util.*

open class X_C_Opportunity : BasePOUser, I_C_Opportunity, I_Persistent {
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
        return get_Value(I_C_Opportunity.COLUMNNAME_C_BPartner_ID) as Int? ?: return 0
    }

    /** Get Campaign.
     * @return Marketing Campaign
     */
    override fun getC_Campaign_ID(): Int {
        return get_Value(I_C_Opportunity.COLUMNNAME_C_Campaign_ID) as Int? ?: return 0
    }

    /** Get Currency.
     * @return The Currency for this record
     */
    override fun getC_Currency_ID(): Int {
        return get_Value(I_C_Opportunity.COLUMNNAME_C_Currency_ID) as Int? ?: return 0
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
        return (get_Value(I_C_Opportunity.COLUMNNAME_C_Order_ID) as Int?) ?: return 0
    }

    /** Get Sales Stage.
     * @return Stages of the sales process
     */
    override fun getC_SalesStage_ID(): Int {
        return get_Value(I_C_Opportunity.COLUMNNAME_C_SalesStage_ID) as Int? ?: return 0
    }

    /** Get Document No.
     * @return Document sequence number of the document
     */
    override fun getDocumentNo(): String {
        return get_Value(I_C_Opportunity.COLUMNNAME_DocumentNo) as String
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

    /** Get Sales Representative.
     * @return Sales Representative or Company Agent
     */
    override fun getSalesRep_ID(): Int {
        return get_Value(I_C_Opportunity.COLUMNNAME_SalesRep_ID) as Int? ?: return 0
    }

}
