package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_User;
import org.compiere.orm.BasePONameValue;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Generated Model for AD_User
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_User extends BasePONameValue implements I_AD_User {

    /**
     * Yes = Y
     */
    public static final String ISMENUAUTOEXPAND_Yes = "Y";
    /**
     * EMail = E
     */
    public static final String NOTIFICATIONTYPE_EMail = "E";
    /**
     * Notice = N
     */
    public static final String NOTIFICATIONTYPE_Notice = "N";
    /**
     * EMail+Notice = B
     */
    public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_User(Properties ctx, int AD_User_ID) {
        super(ctx, AD_User_ID);
        /**
         * if (AD_User_ID == 0) { setUserId (0); setFailedLoginCount (0); // 0
         * setIsAddMailTextAutomatically (false); // N setIsExpired (false); // N setIsFullBPAccess
         * (true); // Y setIsInPayroll (false); // N setIsLocked (false); // 'N' setIsNoPasswordReset
         * (false); // 'N' setIsSalesLead (false); // N setName (null); setNotificationType (null); // E
         * }
         */
    }

    /**
     * Load Constructor
     */
    public X_AD_User(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    }

    public X_AD_User(Properties ctx, Row row) {
        super(ctx, row);
    } //	MUser

    /**
     * AccessLevel
     *
     * @return 7 - System - Client - Org
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        return "X_AD_User[" + getId() + "]";
    }

    /**
     * Get User/Contact.
     *
     * @return User within the system - Internal or Business Partner Contact
     */
    public int getUserId() {
        Integer ii = (Integer) getValue(COLUMNNAME_AD_User_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Business Partner .
     *
     * @param C_BPartner_ID Identifies a Business Partner
     */
    public void setBPartnerId(int C_BPartner_ID) {
        if (C_BPartner_ID < 1) setValue(COLUMNNAME_C_BPartner_ID, null);
        else setValue(COLUMNNAME_C_BPartner_ID, C_BPartner_ID);
    }

    /**
     * Set Date Password Changed.
     *
     * @param DatePasswordChanged Date Password Changed
     */
    public void setDatePasswordChanged(Timestamp DatePasswordChanged) {
        setValue(COLUMNNAME_DatePasswordChanged, DatePasswordChanged);
    }

    /**
     * Get Description.
     *
     * @return Optional short description of the record
     */
    public String getDescription() {
        return (String) getValue(COLUMNNAME_Description);
    }

    /**
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        setValue(COLUMNNAME_Description, Description);
    }

    /**
     * Get EMail Address.
     *
     * @return Electronic Mail Address
     */
    public String getEMail() {
        return (String) getValue(COLUMNNAME_EMail);
    }

    /**
     * Set EMail Address.
     *
     * @param EMail Electronic Mail Address
     */
    public void setEMail(String EMail) {
        setValue(COLUMNNAME_EMail, EMail);
    }

    /**
     * Get EMail User ID.
     *
     * @return User Name (ID) in the Mail System
     */
    public String getEMailUser() {
        return (String) getValue(COLUMNNAME_EMailUser);
    }

    /**
     * Set EMail User ID.
     *
     * @param EMailUser User Name (ID) in the Mail System
     */
    public void setEMailUser(String EMailUser) {
        setValue(COLUMNNAME_EMailUser, EMailUser);
    }

    /**
     * Get EMail User Password.
     *
     * @return Password of your email user id
     */
    public String getEMailUserPW() {
        return (String) getValue(COLUMNNAME_EMailUserPW);
    }

    /**
     * Set EMail User Password.
     *
     * @param EMailUserPW Password of your email user id
     */
    public void setEMailUserPW(String EMailUserPW) {
        setValue(COLUMNNAME_EMailUserPW, EMailUserPW);
    }

    /**
     * Get Verification Info.
     *
     * @return Verification information of EMail Address
     */
    public String getEMailVerify() {
        return (String) getValue(COLUMNNAME_EMailVerify);
    }

    /**
     * Set Verification Info.
     *
     * @param EMailVerify Verification information of EMail Address
     */
    public void setEMailVerify(String EMailVerify) {
        setValueNoCheck(COLUMNNAME_EMailVerify, EMailVerify);
    }

    /**
     * Get EMail Verify.
     *
     * @return Date Email was verified
     */
    public Timestamp getEMailVerifyDate() {
        return (Timestamp) getValue(COLUMNNAME_EMailVerifyDate);
    }

    /**
     * Set EMail Verify.
     *
     * @param EMailVerifyDate Date Email was verified
     */
    public void setEMailVerifyDate(Timestamp EMailVerifyDate) {
        setValueNoCheck(COLUMNNAME_EMailVerifyDate, EMailVerifyDate);
    }

    /**
     * Set Full BP Access.
     *
     * @param IsFullBPAccess The user/contact has full access to Business Partner information and
     *                       resources
     */
    public void setIsFullBPAccess(boolean IsFullBPAccess) {
        setValue(COLUMNNAME_IsFullBPAccess, IsFullBPAccess);
    }

    /**
     * Get Auto expand menu.
     *
     * @return If ticked, the menu is automatically expanded
     */
    public String getIsMenuAutoExpand() {
        return (String) getValue(COLUMNNAME_IsMenuAutoExpand);
    }

    /**
     * Set Last Contact.
     *
     * @param LastContact Date this individual was last contacted
     */
    public void setLastContact(Timestamp LastContact) {
        setValue(COLUMNNAME_LastContact, LastContact);
    }

    /**
     * Set Last Result.
     *
     * @param LastResult Result of last contact
     */
    public void setLastResult(String LastResult) {
        setValue(COLUMNNAME_LastResult, LastResult);
    }

    /**
     * Get LDAP User Name.
     *
     * @return User Name used for authorization via LDAP (directory) services
     */
    public String getLDAPUser() {
        return (String) getValue(COLUMNNAME_LDAPUser);
    }

    /**
     * Get Notification Type.
     *
     * @return Type of Notifications
     */
    public String getNotificationType() {
        return (String) getValue(COLUMNNAME_NotificationType);
    }

    /**
     * Set Notification Type.
     *
     * @param NotificationType Type of Notifications
     */
    public void setNotificationType(String NotificationType) {

        setValue(COLUMNNAME_NotificationType, NotificationType);
    }

    /**
     * Get Password.
     *
     * @return Password of any length (case sensitive)
     */
    public String getPassword() {
        return (String) getValue(COLUMNNAME_Password);
    }

    /**
     * Set Password.
     *
     * @param Password Password of any length (case sensitive)
     */
    public void setPassword(String Password) {
        setValue(COLUMNNAME_Password, Password);
    }

    /**
     * Set Phone.
     *
     * @param Phone Identifies a telephone number
     */
    public void setPhone(String Phone) {
        setValue(COLUMNNAME_Phone, Phone);
    }

    /**
     * Get Salt.
     *
     * @return Random data added to improve password hash effectiveness
     */
    public String getSalt() {
        return (String) getValue(COLUMNNAME_Salt);
    }

    /**
     * Set Salt.
     *
     * @param Salt Random data added to improve password hash effectiveness
     */
    public void setSalt(String Salt) {
        setValueNoCheck(COLUMNNAME_Salt, Salt);
    }

    /**
     * Get Supervisor.
     *
     * @return Supervisor for this user/organization - used for escalation and approval
     */
    public int getSupervisor_ID() {
        Integer ii = (Integer) getValue(COLUMNNAME_Supervisor_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return I_AD_User.Table_ID;
    }
}
