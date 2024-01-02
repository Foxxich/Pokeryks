package com.poker.yks.data.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "username")
    val username: String,
    @Json(name = "money")
    val money: Int,
    @Json(name = "vip")
    val vip: Int,
)