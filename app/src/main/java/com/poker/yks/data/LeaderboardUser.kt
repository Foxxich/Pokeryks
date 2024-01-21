package com.poker.yks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeaderboardUser(
    @Json(name = "username")
    val username: String,
    @Json(name = "win_number")
    val win_number: Int
)