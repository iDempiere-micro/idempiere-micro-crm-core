package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_Job;
import org.compiere.orm.BasePOName;

/**
 * Generated Model for C_Job
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Job extends BasePOName implements I_C_Job {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_C_Job(int C_Job_ID) {
        super(C_Job_ID);
    }

    /**
     * Load Constructor
     */
    public X_C_Job(Row row) {
        super(row);
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
        return "X_C_Job[" + getId() + "]";
    }

    @Override
    public int getTableId() {
        return I_C_Job.Table_ID;
    }
}
