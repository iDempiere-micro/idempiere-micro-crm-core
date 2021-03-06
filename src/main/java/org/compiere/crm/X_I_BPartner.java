package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.HasName2;
import org.compiere.model.I_I_BPartner;
import org.compiere.orm.BasePOUser;

import static org.compiere.model.I_C_BPartner.COLUMNNAME_IsCustomer;
import static org.compiere.model.I_C_BPartner.COLUMNNAME_IsEmployee;
import static org.compiere.model.I_C_BPartner.COLUMNNAME_IsVendor;

/**
 * Generated Model for I_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_I_BPartner extends BasePOUser implements I_I_BPartner {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_I_BPartner(int I_BPartner_ID) {
        super(I_BPartner_ID);
    }

    /**
     * Load Constructor
     */
    public X_I_BPartner(Row row) {
        super(row);
    }

    /**
     * AccessLevel
     *
     * @return 2 - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_I_BPartner[" + getId() + "]";
    }

    /**
     * Get Business Partner Group.
     *
     * @return Business Partner Group
     */
    public int getBPGroupId() {
        Integer ii = getValue(COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Contact Name.
     *
     * @return Business Partner Contact Name
     */
    public String getContactName() {
        return getValue(COLUMNNAME_ContactName);
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return getValue(COLUMNNAME_Description);
    }

    /**
     * Get D-U-N-S.
     *
     * @return Dun & Bradstreet Number
     */
    public String getDUNS() {
        return getValue(COLUMNNAME_DUNS);
    }

    /**
     * Get EMail Address.
     *
     * @return Electronic Mail Address
     */
    public String getEMail() {
        return getValue(COLUMNNAME_EMail);
    }

    /**
     * Get NAICS/SIC.
     *
     * @return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
     */
    public String getNAICS() {
        return getValue(COLUMNNAME_NAICS);
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return getValue(HasName.COLUMNNAME_Name);
    }

    /**
     * Get Name 2.
     *
     * @return Additional Name
     */
    public String getName2() {
        return getValue(HasName2.COLUMNNAME_Name2);
    }

    /**
     * Get Tax ID.
     *
     * @return Tax Identification
     */
    public String getTaxID() {
        return getValue(COLUMNNAME_TaxID);
    }

    /**
     * Get Search Key.
     *
     * @return Search key for the record in the format required - must be unique
     */
    public String getValue() {
        return getValue(COLUMNNAME_Value);
    }

    @Override
    public int getTableId() {
        return I_I_BPartner.Table_ID;
    }

    /**
     * Get Vendor.
     *
     * @return Indicates if this Business Partner is a Vendor
     */
    public boolean isVendor() {
        Object oo = getValue(COLUMNNAME_IsVendor);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Employee.
     *
     * @return Indicates if this Business Partner is an employee
     */
    public boolean isEmployee() {
        Object oo = getValue(COLUMNNAME_IsEmployee);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }

    /**
     * Get Customer.
     *
     * @return Indicates if this Business Partner is a Customer
     */
    public boolean isCustomer() {
        Object oo = getValue(COLUMNNAME_IsCustomer);
        if (oo != null) {
            if (oo instanceof Boolean) return (Boolean) oo;
            return "Y".equals(oo);
        }
        return false;
    }
}
