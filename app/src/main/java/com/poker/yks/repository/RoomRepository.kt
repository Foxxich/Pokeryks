package com.poker.yks.repository

import com.poker.yks.data.login.LoginResponse
import com.poker.yks.data.serverStatus.ServerStatus
import com.poker.yks.rest.room.RoomClient
import com.poker.yks.rest.serverManager.ServerManagerClient
import retrofit2.Response

//class RoomRepository {
//
//
//        private val api = RoomClient.roomService
//
//        suspend fun joinServer(ip: String, loginResponse: LoginResponse ){
//                RoomClient.initialize(ip)
//                api.joinServer(loginResponse)
//        }
//
//}