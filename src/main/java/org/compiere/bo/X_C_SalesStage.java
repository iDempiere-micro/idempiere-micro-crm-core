package org.compiere.bo;

import org.compiere.model.I_C_SalesStage;
import org.compiere.orm.BasePONameValue;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_SalesStage
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_SalesStage extends BasePONameValue implements I_C_SalesStage {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_SalesStage(Properties ctx, int C_SalesStage_ID) {
        super(ctx, C_SalesStage_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_SalesStage(Properties ctx, ResultSet rs) {
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
        StringBuffer sb = new StringBuffer("X_C_SalesStage[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return I_C_SalesStage.Table_ID;
    }
}
