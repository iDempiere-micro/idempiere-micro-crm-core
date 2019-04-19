package software.hsharp.core.util

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.HtmlEmail
import software.hsharp.core.models.Email
import javax.mail.internet.InternetAddress

open class EMail(
    override var smtpHost: String,
    override var smtpPort: Int,
    override var from: InternetAddress,
    override var replyTo: InternetAddress,
    override var subject: String,
    override var userName: String,
    override var password: String,
    override var messageInHTML: String
) : Email {

    companion object {
        fun convert(s: String, email: EMail): InternetAddress? {
            try {
                return InternetAddress(s, true)
            } catch (e: Exception) {
                email.m_valid = false
                return null
            }
        }
    }

    protected fun convert(s: String): InternetAddress? {
        return EMail.convert(s, this)
    }

    protected var m_valid = false

    protected var m_to: MutableList<InternetAddress> = mutableListOf()
    /** CC Addresses				 */
    protected var m_cc: MutableList<InternetAddress> = mutableListOf()
    /** BCC Addresses				 */
    protected var m_bcc: MutableList<InternetAddress> = mutableListOf()

    override fun isValid(): Boolean {
        return m_valid
    }

    override fun addTo(newTo: String): Boolean {
        val ia = convert(newTo)
        if (ia == null) {
            return false
        } else {
            this.m_to.add(ia)
            return true
        }
    }

    override fun addCc(newCc: String): Boolean {
        val ia = convert(newCc)
        if (ia == null) {
            return false
        } else {
            this.m_cc.add(ia)
            return true
        }
    }

    override fun addBcc(newBcc: String): Boolean {
        val ia = convert(newBcc)
        if (ia == null) {
            return false
        } else {
            this.m_bcc.add(ia)
            return true
        }
    }

    override fun send(): String {
        val email = HtmlEmail()
        email.hostName = smtpHost
        email.setSmtpPort(smtpPort)
        email.setAuthenticator(DefaultAuthenticator(userName, password))
        email.isSSLOnConnect = true
        email.setFrom(from.address)
        m_to.forEach { email.addTo(it.address) }
        email.subject = subject
        // val kotlinLogoURL = URL("https://kotlinlang.org/assets/images/twitter-card/kotlin_800x320.png")
        // val cid = email.embed(kotlinLogoURL, "Kotlin logo")
        email.setHtmlMsg(messageInHTML)
        email.send()

        return "Success"
    }
}