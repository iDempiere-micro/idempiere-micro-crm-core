package org.compiere.crm

import kotliquery.Row
import org.compiere.model.HasName
import org.compiere.model.User
import org.compiere.model.I_C_BPartner
import org.compiere.orm.MSysConfig
import org.compiere.orm.Query
import org.compiere.util.getElementTranslation
import org.compiere.util.getMsg
import org.idempiere.common.exceptions.DBException
import org.idempiere.common.util.Secure
import org.idempiere.common.util.SecureEngine
import org.idempiere.common.util.Util
import org.idempiere.common.util.factory
import org.idempiere.common.util.loadUsing
import software.hsharp.core.models.IUser

import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import java.io.UnsupportedEncodingException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.sql.Timestamp
import java.util.Date
import java.util.StringTokenizer

import software.hsharp.core.util.getSQLValue

private val userFactory = factory { MUser(it) }

/**
 * Get currency by Id
 */
fun getUser(id: Int) = id loadUsing userFactory

/**
 * Get active Users of BPartner
 *
 * @param businessPartnerId
 * @return array of users
 */
fun getBusinessPartnerUsers(businessPartnerId: Int): Array<User> {
    val list = Query<User>(User.Table_Name, "C_BPartner_ID=?")
        .setParameters(businessPartnerId)
        .setOnlyActiveRecords(true)
        .list()

    return list.toTypedArray()
} // 	getOfBPartner

/**
 * Get active Users of Client
 *
 * @param clientId
 * @return array of users
 */
fun getClientUsers(clientId: Int): Array<User> {
    val list = Query<User>(User.Table_Name, "ad_client_id=?")
        .setParameters(clientId)
        .setOnlyActiveRecords(true)
        .list()

    return list.toTypedArray()
} // 	getOfBPartner

