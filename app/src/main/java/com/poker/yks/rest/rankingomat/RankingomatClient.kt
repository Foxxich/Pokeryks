package com.poker.yks.rest.rankingomat

import com.poker.yks.rest.serverManager.ServerManagerService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RankingomatClient {

    private const val BASE_URL = "http://192.168.0.103:8000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rankingomatService= retrofit.create(RankingomatService::class.java)

}
