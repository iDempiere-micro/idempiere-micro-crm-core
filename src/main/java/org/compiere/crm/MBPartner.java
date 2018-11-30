package org.compiere.crm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import kotliquery.Row;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.orm.MTree_Base;
import org.compiere.orm.Query;
import org.compiere.util.Msg;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;

public class MBPartner extends MBaseBPartner implements I_C_BPartner {
  /** */
  private static final long serialVersionUID = -803727877324075871L;

  /** BP Group */
  private MBPGroup m_group = null;

  /**
   * Get Empty Template Business Partner
   *
   * @param ctx context
   * @param AD_Client_ID client
   * @return Template Business Partner or null
   */
  public static I_C_BPartner getTemplate(Properties ctx, int AD_Client_ID) {
    I_C_BPartner template = getBPartnerCashTrx(ctx, AD_Client_ID);
    if (template == null) template = new MBPartner(ctx, 0, null);
    //	Reset
    if (template != null) {
      template.set_ValueNoCheck("C_BPartner_ID", new Integer(0));
      template.set_ValueNoCheck("C_BPartner_UU", (String) null);
      template.setValue("");
      template.setName("");
      template.setName2(null);
      template.setDUNS("");
      template.setFirstSale(null);
      //
      template.setSO_CreditLimit(Env.ZERO);
      template.setSO_CreditUsed(Env.ZERO);
      template.setTotalOpenBalance(Env.ZERO);
      //	s_template.setRating(null);
      //
      template.setActualLifeTimeValue(Env.ZERO);
      template.setPotentialLifeTimeValue(Env.ZERO);
      template.setAcqusitionCost(Env.ZERO);
      template.setShareOfCustomer(0);
      template.setSalesVolume(0);
      // Reset Created, Updated to current system time ( teo_sarca )
      Timestamp ts = new Timestamp(System.currentTimeMillis());
      template.set_ValueNoCheck("Created", ts);
      template.set_ValueNoCheck("Updated", ts);
      template.set_ValueNoCheck("CreatedBy", Env.getAD_User_ID(ctx));
      template.set_ValueNoCheck("UpdatedBy", Env.getAD_User_ID(ctx));
    }
    return template;
  } //	getTemplate

  /**
   * Get Cash Trx Business Partner
   *
   * @param ctx context
   * @param AD_Client_ID client
   * @return Cash Trx Business Partner or null
   */
  public static I_C_BPartner getBPartnerCashTrx(Properties ctx, int AD_Client_ID) {
    I_C_BPartner retValue =
        (I_C_BPartner) MClientInfo.get(ctx, AD_Client_ID).getC_BPartnerCashTrx();
    if (retValue == null) s_log.log(Level.SEVERE, "Not found for AD_Client_ID=" + AD_Client_ID);

    return retValue;
  } //	getBPartnerCashTrx

  /**
   * Get BPartner with Value
   *
   * @param ctx context
   * @param Value value
   * @return BPartner or null
   */
  public static MBPartner get(Properties ctx, String Value) {
    return get(ctx, Value, null);
  }

  /**
   * Get BPartner with Value in a transaction
   *
   * @param ctx context
   * @param Value value
   * @param trxName transaction
   * @return BPartner or null
   */
  public static MBPartner get(Properties ctx, String Value, String trxName) {
    if (Value == null || Value.length() == 0) return null;
    final String whereClause = "Value=? AND AD_Client_ID=?";
    MBPartner retValue =
        new Query(ctx, I_C_BPartner.Table_Name, whereClause, trxName)
            .setParameters(Value, Env.getADClientID(ctx))
            .firstOnly();
    return retValue;
  } //	get

  /**
   * Get BPartner with Value
   *
   * @param ctx context
   * @param Value value
   * @return BPartner or null
   */
  public static I_C_BPartner get(Properties ctx, int C_BPartner_ID) {
    return get(ctx, C_BPartner_ID, null);
  }

