package org.compiere.crm;

import org.compiere.model.HasName;
import org.compiere.model.HasName2;
import org.compiere.model.I_I_BPartner;
import org.compiere.orm.BasePOUser;

import java.sql.ResultSet;
import java.util.Properties;

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
    public X_I_BPartner(Properties ctx, int I_BPartner_ID) {
        super(ctx, I_BPartner_ID);
    }

    /**
     * Load Constructor
     */
    public X_I_BPartner(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
    public int getC_BP_Group_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_BP_Group_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Contact Name.
     *
     * @return Business Partner Contact Name
     */
    public String getContactName() {
        return (String) getValue(COLUMNNAME_ContactName);
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(COLUMNNAME_Description);
    }

    /**
     * Get D-U-N-S.
     *
     * @return Dun & Bradstreet Number
     */
    public String getDUNS() {
        return (String) getValue(COLUMNNAME_DUNS);
    }

    /**
     * Get EMail Address.
     *
     * @return Electronic Mail Address
     */
    public String getEMail() {
        return (String) getValue(COLUMNNAME_EMail);
    }

    /**
     * Get NAICS/SIC.
     *
     * @return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
     */
    public String getNAICS() {
        return (String) getValue(COLUMNNAME_NAICS);
    }

    /**
     * Get Name.
     *
     * @return Alphanumeric identifier of the entity
     */
    public String getName() {
        return (String) getValue(HasName.Companion.getCOLUMNNAME_Name());
    }

    /**
     * Get Name 2.
     *
     * @return Additional Name
     */
    public String getName2() {
        return (String) getValue(HasName2.Companion.getCOLUMNNAME_Name2());
    }

    /**
     * Get Tax ID.
     *
     * @return Tax Identification
     */
    public String getTaxID() {
        return (String) getValue(COLUMNNAME_TaxID);
    }

    /**
     * Get Search Key.
     *
     * @return Search key for the record in the format required - must be unique
     */
    public String getValue() {
        return (String) getValue(COLUMNNAME_Value);
    }

    @Override
    public int getTableId() {
        return I_I_BPartner.Table_ID;
    }
}
