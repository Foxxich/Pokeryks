package com.poker.yks.repository

import com.poker.yks.data.LeaderboardUser
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.rest.rankingomat.RankingomatClient
import retrofit2.Response

class LeaderboardRebository {
    private val api = RankingomatClient.rankingomatService

    suspend fun getLeaderboardList(): Response<List<LeaderboardUser>> {
        return api.getUsers()
    }
}