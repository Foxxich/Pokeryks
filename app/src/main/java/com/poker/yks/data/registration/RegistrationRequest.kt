package com.poker.yks.data.registration

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    @Json(name = "login")
    val login: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "username")
    val userName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "endpoint")
    val endpoint: String
)
