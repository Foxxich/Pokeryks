package com.poker.yks.rest.serverManager

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServerManagerClient {

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun createServerManagerService(baseUrl: String): ServerManagerService {
        val retrofit = createRetrofit(baseUrl)
        return retrofit.create(ServerManagerService::class.java)
    }
}