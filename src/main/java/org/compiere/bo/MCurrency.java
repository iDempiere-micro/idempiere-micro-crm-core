package org.compiere.bo;

import org.compiere.model.I_C_Currency;
import org.idempiere.common.util.CCache;

import java.sql.ResultSet;
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
            new CCache<Integer, MCurrency>(I_C_Currency.Table_Name, 50);
    /**
     * Cache System Currencies by using ISO code as key *
     */
    private static CCache<String, MCurrency> s_currenciesISO =
            new CCache<String, MCurrency>(I_C_Currency.Table_Name, "C_CurrencyISO", 50);

    /**
     * Currency Constructor
     *
     * @param ctx           context
     * @param C_Currency_ID id
     * @param trxName       transaction
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

    /**
     * Currency Constructor
     *
     * @param ctx              context
     * @param ISO_Code         ISO
     * @param Description      Name
     * @param CurSymbol        symbol
     * @param StdPrecision     prec
     * @param CostingPrecision prec
     * @param trxName          transaction
     */
    public MCurrency(
            Properties ctx,
            String ISO_Code,
            String Description,
            String CurSymbol,
            int StdPrecision,
            int CostingPrecision,
            String trxName) {
        super(ctx, 0);
        setISO_Code(ISO_Code);
        setDescription(Description);
        setCurSymbol(CurSymbol);
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
        Integer key = new Integer(C_Currency_ID);
        MCurrency retValue = (MCurrency) s_currencies.get(key);
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
    public static String getISO_Code(Properties ctx, int C_Currency_ID) {
        StringBuilder contextKey = new StringBuilder("C_Currency_").append(C_Currency_ID);
        String retValue = ctx.getProperty(contextKey.toString());
        if (retValue != null) return retValue;

        //	Create it
        MCurrency c = get(ctx, C_Currency_ID);
        retValue = c.getISO_Code();
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
        StringBuilder msgreturn =
                new StringBuilder("MCurrency[")
                        .append(getCurrencyId())
                        .append("-")
                        .append(getISO_Code())
                        .append("-")
                        .append(getCurSymbol())
                        .append(",")
                        .append(getDescription())
                        .append(",Precision=")
                        .append(getStdPrecision())
                        .append("/")
                        .append(getCostingPrecision());
        return msgreturn.toString();
    } //	toString
} //	MCurrency
