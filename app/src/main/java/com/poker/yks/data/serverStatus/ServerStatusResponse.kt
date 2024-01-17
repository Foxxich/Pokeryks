package com.poker.yks.data.serverStatus

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerStatusResponse(
    @Json(name = "ip")
    var ip: String,
    @Json(name = "occupation")
    var occupation: Int,
)
