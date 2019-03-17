package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_AD_PrintColor;
import org.compiere.orm.BasePOName;

import java.util.Properties;

/**
 * Generated Model for AD_PrintColor
 *
 * @author iDempiere (generated)
 * @version Release 5.1 - $Id$
 */
public class X_AD_PrintColor extends BasePOName implements I_AD_PrintColor {

    /**
     *
     */
    private static final long serialVersionUID = 20171031L;

    /**
     * Standard Constructor
     */
    public X_AD_PrintColor(Properties ctx, int AD_PrintColor_ID) {
        super(ctx, AD_PrintColor_ID);
    }

    /**
     * Load Constructor
     */
    public X_AD_PrintColor(Properties ctx, Row row) {
        super(ctx, row);
    }

    /**
     * AccessLevel
     *
     * @return 6 - System - Client
     */
    protected int getAccessLevel() {
        return accessLevel.intValue();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_AD_PrintColor[").append(getId()).append("]");
        return sb.toString();
    }

    @Override
    public int getTableId() {
        return I_AD_PrintColor.Table_ID;
    }
}