  /**
   * Get BPartner with Value in a transaction
   *
   * @param ctx context
   * @param Value value
   * @param trxName transaction
   * @return BPartner or null
   */
  public static I_C_BPartner get(Properties ctx, int C_BPartner_ID, String trxName) {
    final String whereClause = "C_BPartner_ID=? AND AD_Client_ID=?";
    I_C_BPartner retValue =
        new Query(ctx, I_C_BPartner.Table_Name, whereClause, trxName)
            .setParameters(C_BPartner_ID, Env.getADClientID(ctx))
            .firstOnly();
    return retValue;
  } //	get

  /** Static Logger */
  private static CLogger s_log = CLogger.getCLogger(MBPartner.class);

  /**
   * ************************************************************************ Constructor for new
   * BPartner from Template
   *
   * @param ctx context
   */
  public MBPartner(Properties ctx) {
    this(ctx, -1, null);
  } //	MBPartner

  /**
   * Default Constructor
   *
   * @param ctx context
   * @param rs ResultSet to load from
   * @param trxName transaction
   */
  public MBPartner(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MBPartner
  public MBPartner(Properties ctx, Row row) {
    super(ctx, row);
  } //	MBPartner

  /**
   * Default Constructor
   *
   * @param ctx context
   * @param C_BPartner_ID partner or 0 or -1 (load from template)
   * @param trxName transaction
   */
  public MBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
    super(ctx, C_BPartner_ID, trxName);
    //
    if (C_BPartner_ID == -1) {
      initTemplate(Env.getContextAsInt(ctx, "AD_Client_ID"));
      C_BPartner_ID = 0;
    }
    if (C_BPartner_ID == 0) {
      //	setValue ("");
      //	setName ("");
      //	setName2 (null);
      //	setDUNS("");
      //
      setIsCustomer(true);
      setIsProspect(true);
      //
      setSendEMail(false);
      setIsOneTime(false);
      setIsVendor(false);
      setIsSummary(false);
      setIsEmployee(false);
      setIsSalesRep(false);
      setIsTaxExempt(false);
      setIsPOTaxExempt(false);
      setIsDiscountPrinted(false);
      //
      setSO_CreditLimit(Env.ZERO);
      setSO_CreditUsed(Env.ZERO);
      setTotalOpenBalance(Env.ZERO);
      setSOCreditStatus(SOCREDITSTATUS_NoCreditCheck);
      //
      setFirstSale(null);
      setActualLifeTimeValue(Env.ZERO);
      setPotentialLifeTimeValue(Env.ZERO);
      setAcqusitionCost(Env.ZERO);
      setShareOfCustomer(0);
      setSalesVolume(0);
    }
    if (log.isLoggable(Level.FINE)) log.fine(toString());
  } //	MBPartner

  /**
   * Import Constructor
   *
   * @param impBP import
   */
  public MBPartner(X_I_BPartner impBP) {
    this(impBP.getCtx(), 0, impBP.get_TrxName());
    setClientOrg(impBP);
    setUpdatedBy(impBP.getUpdatedBy());
    //
    String value = impBP.getValue();
    if (value == null || value.length() == 0) value = impBP.getEMail();
    if (value == null || value.length() == 0) value = impBP.getContactName();
    setValue(value);
    String name = impBP.getName();
    if (name == null || name.length() == 0) name = impBP.getContactName();
    if (name == null || name.length() == 0) name = impBP.getEMail();
    setName(name);
    setName2(impBP.getName2());
    setDescription(impBP.getDescription());
    //	setHelp(impBP.getHelp());
    setDUNS(impBP.getDUNS());
    setTaxID(impBP.getTaxID());
    setNAICS(impBP.getNAICS());
    setC_BP_Group_ID(impBP.getC_BP_Group_ID());
  } //	MBPartner

  /** Prim Address */
  private Integer m_primaryC_BPartner_Location_ID = null;
  /** Prim User */
  private Integer m_primaryAD_User_ID = null;

