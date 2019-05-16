SELECT *
FROM C_Location l
WHERE C_Location_ID IN (SELECT C_Location_ID FROM C_BPartner_Location WHERE C_BPartner_Location_ID=?)