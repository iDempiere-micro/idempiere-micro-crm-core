package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Location;
import org.compiere.orm.PO;

/**
 * Generated Model for C_Location
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Location extends PO {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Location(int C_Location_ID) {
        super(C_Location_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_Location(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 7 - System - Client - Org
     */
    protected int getAccessLevel() {
        return I_C_Location.accessLevel.intValue();
    }

    public String toString() {
        return "X_C_Location[" + getId() + "]";
    }

    /**
     * Get Address 1.
     *
     * @return Address line 1 for this location
     */
    public String getAddress1() {
        return getValue(I_C_Location.COLUMNNAME_Address1);
    }

    /**
     * Set Address 1.
     *
     * @param Address1 Address line 1 for this location
     */
    public void setAddress1(String Address1) {
        setValue(I_C_Location.COLUMNNAME_Address1, Address1);
    }

    /**
     * Get Address 2.
     *
     * @return Address line 2 for this location
     */
    public String getAddress2() {
        return getValue(I_C_Location.COLUMNNAME_Address2);
    }

    /**
     * Set Address 2.
     *
     * @param Address2 Address line 2 for this location
     */
    public void setAddress2(String Address2) {
        setValue(I_C_Location.COLUMNNAME_Address2, Address2);
    }

    /**
     * Get Address 3.
     *
     * @return Address Line 3 for the location
     */
    public String getAddress3() {
        return getValue(I_C_Location.COLUMNNAME_Address3);
    }

    /**
     * Get Address 4.
     *
     * @return Address Line 4 for the location
     */
    public String getAddress4() {
        return getValue(I_C_Location.COLUMNNAME_Address4);
    }

    /**
     * Get Address 5.
     *
     * @return Address Line 5 for the location
     */
    public String getAddress5() {
        return getValue(I_C_Location.COLUMNNAME_Address5);
    }

    /**
     * Get City.
     *
     * @return City
     */
    public int getCityId() {
        Integer ii = getValue(I_C_Location.COLUMNNAME_C_City_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set City.
     *
     * @param C_City_ID City
     */
    public void setCityId(int C_City_ID) {
        if (C_City_ID < 1) setValue(I_C_Location.COLUMNNAME_C_City_ID, null);
        else setValue(I_C_Location.COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
    }

    /**
     * Get Country.
     *
     * @return Country
     */
    public int getCountryId() {
        Integer ii = getValue(I_C_Location.COLUMNNAME_C_Country_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Country.
     *
     * @param C_Country_ID Country
     */
    public void setCountryId(int C_Country_ID) {
        if (C_Country_ID < 1) setValue(I_C_Location.COLUMNNAME_C_Country_ID, null);
        else setValue(I_C_Location.COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
    }

    /**
     * Get City.
     *
     * @return Identifies a City
     */
    public String getCity() {
        return getValue(I_C_Location.COLUMNNAME_City);
    }

    /**
     * Set City.
     *
     * @param City Identifies a City
     */
    public void setCity(String City) {
        setValue(I_C_Location.COLUMNNAME_City, City);
    }

    /**
     * Get Address.
     *
     * @return Location or Address
     */
    public int getLocationId() {
        Integer ii = getValue(I_C_Location.COLUMNNAME_C_Location_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Comments.
     *
     * @return Comments or additional information
     */
    public String getComments() {
        return getValue(I_C_Location.COLUMNNAME_Comments);
    }

    /**
     * Get Region.
     *
     * @return Identifies a geographical Region
     */
    public int getRegionId() {
        Integer ii = getValue(I_C_Location.COLUMNNAME_C_Region_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Region.
     *
     * @param C_Region_ID Identifies a geographical Region
     */
    public void setRegionId(int C_Region_ID) {
        if (C_Region_ID < 1) setValue(I_C_Location.COLUMNNAME_C_Region_ID, null);
        else setValue(I_C_Location.COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
    }

    /**
     * Get ZIP.
     *
     * @return Postal code
     */
    public String getPostal() {
        return getValue(I_C_Location.COLUMNNAME_Postal);
    }

    /**
     * Set ZIP.
     *
     * @param Postal Postal code
     */
    public void setPostal(String Postal) {
        setValue(I_C_Location.COLUMNNAME_Postal, Postal);
    }

    /**
     * Get Additional Zip.
     *
     * @return Additional ZIP or Postal code
     */
    public String getPostal_Add() {
        return getValue(I_C_Location.COLUMNNAME_Postal_Add);
    }

    /**
     * Get Region.
     *
     * @return Name of the Region
     */
    public String getRegionName() {
        return getValue(I_C_Location.COLUMNNAME_RegionName);
    }

    /**
     * Set Region.
     *
     * @param RegionName Name of the Region
     */
    public void setRegionName(String RegionName) {
        setValue(I_C_Location.COLUMNNAME_RegionName, RegionName);
    }

    @Override
    public int getTableId() {
        return I_C_Location.Table_ID;
    }
}
