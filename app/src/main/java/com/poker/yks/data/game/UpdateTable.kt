package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//receiving
@JsonClass(generateAdapter = true)
data class UpdateTable(
    @Json
    val cardsOnTable: List<Card>,
    @Json
    val playersStatus: List<PlayerInGame>,
    @Json
    val nextPlayer: String,
    @Json
    val tokensOnTable: Int,
    @Json
    val lastCall: Int
)
