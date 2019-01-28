package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.HasName;
import org.compiere.model.HasName2;
import org.compiere.model.I_I_BPartner;
import org.compiere.orm.BasePOUser;
import org.idempiere.common.util.KeyNamePair;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for I_BPartner
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_I_BPartner extends BasePOUser implements I_I_BPartner, I_Persistent {

  /** */
  private static final long serialVersionUID = 20171031L;

  /** Standard Constructor */
  public X_I_BPartner(Properties ctx, int I_BPartner_ID, String trxName) {
    super(ctx, I_BPartner_ID, trxName);
  }

  /** Load Constructor */
  public X_I_BPartner(Properties ctx, ResultSet rs, String trxName) {
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
    return "X_I_BPartner[" + getId() + "]";
  }

    /**
   * Get Business Partner .
   *
   * @return Identifies a Business Partner
   */
  public int getC_BPartner_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BPartner_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Partner Location.
   *
   * @return Identifies the (ship to) address for this Business Partner
   */
  public int getC_BPartner_Location_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BPartner_Location_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Business Partner Group.
   *
   * @return Business Partner Group
   */
  public int getC_BP_Group_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_BP_Group_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Country.
   *
   * @return Country
   */
  public int getC_Country_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Country_ID);
    if (ii == null) return 0;
    return ii;
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
   * Get Contact Name.
   *
   * @return Business Partner Contact Name
   */
  public String getContactName() {
    return (String) get_Value(COLUMNNAME_ContactName);
  }

    /**
   * Get Region.
   *
   * @return Identifies a geographical Region
   */
  public int getC_Region_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_C_Region_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Description.
   *
   * @return Optional short description of the record
   */
  public String getDescription() {
    return (String) get_Value(COLUMNNAME_Description);
  }

    /**
   * Get D-U-N-S.
   *
   * @return Dun & Bradstreet Number
   */
  public String getDUNS() {
    return (String) get_Value(COLUMNNAME_DUNS);
  }

    /**
   * Get EMail Address.
   *
   * @return Electronic Mail Address
   */
  public String getEMail() {
    return (String) get_Value(COLUMNNAME_EMail);
  }

    /**
   * Get NAICS/SIC.
   *
   * @return Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
   */
  public String getNAICS() {
    return (String) get_Value(COLUMNNAME_NAICS);
  }

    /**
   * Get Name.
   *
   * @return Alphanumeric identifier of the entity
   */
  public String getName() {
    return (String) get_Value(HasName.Companion.getCOLUMNNAME_Name());
  }

    /**
   * Get Name 2.
   *
   * @return Additional Name
   */
  public String getName2() {
    return (String) get_Value(HasName2.Companion.getCOLUMNNAME_Name2());
  }

    /**
   * Get Interest Area.
   *
   * @return Interest Area or Topic
   */
  public int getR_InterestArea_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_R_InterestArea_ID);
    if (ii == null) return 0;
    return ii;
  }

    /**
   * Get Tax ID.
   *
   * @return Tax Identification
   */
  public String getTaxID() {
    return (String) get_Value(COLUMNNAME_TaxID);
  }

    /**
   * Get Search Key.
   *
   * @return Search key for the record in the format required - must be unique
   */
  public String getValue() {
    return (String) get_Value(COLUMNNAME_Value);
  }

    /**
   * Get Record ID/ColumnName
   *
   * @return ID/ColumnName pair
   */
  public KeyNamePair getKeyNamePair() {
    return new KeyNamePair(getId(), getValue());
  }

  @Override
  public int getTableId() {
    return I_I_BPartner.Table_ID;
  }
}
