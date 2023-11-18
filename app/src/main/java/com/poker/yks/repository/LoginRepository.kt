package com.poker.yks.repository

import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.rest.ApiClient
import retrofit2.Response

class LoginRepository() {

    private val api = ApiClient.apiService

    suspend fun postLoginCredentials(loginRequest: LoginRequest): Response<LoginResponse>{
        return api.loginToGame(loginRequest)
    }

}