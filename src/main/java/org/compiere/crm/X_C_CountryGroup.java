package org.compiere.crm;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_C_CountryGroup;
import org.compiere.orm.BasePONameValue;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_CountryGroup
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_CountryGroup extends BasePONameValue implements I_C_CountryGroup, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_CountryGroup(Properties ctx, int C_CountryGroup_ID, String trxName) {
        super(ctx, C_CountryGroup_ID, trxName);
    }

    /**
     * Load Constructor
     */
    public X_C_CountryGroup(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
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
    public int getC_CountryGroup_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_CountryGroup_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Country Group.
     *
     * @param C_CountryGroup_ID Country Group
     */
    public void setC_CountryGroup_ID(int C_CountryGroup_ID) {
        if (C_CountryGroup_ID < 1) set_ValueNoCheck(COLUMNNAME_C_CountryGroup_ID, null);
        else set_ValueNoCheck(COLUMNNAME_C_CountryGroup_ID, C_CountryGroup_ID);
    }

    /**
     * Get C_CountryGroup_UU.
     *
     * @return C_CountryGroup_UU
     */
    public String getC_CountryGroup_UU() {
        return (String) get_Value(COLUMNNAME_C_CountryGroup_UU);
    }

    /**
     * Set C_CountryGroup_UU.
     *
     * @param C_CountryGroup_UU C_CountryGroup_UU
     */
    public void setC_CountryGroup_UU(String C_CountryGroup_UU) {
        set_Value(COLUMNNAME_C_CountryGroup_UU, C_CountryGroup_UU);
    }

    /**
     * Get Date From.
     *
     * @return Starting date for a range
     */
    public Timestamp getDateFrom() {
        return (Timestamp) get_Value(COLUMNNAME_DateFrom);
    }

    /**
     * Set Date From.
     *
     * @param DateFrom Starting date for a range
     */
    public void setDateFrom(Timestamp DateFrom) {
        set_Value(COLUMNNAME_DateFrom, DateFrom);
    }

    /**
     * Get Date To.
     *
     * @return End date of a date range
     */
    public Timestamp getDateTo() {
        return (Timestamp) get_Value(COLUMNNAME_DateTo);
    }

    /**
     * Set Date To.
     *
     * @param DateTo End date of a date range
     */
    public void setDateTo(Timestamp DateTo) {
        set_Value(COLUMNNAME_DateTo, DateTo);
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) get_Value(COLUMNNAME_Description);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
    }

    /**
     * Get Comment/Help.
     *
     * @return Comment or Hint
     */
    public String getHelp() {
        return (String) get_Value(COLUMNNAME_Help);
    }

    /**
     * Set Comment/Help.
     *
     * @param Help Comment or Hint
     */
    public void setHelp(String Help) {
        set_Value(COLUMNNAME_Help, Help);
    }

    @Override
    public int getTableId() {
        return I_C_CountryGroup.Table_ID;
    }
}