  public MUser[] getContacts() {
    return getContacts(false);
  }

  @Override
  public I_C_BPartner_Location[] getLocations() {
    return getLocations(false);
  }

  /**
   * Get explicit or first bill Location
   *
   * @param C_BPartner_Location_ID optional explicit location
   * @return location or null
   */
  public I_C_BPartner_Location getLocation(int C_BPartner_Location_ID) {
    I_C_BPartner_Location[] locations = getLocations(false);
    if (locations.length == 0) return null;
    I_C_BPartner_Location retValue = null;
    for (int i = 0; i < locations.length; i++) {
      if (locations[i].getC_BPartner_Location_ID() == C_BPartner_Location_ID) return locations[i];
      if (retValue == null && locations[i].isBillTo()) retValue = locations[i];
    }
    if (retValue == null) return locations[0];
    return retValue;
  } //	getLocation

  /**
   * ************************************************************************ String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb =
        new StringBuilder("MBPartner[ID=")
            .append(getId())
            .append(",Value=")
            .append(getValue())
            .append(",Name=")
            .append(getName())
            .append(",Open=")
            .append(getTotalOpenBalance())
            .append("]");
    return sb.toString();
  } //	toString

  /**
   * Set Client/Org
   *
   * @param AD_Client_ID client
   * @param AD_Org_ID org
   */
  public void setClientOrg(int AD_Client_ID, int AD_Org_ID) {
    super.setClientOrg(AD_Client_ID, AD_Org_ID);
  } //	setClientOrg

  /**
   * Set Linked Organization. (is Button)
   *
   * @param AD_OrgBP_ID
   */
  public void setAD_OrgBP_ID(int AD_OrgBP_ID) {
    if (AD_OrgBP_ID == 0) super.setAD_OrgBP_ID(null);
    else super.set_Value("AD_OrgBP_ID", AD_OrgBP_ID);
  } //	setAD_OrgBP_ID

  /**
   * Get Linked Organization. (is Button) The Business Partner is another Organization for explicit
   * Inter-Org transactions
   *
   * @return AD_Org_ID if BP
   */
  public int getAD_OrgBP_ID_Int() {
    String org = super.getAD_OrgBP_ID();
    if (org == null) return 0;
    int AD_OrgBP_ID = 0;
    try {
      AD_OrgBP_ID = Integer.parseInt(org);
    } catch (Exception ex) {
      log.log(Level.SEVERE, org, ex);
    }
    return AD_OrgBP_ID;
  } //	getAD_OrgBP_ID_Int

  /**
   * Get Primary C_BPartner_Location_ID
   *
   * @return C_BPartner_Location_ID
   */
  public int getPrimaryC_BPartner_Location_ID() {
    if (m_primaryC_BPartner_Location_ID == null) {
      I_C_BPartner_Location[] locs = getLocations(false);
      for (int i = 0; m_primaryC_BPartner_Location_ID == null && i < locs.length; i++) {
        if (locs[i].isBillTo()) {
          setPrimaryC_BPartner_Location_ID(locs[i].getC_BPartner_Location_ID());
          break;
        }
      }
      //	get first
      if (m_primaryC_BPartner_Location_ID == null && locs.length > 0)
        setPrimaryC_BPartner_Location_ID(locs[0].getC_BPartner_Location_ID());
    }
    if (m_primaryC_BPartner_Location_ID == null) return 0;
    return m_primaryC_BPartner_Location_ID.intValue();
  } //	getPrimaryC_BPartner_Location_ID

  /**
   * Get Primary C_BPartner_Location
   *
   * @return C_BPartner_Location
   */
  public MBPartnerLocation getPrimaryC_BPartner_Location() {
    if (m_primaryC_BPartner_Location_ID == null) {
      m_primaryC_BPartner_Location_ID = getPrimaryC_BPartner_Location_ID();
    }
    if (m_primaryC_BPartner_Location_ID == null) return null;
    return new MBPartnerLocation(getCtx(), m_primaryC_BPartner_Location_ID, null);
  } //	getPrimaryC_BPartner_Location

