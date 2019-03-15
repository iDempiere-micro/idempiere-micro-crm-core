package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_UserBPAccess;
import org.compiere.orm.BasePOUser;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for AD_UserBPAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_UserBPAccess extends BasePOUser implements I_AD_UserBPAccess {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_UserBPAccess(Properties ctx, int AD_UserBPAccess_ID) {
        super(ctx, AD_UserBPAccess_ID);
        /**
         * if (AD_UserBPAccess_ID == 0) { setAD_UserBPAccess_ID (0); setUserId (0); setBPAccessType
         * (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_UserBPAccess(Properties ctx, Row row) {
        super(ctx, row);
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
        StringBuffer sb = new StringBuffer("X_AD_UserBPAccess[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return I_AD_UserBPAccess.Table_ID;
    }
}
