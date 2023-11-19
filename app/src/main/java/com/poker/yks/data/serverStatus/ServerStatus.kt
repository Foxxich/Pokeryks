package com.poker.yks.data.serverStatus

import com.squareup.moshi.Json

data class ServerStatus(
    @Json(name = "ip")
    var ip: String,
    @Json(name = "occupation")
    var occupation: Int,
)
