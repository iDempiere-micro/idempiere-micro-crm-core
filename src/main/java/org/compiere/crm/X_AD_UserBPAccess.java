package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_AD_UserBPAccess;
import org.compiere.orm.BasePOUser;
import org.idempiere.orm.I_Persistent;

/**
 * Generated Model for AD_UserBPAccess
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_UserBPAccess extends BasePOUser implements I_AD_UserBPAccess, I_Persistent {

  /** BPAccessType AD_Reference_ID=358 */
  public static final int BPACCESSTYPE_AD_Reference_ID = 358;
  /** Business Documents = B */
  public static final String BPACCESSTYPE_BusinessDocuments = "B";
  /** Requests = R */
  public static final String BPACCESSTYPE_Requests = "R";
  /** Assets, Download = A */
  public static final String BPACCESSTYPE_AssetsDownload = "A";
  /** DocBaseType AD_Reference_ID=183 */
  public static final int DOCBASETYPE_AD_Reference_ID = 183;
  /** GL Journal = GLJ */
  public static final String DOCBASETYPE_GLJournal = "GLJ";
  /** GL Document = GLD */
  public static final String DOCBASETYPE_GLDocument = "GLD";
  /** AP Invoice = API */
  public static final String DOCBASETYPE_APInvoice = "API";
  /** AP Payment = APP */
  public static final String DOCBASETYPE_APPayment = "APP";
  /** AR Invoice = ARI */
  public static final String DOCBASETYPE_ARInvoice = "ARI";
  /** AR Receipt = ARR */
  public static final String DOCBASETYPE_ARReceipt = "ARR";
  /** Sales Order = SOO */
  public static final String DOCBASETYPE_SalesOrder = "SOO";
  /** AR Pro Forma Invoice = ARF */
  public static final String DOCBASETYPE_ARProFormaInvoice = "ARF";
  /** Material Delivery = MMS */
  public static final String DOCBASETYPE_MaterialDelivery = "MMS";
  /** Material Receipt = MMR */
  public static final String DOCBASETYPE_MaterialReceipt = "MMR";
  /** Material Movement = MMM */
  public static final String DOCBASETYPE_MaterialMovement = "MMM";
  /** Purchase Order = POO */
  public static final String DOCBASETYPE_PurchaseOrder = "POO";
  /** Purchase Requisition = POR */
  public static final String DOCBASETYPE_PurchaseRequisition = "POR";
  /** Material Physical Inventory = MMI */
  public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";
  /** AP Credit Memo = APC */
  public static final String DOCBASETYPE_APCreditMemo = "APC";
  /** AR Credit Memo = ARC */
  public static final String DOCBASETYPE_ARCreditMemo = "ARC";
  /** Bank Statement = CMB */
  public static final String DOCBASETYPE_BankStatement = "CMB";
  /** Cash Journal = CMC */
  public static final String DOCBASETYPE_CashJournal = "CMC";
  /** Payment Allocation = CMA */
  public static final String DOCBASETYPE_PaymentAllocation = "CMA";
  /** Material Production = MMP */
  public static final String DOCBASETYPE_MaterialProduction = "MMP";
  /** Match Invoice = MXI */
  public static final String DOCBASETYPE_MatchInvoice = "MXI";
  /** Match PO = MXP */
  public static final String DOCBASETYPE_MatchPO = "MXP";
  /** Project Issue = PJI */
  public static final String DOCBASETYPE_ProjectIssue = "PJI";
  /** Maintenance Order = MOF */
  public static final String DOCBASETYPE_MaintenanceOrder = "MOF";
  /** Manufacturing Order = MOP */
  public static final String DOCBASETYPE_ManufacturingOrder = "MOP";
  /** Quality Order = MQO */
  public static final String DOCBASETYPE_QualityOrder = "MQO";
  /** Payroll = HRP */
  public static final String DOCBASETYPE_Payroll = "HRP";
  /** Distribution Order = DOO */
  public static final String DOCBASETYPE_DistributionOrder = "DOO";
  /** Manufacturing Cost Collector = MCC */
  public static final String DOCBASETYPE_ManufacturingCostCollector = "MCC";
  /** Fixed Assets Addition = FAA */
  public static final String DOCBASETYPE_FixedAssetsAddition = "FAA";
  /** Fixed Assets Disposal = FAD */
  public static final String DOCBASETYPE_FixedAssetsDisposal = "FAD";
  /** Fixed Assets Depreciation = FDP */
  public static final String DOCBASETYPE_FixedAssetsDepreciation = "FDP";
  /** */
  private static final long serialVersionUID = 20171031L;
  /** Standard Constructor */
  public X_AD_UserBPAccess(Properties ctx, int AD_UserBPAccess_ID) {
    super(ctx, AD_UserBPAccess_ID);
    /**
     * if (AD_UserBPAccess_ID == 0) { setAD_UserBPAccess_ID (0); setAD_User_ID (0); setBPAccessType
     * (null); }
     */
  }
  /** Load Constructor */
  public X_AD_UserBPAccess(Properties ctx, ResultSet rs) {
    super(ctx, rs);
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
    StringBuffer sb = new StringBuffer("X_AD_UserBPAccess[").append(getId()).append("]");
    return sb.toString();
  }

    /**
   * Get Request Type.
   *
   * @return Type of request (e.g. Inquiry, Complaint, ..)
   */
  public int getR_RequestType_ID() {
    Integer ii = (Integer) get_Value(COLUMNNAME_R_RequestType_ID);
    if (ii == null) return 0;
    return ii;
  }

    @Override
  public int getTableId() {
    return I_AD_UserBPAccess.Table_ID;
  }
}
