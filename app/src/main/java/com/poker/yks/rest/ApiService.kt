package com.poker.yks.rest

import com.poker.yks.data.User
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.data.registration.RegistrationRequest
import com.poker.yks.data.registration.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("login")
    suspend fun loginToGame(@Body request: LoginRequest) : Response<LoginResponse>

    @POST("registration")
    suspend fun registerNewAccount(@Body request: RegistrationRequest): Response<RegistrationResponse>



    // post - login
    // post - registration
    // post - save user`s move
    // get - get user
    // get - retrieve other users moves
    // delete - logout
    // delete - delete account
}
