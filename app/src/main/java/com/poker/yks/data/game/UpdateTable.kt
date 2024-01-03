package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//receiving
@JsonClass(generateAdapter = true)
data class UpdateTable(
    @Json
    val cardsOnTable: List<CardDto>,
    @Json
    val playersInGame: List<PlayerInGameDTO>,
    @Json
    val nextPlayer: String,
    @Json
    val tokensOnTable: Int,
    @Json
    val lastCall: Int
)
