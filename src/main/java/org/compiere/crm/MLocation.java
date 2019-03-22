package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Location;
import org.compiere.orm.MSysConfig;
import org.compiere.orm.PO;
import org.compiere.util.Msg;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.Util;

import java.util.Comparator;
import java.util.Properties;
import java.util.logging.Level;

import static software.hsharp.core.util.DBKt.getSQLValue;
import static software.hsharp.core.util.DBKt.getSQLValueEx;

/**
 * Location (Address)
 *
 * @author Jorg Janke
 * @author Michael Judd (Akuna Ltd)
 * <li>BF [ 2695078 ] Country is not translated on invoice
 * <li>FR [2794312 ] Location AutoComplete - check if allow cities out of list
 * @author Teo Sarca, teo.sarca@gmail.com
 * <li>BF [ 3002736 ] MLocation.get cache all MLocations
 * https://sourceforge.net/tracker/?func=detail&aid=3002736&group_id=176962&atid=879332
 * @version $Id: MLocation.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public class MLocation extends MBaseLocation implements I_C_Location, Comparator<Object> {
    /**
     *
     */
    private static final long serialVersionUID = -8462972029898383163L;
    /**
     * Cache
     */
    private static CCache<Integer, MLocation> s_cache =
            new CCache<Integer, MLocation>(Table_Name, 100, 30);

    private MCountry m_c = null;
    private MRegion m_r = null;

    /**
     * ************************************************************************ Standard Constructor
     *
     * @param ctx           context
     * @param C_Location_ID id
     * @param trxName       transaction
     */
    public MLocation(Properties ctx, int C_Location_ID) {
        super(ctx, C_Location_ID);
        if (C_Location_ID == 0) {
            MCountry defaultCountry = MCountry.getDefault(getCtx());
            setCountry(defaultCountry);
            MRegion defaultRegion = MRegion.getDefault(getCtx());
            if (defaultRegion != null
                    && defaultRegion.getCountryId() == defaultCountry.getCountryId())
                setRegion(defaultRegion);
        }
    } //	MLocation

    /**
     * Parent Constructor
     *
     * @param country mandatory country
     * @param region  optional region
     */
    public MLocation(MCountry country, MRegion region) {
        super(country.getCtx(), 0);
        setCountry(country);
        setRegion(region);
    } //	MLocation

    /**
     * Full Constructor
     *
     * @param ctx          context
     * @param C_Country_ID country
     * @param C_Region_ID  region
     * @param city         city
     * @param trxName      transaction
     */
    public MLocation(Properties ctx, int C_Country_ID, int C_Region_ID, String city) {
        super(ctx, 0);
        setCountryId(C_Country_ID);
        setRegionId(C_Region_ID);
        setCity(city);
    } //	MLocation

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    public MLocation(Properties ctx, Row row) {
        super(ctx, row);
    } //	MLocation

    /**
     * Get Location from Cache
     *
     * @param ctx           context
     * @param C_Location_ID id
     * @return MLocation
     */
    public static MLocation get(Properties ctx, int C_Location_ID) {
        //	New
        if (C_Location_ID == 0) return new MLocation(ctx, C_Location_ID);
        //
        Integer key = C_Location_ID;
        MLocation retValue = null;
        retValue = s_cache.get(key);
        if (retValue != null) return retValue;
        retValue = new MLocation(ctx, C_Location_ID);
        if (retValue.getId() != 0) // 	found
        {
            s_cache.put(key, retValue);
            return retValue;
        }
        return null; //	not found
    } //	get

    /**
     * Set C_Country_ID
     *
     * @param C_Country_ID id
     */
    public void setCountryId(int C_Country_ID) {
        if (getCountryId() != C_Country_ID) setRegion(null);
        setCountry(MCountry.get(getCtx(), C_Country_ID));
    } //	setCountry

    /**
     * Get Country
     *
     * @return country
     */
    public MCountry getCountry() {
        // Reset country if not match
        if (m_c != null && m_c.getId() != getCountryId()) m_c = null;
        // Load
        if (m_c == null) {
            if (getCountryId() != 0) m_c = MCountry.get(getCtx(), getCountryId());
            else m_c = MCountry.getDefault(getCtx());
        }
        return m_c;
    } //	getCountry

    /**
     * Set Country
     *
     * @param country
     */
    public void setCountry(MCountry country) {
        if (country != null) m_c = country;
        else m_c = MCountry.getDefault(getCtx());
        super.setCountryId(m_c.getCountryId());
    } //	setCountry

    /**
     * Get Country Name
     *
     * @return Country Name
     */
    public String getCountryName() {
        return getCountry().getName();
    } //	getCountryName

    /**
     * Set C_Region_ID
     *
     * @param C_Region_ID region
     */
    public void setRegionId(int C_Region_ID) {
        if (C_Region_ID == 0) setRegion(null);
            //	Country defined
        else if (getCountryId() != 0) {
            MCountry cc = getCountry();
            if (cc.isValidRegion(C_Region_ID)) super.setRegionId(C_Region_ID);
            else setRegion(null);
        } else setRegion(MRegion.get(getCtx(), C_Region_ID));
    } //	setRegionId

    /**
     * Get Region
     *
     * @return region
     */
    public MRegion getRegion() {
        // Reset region if not match
        if (m_r != null && m_r.getId() != getRegionId()) m_r = null;
        //
        if (m_r == null && getRegionId() != 0) m_r = MRegion.get(getCtx(), getRegionId());
        return m_r;
    } //	getRegion

    /**
     * Set Region
     *
     * @param region
     */
    public void setRegion(MRegion region) {
        m_r = region;
        if (region == null) {
            super.setRegionId(0);
            setRegionName(null);
        } else {
            super.setRegionId(m_r.getRegionId());
            setRegionName(m_r.getName());
            if (m_r.getCountryId() != getCountryId()) {
                if (log.isLoggable(Level.INFO))
                    log.info(
                            "Region("
                                    + region
                                    + ") C_Country_ID="
                                    + region.getCountryId()
                                    + " - From  C_Country_ID="
                                    + getCountryId());
                setCountryId(region.getCountryId());
            }
        }
    } //	setRegion

    /**
     * Get (local) Region Name
     *
     * @return region Name or ""
     */
    public String getRegionName() {
        return getRegionName(false);
    } //	getRegionName

    /**
     * Get Region Name
     *
     * @param getFromRegion get from region (not locally)
     * @return region Name or ""
     */
    public String getRegionName(boolean getFromRegion) {
        if (getFromRegion && getCountry().isHasRegion() && getRegion() != null) {
            super.setRegionName(""); // 	avoid duplicates
            return getRegion().getName();
        }
        //
        String regionName = super.getRegionName();
        if (regionName == null) regionName = "";
        return regionName;
    } //	getRegionName

    /**
     * Compares to current record
     *
     * @param C_Country_ID if 0 ignored
     * @param C_Region_ID  if 0 ignored
     * @param Postal       match postal
     * @param Postal_Add   match postal add
     * @param City         match city
     * @param Address1     match address 1
     * @param Address2     match address 2
     * @return true if equals
     */
    public boolean equals(
            int C_Country_ID,
            int C_Region_ID,
            String Postal,
            String Postal_Add,
            String City,
            String Address1,
            String Address2) {
        if (C_Country_ID != 0 && getCountryId() != C_Country_ID) return false;
        if (C_Region_ID != 0 && getRegionId() != C_Region_ID) return false;
        //	must match
        if (!equalsNull(Postal, getPostal())) return false;
        if (!equalsNull(Postal_Add, getPostal_Add())) return false;
        if (!equalsNull(City, getCity())) return false;
        if (!equalsNull(Address1, getAddress1())) return false;
        return equalsNull(Address2, getAddress2());
    } //	equals

    /**
     * Equals if "" or Null
     *
     * @param c1 c1
     * @param c2 c2
     * @return true if equal (ignore case)
     */
    private boolean equalsNull(String c1, String c2) {
        if (c1 == null) c1 = "";
        if (c2 == null) c2 = "";
        return c1.equalsIgnoreCase(c2);
    } //	equalsNull

    /**
     * Equals
     *
     * @param cmp comparator
     * @return true if ID the same
     */
    public boolean equals(Object cmp) {
        if (cmp == null) return false;
        if (cmp.getClass().equals(this.getClass())) return ((PO) cmp).getId() == getId();
        return equals(cmp);
    } //	equals

    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    /**
     * Print Address Reverse Order
     *
     * @return true if reverse depending on country
     */
    public boolean isAddressLinesReverse() {
        //	Local
        if (MCountry.getDefault(getCtx()) != null
                && getCountryId() == MCountry.getDefault(getCtx()).getCountryId())
            return getCountry().isAddressLinesLocalReverse();
        return getCountry().isAddressLinesReverse();
    } //	isAddressLinesReverse

    /**
     * Parse according City/Postal/Region according to displaySequence. @C@ - City @R@ - Region @P@ -
     * Postal @A@ - PostalAdd
     *
     * @param c country
     * @return parsed String
     */
    private String parseCRP(MCountry c) {
        if (c == null) return "CountryNotFound";

        boolean local =
                MCountry.getDefault(getCtx()) != null
                        && getCountryId() == MCountry.getDefault(getCtx()).getCountryId();
        String inStr = local ? c.getDisplaySequenceLocal() : c.getDisplaySequence();
        StringBuilder outStr = new StringBuilder();

        String token;
        int i = inStr.indexOf('@');
        while (i != -1) {
            outStr.append(inStr, 0, i); // up to @
            inStr = inStr.substring(i + 1); // from first @

            int j = inStr.indexOf('@'); // next @
            if (j < 0) {
                token = ""; // 	no second tag
                j = i + 1;
            } else token = inStr.substring(0, j);
            //	Tokens
            if (token.equals("C")) {
                if (getCity() != null) outStr.append(getCity());
            } else if (token.equals("R")) {
                if (getRegion() != null) // 	we have a region
                    outStr.append(getRegion().getName());
                else if (super.getRegionName() != null && super.getRegionName().length() > 0)
                    outStr.append(super.getRegionName()); // 	local region name
            } else if (token.equals("P")) {
                if (getPostal() != null) outStr.append(getPostal());
            } else if (token.equals("A")) {
                String add = getPostal_Add();
                if (add != null && add.length() > 0) outStr.append("-").append(add);
            } else outStr.append("@").append(token).append("@");

            inStr = inStr.substring(j + 1); // from second @
            i = inStr.indexOf('@');
        }
        outStr.append(inStr); // add the rest of the string

        //	Print Region Name if entered and not part of pattern
        if (c.getDisplaySequence().indexOf("@R@") == -1
                && super.getRegionName() != null
                && super.getRegionName().length() > 0) outStr.append(" ").append(super.getRegionName());

        String retValue = Util.replace(outStr.toString(), "\\n", "\n");
        if (log.isLoggable(Level.FINEST))
            log.finest("parseCRP - " + c.getDisplaySequence() + " -> " + retValue);
        return retValue;
    } //	parseContext

    /**
     * ************************************************************************ Return printable
     * String representation
     *
     * @return String
     */
    public String toString() {
        StringBuilder retStr = new StringBuilder();
        if (isAddressLinesReverse()) {
            //	City, Region, Postal
            retStr.append(parseCRP(getCountry()));
            if (getAddress5() != null && getAddress5().length() > 0)
                retStr.append(", ").append(getAddress5());
            if (getAddress4() != null && getAddress4().length() > 0)
                retStr.append(", ").append(getAddress4());
            if (getAddress3() != null && getAddress3().length() > 0)
                retStr.append(", ").append(getAddress3());
            if (getAddress2() != null && getAddress2().length() > 0)
                retStr.append(", ").append(getAddress2());
            if (getAddress1() != null) retStr.append(", ").append(getAddress1());
        } else {
            if (getAddress1() != null) retStr.append(getAddress1());
            if (getAddress2() != null && getAddress2().length() > 0)
                retStr.append(", ").append(getAddress2());
            if (getAddress3() != null && getAddress3().length() > 0)
                retStr.append(", ").append(getAddress3());
            if (getAddress4() != null && getAddress4().length() > 0)
                retStr.append(", ").append(getAddress4());
            if (getAddress5() != null && getAddress5().length() > 0)
                retStr.append(", ").append(getAddress5());
            //	City, Region, Postal
            retStr.append(", ").append(parseCRP(getCountry()));
            //	Add Country would come here
            // retStr.append(", ").append(getCountry());
        }
        return retStr.toString();
    } //	toString

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    protected boolean beforeSave(boolean newRecord) {
        if (getOrgId() != 0) setOrgId(0);
        //	Region Check
        if (getRegionId() != 0) {
            if (m_c == null || m_c.getCountryId() != getCountryId()) getCountry();
            if (!m_c.isHasRegion()) setRegionId(0);
        } else {
            setRegionName(null);
        }
        if (getCityId() <= 0 && getCity() != null && getCity().length() > 0) {
            int city_id =
                    getSQLValue(
                            "SELECT C_City_ID FROM C_City WHERE C_Country_ID=? AND COALESCE(C_Region_ID,0)=? AND Name=? AND AD_Client_ID IN (0,?)",
                            getCountryId(),
                            getRegionId(),
                            getCity(),
                            getClientId());
            if (city_id > 0) setCityId(city_id);
        }

        // check city
        if (m_c != null && !m_c.isAllowCitiesOutOfList() && getCityId() <= 0) {
            log.saveError("CityNotFound", Msg.translate(getCtx(), "CityNotFound"));
            return false;
        }

        // check city id
        if (m_c != null && !m_c.isAllowCitiesOutOfList() && getCityId() > 0) {
            int city_id =
                    getSQLValue(
                            "SELECT C_City_ID "
                                    + "  FROM C_City "
                                    + " WHERE C_Country_ID=? "
                                    + "   AND COALESCE(C_Region_ID,0)=? "
                                    + "   AND C_City_ID =?",
                            getCountryId(),
                            getRegionId(),
                            getCityId());

            if (city_id < 0) {
                log.saveError(
                        "CityNotFound",
                        Msg.translate(getCtx(), "CityNotFound") + " C_City_ID[" + getCityId() + "]");
                return false;
            }
        }
        return true;
    } //	beforeSave

    /**
     * After Save
     *
     * @param newRecord new
     * @param success   success
     * @return success
     */
    protected boolean afterSave(boolean newRecord, boolean success) {
        if (!success) return success;

        // Update BP_Location name IDEMPIERE 417
        int bplID =
                getSQLValueEx(
                        "SELECT C_BPartner_Location_ID FROM C_BPartner_Location WHERE C_Location_ID = "
                                + getLocationId());
        if (bplID > 0) {
            // just trigger BPLocation name change when the location change affects the name:
            // START_VALUE_BPLOCATION_NAME
            // 0 - City
            // 1 - City + Address1
            // 2 - City + Address1 + Address2
            // 3 - City + Address1 + Address2 + Region
            // 4 - City + Address1 + Address2 + Region + ID
            int bplocname =
                    MSysConfig.getIntValue(
                            MSysConfig.START_VALUE_BPLOCATION_NAME, 0, getClientId(), getOrgId());
            if (bplocname < 0 || bplocname > 4) bplocname = 0;
            if (isValueChanged(COLUMNNAME_City)
                    || isValueChanged(COLUMNNAME_C_City_ID)
                    || (bplocname >= 1 && isValueChanged(COLUMNNAME_Address1))
                    || (bplocname >= 2 && isValueChanged(COLUMNNAME_Address2))
                    || (bplocname >= 3
                    && (isValueChanged(COLUMNNAME_RegionName)
                    || isValueChanged(COLUMNNAME_C_Region_ID)))) {
                MBPartnerLocation bpl = new MBPartnerLocation(getCtx(), bplID);
                bpl.setName(bpl.getBPLocName(this));
                bpl.saveEx();
            }
        }

        return success;
    } //	afterSave

} //	MLocation
