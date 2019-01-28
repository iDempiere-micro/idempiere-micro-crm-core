package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_PrintColor;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_PrintColor
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PrintColor extends BasePOName implements I_AD_PrintColor, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_AD_PrintColor(Properties ctx, int AD_PrintColor_ID, String trxName) {
    super(ctx, AD_PrintColor_ID, trxName);
  }

  /** Load Constructor */
  public X_AD_PrintColor(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  /**
   * AccessLevel
   *
   * @return 6 - System - Client
   */
  protected int getAccessLevel() {
    return accessLevel.intValue();
  }

  public String toString() {
    StringBuffer sb = new StringBuffer("X_AD_PrintColor[").append(getId()).append("]");
    return sb.toString();
  }

    @Override
  public int getTableId() {
    return I_AD_PrintColor.Table_ID;
  }
}
