package com.poker.yks.repository

import com.poker.yks.data.serverStatus.ServerStatusRequest
import com.poker.yks.rest.serverManager.ServerManagerClient

class ServerStatusRepository {

    suspend fun getServersStatus(baseUrl: String) =
        ServerManagerClient.createServerManagerService(baseUrl).getServerStatus()

    suspend fun setServerDetails(baseUrl: String, serverStatusRequest: ServerStatusRequest) =
        ServerManagerClient.createServerManagerService(baseUrl)
            .updateServerStatus(serverStatusRequest)
}