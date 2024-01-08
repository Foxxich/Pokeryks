package com.poker.yks.rest.rankingomat

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RankingomatClient {

//    private const val BASE_URL = "http://192.168.0.108:8001/"
private const val BASE_URL = "http://10.0.2.2:8001/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rankingomatService: RankingomatService = retrofit.create(RankingomatService::class.java)

}
