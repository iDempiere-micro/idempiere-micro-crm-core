package org.compiere.bo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_C_SalesStage;
import org.compiere.orm.BasePONameValue;
import org.idempiere.common.util.Env;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_SalesStage
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_SalesStage extends BasePONameValue implements I_C_SalesStage, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_SalesStage(Properties ctx, int C_SalesStage_ID, String trxName) {
        super(ctx, C_SalesStage_ID, trxName);
    }

    /**
     * Load Constructor
     */
    public X_C_SalesStage(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
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
        StringBuffer sb = new StringBuffer("X_C_SalesStage[").append(getId()).append("]");
        return sb.toString();
    }

    /**
     * Get Sales Stage.
     *
     * @return Stages of the sales process
     */
    public int getC_SalesStage_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_SalesStage_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Sales Stage.
     *
     * @param C_SalesStage_ID Stages of the sales process
     */
    public void setC_SalesStage_ID(int C_SalesStage_ID) {
        if (C_SalesStage_ID < 1) set_ValueNoCheck(COLUMNNAME_C_SalesStage_ID, null);
        else set_ValueNoCheck(COLUMNNAME_C_SalesStage_ID, C_SalesStage_ID);
    }

    /**
     * Get C_SalesStage_UU.
     *
     * @return C_SalesStage_UU
     */
    public String getC_SalesStage_UU() {
        return (String) get_Value(COLUMNNAME_C_SalesStage_UU);
    }

    /**
     * Set C_SalesStage_UU.
     *
     * @param C_SalesStage_UU C_SalesStage_UU
     */
    public void setC_SalesStage_UU(String C_SalesStage_UU) {
        set_Value(COLUMNNAME_C_SalesStage_UU, C_SalesStage_UU);
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
     * Set Closed Status.
     *
     * @param IsClosed The status is closed
     */
    public void setIsClosed(boolean IsClosed) {
        set_Value(COLUMNNAME_IsClosed, IsClosed);
    }

    /**
     * Get Closed Status.
     *
     * @return The status is closed
     */
    public boolean isClosed() {
        return charToBoolean(get_Value(COLUMNNAME_IsClosed));
    }

    /**
     * Set Won.
     *
     * @param IsWon The opportunity was won
     */
    public void setIsWon(boolean IsWon) {
        set_Value(COLUMNNAME_IsWon, IsWon);
    }

    /**
     * Get Won.
     *
     * @return The opportunity was won
     */
    public boolean isWon() {
        return charToBoolean(get_Value(COLUMNNAME_IsWon));
    }

    /**
     * Get Probability.
     *
     * @return Probability
     */
    public BigDecimal getProbability() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_Probability);
        if (bd == null) return Env.ZERO;
        return bd;
    }

    /**
     * Set Probability.
     *
     * @param Probability Probability
     */
    public void setProbability(BigDecimal Probability) {
        set_Value(COLUMNNAME_Probability, Probability);
    }

    @Override
    public int getTableId() {
        return I_C_SalesStage.Table_ID;
    }
}
