package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_R_ContactInterest;
import org.compiere.orm.BasePOUser;

import java.sql.Timestamp;

/**
 * Generated Model for R_ContactInterest
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_R_ContactInterest extends BasePOUser implements I_R_ContactInterest {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_R_ContactInterest(int R_ContactInterest_ID) {
        super(R_ContactInterest_ID);
        /**
         * if (R_ContactInterest_ID == 0) { setUserId (0); // @AD_User_ID@ setR_InterestArea_ID (0);
         * }
         */
    }

    /**
     * Load Constructor
     */
    public X_R_ContactInterest(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 3 - Client - Org
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_R_ContactInterest[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Opt-out Date.
     *
     * @return Date the contact opted out
     */
    public Timestamp getOptOutDate() {
        return (Timestamp) getValue(COLUMNNAME_OptOutDate);
    }

    /**
     * Set Opt-out Date.
     *
     * @param OptOutDate Date the contact opted out
     */
    public void setOptOutDate(Timestamp OptOutDate) {
        setValue(COLUMNNAME_OptOutDate, OptOutDate);
    }

    /**
     * Get Interest Area.
     *
     * @return Interest Area or Topic
     */
    public int getInterestAreaId() {
        Integer ii = getValue(COLUMNNAME_R_InterestArea_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Interest Area.
     *
     * @param R_InterestArea_ID Interest Area or Topic
     */
    public void setInterestAreaId(int R_InterestArea_ID) {
        if (R_InterestArea_ID < 1) setValueNoCheck(COLUMNNAME_R_InterestArea_ID, null);
        else setValueNoCheck(COLUMNNAME_R_InterestArea_ID, R_InterestArea_ID);
    }

    /**
     * Set Subscribe Date.
     *
     * @param SubscribeDate Date the contact actively subscribed
     */
    public void setSubscribeDate(Timestamp SubscribeDate) {
        setValue(COLUMNNAME_SubscribeDate, SubscribeDate);
    }

    @Override
    public int getTableId() {
        return I_R_ContactInterest.Table_ID;
    }
}
