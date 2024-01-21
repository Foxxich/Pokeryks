package com.poker.yks.rest.serverManager

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServerManagerClient {

    //    private const val BASE_URL = "http://192.168.0.108:8002/"
    private const val BASE_URL = "http://10.0.2.2:8002/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val serverManagerService: ServerManagerService =
        retrofit.create(ServerManagerService::class.java)
}