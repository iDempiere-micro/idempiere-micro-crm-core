package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Country;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_Country
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Country extends BasePOName implements I_C_Country, I_Persistent {

    /**
     * AD_Language AD_Reference_ID=106
     */
    public static final int AD_LANGUAGE_AD_Reference_ID = 106;
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
        Object oo = get_Value(COLUMNNAME_AllowCitiesOutOfList);
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
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Country_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Currency.
     *
     * @return The Currency for this record
     */
    public int getC_Currency_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Currency_ID);
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
        return (String) get_Value(COLUMNNAME_CountryCode);
    }

    /**
     * Get Address Print Format.
     *
     * @return Format for printing this Address
     */
    public String getDisplaySequence() {
        return (String) get_Value(COLUMNNAME_DisplaySequence);
    }

    /**
     * Set Address Print Format.
     *
     * @param DisplaySequence Format for printing this Address
     */
    public void setDisplaySequence(String DisplaySequence) {
        set_Value(COLUMNNAME_DisplaySequence, DisplaySequence);
    }

    /**
     * Get Local Address Format.
     *
     * @return Format for printing this Address locally
     */
    public String getDisplaySequenceLocal() {
        return (String) get_Value(COLUMNNAME_DisplaySequenceLocal);
    }

    /**
     * Set Additional Postal code.
     *
     * @param HasPostal_Add Has Additional Postal Code
     */
    public void setHasPostal_Add(boolean HasPostal_Add) {
        set_Value(COLUMNNAME_HasPostal_Add, Boolean.valueOf(HasPostal_Add));
    }

    /**
     * Get Country has Region.
     *
     * @return Country contains Regions
     */
    public boolean isHasRegion() {
        return charToBoolean(get_Value(COLUMNNAME_HasRegion));
    }

    /**
     * Set Country has Region.
     *
     * @param HasRegion Country contains Regions
     */
    public void setHasRegion(boolean HasRegion) {
        set_Value(COLUMNNAME_HasRegion, Boolean.valueOf(HasRegion));
    }

    /**
     * Set Reverse Local Address Lines.
     *
     * @param IsAddressLinesLocalReverse Print Local Address in reverse Order
     */
    public void setIsAddressLinesLocalReverse(boolean IsAddressLinesLocalReverse) {
        set_Value(COLUMNNAME_IsAddressLinesLocalReverse, Boolean.valueOf(IsAddressLinesLocalReverse));
    }

    /**
     * Get Reverse Local Address Lines.
     *
     * @return Print Local Address in reverse Order
     */
    public boolean isAddressLinesLocalReverse() {
        return charToBoolean(get_Value(COLUMNNAME_IsAddressLinesLocalReverse));
    }

    /**
     * Set Reverse Address Lines.
     *
     * @param IsAddressLinesReverse Print Address in reverse Order
     */
    public void setIsAddressLinesReverse(boolean IsAddressLinesReverse) {
        set_Value(COLUMNNAME_IsAddressLinesReverse, Boolean.valueOf(IsAddressLinesReverse));
    }

    /**
     * Get Reverse Address Lines.
     *
     * @return Print Address in reverse Order
     */
    public boolean isAddressLinesReverse() {
        return charToBoolean(get_Value(COLUMNNAME_IsAddressLinesReverse));
    }

    @Override
    public int getTableId() {
        return I_C_Country.Table_ID;
    }
}
