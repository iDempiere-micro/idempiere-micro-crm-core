package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_AddressTransaction;
import org.compiere.orm.PO;

/**
 * Generated Model for C_AddressTransaction
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_AddressTransaction extends PO implements I_C_AddressTransaction {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_AddressTransaction(int C_AddressTransaction_ID) {
        super(C_AddressTransaction_ID);
        /**
         * if (C_AddressTransaction_ID == 0) { setAddressTransaction_ID (0); setAddressValidationId
         * (0); setIsValid (false); // N setProcessed (false); // N }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_AddressTransaction(Row row) {
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
        return "X_C_AddressTransaction[" + getId() + "]";
    }

    /**
     * Set Address 1.
     *
     * @param Address1 Address line 1 for this location
     */
    public void setAddress1(String Address1) {
        setValue(COLUMNNAME_Address1, Address1);
    }

    /**
     * Set Address 2.
     *
     * @param Address2 Address line 2 for this location
     */
    public void setAddress2(String Address2) {
        setValue(COLUMNNAME_Address2, Address2);
    }

    /**
     * Set Address 3.
     *
     * @param Address3 Address Line 3 for the location
     */
    public void setAddress3(String Address3) {
        setValue(COLUMNNAME_Address3, Address3);
    }

    /**
     * Set Address 4.
     *
     * @param Address4 Address Line 4 for the location
     */
    public void setAddress4(String Address4) {
        setValue(COLUMNNAME_Address4, Address4);
    }

    /**
     * Set Address 5.
     *
     * @param Address5 Address Line 5 for the location
     */
    public void setAddress5(String Address5) {
        setValue(COLUMNNAME_Address5, Address5);
    }

    /**
     * Set Address Validation.
     *
     * @param C_AddressValidation_ID Address Validation
     */
    public void setAddressValidationId(int C_AddressValidation_ID) {
        if (C_AddressValidation_ID < 1) setValueNoCheck(COLUMNNAME_C_AddressValidation_ID, null);
        else setValueNoCheck(COLUMNNAME_C_AddressValidation_ID, C_AddressValidation_ID);
    }

    /**
     * Get City.
     *
     * @return Identifies a City
     */
    public String getCity() {
        return getValue(COLUMNNAME_City);
    }

    /**
     * Set City.
     *
     * @param City Identifies a City
     */
    public void setCity(String City) {
        setValue(COLUMNNAME_City, City);
    }

    /**
     * Set Address.
     *
     * @param C_Location_ID Location or Address
     */
    public void setLocationId(int C_Location_ID) {
        if (C_Location_ID < 1) setValue(COLUMNNAME_C_Location_ID, null);
        else setValue(COLUMNNAME_C_Location_ID, C_Location_ID);
    }

    /**
     * Set Comments.
     *
     * @param Comments Comments or additional information
     */
    public void setComments(String Comments) {
        setValue(COLUMNNAME_Comments, Comments);
    }

    /**
     * Set Country.
     *
     * @param Country Country
     */
    public void setCountry(String Country) {
        setValueNoCheck(COLUMNNAME_Country, Country);
    }

    /**
     * Set ZIP.
     *
     * @param Postal Postal code
     */
    public void setPostal(String Postal) {
        setValue(COLUMNNAME_Postal, Postal);
    }

    /**
     * Set Region.
     *
     * @param Region Region
     */
    public void setRegion(String Region) {
        setValueNoCheck(COLUMNNAME_Region, Region);
    }

    @Override
    public int getTableId() {
        return I_C_AddressTransaction.Table_ID;
    }
}
