package org.compiere.crm;

import org.idempiere.common.util.CLogger;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Business Partner Contact Interest. Adempiere compies with spamming laws. If the opt out date is
 * set (by the user), you should not subscribe the user again. Internally, the isActive flag is
 * used.
 *
 * @author Jorg Janke
 * @author red1 FR: [ 2214883 ] Remove SQL code and Replace for Query
 * @version $Id: MContactInterest.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MContactInterest extends X_R_ContactInterest {
    /**
     *
     */
    private static final long serialVersionUID = -4720845687902863428L;
    /**
     * Static Logger
     */
    private static CLogger s_log = CLogger.getCLogger(MContactInterest.class);

    /**
     * ************************************************************************ Persistency
     * Constructor
     *
     * @param ctx     context
     * @param ignored ignored
     * @param trxName transaction
     */
    public MContactInterest(Properties ctx, int ignored) {
        super(ctx, 0);
        if (ignored != 0) throw new IllegalArgumentException("Multi-Key");
    } //	MContactInterest

    /**
     * Constructor
     *
     * @param ctx               context
     * @param R_InterestArea_ID interest area
     * @param AD_User_ID        partner contact
     * @param isActive          create as active
     * @param trxName           transaction
     */
    public MContactInterest(
            Properties ctx, int R_InterestArea_ID, int AD_User_ID, boolean isActive) {
        super(ctx, 0);
        setR_InterestArea_ID(R_InterestArea_ID);
        setAD_User_ID(AD_User_ID);
        setIsActive(isActive);
    } //	MContactInterest

    /**
     * Create & Load existing Persistent Object.
     *
     * @param ctx     context
     * @param rs      load from current result set position (no navigation, not closed)
     * @param trxName transaction
     */
    public MContactInterest(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MContactInterest

    /**
     * Set OptOut Date User action only.
     *
     * @param OptOutDate date
     */
    public void setOptOutDate(Timestamp OptOutDate) {
        if (OptOutDate == null) OptOutDate = new Timestamp(System.currentTimeMillis());
        if (log.isLoggable(Level.FINE)) log.fine("" + OptOutDate);
        super.setOptOutDate(OptOutDate);
        setIsActive(false);
    } //	setOptOutDate

    /**
     * Set Subscribe Date User action only.
     *
     * @param SubscribeDate date
     */
    public void setSubscribeDate(Timestamp SubscribeDate) {
        if (SubscribeDate == null) SubscribeDate = new Timestamp(System.currentTimeMillis());
        if (log.isLoggable(Level.FINE)) log.fine("" + SubscribeDate);
        super.setSubscribeDate(SubscribeDate);
        super.setOptOutDate(null);
        setIsActive(true);
    } //	setSubscribeDate

    /**
     * Is Subscribed. Active is set internally, the opt out date is set by the user via the web UI.
     *
     * @return true if subscribed
     */
    public boolean isSubscribed() {
        return isActive() && getOptOutDate() == null;
    } //	isSubscribed

    /**
     * String representation
     *
     * @return info
     */
    public String toString() {
        StringBuilder sb =
                new StringBuilder("MContactInterest[")
                        .append("R_InterestArea_ID=")
                        .append(getR_InterestArea_ID())
                        .append(",AD_User_ID=")
                        .append(getAD_User_ID())
                        .append(",Subscribed=")
                        .append(isSubscribed())
                        .append("]");
        return sb.toString();
    } //	toString
} //	MContactInterest
