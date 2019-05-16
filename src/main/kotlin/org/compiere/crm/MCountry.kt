package org.compiere.crm

import kotliquery.Row
import org.compiere.model.HasName
import org.compiere.model.I_C_Country
import org.compiere.orm.Query
import org.idempiere.common.util.Env
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

import java.io.Serializable
import java.text.Collator
import java.util.Comparator
import software.hsharp.core.util.toNullIfEmpty

private fun loadAllCountries() = Query<I_C_Country>(I_C_Country.Table_Name, "IsActive='Y'").list()

private val countryFactory = factory(loadAllCountries()) { MCountry(it) }

/**
 * Get country by Id
 */
fun getCountry(id: Int) = id loadUsing countryFactory

/**
 * Get default country. Note this is used only to create a new e.g. location so not that important.
 */
fun getDefaultCountry(): I_C_Country {
    // 100 = United States
    return getCountry(100)
}

/**
 * Location Country Model (Value Object)
 *
 * @author Jorg Janke
 * @version $Id: MCountry.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 *
 * * @author Michael Judd (Akuna Ltd)
 *  * BF [ 2695078 ] Country is not translated on invoice
 */
class MCountry : X_C_Country, Comparator<Any>, Serializable {

    /**
     * Get Translated Name
     *
     * @return name
     */
    val trlName: String
        get() = getTrlName(Env.getADLanguage()) // 	getTrlName

    /**
     * *********************************************************************** Create empty Country
     *
     * @param C_Country_ID ID
     */
    constructor(C_Country_ID: Int) : super(C_Country_ID) {
        if (C_Country_ID == 0) {
            displaySequence = DISPLAYSEQUENCE
            isHasRegion = false
            setAdditionalPostalCode(false)
            setIsAddressLinesLocalReverse(false)
            setIsAddressLinesReverse(false)
        }
    } //  MCountry

    /**
     * Create Country from current row in ResultSet
     */
    constructor(row: Row) : super(row) {}

    /**
     * Return Name - translated if DisplayLanguage is set.
     *
     * @return Name
     */
    override fun toString(): String {
        return trlName
    } //  toString

    /**
     * Get Translated Name
     *
     * @param language
     * @return name
     */
    fun getTrlName(language: String): String {
        return getTranslation(HasName.COLUMNNAME_Name, language)
    } // 	getTrlName

    /**
     * Get Display Sequence
     *
     * @return display sequence
     */
    override fun getDisplaySequence(): String {
        return super.getDisplaySequence().toNullIfEmpty() ?: DISPLAYSEQUENCE
    } // 	getDisplaySequence

    /**
     * Get Local Display Sequence. If not defined get Display Sequence
     *
     * @return local display sequence
     */
    override fun getDisplaySequenceLocal(): String {
        return super.getDisplaySequenceLocal().toNullIfEmpty() ?: displaySequence
    } // 	getDisplaySequenceLocal

    /**
     * Compare based on Name
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

    /**
     * Is the region valid in the country
     *
     * @param regionId region
     * @return true if valid
     */
    override fun isValidRegion(regionId: Int): Boolean {
        if (regionId == 0 || countryId == 0 || !isHasRegion) return false
        return getRegions(countryId).any { it.regionId == regionId }
    } // 	isValidRegion

    companion object {
        private const val serialVersionUID = -4966707939803861163L
        // 	Default DisplaySequence	*/
        private val DISPLAYSEQUENCE = "@C@, @P@"
    }
} // 	MCountry
