package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Dunning;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Dunning
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Dunning extends BasePOName implements I_C_Dunning, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Dunning(Properties ctx, int C_Dunning_ID, String trxName) {
    super(ctx, C_Dunning_ID, trxName);
  }

  /** Load Constructor */
  public X_C_Dunning(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
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
