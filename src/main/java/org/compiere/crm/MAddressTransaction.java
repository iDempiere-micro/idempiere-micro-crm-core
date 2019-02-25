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

    public MAddressTransaction(Properties ctx, int C_AddressTransaction_ID) {
        super(ctx, C_AddressTransaction_ID);
    }

    public MAddressTransaction(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

}
