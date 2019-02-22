package org.compiere.crm;

import org.compiere.model.I_C_JobCategory;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Generated Model for C_JobCategory
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_JobCategory extends BasePOName implements I_C_JobCategory, I_Persistent {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_JobCategory(Properties ctx, int C_JobCategory_ID) {
        super(ctx, C_JobCategory_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_JobCategory(Properties ctx, ResultSet rs) {
        super(ctx, rs);
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
        StringBuffer sb = new StringBuffer("X_C_JobCategory[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return I_C_JobCategory.Table_ID;
    }
}
