package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.HasName;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.MRole;
import org.compiere.orm.MSysConfig;
import org.compiere.orm.Query;
import org.compiere.util.Msg;
import org.idempiere.common.exceptions.DBException;
import org.idempiere.common.util.CCache;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.Secure;
import org.idempiere.common.util.SecureEngine;
import org.idempiere.common.util.Util;
import software.hsharp.core.models.IUser;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * User Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 * <li>FR [ 2788430 ] MUser.getOfBPartner add trxName parameter
 * https://sourceforge.net/tracker/index.php?func=detail&aid=2788430&group_id=176962&atid=879335
 * @version $Id: MUser.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MUser extends MBaseUser implements IUser {
    /**
     *
     */
    private static final long serialVersionUID = 9027688865361175114L;
    /**
     * Cache
     */
    private static CCache<Integer, MUser> s_cache =
            new CCache<Integer, MUser>(I_AD_User.Table_Name, 30, 60);
    /**
     * Is Administrator
     */
    private Boolean m_isAdministrator = null;
    /**
     * Password Hashed *
     */
    private boolean being_hashed = false;

    /**
     * ************************************************************************ Default Constructor
     *
     * @param ctx        context
     * @param AD_User_ID id
     * @param trxName    transaction
     */
    public MUser(Properties ctx, int AD_User_ID) {
        super(ctx, AD_User_ID); // 	0 is also System
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
        this(partner.getCtx(), 0);
        setClientOrg(partner);
        setBPartnerId(partner.getBusinessPartnerId());
        setName(partner.getName());
    } //	MUser

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    public MUser(Properties ctx, Row row) {
        super(ctx, row);
    } //	MUser

    /**
     * Get active Users of BPartner
     *
     * @param ctx
     * @param C_BPartner_ID
     * @param trxName
     * @return array of users
     */
    public static MUser[] getOfBPartner(Properties ctx, int C_BPartner_ID) {
        List<MUser> list =
                new Query(ctx, I_AD_User.Table_Name, "C_BPartner_ID=?")
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
    public static MUser[] getOfClient(Properties ctx, int ad_client_id) {
        List<MUser> list =
                new Query(ctx, I_AD_User.Table_Name, "ad_client_id=?")
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
     * @param ctx        context
     * @param AD_User_ID id
     * @return user
     */
    public static MUser get(Properties ctx, int AD_User_ID) {
        Integer key = new Integer(AD_User_ID);
        MUser retValue = s_cache.get(key);
        if (retValue == null) {
            retValue = new MUser(ctx, AD_User_ID);
            if (AD_User_ID == 0) {

                retValue.load(); // 	load System Record
            }
            s_cache.put(key, retValue);
        }
        return retValue;
    } //	get

    /**
     * Get Value - 7 bit lower case alpha numerics max length 8
     *
     * @return value
     */
    protected String doGetSearchKey() {
        String s = super.doGetSearchKey();
        if (s != null) return s;
        setSearchKey(null);
        return super.doGetSearchKey();
    } //	getValue

    /**
     * Set Value - 7 bit lower case alpha numerics max length 8
     *
     * @param Value
     */
    protected void doSetSearchKey(String Value) {
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
        super.doSetSearchKey(result);
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

    /**
     * check if hashed password matches
     */
    public boolean authenticateHash(String password) {
        return SecureEngine.isMatchHash(getPassword(), getSalt(), password);
    }

    /**
     * Get First/Last Name
     *
     * @param name     name
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
     * Get EMail Validation Code
     *
     * @return code
     */
    public String getEMailVerifyCode() {
        long code = getUserId() + getName().hashCode();
        return "C" + Math.abs(code) + "C";
    } //	getEMailValidationCode

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
        if (!newRecord && isValueChanged("EMail")) setEMailVerifyDate(null);

        // IDEMPIERE-1409
        if (!Util.isEmpty(getEMail()) && (newRecord || isValueChanged("EMail"))) {
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

        if (newRecord || super.getSearchKey() == null || isValueChanged("Value"))
            setSearchKey(super.getSearchKey());

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
                                "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND EMail=? AND AD_Client_ID=? AND AD_User_ID!=?",
                                getEMail(),
                                getClientId(),
                                getUserId());
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
                                "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND COALESCE(LDAPUser,Name)=? AND AD_Client_ID=? AND AD_User_ID!=?",
                                nameToValidate,
                                getClientId(),
                                getUserId());
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
                && (newRecord || isValueChanged("Password"))) {
            // Hash password - IDEMPIERE-347
            boolean hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false);
            if (hash_password) setPassword(getPassword());

            setDatePasswordChanged(new Timestamp(new Date().getTime()));
        }

        return true;
    } //	beforeSave

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

    /**
     * Is User an Administrator?
     *
     * @return true id Admin
     */
    public boolean isAdministrator() {
        if (m_isAdministrator == null) {
            m_isAdministrator = Boolean.FALSE;
            MRole[] roles = getRoles(0);
            for (int i = 0; i < roles.length; i++) {
                if (roles[i].getRoleId() == 0) {
                    m_isAdministrator = Boolean.TRUE;
                    break;
                }
            }
        }
        return m_isAdministrator.booleanValue();
    } //	isAdministrator
} //	MUser
