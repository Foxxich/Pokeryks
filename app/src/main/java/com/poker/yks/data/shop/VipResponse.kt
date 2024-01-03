package com.poker.yks.data.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VipResponse(
    @Json(name = "username")
    val username: String,
    @Json(name = "vip")
    var vip: Int,
)