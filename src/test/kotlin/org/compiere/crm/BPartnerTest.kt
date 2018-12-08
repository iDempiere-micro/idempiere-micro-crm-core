package org.compiere.crm

import company.bigger.test.support.randomString
import org.compiere.model.I_C_BPartner
import org.idempiere.common.util.Env
import org.junit.Test
import kotlin.test.assertEquals

class BPartnerTest : BaseCrmTest() {

    @Test
    fun `loading saving finding business partner work`() {
        val ctx = Env.getCtx()
        val AD_CLIENT_ID = 11
        val AD_CLIENT_ID_s = AD_CLIENT_ID.toString()
        ctx.setProperty(Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        Env.setContext(ctx, Env.AD_CLIENT_ID, AD_CLIENT_ID_s)
        val AD_USER_ID = 104
        val AD_USER_ID_s = AD_USER_ID.toString()
        ctx.setProperty(Env.AD_USER_ID, AD_USER_ID_s)
        Env.setContext(ctx, Env.AD_USER_ID, AD_USER_ID_s)

        val id = 118
        val partner = MBPartner.get(Env.getCtx(), id)

        assertEquals(id, partner.c_BPartner_ID)
        assertEquals("JoeBlock", partner.value)
        assertEquals("Joe Block", partner.name)

        val partner2: I_C_BPartner = partner as I_C_BPartner

        val newValue = "JoeBlock*"
        partner2.setValue(newValue)
        partner2.save()

        val partner3 = MBPartner.get(Env.getCtx(), id)

        assertEquals(id, partner3.c_BPartner_ID)
        assertEquals(newValue, partner3.value)
        assertEquals("Joe Block", partner3.name)

        partner2.setValue("JoeBlock")
        partner2.save()

        val newPartner = MBPartner.getTemplate(ctx, AD_CLIENT_ID)
        val name = "Test " + randomString(10)
        newPartner.setName(name)
        val value = "t-" + randomString(5)
        newPartner.setValue(value)
        newPartner.save()

        val defaultCountry = MCountry.getDefault(ctx)
        val defaultRegion = MRegion.getDefault(ctx)
        val location = MLocation(defaultCountry, defaultRegion)
        location.address1 = "Address 1"
        location.address2 = "Address 2"
        location.city = "City"
        location.save()
        val partnerLocation = MBPartnerLocation(newPartner)
        partnerLocation.c_Location_ID = location.c_Location_ID
        partnerLocation.save()

        val newPartner2 = MBPartner.get(Env.getCtx(), newPartner.c_BPartner_ID)
        assertEquals(1, newPartner2.locations.count())

        newPartner.delete(true)

        location.delete(true)
    }
}