package org.compiere.crm;

import kotliquery.Row;
import org.idempiere.common.util.CCache;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.Collator;
import java.util.*;

/**
 * Localtion Region Model (Value Object)
 *
 * @author Jorg Janke
 * @version $Id: MRegion.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 */
public class MRegion extends MBaseRegion implements Comparator<Object>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1124865777747582617L;
    /**
     * Default Region
     */
    private static MRegion s_default = null;

    /**
     * ************************************************************************ Create empty Region
     *
     * @param ctx         context
     * @param C_Region_ID id
     * @param trxName     transaction
     */
    public MRegion(Properties ctx, int C_Region_ID) {
        super(ctx, C_Region_ID);
    } //  MRegion

    /**
     * Create Region from current row in ResultSet
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MRegion(Properties ctx, ResultSet rs) {
        super(ctx, rs);
    } //	MRegion

    public MRegion(Properties ctx, Row row) {
        super(ctx, row);
    } //	MRegion

    /**
     * Parent Constructor
     *
     * @param country    country
     * @param regionName Region Name
     */
    public MRegion(MCountry country, String regionName) {
        super(country.getCtx(), 0);
        setC_Country_ID(country.getC_Country_ID());
        setName(regionName);
    } //  MRegion

    /**
     * Load Regions (cached)
     *
     * @param ctx context
     */
    private static void loadAllRegions(Properties ctx) {
        MBaseRegionKt.loadAllRegions(ctx);
    } //	loadAllRegions

    /**
     * Get Country (cached)
     *
     * @param ctx         context
     * @param C_Region_ID ID
     * @return Country
     */
    public static MRegion get(Properties ctx, int C_Region_ID) {
        CCache<String, MRegion> s_regions = MBaseRegionKt.getRegionsCache();
        if (s_regions == null || s_regions.size() == 0) loadAllRegions(ctx);
        String key = String.valueOf(C_Region_ID);
        MRegion r = s_regions.get(key);
        if (r != null) return r;
        r = new MRegion(ctx, C_Region_ID);
        if (r.getC_Region_ID() == C_Region_ID) {
            s_regions.put(key, r);
            return r;
        }
        return null;
    } //	get

    /**
     * Get Default Region
     *
     * @param ctx context
     * @return Region or null
     */
    public static MRegion getDefault(Properties ctx) {
        CCache<String, MRegion> s_regions = MBaseRegionKt.getRegionsCache();
        if (s_regions == null || s_regions.size() == 0) loadAllRegions(ctx);
        return s_default;
    } //	get

    /**
     * Return Array of Regions of Country
     *
     * @param ctx          context
     * @param C_Country_ID country
     * @return MRegion Array
     */
    public static MRegion[] getRegions(Properties ctx, int C_Country_ID) {
        CCache<String, MRegion> s_regions = MBaseRegionKt.getRegionsCache();
        if (s_regions == null || s_regions.size() == 0) loadAllRegions(ctx);
        ArrayList<MRegion> list = new ArrayList<MRegion>();
        Iterator<MRegion> it = s_regions.values().iterator();
        while (it.hasNext()) {
            MRegion r = it.next();
            if (r.getC_Country_ID() == C_Country_ID) list.add(r);
        }
        //  Sort it
        MRegion[] retValue = new MRegion[list.size()];
        list.toArray(retValue);
        Arrays.sort(retValue, new MRegion(ctx, 0));
        return retValue;
    } //	getRegions

    /**
     * Return Name
     *
     * @return Name
     */
    public String toString() {
        return getName();
    } //  toString

    /**
     * Compare
     *
     * @param o1 object 1
     * @param o2 object 2
     * @return -1,0, 1
     */
    public int compare(Object o1, Object o2) {
        String s1 = o1.toString();
        if (s1 == null) s1 = "";
        String s2 = o2.toString();
        if (s2 == null) s2 = "";
        Collator collator = Collator.getInstance();
        return collator.compare(s1, s2);
    } //	compare
} //	MRegion
