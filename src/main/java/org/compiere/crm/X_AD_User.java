package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_User;
import org.compiere.orm.BasePONameValue;
import org.idempiere.orm.I_Persistent;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Generated Model for AD_User
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_User extends BasePONameValue implements I_AD_User, I_Persistent {

    /**
     * IsMenuAutoExpand AD_Reference_ID=319
     */
    public static final int ISMENUAUTOEXPAND_AD_Reference_ID = 319;
    /**
     * Yes = Y
     */
    public static final String ISMENUAUTOEXPAND_Yes = "Y";
    /**
     * No = N
     */
    public static final String ISMENUAUTOEXPAND_No = "N";
    /**
     * LeadSource AD_Reference_ID=53415
     */
    public static final int LEADSOURCE_AD_Reference_ID = 53415;
    /**
     * Cold Call = CC
     */
    public static final String LEADSOURCE_ColdCall = "CC";
    /**
     * Existing Customer = EC
     */
    public static final String LEADSOURCE_ExistingCustomer = "EC";
    /**
     * Employee = EM
     */
    public static final String LEADSOURCE_Employee = "EM";
    /**
     * Partner = PT
     */
    public static final String LEADSOURCE_Partner = "PT";
    /**
     * Conference = CN
     */
    public static final String LEADSOURCE_Conference = "CN";
    /**
     * Trade Show = TS
     */
    public static final String LEADSOURCE_TradeShow = "TS";
    /**
     * Web Site = WS
     */
    public static final String LEADSOURCE_WebSite = "WS";
    /**
     * Word of Mouth = WM
     */
    public static final String LEADSOURCE_WordOfMouth = "WM";
    /**
     * Email = EL
     */
    public static final String LEADSOURCE_Email = "EL";
    /**
     * LeadStatus AD_Reference_ID=53416
     */
    public static final int LEADSTATUS_AD_Reference_ID = 53416;
    /**
     * New = N
     */
    public static final String LEADSTATUS_New = "N";
    /**
     * Working = W
     */
    public static final String LEADSTATUS_Working = "W";
    /**
     * Expired = E
     */
    public static final String LEADSTATUS_Expired = "E";
    /**
     * Recycled = R
     */
    public static final String LEADSTATUS_Recycled = "R";
    /**
     * Converted = C
     */
    public static final String LEADSTATUS_Converted = "C";
    /**
     * NotificationType AD_Reference_ID=344
     */
    public static final int NOTIFICATIONTYPE_AD_Reference_ID = 344;
    /**
     * EMail = E
     */
    public static final String NOTIFICATIONTYPE_EMail = "E";
    /**
     * Notice = N
     */
    public static final String NOTIFICATIONTYPE_Notice = "N";
    /**
     * None = X
     */
    public static final String NOTIFICATIONTYPE_None = "X";
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
         * if (AD_User_ID == 0) { setAD_User_ID (0); setFailedLoginCount (0); // 0
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
    public int getAD_User_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_AD_User_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get BP Address.
     *
     * @return Address of the Business Partner
     */
    public int getBP_Location_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_BP_Location_ID);
        if (ii == null) return 0;
        return ii;
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
     * Set Business Partner .
     *
     * @param C_BPartner_ID Identifies a Business Partner
     */
    public void setC_BPartner_ID(int C_BPartner_ID) {
        if (C_BPartner_ID < 1) set_Value(COLUMNNAME_C_BPartner_ID, null);
        else set_Value(COLUMNNAME_C_BPartner_ID, C_BPartner_ID);
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
     * Get Campaign.
     *
     * @return Marketing Campaign
     */
    public int getC_Campaign_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Campaign_ID);
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
     * Get Position.
     *
     * @return Job Position
     */
    public int getC_Job_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Job_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Address.
     *
     * @return Location or Address
     */
    public int getC_Location_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_Location_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Set Date Password Changed.
     *
     * @param DatePasswordChanged Date Password Changed
     */
    public void setDatePasswordChanged(Timestamp DatePasswordChanged) {
        set_Value(COLUMNNAME_DatePasswordChanged, DatePasswordChanged);
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
     * Set Description.
     *
     * @param Description Optional short description of the record
     */
    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
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
     * Set EMail Address.
     *
     * @param EMail Electronic Mail Address
     */
    public void setEMail(String EMail) {
        set_Value(COLUMNNAME_EMail, EMail);
    }

    /**
     * Get EMail User ID.
     *
     * @return User Name (ID) in the Mail System
     */
    public String getEMailUser() {
        return (String) get_Value(COLUMNNAME_EMailUser);
    }

    /**
     * Set EMail User ID.
     *
     * @param EMailUser User Name (ID) in the Mail System
     */
    public void setEMailUser(String EMailUser) {
        set_Value(COLUMNNAME_EMailUser, EMailUser);
    }

    /**
     * Get EMail User Password.
     *
     * @return Password of your email user id
     */
    public String getEMailUserPW() {
        return (String) get_Value(COLUMNNAME_EMailUserPW);
    }

    /**
     * Set EMail User Password.
     *
     * @param EMailUserPW Password of your email user id
     */
    public void setEMailUserPW(String EMailUserPW) {
        set_Value(COLUMNNAME_EMailUserPW, EMailUserPW);
    }

    /**
     * Get Verification Info.
     *
     * @return Verification information of EMail Address
     */
    public String getEMailVerify() {
        return (String) get_Value(COLUMNNAME_EMailVerify);
    }

    /**
     * Set Verification Info.
     *
     * @param EMailVerify Verification information of EMail Address
     */
    public void setEMailVerify(String EMailVerify) {
        set_ValueNoCheck(COLUMNNAME_EMailVerify, EMailVerify);
    }

    /**
     * Get EMail Verify.
     *
     * @return Date Email was verified
     */
    public Timestamp getEMailVerifyDate() {
        return (Timestamp) get_Value(COLUMNNAME_EMailVerifyDate);
    }

    /**
     * Set EMail Verify.
     *
     * @param EMailVerifyDate Date Email was verified
     */
    public void setEMailVerifyDate(Timestamp EMailVerifyDate) {
        set_ValueNoCheck(COLUMNNAME_EMailVerifyDate, EMailVerifyDate);
    }

    /**
     * Set Full BP Access.
     *
     * @param IsFullBPAccess The user/contact has full access to Business Partner information and
     *                       resources
     */
    public void setIsFullBPAccess(boolean IsFullBPAccess) {
        set_Value(COLUMNNAME_IsFullBPAccess, IsFullBPAccess);
    }

    /**
     * Get Auto expand menu.
     *
     * @return If ticked, the menu is automatically expanded
     */
    public String getIsMenuAutoExpand() {
        return (String) get_Value(COLUMNNAME_IsMenuAutoExpand);
    }

    /**
     * Set Last Contact.
     *
     * @param LastContact Date this individual was last contacted
     */
    public void setLastContact(Timestamp LastContact) {
        set_Value(COLUMNNAME_LastContact, LastContact);
    }

    /**
     * Set Last Result.
     *
     * @param LastResult Result of last contact
     */
    public void setLastResult(String LastResult) {
        set_Value(COLUMNNAME_LastResult, LastResult);
    }

    /**
     * Get LDAP User Name.
     *
     * @return User Name used for authorization via LDAP (directory) services
     */
    public String getLDAPUser() {
        return (String) get_Value(COLUMNNAME_LDAPUser);
    }

    /**
     * Get Notification Type.
     *
     * @return Type of Notifications
     */
    public String getNotificationType() {
        return (String) get_Value(COLUMNNAME_NotificationType);
    }

    /**
     * Set Notification Type.
     *
     * @param NotificationType Type of Notifications
     */
    public void setNotificationType(String NotificationType) {

        set_Value(COLUMNNAME_NotificationType, NotificationType);
    }

    /**
     * Get Password.
     *
     * @return Password of any length (case sensitive)
     */
    public String getPassword() {
        return (String) get_Value(COLUMNNAME_Password);
    }

    /**
     * Set Password.
     *
     * @param Password Password of any length (case sensitive)
     */
    public void setPassword(String Password) {
        set_Value(COLUMNNAME_Password, Password);
    }

    /**
     * Get Phone.
     *
     * @return Identifies a telephone number
     */
    public String getPhone() {
        return (String) get_Value(COLUMNNAME_Phone);
    }

    /**
     * Set Phone.
     *
     * @param Phone Identifies a telephone number
     */
    public void setPhone(String Phone) {
        set_Value(COLUMNNAME_Phone, Phone);
    }

    /**
     * Get Default mail template.
     *
     * @return Default mail template
     */
    public int getR_DefaultMailText_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_R_DefaultMailText_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Sales Representative.
     *
     * @return Sales Representative or Company Agent
     */
    public int getSalesRep_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_SalesRep_ID);
        if (ii == null) return 0;
        return ii;
    }

    /**
     * Get Salt.
     *
     * @return Random data added to improve password hash effectiveness
     */
    public String getSalt() {
        return (String) get_Value(COLUMNNAME_Salt);
    }

    /**
     * Set Salt.
     *
     * @param Salt Random data added to improve password hash effectiveness
     */
    public void setSalt(String Salt) {
        set_ValueNoCheck(COLUMNNAME_Salt, Salt);
    }

    /**
     * Get Supervisor.
     *
     * @return Supervisor for this user/organization - used for escalation and approval
     */
    public int getSupervisor_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_Supervisor_ID);
        if (ii == null) return 0;
        return ii;
    }

    @Override
    public int getTableId() {
        return I_AD_User.Table_ID;
    }
}
