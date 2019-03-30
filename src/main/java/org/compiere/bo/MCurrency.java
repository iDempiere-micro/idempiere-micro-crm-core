package org.compiere.bo;

import kotliquery.Row;
import org.compiere.model.I_C_Currency;
import org.idempiere.common.util.CCache;

import java.util.Properties;

/**
 * Currency Model.
 *
 * @author Jorg Janke
 */
public class MCurrency extends X_C_Currency {
    /**
     *
     */
    private static final long serialVersionUID = 2262097171335518186L;

    /**
     * Store System Currencies *
     */
    private static CCache<Integer, MCurrency> s_currencies =
            new CCache<>(I_C_Currency.Table_Name, 50);

    /**
     * Currency Constructor
     *
     * @param ctx           context
     * @param C_Currency_ID id
     */
    public MCurrency(Properties ctx, int C_Currency_ID) {
        super(ctx, C_Currency_ID);
        if (C_Currency_ID == 0) {
            setIsEMUMember(false);
            setIsEuro(false);
            setStdPrecision(2);
            setCostingPrecision(4);
        }
    } //	MCurrency
    public MCurrency(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * Currency Constructor
     *  @param ctx              context
     * @param ISO_Code         ISO
     * @param Description      Name
     * @param CurSymbol        symbol
     * @param StdPrecision     precision
     * @param CostingPrecision precision
     */
    public MCurrency(
            Properties ctx,
            String ISO_Code,
            String Description,
            String CurSymbol,
            int StdPrecision,
            int CostingPrecision) {
        super(ctx, 0);
        setISOCode(ISO_Code);
        setDescription(Description);
        setCurrencySymbol(CurSymbol);
        setStdPrecision(StdPrecision);
        setCostingPrecision(CostingPrecision);
        setIsEMUMember(false);
        setIsEuro(false);
    } //	MCurrency

    /**
     * Get Currency
     *
     * @param ctx           Context
     * @param C_Currency_ID currency
     * @return ISO Code
     */
    public static MCurrency get(Properties ctx, int C_Currency_ID) {
        //	Try Cache
        Integer key = C_Currency_ID;
        MCurrency retValue = s_currencies.get(key);
        if (retValue != null) return retValue;

        //	Create it
        retValue = new MCurrency(ctx, C_Currency_ID);
        //	Save in System
        if (retValue.getClientId() == 0) s_currencies.put(key, retValue);
        return retValue;
    } //	get

    /**
     * Get Currency Iso Code.
     *
     * @param ctx           Context
     * @param C_Currency_ID currency
     * @return ISO Code
     */
    public static String getISOCode(Properties ctx, int C_Currency_ID) {
        StringBuilder contextKey = new StringBuilder("C_Currency_").append(C_Currency_ID);
        String retValue = ctx.getProperty(contextKey.toString());
        if (retValue != null) return retValue;

        //	Create it
        MCurrency c = get(ctx, C_Currency_ID);
        retValue = c.getISOCode();
        ctx.setProperty(contextKey.toString(), retValue);
        return retValue;
    } //	getISO

    /**
     * Get Standard Precision.
     *
     * @param ctx           Context
     * @param C_Currency_ID currency
     * @return Standard Precision
     */
    public static int getStdPrecision(Properties ctx, int C_Currency_ID) {
        MCurrency c = get(ctx, C_Currency_ID);
        return c.getStdPrecision();
    } //	getStdPrecision

    /**
     * Get Costing Precision.
     *
     * @param ctx           Context
     * @param C_Currency_ID currency
     * @return Costing Precision
     */
    public static int getCostingPrecision(Properties ctx, int C_Currency_ID) {
        MCurrency c = get(ctx, C_Currency_ID);
        return c.getCostingPrecision();
    }

    /**
     * String Representation
     *
     * @return info
     */
    public String toString() {
        return "MCurrency[" +
                getCurrencyId() +
                "-" +
                getISOCode() +
                "-" +
                getCurrencySymbol() +
                "," +
                getDescription() +
                ",Precision=" +
                getStdPrecision() +
                "/" +
                getCostingPrecision();
    } //	toString
} //	MCurrency
