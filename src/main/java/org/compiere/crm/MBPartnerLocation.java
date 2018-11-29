package org.compiere.crm;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import kotliquery.Row;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_BPartner_Location;
import org.compiere.model.I_C_Location;
import org.compiere.orm.MSysConfig;
import org.compiere.orm.Query;

/**
 * Partner Location Model
 *
 * @author Jorg Janke
 * @version $Id: MBPartnerLocation.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 * @author Teo Sarca, www.arhipac.ro
 *     <li>FR [ 2788465 ] MBPartnerLocation.getForBPartner method add trxName https://sourceforge
 *         .net/tracker/index.php?func=detail&aid=2788465&group_id =176962&atid=879335
 */
public class MBPartnerLocation extends X_C_BPartner_Location implements I_C_BPartner_Location {
  /** */
  private static final long serialVersionUID = -8412652367051443276L;

  /**
   * Get Locations for BPartner
   *
   * @param ctx context
   * @param C_BPartner_ID bp
   * @return array of locations
   * @deprecated Since 3.5.3a. Please use {@link #getForBPartner(Properties, int, String)}.
   */
  public static MBPartnerLocation[] getForBPartner(Properties ctx, int C_BPartner_ID) {
    return getForBPartner(ctx, C_BPartner_ID, null);
  }

  /**
   * Get Locations for BPartner
   *
   * @param ctx context
   * @param C_BPartner_ID bp
   * @param trxName
   * @return array of locations
   */
  public static MBPartnerLocation[] getForBPartner(
      Properties ctx, int C_BPartner_ID, String trxName) {
    List<MBPartnerLocation> list =
        new Query(ctx, I_C_BPartner_Location.Table_Name, "C_BPartner_ID=?", trxName)
            .setParameters(C_BPartner_ID)
            .list();
    MBPartnerLocation[] retValue = new MBPartnerLocation[list.size()];
    list.toArray(retValue);
    return retValue;
  } // getForBPartner

  /**
   * ************************************************************************ Default Constructor
   *
   * @param ctx context
   * @param C_BPartner_Location_ID id
   * @param trxName transaction
   */
  public MBPartnerLocation(Properties ctx, int C_BPartner_Location_ID, String trxName) {
    super(ctx, C_BPartner_Location_ID, trxName);
    if (C_BPartner_Location_ID == 0) {
      setName(".");
      //
      setIsShipTo(true);
      setIsRemitTo(true);
      setIsPayFrom(true);
      setIsBillTo(true);
    }
  } // MBPartner_Location

  /**
   * BP Parent Constructor
   *
   * @param bp partner
   */
  public MBPartnerLocation(I_C_BPartner bp) {
    this(bp.getCtx(), 0, bp.get_TrxName());
    setClientOrg((org.idempiere.orm.PO) bp);
    // may (still) be 0
    set_ValueNoCheck("C_BPartner_ID", new Integer(bp.getC_BPartner_ID()));
  } // MBPartner_Location

  /**
   * Constructor from ResultSet row
   *
   * @param ctx context
   * @param rs current row of result set to be loaded
   * @param trxName transaction
   */
  public MBPartnerLocation(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } // MBPartner_Location

  public MBPartnerLocation(Properties ctx, Row row) {
    super(ctx, row);
  } // MBPartner_Location

  /** Cached Location */
  private MLocation m_location = null;
  /** Unique Name */
  private String m_uniqueName = null;

  private int m_unique = 0;

  @Override
  public I_C_Location getLocation() {
    return getLocation(false);
  }

  /**
   * Get Location/Address
   *
   * @param requery get again the location from DB - please note that if used out of transaction the
   *     result is get from the cache
   * @return location
   */
  public MLocation getLocation(boolean requery) {
    if (requery || m_location == null)
      m_location = MLocation.get(getCtx(), getC_Location_ID(), get_TrxName());
    return m_location;
  } // getLocation

  /**
   * String Representation
   *
   * @return info
   */
  public String toString() {
    StringBuilder sb =
        new StringBuilder("MBPartner_Location[ID=")
            .append(getId())
            .append(",C_Location_ID=")
            .append(getC_Location_ID())
            .append(",Name=")
            .append(getName())
            .append("]");
    return sb.toString();
  } // toString

  /**
   * ************************************************************************ Before Save. - Set
   * Name
   *
   * @param newRecord new
   * @return save
   */
  protected boolean beforeSave(boolean newRecord) {
    if (getC_Location_ID() == 0) return false;

    // Set New Name
    if (".".equals(getName())) {
      MLocation address = getLocation(true);
      setName(getBPLocName(address));
    }
    return true;
  } // beforeSave

  /**
   * Make name Unique
   *
   * @param address address
   */
  private void makeUnique(MLocation address) {
    m_uniqueName = "";

    // 0 - City
    if (m_unique >= 0 || m_uniqueName.length() == 0) {
      String xx = address.getCity();
      if (xx != null && xx.length() > 0) m_uniqueName = xx;
    }
    // 1 + Address1
    if (m_unique >= 1 || m_uniqueName.length() == 0) {
      String xx = address.getAddress1();
      if (xx != null && xx.length() > 0) {
        if (m_uniqueName.length() > 0) m_uniqueName += " ";
        m_uniqueName += xx;
      }
    }
    // 2 + Address2
    if (m_unique >= 2 || m_uniqueName.length() == 0) {
      String xx = address.getAddress2();
      if (xx != null && xx.length() > 0) {
        if (m_uniqueName.length() > 0) m_uniqueName += " ";
        m_uniqueName += xx;
      }
    }
    // 3 - Region
    if (m_unique >= 3 || m_uniqueName.length() == 0) {
      String xx = address.getRegionName(true);
      if (xx != null && xx.length() > 0) {
        if (m_uniqueName.length() > 0) m_uniqueName += " ";
        m_uniqueName += xx;
      }
    }
    // 4 - ID
    if (m_unique >= 4 || m_uniqueName.length() == 0) {
      int id = getId();
      if (id == 0) id = address.getId();
      m_uniqueName += "#" + id;
    }
  } // makeUnique

  public String getBPLocName(MLocation address) {
    m_uniqueName = getName();
    m_unique =
        MSysConfig.getIntValue(
            MSysConfig.START_VALUE_BPLOCATION_NAME, 0, getClientId(), getOrgId());
    if (m_unique < 0 || m_unique > 4) m_unique = 0;
    if (m_uniqueName != null) { // && m_uniqueName.equals(".")) {
      // default
      m_uniqueName = null;
      makeUnique(address);
    }

    // Check uniqueness
    MBPartnerLocation[] locations = getForBPartner(getCtx(), getC_BPartner_ID());
    boolean unique = locations.length == 0;
    while (!unique) {
      unique = true;
      for (int i = 0; i < locations.length; i++) {
        MBPartnerLocation location = locations[i];
        if (location.getC_BPartner_Location_ID() == getId()) continue;
        if (m_uniqueName.equals(location.getName())) {
          // m_uniqueName = null;
          m_unique++;
          makeUnique(address);
          unique = false;
          break;
        }
      }
    }
    return m_uniqueName.toString();
  }
} // MBPartnerLocation
