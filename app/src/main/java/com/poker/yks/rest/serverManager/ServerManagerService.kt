package com.poker.yks.rest.serverManager

import com.poker.yks.data.serverStatus.ServerStatusRequest
import com.poker.yks.data.serverStatus.ServerStatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerManagerService {

    @GET("status/")
    suspend fun getServerStatus(): Response<ServerStatusResponse>

    @POST("update/")
    suspend fun updateServerStatus(@Body request: ServerStatusRequest):
            Response<ServerStatusResponse>
}