package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_CountryGroup;
import org.compiere.orm.BasePONameValue;

import java.util.Properties;

/**
 * Generated Model for C_CountryGroup
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_CountryGroup extends BasePONameValue implements I_C_CountryGroup {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_CountryGroup(Properties ctx, int C_CountryGroup_ID) {
        super(ctx, C_CountryGroup_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_CountryGroup(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 7 - System - Client - Org
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_C_CountryGroup[" + getId() + "]";
    }

    /**
     * Get Country Group.
     *
     * @return Country Group
     */
    public int getCountryGroupId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_CountryGroup_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return I_C_CountryGroup.Table_ID;
    }
}
