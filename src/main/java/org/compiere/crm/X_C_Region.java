package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Region;
import org.compiere.orm.BasePOName;


/**
 * Generated Model for C_Region
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Region extends BasePOName implements I_C_Region {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Region(int C_Region_ID) {
        super(C_Region_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_Region(Row row) {
        super(row);
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
        return "X_C_Region[" + getId() + "]";
    }

    /**
     * Get Country.
     *
     * @return Country
     */
    public int getCountryId() {
        Integer ii = getValue(COLUMNNAME_C_Country_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Country.
     *
     * @param C_Country_ID Country
     */
    public void setCountryId(int C_Country_ID) {
        if (C_Country_ID < 1) setValueNoCheck(COLUMNNAME_C_Country_ID, null);
        else setValueNoCheck(COLUMNNAME_C_Country_ID, C_Country_ID);
    }

    /**
     * Get Region.
     *
     * @return Identifies a geographical Region
     */
    public int getRegionId() {
        Integer ii = getValue(COLUMNNAME_C_Region_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Default.
     *
     * @return Default value
     */
    public boolean isDefault() {
        return charToBoolean(getValue(COLUMNNAME_IsDefault));
    }

    @Override
    public int getTableId() {
        return I_C_Region.Table_ID;
    }
}
