package org.compiere.crm

import kotliquery.Row
import org.compiere.model.I_C_Region
import org.compiere.orm.Query
import org.idempiere.common.util.all
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

import java.io.Serializable
import java.text.Collator
import java.util.Comparator

private fun loadAllRegions() = Query<I_C_Region>(I_C_Region.Table_Name, "IsActive='Y'").list()

private val regionFactory = factory(loadAllRegions()) { MRegion(it) }

val defaultRegion: I_C_Region = regionFactory.all().first { it.isDefault }

/**
 * Get country by Id
 */
fun getRegion(id: Int) = id loadUsing regionFactory

/**
 * Return Array of Regions of Country
 *
 * @param countryId country
 * @return MRegion Array
 */
fun getRegions(countryId: Int): List<I_C_Region> = regionFactory.all().filter { it.countryId == countryId }

/**
 * Location Region Model (Value Object)
 *
 * @author Jorg Janke
 * @version $Id: MRegion.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 */
class MRegion : X_C_Region, Comparator<Any>, Serializable, I_C_Region {
    /**
     * ************************************************************************ Create empty Region
     *
     * @param C_Region_ID id
     */
    constructor(C_Region_ID: Int) : super(C_Region_ID) {} //  MRegion

    /**
     * Create Region from current row in ResultSet
     */
    constructor(row: Row) : super(row) {} // 	MRegion

    /**
     * Parent Constructor
     *
     * @param country country
     * @param regionName Region Name
     */
    constructor(country: MCountry, regionName: String) : super(0) {
        countryId = country.countryId
        name = regionName
    } //  MRegion

    /**
     * Return Name
     *
     * @return Name
     */
    override fun toString(): String {
        return name
    } //  toString

    /**
     * Compare
     *
     * @param o1 object 1
     * @param o2 object 2
     * @return -1,0, 1
     */
    override fun compare(o1: Any, o2: Any): Int {
        var s1: String? = o1.toString()
        if (s1 == null) s1 = ""
        var s2: String? = o2.toString()
        if (s2 == null) s2 = ""
        val collator = Collator.getInstance()
        return collator.compare(s1, s2)
    } // 	compare

    companion object {
        private const val serialVersionUID = 1124865777747582617L
    }
} // 	MRegion
