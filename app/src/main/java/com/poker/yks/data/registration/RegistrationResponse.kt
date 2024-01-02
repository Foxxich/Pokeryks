package com.poker.yks.data.registration

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationResponse(
    @Json(name = "status")
    val status: Int,
    @Json(name = "username")
    val username: String?,
    @Json(name = "money")
    val money: Int?,
    @Json(name = "vip")
    val vip: Int?,
)
