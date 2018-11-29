package org.compiere.crm;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.MClient;
import org.compiere.orm.MRole;
import org.compiere.orm.MSysConfig;
import org.compiere.orm.Query;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Secure;
import org.idempiere.common.util.SecureEngine;
import org.idempiere.common.util.Util;
import software.hsharp.core.models.IUser;
import software.hsharp.core.util.DB;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * User Model
 *
 * @author Jorg Janke
 * @version $Id: MUser.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 * @author Teo Sarca, www.arhipac.ro
 *     <li>FR [ 2788430 ] MUser.getOfBPartner add trxName parameter
 *         https://sourceforge.net/tracker/index.php?func=detail&aid=2788430&group_id=176962&atid=879335
 */
public class MUser extends X_AD_User implements IUser {
  /** */
  private static final long serialVersionUID = 9027688865361175114L;

  /**
   * Get active Users of BPartner
   *
   * @param ctx context
   * @param C_BPartner_ID id
   * @return array of users
   * @deprecated Since 3.5.3a. Please use {@link #getOfBPartner(Properties, int, String)}.
   */
  public static MUser[] getOfBPartner(Properties ctx, int C_BPartner_ID) {
    return getOfBPartner(ctx, C_BPartner_ID, null);
  }

  /**
   * Get active Users of BPartner
   *
   * @param ctx
   * @param C_BPartner_ID
   * @param trxName
   * @return array of users
   */
  public static MUser[] getOfBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
    List<MUser> list =
        new Query(ctx, I_AD_User.Table_Name, "C_BPartner_ID=?", trxName)
            .setParameters(C_BPartner_ID)
            .setOnlyActiveRecords(true)
            .list();

