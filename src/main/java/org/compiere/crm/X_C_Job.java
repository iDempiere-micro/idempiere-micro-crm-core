package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Job;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Job
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Job extends BasePOName implements I_C_Job, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Job(Properties ctx, int C_Job_ID, String trxName) {
    super(ctx, C_Job_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Job(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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

    /**
   * Get Position Category.
   *
   * @return Job Position Category
   */
  public int getC_JobCategory_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_JobCategory_ID);
    if (ii == null) return 0;
    return ii;
  }

    @Override
  public int getTableId() {
    return I_C_Job.Table_ID;
  }
}
