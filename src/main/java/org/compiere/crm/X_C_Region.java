package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Region;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_Region
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Region extends BasePOName implements I_C_Region, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Region(Properties ctx, int C_Region_ID) {
        super(ctx, C_Region_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_Region(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_C_Region(Properties ctx, Row row) {
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
        return "X_C_Region[" + getId() + "]";
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
     * Set Country.
     *
     * @param C_Country_ID Country
     */
    public void setC_Country_ID(int C_Country_ID) {
        if (C_Country_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Country_ID, null);
        else set_ValueNoCheck(COLUMNNAME_C_Country_ID, C_Country_ID);
    }

    /**
     * Get Region.
     *
     * @return Identifies a geographical Region
     */
    public int getC_Region_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Region_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Default.
     *
     * @return Default value
     */
    public boolean isDefault() {
        return charToBoolean(get_Value(COLUMNNAME_IsDefault));
    }

    @Override
    public int getTableId() {
        return I_C_Region.Table_ID;
    }
}
