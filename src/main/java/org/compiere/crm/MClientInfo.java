package org.compiere.crm;

import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.MClient;
import org.compiere.orm.MTable;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;

public class MClientInfo extends org.compiere.orm.MClientInfo {
  /** */
  private static final long serialVersionUID = 4861006368856890116L;
  /** Logger */
  private static CLogger s_log = CLogger.getCLogger(MClientInfo.class);

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MClientInfo(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  }

  public MClientInfo(Properties ctx, Row row) {
    super(ctx, row);
  }

  public MClientInfo(
      MClient client,
      int AD_Tree_Org_ID,
      int AD_Tree_BPartner_ID,
      int AD_Tree_Project_ID,
      int AD_Tree_SalesRegion_ID,
      int AD_Tree_Product_ID,
      int AD_Tree_Campaign_ID,
      int AD_Tree_Activity_ID,
      String trxName) {
    super(
        client,
        AD_Tree_Org_ID,
        AD_Tree_BPartner_ID,
        AD_Tree_Project_ID,
        AD_Tree_SalesRegion_ID,
        AD_Tree_Product_ID,
        AD_Tree_Campaign_ID,
        AD_Tree_Activity_ID,
        trxName);
  }

  /**
   * Get optionally cached client
   *
   * @param ctx context
   * @return client
   */
  public static MClientInfo get(Properties ctx) {
    return get(ctx, Env.getClientId(ctx), null);
  } //	get

  /**
   * Get Client Info
   *
   * @param ctx context
   * @param AD_Client_ID id
   * @param trxName optional trx
   * @return Client Info
   */
  public static MClientInfo get(Properties ctx, int AD_Client_ID, String trxName) {
    return MBaseClientInfoKt.get(ctx, AD_Client_ID, trxName);
  } //	get

  /**
   * Get Client Info
   *
   * @param ctx context
   * @param AD_Client_ID id
   * @return Client Info
   */
  public static MClientInfo get(Properties ctx, int AD_Client_ID) {
    return get(ctx, AD_Client_ID, null);
  } //	get

  /**
   * Overwrite Save
   *
   * @return true if saved
   * @overwrite
   */
  public boolean save() {
    if (getOrgId() != 0) setAD_Org_ID(0);
    if (getCreateNew()) return super.save();
    return saveUpdate();
  } //	save

  public I_C_BPartner getC_BPartnerCashTrx() throws RuntimeException {
    return (I_C_BPartner)
        MTable.get(getCtx(), I_C_BPartner.Table_Name)
            .getPO(getC_BPartnerCashTrx_ID(), null);
  }
} //	MClientInfo
