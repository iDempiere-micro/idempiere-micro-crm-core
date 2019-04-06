package org.compiere.crm

import kotliquery.Row
import org.idempiere.common.util.Env
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

import java.math.BigDecimal

private val businessPartnerGroupFactory = factory { MBPGroup(it) }

/**
 * Get organization by Id
 */
fun getBusinessPartnerGroup(id: Int) = id loadUsing businessPartnerGroupFactory

/**
 * Business Partner Group Model
 *
 * @author Jorg Janke
 * @version $Id: MBPGroup.java,v 1.4 2006/09/23 15:54:22 jjanke Exp $
 */
class MBPGroup : X_C_BP_Group {

    /**
     * Get Credit Watch Ratio
     *
     * @return 0.90 or defined percent
     */
    override fun getCreditWatchRatio(): BigDecimal {
            val bd = super.getCreditWatchPercent()
            return if (bd.compareTo(Env.ZERO) != 0) bd.divide(
                Env.ONEHUNDRED,
                2,
                BigDecimal.ROUND_HALF_UP
            ) else BigDecimal.valueOf(0.90)
        } // 	getCreditWatchRatio

    /**
     * Standard Constructor
     *
     * @param C_BP_Group_ID id
     */
    constructor(C_BP_Group_ID: Int) : super(C_BP_Group_ID) {
        if (C_BP_Group_ID == 0) {
            setIsConfidentialInfo(false) // N
            setIsDefault(false)
            priorityBase = X_C_BP_Group.PRIORITYBASE_Same
        }
    } // 	MBPGroup

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) // 	MBPGroup

    /**
     * Get Credit Watch Percent
     *
     * @return 90 or defined percent
     */
    override fun getCreditWatchPercent(): BigDecimal {
        val bd = super.getCreditWatchPercent()
        return bd ?: BigDecimal(90)
    } // 	getCreditWatchPercent

    override fun beforeSave(newRecord: Boolean): Boolean {
        // TODO Auto-generated method stub
        return true
    }

    /**
     * After Save
     *
     * @param newRecord new record
     * @param success success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        return success
    } // 	afterSave

    companion object {
        private const val serialVersionUID = 8897399796117872715L
    }
} // 	MBPGroup
