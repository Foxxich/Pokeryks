package com.poker.yks.rest.serverManager

import com.poker.yks.data.serverStatus.ServerStatus
import retrofit2.Response
import retrofit2.http.GET

interface ServerManagerService {

    @GET("status")
    suspend fun getServersStatus(): Response<List<ServerStatus>>
}