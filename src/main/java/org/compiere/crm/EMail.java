package org.compiere.crm;

import java.io.Serializable;
import java.util.logging.Level;
import javax.mail.internet.InternetAddress;

import org.idempiere.common.util.CLogger;

public class EMail extends software.hsharp.core.util.EMail implements Serializable {
    protected static transient CLogger log = CLogger.getCLogger(EMail.class);

    public EMail(
            String smtpHost,
            int smtpPort,
            InternetAddress from,
            InternetAddress replyTo,
            String subject,
            String userName,
            String password,
            String messageHTML) {
        super(smtpHost, smtpPort, from, replyTo, subject, userName, password, messageHTML);
    }

    /**
     * Validate format of an email address IDEMPIERE-1409
     *
     * @return true if email has proper format
     */
    public static boolean validate(final String email) {
        try {
            new InternetAddress(email, true);
        } catch (Exception e) {
            log.log(Level.WARNING, email + ": " + e.toString());
            return false;
        }
        return true;
    }
}
