package com.poker.yks.data.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VipRequest(
    @Json(name = "username")
    val username: String,
    @Json(name = "vip")
    val vip: String,
    @Json(name = "endpoint")
    val endpoint: String,
)
