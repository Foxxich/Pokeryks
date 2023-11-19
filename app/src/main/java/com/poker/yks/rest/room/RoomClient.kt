package com.poker.yks.rest.room

import com.poker.yks.rest.serverManager.ServerManagerService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Url

object RoomClient {

    private var BASE_URL = ""

    fun initialize(url: String){
        BASE_URL = url
    }


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val roomService: RoomService = retrofit.create(RoomService::class.java)

}