package org.compiere.bo

import kotliquery.Row
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

private val currencyFactory = factory { MCurrency(it) }

/**
 * Get currency by Id
 */
fun getCurrency(id: Int) = id loadUsing currencyFactory

/**
 * Get Standard Precision.
 *
 * @param currencyId currency
 * @return Standard Precision
 */
fun getCurrencyStdPrecision(currencyId: Int): Int {
    val c = getCurrency(currencyId)
    return c.stdPrecision
} // 	getStdPrecision

/**
 * Get Costing Precision.
 *
 * @param currencyId currency
 * @return Costing Precision
 */
fun getCurrencyCostingPrecision(currencyId: Int): Int {
    val c = getCurrency(currencyId)
    return c.costingPrecision
}

/**
 * Get Currency Iso Code.
 *
 * @param currencyId currency
 * @return ISO Code
 */
fun getCurrencyISOCode(currencyId: Int): String {
    // 	Create it
    val c = getCurrency(currencyId)
    return c.isoCode
} // 	getISO

/**
 * Currency Model.
 *
 * @author Jorg Janke
 */
class MCurrency : X_C_Currency {

    /**
     * Currency Constructor
     *
     * @param currencyId id
     */
    constructor(currencyId: Int) : super(currencyId) {
        if (currencyId == 0) {
            setIsEMUMember(false)
            setIsEuro(false)
            stdPrecision = 2
            costingPrecision = 4
        }
    } // 	MCurrency

    constructor(row: Row) : super(row) {}

    /**
     * Currency Constructor
     *
     * @param ISO_Code ISO
     * @param Description Name
     * @param CurSymbol symbol
     * @param StdPrecision precision
     * @param CostingPrecision precision
     */
    constructor(
        ISO_Code: String,
        Description: String,
        CurSymbol: String,
        StdPrecision: Int,
        CostingPrecision: Int
    ) : super(0) {
        isoCode = ISO_Code
        description = Description
        currencySymbol = CurSymbol
        stdPrecision = StdPrecision
        costingPrecision = CostingPrecision
        setIsEMUMember(false)
        setIsEuro(false)
    } // 	MCurrency

    /**
     * String Representation
     *
     * @return info
     */
    override fun toString(): String {
        return "MCurrency[" +
                currencyId +
                "-" +
                isoCode +
                "-" +
                currencySymbol +
                "," +
                description +
                ",Precision=" +
                stdPrecision +
                "/" +
                costingPrecision
    } // 	toString

    companion object {
        private const val serialVersionUID = 2262097171335518186L
    }
} // 	MCurrency
