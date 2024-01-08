package com.poker.yks.repository

import com.poker.yks.data.serverStatus.ServerStatusRequest
import com.poker.yks.rest.serverManager.ServerManagerClient
import retrofit2.Response

class ServerStatusRepository {

    private val api = ServerManagerClient.serverManagerService

    suspend fun getServersStatus(): Response<List<ServerStatusRequest>> {
        return api.getServersStatus()
    }

    suspend fun getServerSpecificConnection(serverId: String): Response<ServerStatusRequest> {
        return api.getServerConnection(serverId)
    }

    suspend fun getServerSpecificConnectionStopped(serverId: Int): Response<ServerStatusRequest> {
        return api.getServerConnectionStopped(serverId)
    }
}