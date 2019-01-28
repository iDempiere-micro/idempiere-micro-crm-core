package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Greeting;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_Greeting
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_Greeting extends BasePOName implements I_C_Greeting, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_Greeting(Properties ctx, int C_Greeting_ID, String trxName) {
    super(ctx, C_Greeting_ID, trxName);
    /**
     * if (C_Greeting_ID == 0) { setC_Greeting_ID (0); setIsDefault (false); setIsFirstNameOnly
     * (false); setName (null); }
     */
  }

  /** Load Constructor */
  public X_C_Greeting(Properties ctx, ResultSet rs, String trxName) {
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
    StringBuffer sb = new StringBuffer("X_C_Greeting[").append(getId()).append("]");
    return sb.toString();
  }

    @Override
  public int getTableId() {
    return I_C_Greeting.Table_ID;
  }
}
