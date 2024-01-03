package com.poker.yks.rest.rankingomat

import com.poker.yks.data.LeaderboardUser
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.data.registration.RegistrationRequest
import com.poker.yks.data.registration.RegistrationResponse
import com.poker.yks.data.shop.TokensRequest
import com.poker.yks.data.shop.TokensResponse
import com.poker.yks.data.shop.VipRequest
import com.poker.yks.data.shop.VipResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RankingomatService {

    @GET("ranks/")
    suspend fun getUsers(): Response<List<LeaderboardUser>>

    @POST("login/")
    suspend fun loginToGame(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register/")
    suspend fun registerNewAccount(@Body request: RegistrationRequest): Response<RegistrationResponse>

    @POST("update_vip/")
    suspend fun updateVipStatus(@Body request: VipRequest): Response<VipResponse>

    @POST("purchase_tokens/")
    suspend fun updateTokensNumber(@Body request: TokensRequest): Response<TokensResponse>

}