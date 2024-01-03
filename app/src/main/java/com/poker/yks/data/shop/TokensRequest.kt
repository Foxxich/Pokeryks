package com.poker.yks.data.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokensRequest(
    @Json(name = "username")
    val username: String,
    @Json(name = "tokens")
    val tokens: String,
    @Json(name = "endpoint")
    val endpoint: String,
)