  /**
   * Set Primary C_BPartner_Location_ID
   *
   * @param C_BPartner_Location_ID id
   */
  public void setPrimaryC_BPartner_Location_ID(int C_BPartner_Location_ID) {
    m_primaryC_BPartner_Location_ID = new Integer(C_BPartner_Location_ID);
  } //	setPrimaryC_BPartner_Location_ID

  /**
   * Set Primary AD_User_ID
   *
   * @param AD_User_ID id
   */
  public void setPrimaryAD_User_ID(int AD_User_ID) {
    m_primaryAD_User_ID = new Integer(AD_User_ID);
  } //	setPrimaryAD_User_ID

  /** Set Credit Status */
  public void setSOCreditStatus() {
    BigDecimal creditLimit = getSO_CreditLimit();
    //	Nothing to do
    if (SOCREDITSTATUS_NoCreditCheck.equals(getSOCreditStatus())
        || SOCREDITSTATUS_CreditStop.equals(getSOCreditStatus())
        || Env.ZERO.compareTo(creditLimit) == 0) return;

    //	Above Credit Limit
    if (creditLimit.compareTo(getTotalOpenBalance()) < 0)
      setSOCreditStatus(SOCREDITSTATUS_CreditHold);
    else {
      //	Above Watch Limit
      BigDecimal watchAmt = creditLimit.multiply(getCreditWatchRatio());
      if (watchAmt.compareTo(getTotalOpenBalance()) < 0)
        setSOCreditStatus(SOCREDITSTATUS_CreditWatch);
      else //	is OK
      setSOCreditStatus(SOCREDITSTATUS_CreditOK);
    }
  } //	setSOCreditStatus

  /**
   * Get SO CreditStatus with additional amount
   *
   * @param additionalAmt additional amount in Accounting Currency
   * @return sinulated credit status
   */
  public String getSOCreditStatus(BigDecimal additionalAmt) {
    if (additionalAmt == null || additionalAmt.signum() == 0) return getSOCreditStatus();
    //
    BigDecimal creditLimit = getSO_CreditLimit();
    //	Nothing to do
    if (SOCREDITSTATUS_NoCreditCheck.equals(getSOCreditStatus())
        || SOCREDITSTATUS_CreditStop.equals(getSOCreditStatus())
        || Env.ZERO.compareTo(creditLimit) == 0) return getSOCreditStatus();

    //	Above (reduced) Credit Limit
    creditLimit = creditLimit.subtract(additionalAmt);
    if (creditLimit.compareTo(getTotalOpenBalance()) < 0) return SOCREDITSTATUS_CreditHold;

    //	Above Watch Limit
    BigDecimal watchAmt = creditLimit.multiply(getCreditWatchRatio());
    if (watchAmt.compareTo(getTotalOpenBalance()) < 0) return SOCREDITSTATUS_CreditWatch;

    //	is OK
    return SOCREDITSTATUS_CreditOK;
  } //	getSOCreditStatus

  /**
   * Get Credit Watch Ratio
   *
   * @return BP Group ratio or 0.9
   */
  public BigDecimal getCreditWatchRatio() {
    return getBPGroup().getCreditWatchRatio();
  } //	getCreditWatchRatio

  /**
   * Credit Status is Stop or Hold.
   *
   * @return true if Stop/Hold
   */
  public boolean isCreditStopHold() {
    String status = getSOCreditStatus();
    return SOCREDITSTATUS_CreditStop.equals(status) || SOCREDITSTATUS_CreditHold.equals(status);
  } //	isCreditStopHold
  /**
   * Get BP Group
   *
   * @return group
   */
  public MBPGroup getBPGroup() {
    if (m_group == null) {
      if (getC_BP_Group_ID() == 0) m_group = MBPGroup.getDefault(getCtx());
      else m_group = MBPGroup.get(getCtx(), getC_BP_Group_ID(), get_TrxName());
    }
    return m_group;
  } //	getBPGroup

