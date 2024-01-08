package com.poker.yks.data.serverStatus

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerStatusRequest(
    @Json(name = "ip")
    var ip: String,
    @Json(name = "occupation")
    var occupation: Int,
    @Json(name = "status")
    var status: String,
)
