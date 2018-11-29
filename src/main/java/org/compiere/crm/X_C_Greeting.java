package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_C_Greeting;
import org.compiere.orm.BasePOName;
import org.idempiere.orm.I_Persistent;
import org.idempiere.orm.POInfo;

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

  /**
   * Set Greeting.
   *
   * @param C_Greeting_ID Greeting to print on correspondence
   */
  public void setC_Greeting_ID(int C_Greeting_ID) {
    if (C_Greeting_ID < 1) set_ValueNoCheck(COLUMNNAME_C_Greeting_ID, null);
    else set_ValueNoCheck(COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
  }

  /**
   * Get Greeting.
   *
   * @return Greeting to print on correspondence
   */
  public int getC_Greeting_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Greeting_ID);
    if (ii == null) return 0;
    return ii;
  }

  /**
   * Set C_Greeting_UU.
   *
   * @param C_Greeting_UU C_Greeting_UU
   */
  public void setC_Greeting_UU(String C_Greeting_UU) {
    set_Value(COLUMNNAME_C_Greeting_UU, C_Greeting_UU);
  }

  /**
   * Get C_Greeting_UU.
   *
   * @return C_Greeting_UU
   */
  public String getC_Greeting_UU() {
    return (String) get_Value(COLUMNNAME_C_Greeting_UU);
  }

  /**
   * Set Greeting.
   *
   * @param Greeting For letters, e.g. "Dear {0}" or "Dear Mr. {0}" - At runtime, "{0}" is replaced
   *     by the name
   */
  public void setGreeting(String Greeting) {
    set_Value(COLUMNNAME_Greeting, Greeting);
  }

  /**
   * Get Greeting.
   *
   * @return For letters, e.g. "Dear {0}" or "Dear Mr. {0}" - At runtime, "{0}" is replaced by the
   *     name
   */
  public String getGreeting() {
    return (String) get_Value(COLUMNNAME_Greeting);
  }

  /**
   * Set Default.
   *
   * @param IsDefault Default value
   */
  public void setIsDefault(boolean IsDefault) {
    set_Value(COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
  }

  /**
   * Get Default.
   *
   * @return Default value
   */
  public boolean isDefault() {
    return charToBoolean(get_Value(COLUMNNAME_IsDefault));
  }

  /**
   * Set First name only.
   *
   * @param IsFirstNameOnly Print only the first name in greetings
   */
  public void setIsFirstNameOnly(boolean IsFirstNameOnly) {
    set_Value(COLUMNNAME_IsFirstNameOnly, Boolean.valueOf(IsFirstNameOnly));
  }

  /**
   * Get First name only.
   *
   * @return Print only the first name in greetings
   */
  public boolean isFirstNameOnly() {
    return charToBoolean(get_Value(COLUMNNAME_IsFirstNameOnly));
  }

  @Override
  public int getTableId() {
    return I_C_Greeting.Table_ID;
  }
}
