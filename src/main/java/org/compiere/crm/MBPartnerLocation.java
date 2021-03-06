package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_C_Location;
import org.compiere.orm.MSysConfig;
import org.compiere.orm.Query;

import java.util.List;

/**
 * Partner Location Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 * <li>FR [ 2788465 ] MBPartnerLocation.getForBPartner method add trxName https://sourceforge
 * .net/tracker/index.php?func=detail&aid=2788465&group_id =176962&atid=879335
 * @version $Id: MBPartnerLocation.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MBPartnerLocation extends X_C_BPartner_Location implements I_C_BPartner_Location {
    /**
     *
     */
    private static final long serialVersionUID = -8412652367051443276L;
    /**
     * Cached Location
     */
    private MLocation m_location = null;
    /**
     * Unique Name
     */
    private String m_uniqueName = null;

    private int m_unique = 0;

    /**
     * ************************************************************************ Default Constructor
     *
     * @param C_BPartner_Location_ID id
     */
    public MBPartnerLocation(int C_BPartner_Location_ID) {
        super(C_BPartner_Location_ID);
        if (C_BPartner_Location_ID == 0) {
            setName(".");
            //
            setIsShipTo(true);
            setIsRemitTo(true);
            setIsPayFrom(true);
            setIsBillTo(true);
        }
    } // MBPartner_Location

    /**
     * BP Parent Constructor
     *
     * @param bp partner
     */
    public MBPartnerLocation(I_C_BPartner bp) {
        this(0);
        setClientOrg(bp);
        // may (still) be 0
        setValueNoCheck("C_BPartner_ID", bp.getBusinessPartnerId());
    } // MBPartner_Location

    /**
     * Constructor from ResultSet row
     *
     */
    public MBPartnerLocation(Row row) {
        super(row);
    } // MBPartner_Location

    /**
     * Get Locations for BPartner
     *
     * @param C_BPartner_ID bp
     * @return array of locations
     */
    public static I_C_BPartner_Location[] getForBPartner(
            int C_BPartner_ID) {
        List<I_C_BPartner_Location> list =
                new Query<I_C_BPartner_Location>(I_C_BPartner_Location.Table_Name, "C_BPartner_ID=?")
                        .setParameters(C_BPartner_ID)
                        .list();
        I_C_BPartner_Location[] retValue = new I_C_BPartner_Location[list.size()];
        list.toArray(retValue);
        return retValue;
    } // getForBPartner

    @Override
    public I_C_Location getLocation() {
        return getLocation(false);
    }

    /**
     * Get Location/Address
     *
     * @param requery get again the location from DB - please note that if used out of transaction the
     *                result is get from the cache
     * @return location
     */
    public MLocation getLocation(boolean requery) {
        if (requery || m_location == null)
            m_location = MLocationKt.getLocation(getLocationId());
        return m_location;
    } // getLocation

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "MBPartner_Location[ID=" +
                getId() +
                ",C_Location_ID=" +
                getLocationId() +
                ",Name=" +
                getName() +
                "]";
    } // toString

    /**
     * ************************************************************************ Before Save. - Set
     * Name
     *
     * @param newRecord new
     * @return save
     */
    protected boolean beforeSave(boolean newRecord) {
        if (getLocationId() == 0) return false;

        // Set New Name
        if (".".equals(getName()) && !isPreserveCustomName()) {
            MLocation address = getLocation(true);
            setName(getBPLocName(address));
        }
        return true;
    } // beforeSave

    /**
     * Make name Unique
     *
     * @param address address
     */
    private void makeUnique(MLocation address) {
        m_uniqueName = "";

        // 0 - City
        {
            String xx = address.getCity();
            if (xx != null && xx.length() > 0) m_uniqueName = xx;
        }
        // 1 + Address1
        if (m_unique >= 1 || m_uniqueName.length() == 0) {
            String xx = address.getAddress1();
            if (xx != null && xx.length() > 0) {
                if (m_uniqueName.length() > 0) m_uniqueName += " ";
                m_uniqueName += xx;
            }
        }
        // 2 + Address2
        if (m_unique >= 2 || m_uniqueName.length() == 0) {
            String xx = address.getAddress2();
            if (xx != null && xx.length() > 0) {
                if (m_uniqueName.length() > 0) m_uniqueName += " ";
                m_uniqueName += xx;
            }
        }
        // 3 - Region
        if (m_unique >= 3 || m_uniqueName.length() == 0) {
            String xx = address.getRegionName(true);
            if (xx.length() > 0) {
                if (m_uniqueName.length() > 0) m_uniqueName += " ";
                m_uniqueName += xx;
            }
        }
        // 4 - ID
        if (m_unique >= 4 || m_uniqueName.length() == 0) {
            int id = getId();
            if (id == 0) id = address.getId();
            m_uniqueName += "#" + id;
        }
    } // makeUnique

    public String getBPLocName(MLocation address) {

        if (isPreserveCustomName()) return getName();

        m_uniqueName = getName();
        m_unique =
                MSysConfig.getIntValue(
                        MSysConfig.START_VALUE_BPLOCATION_NAME, 0, getClientId(), getOrgId());
        if (m_unique < 0 || m_unique > 4) m_unique = 0;
        if (m_uniqueName != null) { // && m_uniqueName.equals(".")) {
            // default
            m_uniqueName = null;
            makeUnique(address);
        }

        // Check uniqueness
        I_C_BPartner_Location[] locations = getForBPartner(getBusinessPartnerId());
        boolean unique = locations.length == 0;
        while (!unique) {
            unique = true;
            for (I_C_BPartner_Location location : locations) {
                if (location.getBusinessPartnerLocationId() == getId()) continue;
                if (m_uniqueName.equals(location.getName())) {
                    // m_uniqueName = null;
                    m_unique++;
                    makeUnique(address);
                    unique = false;
                    break;
                }
            }
        }
        return m_uniqueName;
    }

    /**
     * Get Invoice Address.
     *
     * @return Business Partner Invoice/Bill Address
     */
    public boolean getIsBillTo() {
        Object oo = getValue(I_C_BPartner_Location.COLUMNNAME_IsBillTo);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Pay-From Address.
     *
     * @return Business Partner pays from that address and we'll send dunning letters there
     */
    public boolean isPayFrom() {
        return charToBoolean(getValue(I_C_BPartner_Location.COLUMNNAME_IsPayFrom));
    }

    /**
     * Get Ship Address.
     *
     * @return Business Partner Shipment Address
     */
    public boolean getIsShipTo() {
        return charToBoolean(getValue(I_C_BPartner_Location.COLUMNNAME_IsShipTo));
    }

    /**
     * Get Phone.
     *
     * @return Identifies a telephone number
     */
    public String getPhone() {
        return getValue(I_C_BPartner_Location.COLUMNNAME_Phone);
    }

    /**
     * Set Phone.
     *
     * @param Phone Identifies a telephone number
     */
    public void setPhone(String Phone) {
        setValue(I_C_BPartner_Location.COLUMNNAME_Phone, Phone);
    }

} // MBPartnerLocation
