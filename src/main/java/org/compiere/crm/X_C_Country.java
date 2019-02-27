package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Country;
import org.compiere.orm.BasePOName;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_Country
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Country extends BasePOName implements I_C_Country {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Country(Properties ctx, int C_Country_ID) {
        super(ctx, C_Country_ID);
        /**
         * if (C_Country_ID == 0) { setC_Country_ID (0); setCountryCode (null); setDisplaySequence
         * (null); // @C@, @R@ @P@ setHasPostal_Add (false); setHasRegion (false);
         * setIsAddressLinesLocalReverse (false); setIsAddressLinesReverse (false); setName (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_Country(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_C_Country(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_C_Country[" + getId() + "]";
    }

    /**
     * Get Allow Cities out of List.
     *
     * @return A flag to allow cities, currently not in the list, to be entered
     */
    public boolean isAllowCitiesOutOfList() {
        Object oo = getValue(COLUMNNAME_AllowCitiesOutOfList);
        if (oo != null) {
            if (oo instanceof Boolean) return ((Boolean) oo).booleanValue();
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Country.
     *
     * @return Country
     */
    public int getC_Country_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_Country_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get ISO Country Code.
     *
     * @return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 -
     * http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
     */
    public String getCountryCode() {
        return (String) getValue(COLUMNNAME_CountryCode);
    }

    /**
     * Get Address Print Format.
     *
     * @return Format for printing this Address
     */
    public String getDisplaySequence() {
        return (String) getValue(COLUMNNAME_DisplaySequence);
    }

    /**
     * Set Address Print Format.
     *
     * @param DisplaySequence Format for printing this Address
     */
    public void setDisplaySequence(String DisplaySequence) {
        setValue(COLUMNNAME_DisplaySequence, DisplaySequence);
    }

    /**
     * Get Local Address Format.
     *
     * @return Format for printing this Address locally
     */
    public String getDisplaySequenceLocal() {
        return (String) getValue(COLUMNNAME_DisplaySequenceLocal);
    }

    /**
     * Set Additional Postal code.
     *
     * @param HasPostal_Add Has Additional Postal Code
     */
    public void setHasPostal_Add(boolean HasPostal_Add) {
        setValue(COLUMNNAME_HasPostal_Add, Boolean.valueOf(HasPostal_Add));
    }

    /**
     * Get Country has Region.
     *
     * @return Country contains Regions
     */
    public boolean isHasRegion() {
        return charToBoolean(getValue(COLUMNNAME_HasRegion));
    }

    /**
     * Set Country has Region.
     *
     * @param HasRegion Country contains Regions
     */
    public void setHasRegion(boolean HasRegion) {
        setValue(COLUMNNAME_HasRegion, Boolean.valueOf(HasRegion));
    }

    /**
     * Set Reverse Local Address Lines.
     *
     * @param IsAddressLinesLocalReverse Print Local Address in reverse Order
     */
    public void setIsAddressLinesLocalReverse(boolean IsAddressLinesLocalReverse) {
        setValue(COLUMNNAME_IsAddressLinesLocalReverse, Boolean.valueOf(IsAddressLinesLocalReverse));
    }

    /**
     * Get Reverse Local Address Lines.
     *
     * @return Print Local Address in reverse Order
     */
    public boolean isAddressLinesLocalReverse() {
        return charToBoolean(getValue(COLUMNNAME_IsAddressLinesLocalReverse));
    }

    /**
     * Set Reverse Address Lines.
     *
     * @param IsAddressLinesReverse Print Address in reverse Order
     */
    public void setIsAddressLinesReverse(boolean IsAddressLinesReverse) {
        setValue(COLUMNNAME_IsAddressLinesReverse, Boolean.valueOf(IsAddressLinesReverse));
    }

    /**
     * Get Reverse Address Lines.
     *
     * @return Print Address in reverse Order
     */
    public boolean isAddressLinesReverse() {
        return charToBoolean(getValue(COLUMNNAME_IsAddressLinesReverse));
    }

    @Override
    public int getTableId() {
        return I_C_Country.Table_ID;
    }
}
