package org.compiere.orm

import org.compiere.crm.getClientUsers
import org.compiere.model.I_AD_User
import software.hsharp.core.services.EnvironmentService
import software.hsharp.services.UsersService

class UsersServiceImpl(
    private val environmentService: EnvironmentService
) : UsersService {
    override fun getUsers(): List<I_AD_User> {
        return getClientUsers(environmentService.clientId)
            .filter { it.description != null }
    }
}