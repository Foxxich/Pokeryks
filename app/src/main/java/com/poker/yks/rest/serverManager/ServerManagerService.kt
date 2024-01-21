package com.poker.yks.rest.serverManager

import com.poker.yks.data.serverStatus.ServerStatusResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServerManagerService {

    @GET("status/")
    suspend fun getServerStatus(): Response<List<ServerStatusResponse>>

}