  /**
   * Get BP Group
   *
   * @param group group
   */
  public void setBPGroup(MBPGroup group) {
    m_group = group;
    if (m_group == null) return;
    setC_BP_Group_ID(m_group.getC_BP_Group_ID());
    if (m_group.getC_Dunning_ID() != 0) setC_Dunning_ID(m_group.getC_Dunning_ID());
    if (m_group.getM_PriceList_ID() != 0) setM_PriceList_ID(m_group.getM_PriceList_ID());
    if (m_group.getPO_PriceList_ID() != 0) setPO_PriceList_ID(m_group.getPO_PriceList_ID());
    if (m_group.getM_DiscountSchema_ID() != 0)
      setM_DiscountSchema_ID(m_group.getM_DiscountSchema_ID());
    if (m_group.getPO_DiscountSchema_ID() != 0)
      setPO_DiscountSchema_ID(m_group.getPO_DiscountSchema_ID());
  } //	setBPGroup

  /**
   * Get PriceList
   *
   * @return price List
   */
  public int getM_PriceList_ID() {
    int ii = super.getM_PriceList_ID();
    if (ii == 0) ii = getBPGroup().getM_PriceList_ID();
    return ii;
  } //	getM_PriceList_ID

  /**
   * Get PO PriceList
   *
   * @return price list
   */
  public int getPO_PriceList_ID() {
    int ii = super.getPO_PriceList_ID();
    if (ii == 0) ii = getBPGroup().getPO_PriceList_ID();
    return ii;
  } //

  /**
   * Get DiscountSchema
   *
   * @return Discount Schema
   */
  public int getM_DiscountSchema_ID() {
    int ii = super.getM_DiscountSchema_ID();
    if (ii == 0) ii = getBPGroup().getM_DiscountSchema_ID();
    return ii;
  } //	getM_DiscountSchema_ID

  /**
   * Get PO DiscountSchema
   *
   * @return po discount
   */
  public int getPO_DiscountSchema_ID() {
    int ii = super.getPO_DiscountSchema_ID();
    if (ii == 0) ii = getBPGroup().getPO_DiscountSchema_ID();
    return ii;
  } //	getPO_DiscountSchema_ID

  /**
   * Before Save
   *
   * @param newRecord new
   * @return true
   */
  protected boolean beforeSave(boolean newRecord) {
    if (newRecord || is_ValueChanged("C_BP_Group_ID")) {
      MBPGroup grp = getBPGroup();
      if (grp == null) {
        log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@:  @C_BP_Group_ID@"));
        return false;
      }
      setBPGroup(grp); // 	setDefaults
    }
    return true;
  } //	beforeSave

  /**
   * ************************************************************************ After Save
   *
   * @param newRecord new
   * @param success success
   * @return success
   */
  protected boolean afterSave(boolean newRecord, boolean success) {
    if (!success) return success;
    if (newRecord) {
      //	Trees
      insert_Tree(MTree_Base.TREETYPE_BPartner);
    }
    if (newRecord || is_ValueChanged(COLUMNNAME_Value)) update_Tree(MTree_Base.TREETYPE_BPartner);

    //	Value/Name change
    if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name"))) {
      StringBuilder msgacc = new StringBuilder("C_BPartner_ID=").append(getC_BPartner_ID());
    }
    return success;
  } //	afterSave

  /**
   * After Delete
   *
   * @param success
   * @return deleted
   */
  protected boolean afterDelete(boolean success) {
    if (success) delete_Tree(MTree_Base.TREETYPE_BPartner);
    return success;
  } //	afterDelete

  @Override
  public boolean save() {
    return super.save();
  }

  @Override
  public void saveEx() {
    super.saveEx();
  }

  @Override
  public int getTableId() {
    return I_C_BPartner.Table_ID;
  }
} //	MBPartner
