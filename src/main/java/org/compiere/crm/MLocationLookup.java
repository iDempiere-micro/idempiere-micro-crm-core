package org.compiere.crm;

import org.compiere.util.DisplayType;
import org.idempiere.orm.Lookup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Address Loaction Lookup Model.
 *
 * @author Jorg Janke
 * @version $Id: MLocationLookup.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public final class MLocationLookup extends Lookup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7238110708451510319L;
    /**
     * Context
     */
    private Properties m_ctx;

    /**
     * Constructor
     *
     * @param ctx      context
     * @param WindowNo window no (to derive AD_Client/Org for new records)
     */
    public MLocationLookup(Properties ctx, int WindowNo) {
        super(DisplayType.TableDir, WindowNo);
        m_ctx = ctx;
    } //	MLocation

    /**
     * Get Location
     *
     * @param C_Location_ID id
     * @param trxName       transaction
     * @return Location
     */
    public MLocation getLocation(int C_Location_ID) {
        return MLocation.get(m_ctx, C_Location_ID);
    } //	getLocationId

    /**
     * Get underlying fully qualified Table.Column Name. Used for VLookup.actionButton (Zoom)
     *
     * @return column name
     */
    public String getColumnName() {
        return "C_Location_ID";
    } //  getColumnName

    /**
     * Return data as sorted Array - not implemented
     *
     * @param mandatory     mandatory
     * @param onlyValidated only validated
     * @param onlyActive    only active
     * @param temporary     force load for temporary display
     * @return null
     */
    public ArrayList<Object> getData(
            boolean mandatory,
            boolean onlyValidated,
            boolean onlyActive,
            boolean temporary,
            boolean shortlist) // IDEMPIERE 90
    {
        log.log(Level.SEVERE, "not implemented");
        return null;
    } //  getArray
} //	MLocation
