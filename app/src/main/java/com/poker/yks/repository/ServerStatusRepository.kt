package com.poker.yks.repository

import com.poker.yks.data.serverStatus.ServerStatus
import com.poker.yks.rest.serverManager.ServerManagerClient
import retrofit2.Response

class ServerStatusRepository {

    private val api = ServerManagerClient.serverManagerService

    suspend fun getServersStatus(): Response<List<ServerStatus>> {
        return api.getServersStatus()
    }
}