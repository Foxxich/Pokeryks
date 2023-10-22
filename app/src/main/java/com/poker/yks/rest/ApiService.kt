package com.poker.yks.rest

import com.poker.yks.data.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    // post - login
    // post - registration
    // post - save user`s move
    // get - get user
    // get - retrieve other users moves
    // delete - logout
    // delete - delete account
}
