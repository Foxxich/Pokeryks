package com.poker.yks.rest.rankingomat

import com.poker.yks.rest.serverManager.ServerManagerService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RankingomatClient {

    private const val BASE_URL = "https://rankingomat.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rankingomatService: RankingomatService = retrofit.create(RankingomatService::class.java)

}