package com.poker.yks.data.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "vip")
    val vip: String,
    @Json(name = "endpoint")
    val endpoint: String,
)
