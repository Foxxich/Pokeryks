package com.poker.yks.rest.serverManager

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServerManagerClient {

        private const val BASE_URL = "https://serverManager.com/"

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val serverManagerService: ServerManagerService = retrofit.create(ServerManagerService::class.java)

}