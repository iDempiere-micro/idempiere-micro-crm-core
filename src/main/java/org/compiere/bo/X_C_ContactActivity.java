package org.compiere.bo;

import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_ContactActivity;
import org.compiere.orm.BasePOUser;
import org.compiere.orm.MTable;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Generated Model for C_ContactActivity
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_ContactActivity extends BasePOUser implements I_C_ContactActivity {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_ContactActivity(Properties ctx, int C_ContactActivity_ID) {
        super(ctx, C_ContactActivity_ID);
        /**
         * if (C_ContactActivity_ID == 0) { setC_ContactActivity_ID (0); setContactActivityType (null);
         * setDescription (null); setStartDate (new Timestamp( System.currentTimeMillis() ));
         * // @SQL=SELECT SYSDATE AS DefaultValue FROM DUAL }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_ContactActivity(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    /**
     * Load Constructor with rename
     */
    public X_C_ContactActivity(
            Properties ctx, ResultSet rs, String columnNamePrefix) {
        super(ctx, rs, columnNamePrefix);
    }

    /**
     * AccessLevel
     *
     * @return 3 - Client - Org
     */
    protected int getAccessLevel() {
        return I_C_ContactActivity.accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_C_ContactActivity[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Contact Activity.
     *
     * @return Events, tasks, communications related to a contact
     */
    public int getContactActivityId() {
        Integer ii = (Integer) getValue(I_C_ContactActivity.COLUMNNAME_C_ContactActivity_ID);
        if (ii == null) return 0;
        return ii;
    }

    public org.compiere.model.I_C_Opportunity getOpportunity() throws RuntimeException {
        return (org.compiere.model.I_C_Opportunity)
                MTable.get(getCtx(), org.compiere.model.I_C_Opportunity.Table_Name)
                        .getPO(getOpportunityId());
    }

    public void setC_Opportunity(org.compiere.model.I_C_Opportunity opportunity) {
        setValue(I_C_ContactActivity.COLUMNNAME_C_Opportunity_ID, opportunity.getId());
    }

    /**
     * Get Sales Opportunity.
     *
     * @return Sales Opportunity
     */
    public int getOpportunityId() {
        Integer ii = (Integer) getValue(I_C_ContactActivity.COLUMNNAME_C_Opportunity_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(I_C_ContactActivity.COLUMNNAME_Description);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        setValue(COLUMNNAME_Description, Description);
    }

    /**
     * Get Complete.
     *
     * @return It is complete
     */
    public boolean isComplete() {
        Object oo = getValue(I_C_ContactActivity.COLUMNNAME_IsComplete);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    public org.compiere.model.I_AD_User getSalesRepresentative() throws RuntimeException {
        return (org.compiere.model.I_AD_User)
                MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name).getPO(getSalesRepresentativeId());
    }

    @Override
    public void setSalesRepresentative(I_AD_User salesRepresentative) throws RuntimeException {
        setSalesRepresentativeId( salesRepresentative == null ? 0 : salesRepresentative.getId() );
    }

    /**
     * Get Sales Representative.
     *
     * @return Sales Representative or Company Agent
     */
    public int getSalesRepresentativeId() {
        Integer ii = (Integer) getValue(I_C_ContactActivity.COLUMNNAME_SalesRep_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public void setSalesRepresentativeId(int salesRepresentativeId) {
        if (salesRepresentativeId < 1)
            setValue (COLUMNNAME_SalesRep_ID, null);
        else
            setValue (COLUMNNAME_SalesRep_ID, salesRepresentativeId);
    }

    /**
     * Get Start Date.
     *
     * @return First effective day (inclusive)
     */
    public Timestamp getStartDate() {
        return (Timestamp) getValue(I_C_ContactActivity.COLUMNNAME_StartDate);
    }

    public void setStartDate(Timestamp value) {
        setValue(I_C_ContactActivity.COLUMNNAME_StartDate, value);
    }

    @Override
    public int getTableId() {
        return I_C_ContactActivity.Table_ID;
    }

    /**
     * Get Activity Type.
     *
     * @return Type of activity, e.g. task, email, phone call
     */
    public String getContactActivityType() {
        return (String) getValue(COLUMNNAME_ContactActivityType);
    }

    /**
     * Set Activity Type.
     *
     * @param ContactActivityType Type of activity, e.g. task, email, phone call
     */
    public void setContactActivityType(String ContactActivityType) {

        setValueNoCheck(COLUMNNAME_ContactActivityType, ContactActivityType);
    }
}
