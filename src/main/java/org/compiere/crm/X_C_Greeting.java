package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Greeting;
import org.compiere.orm.BasePOName;

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
    public X_C_Greeting(int C_Greeting_ID) {
        super(C_Greeting_ID);
        /**
         * if (C_Greeting_ID == 0) { setGreeting_ID (0); setIsDefault (false); setIsFirstNameOnly
         * (false); setName (null); }
         */
    }

    /**
     * Load Constructor
     */
    public X_C_Greeting(Row row) {
        super(row);
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
