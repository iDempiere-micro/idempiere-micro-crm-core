package org.compiere.orm

import org.compiere.crm.MUser
import org.compiere.model.I_AD_User
import software.hsharp.core.models.EnvironmentService

class UsersService(
    private val environmentService: EnvironmentService
) {
    fun getUsers(): List<I_AD_User> {
        return MUser
            .getOfClient(environmentService.context, environmentService.clientId)
            .filter { it.description != null }
    }
}