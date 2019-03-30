SELECT C.*
FROM crm_category C
       INNER JOIN crm_customer_category CC ON C.crm_category_id = CC.crm_category_id
WHERE c_bpartner_id = ?