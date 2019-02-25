package org.compiere.crm;

import org.compiere.model.I_C_Greeting;
import org.compiere.orm.BasePOName;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_Greeting
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Greeting extends BasePOName implements I_C_Greeting {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Greeting(Properties ctx, int C_Greeting_ID) {
        super(ctx, C_Greeting_ID);
        /**
         * if (C_Greeting_ID == 0) { setC_Greeting_ID (0); setIsDefault (false); setIsFirstNameOnly
         * (false); setName (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_Greeting(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        StringBuffer sb = new StringBuffer("X_C_Greeting[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return I_C_Greeting.Table_ID;
    }
}
