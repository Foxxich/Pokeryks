package com.poker.yks.repository

import com.poker.yks.rest.serverManager.ServerManagerClient

class ServerStatusRepository {

    private val api = ServerManagerClient.serverManagerService

    suspend fun getServersStatus() = api.getServerStatus()
}