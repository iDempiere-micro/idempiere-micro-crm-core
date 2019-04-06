package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.orm.BasePOName;

import static org.compiere.model.I_C_BPartner_Location.COLUMNNAME_IsPreserveCustomName;

/**
 * Generated Model for C_BPartner_Location
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_BPartner_Location extends BasePOName {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_BPartner_Location(int C_BPartner_Location_ID) {
        super(C_BPartner_Location_ID);
        /**
         * if (C_BPartner_Location_ID == 0) { setBusinessPartnerId (0); setBusinessPartnerLocationId (0);
         * setLocationId (0); setIsBillTo (true); // Y setIsPayFrom (true); // Y setIsRemitTo (true);
         * // Y setIsShipTo (true); // Y setName (null); // . }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_BPartner_Location(Row row) {
        super(row);
    } // MBPartner_Location

    /**
     * AccessLevel
     *
     * @return 3 - Client - Org
     */
    protected int getAccessLevel() {
        return I_C_BPartner_Location.accessLevel.intValue();
    }

    public String toString() {
        return "X_C_BPartner_Location[" + getId() + "]";
    }

    /**
     * Get Business Partner .
     *
     * @return Identifies a Business Partner
     */
    public int getBusinessPartnerId() {
        Integer ii = (Integer) getValue(I_C_BPartner_Location.COLUMNNAME_C_BPartner_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Partner Location.
     *
     * @return Identifies the (ship to) address for this Business Partner
     */
    public int getBusinessPartnerLocationId() {
        Integer ii = (Integer) getValue(I_C_BPartner_Location.COLUMNNAME_C_BPartner_Location_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Address.
     *
     * @return Location or Address
     */
    public int getLocationId() {
        Integer ii = (Integer) getValue(I_C_BPartner_Location.COLUMNNAME_C_Location_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Address.
     *
     * @param C_Location_ID Location or Address
     */
    public void setLocationId(int C_Location_ID) {
        if (C_Location_ID < 1) setValue(I_C_BPartner_Location.COLUMNNAME_C_Location_ID, null);
        else setValue(I_C_BPartner_Location.COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
    }

    /**
     * Set Invoice Address.
     *
     * @param IsBillTo Business Partner Invoice/Bill Address
     */
    public void setIsBillTo(boolean IsBillTo) {
        setValue(I_C_BPartner_Location.COLUMNNAME_IsBillTo, Boolean.valueOf(IsBillTo));
    }

    /**
     * Set Pay-From Address.
     *
     * @param IsPayFrom Business Partner pays from that address and we'll send dunning letters there
     */
    public void setIsPayFrom(boolean IsPayFrom) {
        setValue(I_C_BPartner_Location.COLUMNNAME_IsPayFrom, Boolean.valueOf(IsPayFrom));
    }

    /**
     * Get Preserve custom name.
     *
     * @return Preserve custom name
     */
    public boolean isPreserveCustomName() {
        return charToBoolean(getValue(COLUMNNAME_IsPreserveCustomName));
    }

    /**
     * Set Remit-To Address.
     *
     * @param IsRemitTo Business Partner payment address
     */
    public void setIsRemitTo(boolean IsRemitTo) {
        setValue(I_C_BPartner_Location.COLUMNNAME_IsRemitTo, Boolean.valueOf(IsRemitTo));
    }

    /**
     * Set Ship Address.
     *
     * @param IsShipTo Business Partner Shipment Address
     */
    public void setIsShipTo(boolean IsShipTo) {
        setValue(I_C_BPartner_Location.COLUMNNAME_IsShipTo, Boolean.valueOf(IsShipTo));
    }

    @Override
    public int getTableId() {
        return I_C_BPartner_Location.Table_ID;
    }
}
