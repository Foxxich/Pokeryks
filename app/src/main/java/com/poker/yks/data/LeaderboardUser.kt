package com.poker.yks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeaderboardUser(
    @Json(name = "player")
    val player: Int,
    @Json(name = "win_number")
    val win_number: Int
)