/**
 * User Model
 *
 * @author Jorg Janke
 * @author Teo Sarca, www.arhipac.ro
 *  * FR [ 2788430 ] MUserKt.getUserOfBPartner add trxName parameter
 * https://sourceforge.net/tracker/index.php?func=detail&aid=2788430&group_id=176962&atid=879335
 * @version $Id: MUser.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
class MUser : MBaseUser, IUser {
    /**
     * Is Administrator
     */
    private var m_isAdministrator: Boolean? = null
    /**
     * Password Hashed *
     */
    private var being_hashed = false

    /**
     * Convert EMail
     *
     * @return Valid Internet Address
     */
    val internetAddress: InternetAddress?
        get() {
            val email = eMail
            if (email == null || email.length == 0) return null
            try {
                val ia = InternetAddress(email, true)
                ia.validate()
                return ia
            } catch (ex: AddressException) {
                log.warning(email + " - " + ex.localizedMessage)
            }

            return null
        } // 	getInternetAddress

    /**
     * Get EMail Validation Code
     *
     * @return code
     */
    val eMailVerifyCode: String
        get() {
            val code = (userId + name.hashCode()).toLong()
            return "C" + Math.abs(code) + "C"
        } // 	getEMailValidationCode

    /**
     * Get Notification via EMail
     *
     * @return true if email
     */
    val isNotificationEMail: Boolean
        get() {
            val s = notificationType
            return (s == null ||
                    X_AD_User.NOTIFICATIONTYPE_EMail == s ||
                    X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice == s)
        } // 	isNotificationEMail

    /**
     * Get Notification via Note
     *
     * @return true if note
     */
    val isNotificationNote: Boolean
        get() {
            val s = notificationType
            return s != null && (X_AD_User.NOTIFICATIONTYPE_Notice == s || X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice == s)
        } // 	isNotificationNote

    /**
     * Is User an Administrator?
     *
     * @return true id Admin
     */
    val isAdministrator: Boolean
        get() {
            if (m_isAdministrator == null) {
                m_isAdministrator = java.lang.Boolean.FALSE
                val roles = getRoles(0)
                for (i in roles.indices) {
                    if (roles[i].roleId == 0) {
                        m_isAdministrator = java.lang.Boolean.TRUE
                        break
                    }
                }
            }
            return m_isAdministrator!!
        } // 	isAdministrator

    /**
     * ************************************************************************ Default Constructor
     *
     * @param AD_User_ID id
     */
    constructor(AD_User_ID: Int) : super(AD_User_ID) {
        if (AD_User_ID == 0) {
            setIsFullBPAccess(true)
            notificationType = X_AD_User.NOTIFICATIONTYPE_EMail
        }
    } // 	0 is also System
    // 	MUser

    /**
     * Parent Constructor
     *
     * @param partner partner
     */
    constructor(partner: I_C_BPartner) : this(0) {
        setClientOrg(partner)
        setBPartnerId(partner.businessPartnerId)
        name = partner.name
    } // 	MUser

    /**
     * Load Constructor
     */
    constructor(row: Row) : super(row) {} // 	MUser

    /**
     * Get Value - 7 bit lower case alpha numerics max length 8
     *
     * @return value
     */
    override fun doGetSearchKey(): String {
        return super.doGetSearchKey()
    } // 	getValue

    /**
     * Set Value - 7 bit lower case alpha numerics max length 8
     *
     * @param Value
     */
    override fun doSetSearchKey(Value: String) {
        var localValue = Value
        if (localValue.trim { it <= ' ' }.isEmpty()) localValue = ldapUser
        if (localValue.isEmpty()) localValue = name
        if (localValue.isEmpty()) localValue = "noname"
        //
        var result = cleanValue(localValue)
        if (result.length > 8) {
            val first = getName(localValue, true)
            val last = getName(localValue, false)
            if (last.isNotEmpty()) {
                var temp = last
                if (first.isNotEmpty()) temp = first.substring(0, 1) + last
                result = cleanValue(temp)
            } else
                result = cleanValue(first)
        }
        if (result.length > 8) result = result.substring(0, 8)
        super.doSetSearchKey(result)
    } // 	setValue

    /**
     * Clean Value
     *
     * @param value value
     * @return lower case cleaned value
     */
    private fun cleanValue(value: String): String {
        val chars = value.toCharArray()
        val sb = StringBuilder()
        for (aChar in chars) {
            var ch = aChar
            ch = Character.toLowerCase(ch)
            if (ch >= '0' && ch <= '9' || // 	digits
                ch >= 'a' && ch <= 'z'
            )
            // 	characters
                sb.append(ch)
        }
        return sb.toString()
    } // 	cleanValue

    /**
     * Convert Password to SHA-512 hash with salt * 1000 iterations
     * https://www.owasp.org/index.php/Hashing_Java
     *
     * @param password -- plain text password
     */
    override fun setPassword(password: String?) {
        if (password == null) {
            super.setPassword(password)
            return
        }
        val hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false)

        if (!hash_password) {
            super.setPassword(password)
            return
        }

        if (being_hashed) return

        being_hashed = true // prevents double call from beforeSave

        // Uses a secure Random not a simple Random
        val random: SecureRandom
        try {
            random = SecureRandom.getInstance("SHA1PRNG")
            // Salt generation 64 bits long
            val bSalt = ByteArray(8)
            random.nextBytes(bSalt)
            // Digest computation
            val hash: String
            hash = SecureEngine.getSHA512Hash(1000, password, bSalt)

            val sSalt = Secure.convertToHexString(bSalt)
            super.setPassword(hash)
            salt = sSalt
        } catch (e: NoSuchAlgorithmException) {
            super.setPassword(password)
        } catch (e: UnsupportedEncodingException) {
            super.setPassword(password)
        }
    }

    /**
     * check if hashed password matches
     */
    fun authenticateHash(password: String): Boolean {
        return SecureEngine.isMatchHash(getPassword(), salt, password)
    }

    /**
     * Get First/Last Name
     *
     * @param name name
     * @param getFirst if true first name is returned
     * @return first/last name
     */
    private fun getName(name: String?, getFirst: Boolean): String {
        if (name == null || name.length == 0) return ""
        var first: String? = null
        var last: String? = null
        // 	Janke, Jorg R - Jorg R Janke
        // 	double names not handled gracefully nor titles
        // 	nor (former) aristrocratic world de/la/von
        val lastFirst = name.indexOf(',') != -1
        val st: StringTokenizer
        if (lastFirst)
            st = StringTokenizer(name, ",")
        else
            st = StringTokenizer(name, " ")
        while (st.hasMoreTokens()) {
            val s = st.nextToken().trim { it <= ' ' }
            if (lastFirst) {
                if (last == null)
                    last = s
                else if (first == null) first = s
            } else {
                if (first == null)
                    first = s
                else
                    last = s
            }
        }
        return if (getFirst) {
            first?.trim { it <= ' ' } ?: ""
        } else last?.trim { it <= ' ' } ?: ""
    } // 	getName

    /**
     * String Representation
     *
     * @return Info
     */
    override fun toString(): String {
        return "MUser[" +
                id +
                ",Name=" +
                name +
                ",EMailUserID=" +
                eMailUser +
                "]"
    } // 	toString

    /**
     * Set EMail - reset validation
     *
     * @param EMail email
     */
    override fun setEMail(EMail: String?) {
        super.setEMail(EMail)
        eMailVerifyDate = null
    } // 	setEMail

    /**
     * Before Save
     *
     * @param newRecord new
     * @return true
     */
    override fun beforeSave(newRecord: Boolean): Boolean {
        // 	New Address invalidates verification
        if (!newRecord && isValueChanged("EMail")) eMailVerifyDate = null

        // IDEMPIERE-1409
        if (!Util.isEmpty(eMail) && (newRecord || isValueChanged("EMail"))) {
            if (!EMail.validate(eMail)) {
                log.saveError(
                    "SaveError",
                    getMsg("InvalidEMailFormat") +
                            getElementTranslation(User.COLUMNNAME_EMail) +
                            " - [" +
                            eMail +
                            "]"
                )
                return false
            }
        }

        if (newRecord || isValueChanged("Value"))
            searchKey = super.searchKey

        if (password != null && password.length > 0) {
            val email_login = MSysConfig.getBooleanValue(MSysConfig.USE_EMAIL_FOR_LOGIN, false)
            if (email_login) {
                // email is mandatory for users with password
                if (eMail == null || eMail.length == 0) {
                    log.saveError(
                        "SaveError",
                        getMsg("FillMandatory") +
                                getElementTranslation(User.COLUMNNAME_EMail) +
                                " - " +
                                toString()
                    )
                    return false
                }
                // email with password must be unique on the same tenant
                val cnt = getSQLValue(
                    "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND EMail=? AND AD_Client_ID=? AND AD_User_ID!=?",
                    eMail,
                    clientId,
                    userId
                )
                if (cnt > 0) {
                    log.saveError(
                        "SaveError",
                        getMsg(
                            DBException.SAVE_ERROR_NOT_UNIQUE_MSG,
                            true
                        ) + getElementTranslation(User.COLUMNNAME_EMail)
                    )
                    return false
                }
            } else {
                // IDEMPIERE-1672 check duplicate name in client
                var nameToValidate = ldapUser
                if (Util.isEmpty(nameToValidate)) nameToValidate = name
                val cnt = getSQLValue(
                    "SELECT COUNT(*) FROM AD_User WHERE Password IS NOT NULL AND COALESCE(LDAPUser,Name)=? AND AD_Client_ID=? AND AD_User_ID!=?",
                    nameToValidate,
                    clientId,
                    userId
                )
                if (cnt > 0) {
                    log.saveError(
                        "SaveError",
                        getMsg(DBException.SAVE_ERROR_NOT_UNIQUE_MSG, true) +
                                getElementTranslation(HasName.COLUMNNAME_Name) +
                                " / " +
                                getElementTranslation(User.COLUMNNAME_LDAPUser)
                    )
                    return false
                }
            }
        }

        if (password != null &&
            password.length > 0 &&
            (newRecord || isValueChanged("Password"))
        ) {
            // Hash password - IDEMPIERE-347
            val hash_password = MSysConfig.getBooleanValue(MSysConfig.USER_PASSWORD_HASH, false)
            if (hash_password) password = password

            setDatePasswordChanged(Timestamp(Date().time))
        }

        return true
    } // 	beforeSave

    companion object {
        private const val serialVersionUID = 9027688865361175114L
    }
} // 	MUser
