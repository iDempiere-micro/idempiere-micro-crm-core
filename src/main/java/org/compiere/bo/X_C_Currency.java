package org.compiere.bo;

import kotliquery.Row;
import org.compiere.model.I_C_Currency;
import org.compiere.orm.PO;

import java.util.Properties;

/**
 * Generated Model for C_Currency
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Currency extends PO implements I_C_Currency {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Currency(Properties ctx, int C_Currency_ID) {
        super(ctx, C_Currency_ID);
    }
    public X_C_Currency(Properties ctx, Row row) {
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
        return "X_C_Currency[" + getId() + "]";
    }

    /**
     * Get Currency.
     *
     * @return The Currency for this record
     */
    public int getCurrencyId() {
        Integer ii = (Integer) getValue(COLUMNNAME_C_Currency_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Costing Precision.
     *
     * @return Rounding used costing calculations
     */
    public int getCostingPrecision() {
        Integer ii = (Integer) getValue(COLUMNNAME_CostingPrecision);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Costing Precision.
     *
     * @param CostingPrecision Rounding used costing calculations
     */
    public void setCostingPrecision(int CostingPrecision) {
        setValue(COLUMNNAME_CostingPrecision, CostingPrecision);
    }

    /**
     * Get Symbol.
     *
     * @return Symbol of the currency (opt used for printing only)
     */
    public String getCurrencySymbol() {
        return (String) getValue(COLUMNNAME_CurSymbol);
    }

    /**
     * Set Symbol.
     *
     * @param CurSymbol Symbol of the currency (opt used for printing only)
     */
    public void setCurrencySymbol(String CurSymbol) {
        setValue(COLUMNNAME_CurSymbol, CurSymbol);
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
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        setValue(COLUMNNAME_Description, Description);
    }

    /**
     * Set EMU Member.
     *
     * @param IsEMUMember This currency is member if the European Monetary Union
     */
    public void setIsEMUMember(boolean IsEMUMember) {
        setValue(COLUMNNAME_IsEMUMember, IsEMUMember);
    }

    /**
     * Set The Euro Currency.
     *
     * @param IsEuro This currency is the Euro
     */
    public void setIsEuro(boolean IsEuro) {
        setValue(COLUMNNAME_IsEuro, IsEuro);
    }

    /**
     * Get ISO Currency Code.
     *
     * @return Three letter ISO 4217 Code of the Currency
     */
    public String getISOCode() {
        return (String) getValue(COLUMNNAME_ISO_Code);
    }

    /**
     * Set ISO Currency Code.
     *
     * @param ISO_Code Three letter ISO 4217 Code of the Currency
     */
    public void setISOCode(String ISO_Code) {
        setValue(COLUMNNAME_ISO_Code, ISO_Code);
    }

    /**
     * Get Standard Precision.
     *
     * @return Rule for rounding calculated amounts
     */
    public int getStdPrecision() {
        Integer ii = (Integer) getValue(COLUMNNAME_StdPrecision);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Standard Precision.
     *
     * @param StdPrecision Rule for rounding calculated amounts
     */
    public void setStdPrecision(int StdPrecision) {
        setValue(COLUMNNAME_StdPrecision, StdPrecision);
    }

    @Override
    public int getTableId() {
        return I_C_Currency.Table_ID;
    }
}
