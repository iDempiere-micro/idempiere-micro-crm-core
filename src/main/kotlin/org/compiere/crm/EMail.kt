package org.compiere.crm

import org.idempiere.common.util.CLogger

import javax.mail.internet.InternetAddress
import java.io.Serializable
import java.util.logging.Level

class EMail(
    smtpHost: String,
    smtpPort: Int,
    from: InternetAddress,
    replyTo: InternetAddress,
    subject: String,
    userName: String,
    password: String,
    messageHTML: String
) : software.hsharp.core.util.EMail(smtpHost, smtpPort, from, replyTo, subject, userName, password, messageHTML),
    Serializable {
    companion object {
        @Transient
        protected var log = CLogger.getCLogger(EMail::class.java)

        /**
         * Validate format of an email address IDEMPIERE-1409
         *
         * @return true if email has proper format
         */
        fun validate(email: String): Boolean {
            try {
                InternetAddress(email, true)
            } catch (e: Exception) {
                log.log(Level.WARNING, "$email: $e")
                return false
            }

            return true
        }
    }
}
