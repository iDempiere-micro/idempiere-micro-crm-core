package org.compiere.crm;

import kotliquery.Row;
import org.compiere.model.I_C_BPartner;
import org.compiere.orm.MClient;
import org.compiere.orm.MTable;

public class MClientInfo extends org.compiere.orm.MClientInfo {
    /**
     *
     */
    private static final long serialVersionUID = 4861006368856890116L;

    /**
     * Load Constructor
     *
     * @param ctx context
     */
    public MClientInfo(Row row) {
        super(row);
    }

    public MClientInfo(
            MClient client,
            int AD_Tree_Org_ID,
            int AD_Tree_BPartner_ID,
            int AD_Tree_Project_ID,
            int AD_Tree_SalesRegion_ID,
            int AD_Tree_Product_ID,
            int AD_Tree_Campaign_ID,
            int AD_Tree_Activity_ID,
            String trxName) {
        super(
                client,
                AD_Tree_Org_ID,
                AD_Tree_BPartner_ID,
                AD_Tree_Project_ID,
                AD_Tree_SalesRegion_ID,
                AD_Tree_Product_ID,
                AD_Tree_Campaign_ID,
                AD_Tree_Activity_ID
        );
    }

    /**
     * Get Client Info
     *
     * @param ctx          context
     * @param AD_Client_ID id
     * @param trxName      optional trx
     * @return Client Info
     */
    public static MClientInfo get(int AD_Client_ID) {
        return MBaseClientInfoKt.get(AD_Client_ID);
    } //	get

    /**
     * Overwrite Save
     *
     * @return true if saved
     * @overwrite
     */
    public boolean save() {
        if (getOrgId() != 0) setOrgId(0);
        if (getCreateNew()) return super.save();
        return saveUpdate();
    } //	save

    public I_C_BPartner getBPartnerCashTrx() throws RuntimeException {
        return (I_C_BPartner)
                MTable.get(I_C_BPartner.Table_Name).getPO(getBPartnerCashTrxId());
    }
} //	MClientInfo
