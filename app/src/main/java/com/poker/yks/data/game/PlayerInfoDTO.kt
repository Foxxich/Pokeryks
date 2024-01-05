package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerInfoDTO (
    @Json
    val type:String = "PlayerInfo",
    @Json
    val player_nick:String,
    @Json
    val tokens:Int,
    @Json
    val vip: Int

)