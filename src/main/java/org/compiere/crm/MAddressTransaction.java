package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Address transaction model
 *
 * @author Elaine
 */
public class MAddressTransaction extends X_C_AddressTransaction {
    /**
     *
     */
    private static final long serialVersionUID = 8572809249265680649L;
    /**
     * Error Message
     */
    private String m_errorMessage = null;

    public MAddressTransaction(Properties ctx, int C_AddressTransaction_ID, String trxName) {
        super(ctx, C_AddressTransaction_ID, trxName);
    }

    public MAddressTransaction(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Get error message
     *
     * @return error message
     */
    public String getErrorMessage() {
        return m_errorMessage;
    }

    /**
     * Set error message
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        m_errorMessage = errorMessage;
    }
}
