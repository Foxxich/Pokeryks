package com.poker.yks.rest.serverManager

import com.poker.yks.data.serverStatus.ServerStatusRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerManagerService {

    @GET("status/")
    suspend fun getServersStatus(): Response<List<ServerStatusRequest>>

    @POST("manage_server/")
    suspend fun getServerConnection(@Body server_ip: String): Response<ServerStatusRequest>

    @POST("replace_server/")
    suspend fun getServerConnectionStopped(@Body server_ip: Int): Response<ServerStatusRequest>
}