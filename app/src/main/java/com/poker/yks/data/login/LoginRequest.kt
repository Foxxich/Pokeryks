package com.poker.yks.data.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(

    @Json(name = "login")
    val login: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "endpoint")
    val endpoint: String,
)
