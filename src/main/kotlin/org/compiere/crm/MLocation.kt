package org.compiere.crm

import kotliquery.Row
import org.compiere.model.I_C_Country
import org.compiere.model.I_C_Location
import org.compiere.model.I_C_Region
import org.compiere.orm.MSysConfig
import org.compiere.orm.PO
import org.compiere.util.Msg
import org.idempiere.common.exceptions.AdempiereException
import org.idempiere.common.util.Util
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing

import java.util.Comparator
import java.util.logging.Level

import software.hsharp.core.util.getSQLValue
import software.hsharp.core.util.getSQLValueEx

private val locationFactory = factory { MLocation(it) }

/**
 * Get currency by Id
 */
fun getLocation(id: Int) = id loadUsing locationFactory

/**
 * Location (Address)
 *
 * @author Jorg Janke
 * @author Michael Judd (Akuna Ltd)
 *  * BF [ 2695078 ] Country is not translated on invoice
 *  * FR [2794312 ] Location AutoComplete - check if allow cities out of list
 * @author Teo Sarca, teo.sarca@gmail.com
 *  * BF [ 3002736 ] MLocation.get cache all MLocations
 * https://sourceforge.net/tracker/?func=detail&aid=3002736&group_id=176962&atid=879332
 * @version $Id: MLocation.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
class MLocation : MBaseLocation, I_C_Location, Comparator<Any> {

    private var m_c: I_C_Country? = null
    private var m_r: I_C_Region? = null

    /**
     * Get Country
     *
     * @return country
     */
    /**
     * Set Country
     *
     * @param country
     */
    // Reset country if not match
    // Load
    var country: I_C_Country?
        get() {
            if (m_c != null && m_c!!.id != countryId) m_c = null
            if (m_c == null) {
                if (countryId != 0)
                    m_c = getCountry(countryId)
                else
                    m_c = getDefaultCountry()
            }
            return m_c
        }
        set(country) {
            if (country != null)
                m_c = country
            else
                m_c = getDefaultCountry()
            super.setCountryId(m_c!!.countryId)
        } // 	getCountry
    // 	setCountry

    /**
     * Get Region
     *
     * @return region
     */
    /**
     * Set Region
     *
     * @param region
     */
    // Reset region if not match
    //
    var region: I_C_Region?
        get() {
            if (m_r != null && m_r!!.id != regionId) m_r = null
            if (m_r == null && regionId != 0) m_r = getRegion(regionId)
            return m_r
        }
        set(region) {
            m_r = region
            if (region == null) {
                super.setRegionId(0)
                setRegionName(null)
            } else {
                super.setRegionId(m_r!!.regionId)
                setRegionName(m_r!!.name)
                if (m_r!!.countryId != countryId) {
                    if (log.isLoggable(Level.INFO))
                        log.info(
                            "Region(" +
                                    region +
                                    ") C_Country_ID=" +
                                    region.countryId +
                                    " - From  C_Country_ID=" +
                                    countryId
                        )
                    countryId = region.countryId
                }
            }
        } // 	getRegion
    // 	setRegion

    /**
     * Print Address Reverse Order
     *
     * @return true if reverse depending on country
     */
    // 	Local
    val isAddressLinesReverse: Boolean
        get() {
            val c = country ?: throw AdempiereException("Location needs Country always")
            return if (c == getDefaultCountry()) c.isAddressLinesLocalReverse else c.isAddressLinesReverse
        } // 	isAddressLinesReverse

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param C_Location_ID id
     */
    constructor(C_Location_ID: Int) : super(C_Location_ID) {
        if (C_Location_ID == 0) {
            val defaultCountry = getDefaultCountry()
            country = defaultCountry
            if (defaultRegion.countryId == defaultCountry.countryId)
                region = defaultRegion
        }
    } // 	MLocation

    /**
     * Parent Constructor
     *
     * @param country mandatory country
     * @param region optional region
     */
    constructor(country: I_C_Country, region: I_C_Region) : super(0) {
        this.country = country
        this.region = region
    } // 	MLocation

    /**
     * Full Constructor
     *
     * @param C_Country_ID country
     * @param C_Region_ID region
     * @param city city
     */
    constructor(C_Country_ID: Int, C_Region_ID: Int, city: String) : super(0) {
        countryId = C_Country_ID
        regionId = C_Region_ID
        setCity(city)
    } // 	MLocation

    /**
     * Load Constructor
     *
     */
    constructor(row: Row) : super(row) {} // 	MLocation

    /**
     * Set C_Country_ID
     *
     * @param C_Country_ID id
     */
    override fun setCountryId(C_Country_ID: Int) {
        if (countryId != C_Country_ID) region = null
        country = getCountry(C_Country_ID)
    } // 	setCountry

    /**
     * Get Country Name
     *
     * @return Country Name
     */
    override fun getCountryName(): String {
        return country?.name ?: ""
    } // 	getCountryName

    /**
     * Set C_Region_ID
     *
     * @param C_Region_ID region
     */
    override fun setRegionId(C_Region_ID: Int) {
        if (C_Region_ID == 0)
            region = null
        else if (countryId != 0) {
            val cc = country
            if (cc?.isValidRegion(C_Region_ID) == true)
                super.setRegionId(C_Region_ID)
            else
                region = null
        } else
            region = getRegion(C_Region_ID) // 	Country defined
    } // 	setRegionId

    /**
     * Get (local) Region Name
     *
     * @return region Name or ""
     */
    override fun getRegionName(): String {
        return getRegionName(false)
    } // 	getRegionName

    /**
     * Get Region Name
     *
     * @param getFromRegion get from region (not locally)
     * @return region Name or ""
     */
    fun getRegionName(getFromRegion: Boolean): String {
        if (getFromRegion && country?.isHasRegion == true && region != null) {
            super.setRegionName("") // 	avoid duplicates
            return region!!.name
        }
        //
        var regionName: String? = super.getRegionName()
        if (regionName == null) regionName = ""
        return regionName
    } // 	getRegionName

    /**
     * Compares to current record
     *
     * @param C_Country_ID if 0 ignored
     * @param C_Region_ID if 0 ignored
     * @param Postal match postal
     * @param Postal_Add match postal add
     * @param City match city
     * @param Address1 match address 1
     * @param Address2 match address 2
     * @return true if equals
     */
    override fun equals(
        C_Country_ID: Int,
        C_Region_ID: Int,
        Postal: String,
        Postal_Add: String,
        City: String,
        Address1: String,
        Address2: String
    ): Boolean {
        if (C_Country_ID != 0 && countryId != C_Country_ID) return false
        if (C_Region_ID != 0 && regionId != C_Region_ID) return false
        // 	must match
        if (!equalsNull(Postal, postal)) return false
        if (!equalsNull(Postal_Add, postal_Add)) return false
        if (!equalsNull(City, city)) return false
        return if (!equalsNull(Address1, address1)) false else equalsNull(Address2, address2)
    } // 	equals

    /**
     * Equals if "" or Null
     *
     * @param c1 c1
     * @param c2 c2
     * @return true if equal (ignore case)
     */
    private fun equalsNull(c1: String?, c2: String?): Boolean {
        var lc1 = c1
        var lc2 = c2
        if (lc1 == null) lc1 = ""
        if (lc2 == null) lc2 = ""
        return lc1.equals(lc2, ignoreCase = true)
    } // 	equalsNull

    /**
     * Equals
     *
     * @param other comparator
     * @return true if ID the same
     */
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        return if (other.javaClass == this.javaClass) (other as PO).id == id else super.equals(other)
    } // 	equals

    override fun hashCode(): Int {
        assert(false) { "hashCode not designed" }
        return 42 // any arbitrary constant will do
    }

    /**
     * Parse according City/Postal/Region according to displaySequence. @C@ - City @R@ - Region @P@ -
     * Postal @A@ - PostalAdd
     *
     * @param c country
     * @return parsed String
     */
    private fun parseCRP(c: I_C_Country?): String {
        if (c == null) return "CountryNotFound"

        getDefaultCountry()
        val local = countryId == getDefaultCountry().countryId
        var inStr = if (local) c.displaySequenceLocal else c.displaySequence
        val outStr = StringBuilder()

        var token: String
        var i = inStr.indexOf('@')
        while (i != -1) {
            outStr.append(inStr, 0, i) // up to @
            inStr = inStr.substring(i + 1) // from first @

            var j = inStr.indexOf('@') // next @
            if (j < 0) {
                token = "" // 	no second tag
                j = i + 1
            } else
                token = inStr.substring(0, j)
            // 	Tokens
            when (token) {
                "C" -> if (city != null) outStr.append(city)
                "R" -> if (region != null)
                // 	we have a region
                    outStr.append(region!!.name)
                else if (super.getRegionName() != null && super.getRegionName().length > 0)
                    outStr.append(super.getRegionName()) // 	local region name
                "P" -> if (postal != null) outStr.append(postal)
                "A" -> {
                    val add = postal_Add
                    if (add != null && add.length > 0) outStr.append("-").append(add)
                }
                else -> outStr.append("@").append(token).append("@")
            }

            inStr = inStr.substring(j + 1) // from second @
            i = inStr.indexOf('@')
        }
        outStr.append(inStr) // add the rest of the string

        // 	Print Region Name if entered and not part of pattern
        if (!c.displaySequence.contains("@R@") &&
            super.getRegionName() != null &&
            super.getRegionName().length > 0
        )
            outStr.append(" ").append(super.getRegionName())

        val retValue = Util.replace(outStr.toString(), "\\n", "\n")
        if (log.isLoggable(Level.FINEST))
            log.finest("parseCRP - " + c.displaySequence + " -> " + retValue)
        return retValue
    } // 	parseContext

    /**
     * ************************************************************************ Return printable
     * String representation
     *
     * @return String
     */
    override fun toString(): String {
        val retStr = StringBuilder()
        if (isAddressLinesReverse) {
            // 	City, Region, Postal
            retStr.append(parseCRP(country))
            if (address5 != null && address5.length > 0)
                retStr.append(", ").append(address5)
            if (address4 != null && address4.length > 0)
                retStr.append(", ").append(address4)
            if (address3 != null && address3.length > 0)
                retStr.append(", ").append(address3)
            if (address2 != null && address2.length > 0)
                retStr.append(", ").append(address2)
            if (address1 != null) retStr.append(", ").append(address1)
        } else {
            if (address1 != null) retStr.append(address1)
            if (address2 != null && address2.length > 0)
                retStr.append(", ").append(address2)
            if (address3 != null && address3.length > 0)
                retStr.append(", ").append(address3)
            if (address4 != null && address4.length > 0)
                retStr.append(", ").append(address4)
            if (address5 != null && address5.length > 0)
                retStr.append(", ").append(address5)
            // 	City, Region, Postal
            retStr.append(", ").append(parseCRP(country))
            // 	Add Country would come here
            // retStr.append(", ").append(getCountry());
        }
        return retStr.toString()
    } // 	toString

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        if (orgId != 0) setOrgId(0)
        // 	Region Check
        if (regionId != 0) {
            if (m_c == null || m_c!!.countryId != countryId) country
            if (!m_c!!.isHasRegion) regionId = 0
        } else {
            setRegionName(null)
        }
        if (cityId <= 0 && city != null && city.length > 0) {
            val city_id = getSQLValue(
                "SELECT C_City_ID FROM C_City WHERE C_Country_ID=? AND COALESCE(C_Region_ID,0)=? AND Name=? AND AD_Client_ID IN (0,?)",
                countryId,
                regionId,
                city,
                clientId
            )
            if (city_id > 0) cityId = city_id
        }

        // check city
        if (m_c != null && !m_c!!.isAllowCitiesOutOfList && cityId <= 0) {
            log.saveError("CityNotFound", Msg.translate("CityNotFound"))
            return false
        }

        // check city id
        if (m_c != null && !m_c!!.isAllowCitiesOutOfList && cityId > 0) {
            val city_id = getSQLValue(
                "SELECT C_City_ID " +
                        "  FROM C_City " +
                        " WHERE C_Country_ID=? " +
                        "   AND COALESCE(C_Region_ID,0)=? " +
                        "   AND C_City_ID =?",
                countryId,
                regionId,
                cityId
            )

            if (city_id < 0) {
                log.saveError(
                    "CityNotFound",
                    Msg.translate("CityNotFound") + " C_City_ID[" + cityId + "]"
                )
                return false
            }
        }
        return true
    } // 	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success success
     * @return success
     */
    override fun afterSave(newRecord: Boolean, success: Boolean): Boolean {
        if (!success) return success

        // Update BP_Location name IDEMPIERE 417
        val bplID = getSQLValueEx(
            "SELECT C_BPartner_Location_ID FROM C_BPartner_Location WHERE C_Location_ID = $locationId"
        )
        if (bplID > 0) {
            // just trigger BPLocation name change when the location change affects the name:
            // START_VALUE_BPLOCATION_NAME
            // 0 - City
            // 1 - City + Address1
            // 2 - City + Address1 + Address2
            // 3 - City + Address1 + Address2 + Region
            // 4 - City + Address1 + Address2 + Region + ID
            var bplocname = MSysConfig.getIntValue(
                MSysConfig.START_VALUE_BPLOCATION_NAME, 0, clientId, orgId
            )
            if (bplocname < 0 || bplocname > 4) bplocname = 0
            if (isValueChanged(I_C_Location.COLUMNNAME_City) ||
                isValueChanged(I_C_Location.COLUMNNAME_C_City_ID) ||
                bplocname >= 1 && isValueChanged(I_C_Location.COLUMNNAME_Address1) ||
                bplocname >= 2 && isValueChanged(I_C_Location.COLUMNNAME_Address2) ||
                bplocname >= 3 && (isValueChanged(I_C_Location.COLUMNNAME_RegionName) || isValueChanged(I_C_Location.COLUMNNAME_C_Region_ID))
            ) {
                val bpl = MBPartnerLocation(bplID)
                bpl.name = bpl.getBPLocName(this)
                bpl.saveEx()
            }
        }

        return success
    } // 	afterSave

    companion object {
        private const val serialVersionUID = -8462972029898383163L
    }
} // 	MLocation
