package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Move(
    @Json
    val type: String = "Move",
    @Json(name = "nick")
    val nick: String, //our name
    @Json(name = "moveType")
    val moveType: String, //call raise fold
    @Json(name = "amount")
    val amount: Int,
)

