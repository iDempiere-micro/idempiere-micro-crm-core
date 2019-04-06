package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Dunning;
import org.compiere.orm.BasePOName;

/**
 * Generated Model for C_Dunning
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Dunning extends BasePOName implements I_C_Dunning {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Dunning(int C_Dunning_ID) {
        super(C_Dunning_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_Dunning(Row row) {
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
        return "X_C_Dunning[" + getId() + "]";
    }

    @Override
    public int getTableId() {
        return I_C_Dunning.Table_ID;
    }
}
