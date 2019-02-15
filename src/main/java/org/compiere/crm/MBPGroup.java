package org.compiere.crm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import kotliquery.Row;
import org.compiere.model.I_C_BP_Group;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;

/**
 * Business Partner Group Model
 *
 * @author Jorg Janke
 * @version $Id: MBPGroup.java,v 1.4 2006/09/23 15:54:22 jjanke Exp $
 */
public class MBPGroup extends X_C_BP_Group {
  /** */
  private static final long serialVersionUID = 8897399796117872715L;
  /** Cache */
  private static CCache<Integer, MBPGroup> s_cache =
      new CCache<Integer, MBPGroup>(I_C_BP_Group.Table_Name, 10);
  /** Logger */
  private static CLogger s_log = CLogger.getCLogger(MBPGroup.class);

  /**
   * Standard Constructor
   *
   * @param ctx context
   * @param C_BP_Group_ID id
   * @param trxName transaction
   */
  public MBPGroup(Properties ctx, int C_BP_Group_ID) {
    super(ctx, C_BP_Group_ID);
    if (C_BP_Group_ID == 0) {
      //	setValue (null);
      //	setName (null);
      setIsConfidentialInfo(false); // N
      setIsDefault(false);
      setPriorityBase(X_C_BP_Group.PRIORITYBASE_Same);
    }
  } //	MBPGroup

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs result set
   * @param trxName transaction
   */
  public MBPGroup(Properties ctx, ResultSet rs) {
    super(ctx, rs);
  } //	MBPGroup

  public MBPGroup(Properties ctx, Row row) {
    super(ctx, row);
  } //	MBPGroup

  /**
   * Get MBPGroup from Cache
   *
   * @param ctx context
   * @param C_BP_Group_ID id
   * @return MBPGroup
   */
  public static MBPGroup get(Properties ctx, int C_BP_Group_ID) {
    Integer key = C_BP_Group_ID;
    MBPGroup retValue = s_cache.get(key);
    if (retValue != null) return retValue;
    retValue = new MBPGroup(ctx, C_BP_Group_ID);
    if (retValue.getId() != 0) s_cache.put(key, retValue);
    return retValue;
  } //	get

  /**
   * Get Default MBPGroup
   *
   * @param ctx context
   * @return MBPGroup
   */
  public static MBPGroup getDefault(Properties ctx) {
    return MBaseBPGroupKt.getDefault(ctx);
  } //	get

  /**
   * Get Credit Watch Percent
   *
   * @return 90 or defined percent
   */
  public BigDecimal getCreditWatchPercent() {
    BigDecimal bd = super.getCreditWatchPercent();
    if (bd != null) return bd;
    return new BigDecimal(90);
  } //	getCreditWatchPercent

  /**
   * Get Credit Watch Ratio
   *
   * @return 0.90 or defined percent
   */
  public BigDecimal getCreditWatchRatio() {
    BigDecimal bd = super.getCreditWatchPercent();
    if (bd.compareTo(Env.ZERO) != 0) return bd.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP);
    return BigDecimal.valueOf(0.90);
  } //	getCreditWatchRatio

  @Override
  protected boolean beforeSave(boolean newRecord) {
    // TODO Auto-generated method stub
    return true;
  }

  /**
   * After Save
   *
   * @param newRecord new record
   * @param success success
   * @return success
   */
  protected boolean afterSave(boolean newRecord, boolean success) {
    return success;
  } //	afterSave
} //	MBPGroup
