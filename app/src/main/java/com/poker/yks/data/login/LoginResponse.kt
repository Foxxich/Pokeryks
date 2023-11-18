package com.poker.yks.data.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//status - 0- jeszcze nie podjęto próby 1 gitówa 2- błędnie -
@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "status")
    val status: Int,
    @Json(name = "nick")
    val nick: String,
    @Json(name = "money")
    val money: Int,
    @Json(name = "vip")
    val vip: Int,
)