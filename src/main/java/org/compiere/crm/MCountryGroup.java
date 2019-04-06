package org.compiere.crm;

import kotliquery.Row;
import org.idempiere.common.util.CLogger;

import static software.hsharp.core.util.DBKt.getSQLValue;

/**
 * Country Group Model
 */
public class MCountryGroup extends X_C_CountryGroup {
    /**
     *
     */
    private static final long serialVersionUID = 4986629677773273899L;
    /**
     * Static Logger
     */
    @SuppressWarnings("unused")
    private static CLogger s_log = CLogger.getCLogger(MCountryGroup.class);

    /**
     * *********************************************************************** Create empty Country
     *
     * @param C_CountryGroup_ID ID
     */
    public MCountryGroup(int C_CountryGroup_ID) {
        super(C_CountryGroup_ID);
    } //  MCountryGroup

    /**
     * Create Country Group from current row in ResultSet
     */
    public MCountryGroup(Row row) {
        super(row);
    } //	MCountryGroup

    public static boolean countryGroupContains(int c_CountryGroup_ID, int c_Country_ID) {

        if (c_CountryGroup_ID == 0 || c_Country_ID == 0) return false;

        final String sql =
                ""
                        + "SELECT Count(*) "
                        + "FROM   c_countrygroupcountry "
                        + "WHERE  c_country_id = ? "
                        + "       AND c_countrygroup_id = ? "
                        + "       AND isactive = 'Y' ";
        int cnt = getSQLValue(sql, c_Country_ID, c_CountryGroup_ID);

        return cnt > 0;
    }
} //	MCountryGroup