    MUser[] retValue = new MUser[list.size()];
    list.toArray(retValue);
    return retValue;
  } //	getOfBPartner

  /**
   * Get active Users of Client
   *
   * @param ctx
   * @param ad_client_id
   * @param trxName
   * @return array of users
   */
  public static MUser[] getOfClient(Properties ctx, int ad_client_id, String trxName) {
    List<MUser> list =
        new Query(ctx, I_AD_User.Table_Name, "ad_client_id=?", trxName)
            .setParameters(ad_client_id)
            .setOnlyActiveRecords(true)
            .list();

    MUser[] retValue = new MUser[list.size()];
    list.toArray(retValue);
    return retValue;
  } //	getOfBPartner

  /**
   * Get User (cached) Also loads Admninistrator (0)
   *
   * @param ctx context
   * @param AD_User_ID id
   * @return user
   */
  public static MUser get(Properties ctx, int AD_User_ID) {
    Integer key = new Integer(AD_User_ID);
    MUser retValue = (MUser) s_cache.get(key);
    if (retValue == null) {
      retValue = new MUser(ctx, AD_User_ID, null);
      if (AD_User_ID == 0) {
        String trxName = null;
        retValue.load(trxName); // 	load System Record
      }
      s_cache.put(key, retValue);
    }
    return retValue;
  } //	get

  /**
   * Get Current User (cached)
   *
   * @param ctx context
   * @return user
   */
  public static MUser get(Properties ctx) {
    return get(ctx, Env.getAD_User_ID(ctx));
  } //	get

  /**
   * Get User
   *
   * @param ctx context
   * @param name name
   * @param password password
   * @return user or null
   */
  public static MUser get(Properties ctx, String name, String password) {
    if (name == null || name.length() == 0 || password == null || password.length() == 0) {
      s_log.warning("Invalid Name/Password = " + name);
      return null;
    }
    boolean hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false);
    boolean email_login = MSysConfig.getBooleanValue(MSysConfig.USE_EMAIL_FOR_LOGIN, false);
    //		ArrayList<KeyNamePair> clientList = new ArrayList<KeyNamePair>();
    ArrayList<Integer> clientsValidated = new ArrayList<Integer>();
    MUser retValue = null;

    StringBuilder where = new StringBuilder("Password IS NOT NULL AND ");
    if (email_login) where.append("EMail=?");
    else where.append("COALESCE(LDAPUser,Name)=?");
    where
        .append(" AND")
        .append(" EXISTS (SELECT * FROM AD_User_Roles ur")
        .append("         INNER JOIN AD_Role r ON (ur.AD_Role_ID=r.AD_Role_ID)")
        .append(
            "         WHERE ur.AD_User_ID=AD_User.AD_User_ID AND ur.IsActive='Y' AND r.IsActive='Y') AND ")
        .append(" EXISTS (SELECT * FROM AD_Client c")
        .append("         WHERE c.AD_Client_ID=AD_User.AD_Client_ID")
        .append("         AND c.IsActive='Y') AND ")
        .append(" AD_User.IsActive='Y'");

    List<MUser> users =
        new Query(ctx, MUser.Table_Name, where.toString(), null)
            .setParameters(name)
            .setOrderBy("AD_Client_ID, AD_User_ID") // prefer first user on System
            .list();

    if (users.size() == 0) {
      s_log.saveError("UserPwdError", name, false);
      return null;
    }

    for (MUser user : users) {
      if (clientsValidated.contains(user.getClientId())) {
        s_log.severe(
            "Two users with password with the same name/email combination on same tenant: " + name);
        return null;
      }

      clientsValidated.add(user.getClientId());
      boolean valid = false;
      if (hash_password) {
        valid = user.authenticateHash(password);
      } else {
        // password not hashed
        valid = user.getPassword().equals(password);
      }

      if (valid) {
        retValue = user;
        break;
      }
    }

    return retValue;
  } //	get

  /** Cache */
  private static CCache<Integer, MUser> s_cache =
      new CCache<Integer, MUser>(I_AD_User.Table_Name, 30, 60);
  /** Static Logger */
  private static CLogger s_log = CLogger.getCLogger(MUser.class);

  /**
   * ************************************************************************ Default Constructor
   *
   * @param ctx context
   * @param AD_User_ID id
   * @param trxName transaction
   */
  public MUser(Properties ctx, int AD_User_ID, String trxName) {
    super(ctx, AD_User_ID, trxName); // 	0 is also System
    if (AD_User_ID == 0) {
      setIsFullBPAccess(true);
      setNotificationType(X_AD_User.NOTIFICATIONTYPE_EMail);
    }
  } //	MUser

  /**
   * Parent Constructor
   *
   * @param partner partner
   */
  public MUser(I_C_BPartner partner) {
    this(partner.getCtx(), 0, partner.get_TrxName());
    setClientOrg(partner);
    setC_BPartner_ID(partner.getC_BPartner_ID());
    setName(partner.getName());
  } //	MUser

  /**
   * Load Constructor
   *
   * @param ctx context
   * @param rs current row of result set to be loaded
   * @param trxName transaction
   */
  public MUser(Properties ctx, ResultSet rs, String trxName) {
    super(ctx, rs, trxName);
  } //	MUser

  /** Roles of User with Org */
  private MRole[] m_roles = null;
  /** Roles of User with Org */
  private int m_rolesAD_Org_ID = -1;
  /** Is Administrator */
  private Boolean m_isAdministrator = null;
  /** User Access Rights */
  private X_AD_UserBPAccess[] m_bpAccess = null;
  /** Password Hashed * */
  private boolean being_hashed = false;

  /**
   * Get Value - 7 bit lower case alpha numerics max length 8
   *
   * @return value
   */
  public String getValue() {
    String s = super.getValue();
    if (s != null) return s;
    setValue(null);
    return super.getValue();
  } //	getValue

  /**
   * Set Value - 7 bit lower case alpha numerics max length 8
   *
   * @param Value
   */
  public void setValue(String Value) {
    if (Value == null || Value.trim().length() == 0) Value = getLDAPUser();
    if (Value == null || Value.length() == 0) Value = getName();
    if (Value == null || Value.length() == 0) Value = "noname";
    //
    String result = cleanValue(Value);
    if (result.length() > 8) {
      String first = getName(Value, true);
      String last = getName(Value, false);
      if (last.length() > 0) {
        String temp = last;
        if (first.length() > 0) temp = first.substring(0, 1) + last;
        result = cleanValue(temp);
      } else result = cleanValue(first);
    }
    if (result.length() > 8) result = result.substring(0, 8);
    super.setValue(result);
  } //	setValue

  /**
   * Clean Value
   *
   * @param value value
   * @return lower case cleaned value
   */
  private String cleanValue(String value) {
    char[] chars = value.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      ch = Character.toLowerCase(ch);
      if ((ch >= '0' && ch <= '9') // 	digits
          || (ch >= 'a' && ch <= 'z')) // 	characters
      sb.append(ch);
    }
    return sb.toString();
  } //	cleanValue

  /**
   * Convert Password to SHA-512 hash with salt * 1000 iterations
   * https://www.owasp.org/index.php/Hashing_Java
   *
   * @param password -- plain text password
   */
  @Override
  public void setPassword(String password) {
    if (password == null) {
      super.setPassword(password);
      return;
    }
    boolean hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false);

    if (!hash_password) {
      super.setPassword(password);
      return;
    }

    if (being_hashed) return;

    being_hashed = true; // prevents double call from beforeSave

    // Uses a secure Random not a simple Random
    SecureRandom random;
    try {
      random = SecureRandom.getInstance("SHA1PRNG");
      // Salt generation 64 bits long
      byte[] bSalt = new byte[8];
      random.nextBytes(bSalt);
      // Digest computation
      String hash;
      hash = SecureEngine.getSHA512Hash(1000, password, bSalt);

      String sSalt = Secure.convertToHexString(bSalt);
      super.setPassword(hash);
      setSalt(sSalt);
    } catch (NoSuchAlgorithmException e) {
      super.setPassword(password);
    } catch (UnsupportedEncodingException e) {
      super.setPassword(password);
    }
  }

  /** check if hashed password matches */
  public boolean authenticateHash(String password) {
    return SecureEngine.isMatchHash(getPassword(), getSalt(), password);
  }

  /**
   * Get First Name
   *
   * @return first name
   */
  public String getFirstName() {
    return getName(getName(), true);
  } //	getFirstName

  /**
   * Get Last Name
   *
   * @return first name
   */
  public String getLastName() {
    return getName(getName(), false);
  } //	getLastName

  /**
   * Get First/Last Name
   *
   * @param name name
   * @param getFirst if true first name is returned
   * @return first/last name
   */
  private String getName(String name, boolean getFirst) {
    if (name == null || name.length() == 0) return "";
    String first = null;
    String last = null;
    //	Janke, Jorg R - Jorg R Janke
    //	double names not handled gracefully nor titles
    //	nor (former) aristrocratic world de/la/von
    boolean lastFirst = name.indexOf(',') != -1;
    StringTokenizer st = null;
    if (lastFirst) st = new StringTokenizer(name, ",");
    else st = new StringTokenizer(name, " ");
    while (st.hasMoreTokens()) {
      String s = st.nextToken().trim();
      if (lastFirst) {
        if (last == null) last = s;
        else if (first == null) first = s;
      } else {
        if (first == null) first = s;
        else last = s;
      }
    }
    if (getFirst) {
      if (first == null) return "";
      return first.trim();
    }
    if (last == null) return "";
    return last.trim();
  } //	getName

  /**
   * Add to Description
   *
   * @param description description to be added
   */
  public void addDescription(String description) {
    if (description == null || description.length() == 0) return;
    String descr = getDescription();
    if (descr == null || descr.length() == 0) setDescription(description);
    else setDescription(descr + " - " + description);
  } //	addDescription

  /**
   * String Representation
   *
   * @return Info
   */
  public String toString() {
    StringBuffer sb =
        new StringBuffer("MUser[")
            .append(getId())
            .append(",Name=")
            .append(getName())
            .append(",EMailUserID=")
            .append(getEMailUser())
            .append("]");
    return sb.toString();
  } //	toString

  /**
   * Is it an Online Access User
   *
   * @return true if it has an email and password
   */
  public boolean isOnline() {
    if (getEMail() == null || getPassword() == null) return false;
    return true;
  } //	isOnline

  /**
   * Set EMail - reset validation
   *
   * @param EMail email
   */
  public void setEMail(String EMail) {
    super.setEMail(EMail);
    setEMailVerifyDate(null);
  } //	setEMail

  /**
   * Convert EMail
   *
   * @return Valid Internet Address
   */
  public InternetAddress getInternetAddress() {
    String email = getEMail();
    if (email == null || email.length() == 0) return null;
    try {
      InternetAddress ia = new InternetAddress(email, true);
      if (ia != null) ia.validate(); // 	throws AddressException
      return ia;
    } catch (AddressException ex) {
      log.warning(email + " - " + ex.getLocalizedMessage());
    }
    return null;
  } //	getInternetAddress

  /**
   * Validate Email (does not work). Check DNS MX record
   *
   * @param ia email
   * @return error message or ""
   */
  private String validateEmail(InternetAddress ia) {
    if (ia == null) return "NoEmail";
    else return ia.getAddress();
    /*
                   if (true)
    		return null;

    	Hashtable<String,String> env = new Hashtable<String,String>();
    	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
    //	env.put(Context.PROVIDER_URL, "dns://admin.adempiere.org");
    	try
    	{
    		DirContext ctx = new InitialDirContext(env);
    	//	Attributes atts = ctx.getAttributes("admin");
    		Attributes atts = ctx.getAttributes("dns://admin.adempiere.org", new String[] {"MX"});
    		NamingEnumeration en = atts.getAll();
    //		NamingEnumeration en = ctx.list("adempiere.org");
    		while (en.hasMore())
    		{
    			System.out.println(en.next());
    		}

    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		return e.getLocalizedMessage();
    	}
    	return null;
                   */
  } //	validateEmail

  /**
   * Is the email valid
   *
   * @return return true if email is valid (artificial check)
   */
  public boolean isEMailValid() {
    return validateEmail(getInternetAddress()) != null;
  } //	isEMailValid

  /**
   * Could we send an email
   *
   * @return true if EMail Uwer/PW exists
   */
  public boolean isCanSendEMail() {
    String s = getEMailUser();
    if (s == null || s.length() == 0) return false;
    // If SMTP authorization is not required, then don't check password - teo_sarca [ 1723309 ]
    if (!MClient.get(getCtx()).isSmtpAuthorization()) return true;
    s = getEMailUserPW();
    return s != null && s.length() > 0;
  } //	isCanSendEMail

  /**
   * Get EMail Validation Code
   *
   * @return code
   */
  public String getEMailVerifyCode() {
    long code = getAD_User_ID() + getName().hashCode();
    return "C" + String.valueOf(Math.abs(code)) + "C";
  } //	getEMailValidationCode

  /**
   * Check & Set EMail Validation Code.
   *
   * @param code code
   * @param info info
   * @return true if valid
   */
  public boolean setEMailVerifyCode(String code, String info) {
    boolean ok = code != null && code.equals(getEMailVerifyCode());
    if (ok) setEMailVerifyDate(new Timestamp(System.currentTimeMillis()));
    else setEMailVerifyDate(null);
    setEMailVerify(info);
    return ok;
  } //	setEMailValidationCode

  /**
   * Is EMail Verified by response
   *
   * @return true if verified
   */
  public boolean isEMailVerified() {
    //	UPDATE AD_User SET EMailVerifyDate=SysDate, EMailVerify='Direct' WHERE AD_User_ID=1
    return getEMailVerifyDate() != null
        && getEMailVerify() != null
        && getEMailVerify().length() > 0;
  } //	isEMailVerified

  /**
   * Get Notification via EMail
   *
   * @return true if email
   */
  public boolean isNotificationEMail() {
    String s = getNotificationType();
    return s == null
        || X_AD_User.NOTIFICATIONTYPE_EMail.equals(s)
        || X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(s);
  } //	isNotificationEMail

  /**
   * Get Notification via Note
   *
   * @return true if note
   */
  public boolean isNotificationNote() {
    String s = getNotificationType();
    return s != null
        && (X_AD_User.NOTIFICATIONTYPE_Notice.equals(s)
            || X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(s));
  } //	isNotificationNote

  /**
   * Before Save
   *
   * @param newRecord new
   * @return true
   */
  protected boolean beforeSave(boolean newRecord) {
    //	New Address invalidates verification
    if (!newRecord && is_ValueChanged("EMail")) setEMailVerifyDate(null);

    // IDEMPIERE-1409
    if (!Util.isEmpty(getEMail()) && (newRecord || is_ValueChanged("EMail"))) {
      if (!EMail.validate(getEMail())) {
        log.saveError(
            "SaveError",
            Msg.getMsg(getCtx(), "InvalidEMailFormat")
                + Msg.getElement(getCtx(), I_AD_User.COLUMNNAME_EMail)
                + " - ["
                + getEMail()
                + "]");
        return false;
      }
    }

    if (newRecord || super.getValue() == null || is_ValueChanged("Value"))
      setValue(super.getValue());

    if (getPassword() != null && getPassword().length() > 0) {
      boolean email_login = MSysConfig.getBooleanValue(MSysConfig.USE_EMAIL_FOR_LOGIN, false);
      if (email_login) {
        // email is mandatory for users with password
        if (getEMail() == null || getEMail().length() == 0) {
          log.saveError(
              "SaveError",
              Msg.getMsg(getCtx(), "FillMandatory")
                  + Msg.getElement(getCtx(), I_AD_User.COLUMNNAME_EMail)
                  + " - "
                  + toString());
          return false;
        }
        // email with password must be unique on the same tenant
        int cnt =
            getSQLValue(
                get_TrxName(),
                "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND EMail=? AND AD_Client_ID=? AND AD_User_ID!=?",
                getEMail(),
                getClientId(),
                getAD_User_ID());
        if (cnt > 0) {
          log.saveError(
              "SaveError",
              Msg.getMsg(getCtx(), DBException.SAVE_ERROR_NOT_UNIQUE_MSG, true)
                  + Msg.getElement(getCtx(), I_AD_User.COLUMNNAME_EMail));
          return false;
        }
      } else {
        // IDEMPIERE-1672 check duplicate name in client
        String nameToValidate = getLDAPUser();
        if (Util.isEmpty(nameToValidate)) nameToValidate = getName();
        int cnt =
            getSQLValue(
                get_TrxName(),
                "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND COALESCE(LDAPUser,Name)=? AND AD_Client_ID=? AND AD_User_ID!=?",
                nameToValidate,
                getClientId(),
                getAD_User_ID());
        if (cnt > 0) {
          log.saveError(
              "SaveError",
              Msg.getMsg(getCtx(), DBException.SAVE_ERROR_NOT_UNIQUE_MSG, true)
                  + Msg.getElement(getCtx(), HasName.Companion.getCOLUMNNAME_Name())
                  + " / "
                  + Msg.getElement(getCtx(), I_AD_User.COLUMNNAME_LDAPUser));
          return false;
        }
      }
    }

    if (getPassword() != null
        && getPassword().length() > 0
        && (newRecord || is_ValueChanged("Password"))) {
      // Hash password - IDEMPIERE-347
      boolean hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false);
      if (hash_password) setPassword(getPassword());

      setDatePasswordChanged(new Timestamp(new Date().getTime()));
    }

    return true;
  } //	beforeSave

  /**
   * Is Menu Auto Expand - user preference Check if the user has a preference, otherwise use the
   * value from current role
   *
   * @return boolean
   */
  public boolean isMenuAutoExpand() {
    boolean isMenuAutoExpand = false;
    if (getIsMenuAutoExpand() != null)
      isMenuAutoExpand = X_AD_User.ISMENUAUTOEXPAND_Yes.equals(getIsMenuAutoExpand());
    else isMenuAutoExpand = MRole.getDefault().isMenuAutoExpand();
    return isMenuAutoExpand;
  }

  @Override
  public String getEMailUser() {
    // IDEMPIERE-722
    return super.getEMailUser();
  }

  @Override
  public String getEMailUserPW() {
    return super.getEMailUserPW();
  }

  public void setADClientID(int AD_Client_ID) {
    super.setADClientID(AD_Client_ID);
  }
} //	MUser
