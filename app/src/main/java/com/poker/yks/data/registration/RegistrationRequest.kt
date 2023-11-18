package com.poker.yks.data.registration

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationRequest(
    @Json(name = "login")
    val login: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "nick")
    val nick: String
)
