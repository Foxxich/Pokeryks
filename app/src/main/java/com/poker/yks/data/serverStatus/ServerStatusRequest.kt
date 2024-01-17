package com.poker.yks.data.serverStatus

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerStatusRequest(
    @Json(name = "occupation")
    val occupation: String,
    @Json(name = "ip")
    val ip: Int,
)