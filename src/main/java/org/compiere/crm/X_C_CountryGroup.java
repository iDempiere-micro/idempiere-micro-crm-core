package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_CountryGroup;
import org.compiere.orm.BasePONameValue;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for C_CountryGroup
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_C_CountryGroup extends BasePONameValue implements I_C_CountryGroup, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_C_CountryGroup(Properties ctx, int C_CountryGroup_ID, String trxName) {
    super(ctx, C_CountryGroup_ID, trxName);
  }

  /** Load Constructor */
  public X_C_CountryGroup(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 7 - System - Client - Org
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    return "X_C_CountryGroup[" + getId() + "]";
  }

  /**
   * Get Country Group.
   *
   * @return Country Group
   */
  public int getC_CountryGroup_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_CountryGroup_ID);
    if (ii == null) return 0;
    return ii;
  }

    @Override
  public int getTableId() {
    return I_C_CountryGroup.Table_ID;
  }
}
