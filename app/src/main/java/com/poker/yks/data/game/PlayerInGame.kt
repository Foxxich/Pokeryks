package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerInGame(
    @Json(name = "nick")
    val nick: String,
    @Json(name = "tokensInHand")
    val tokensInHand: Int,
    @Json(name = "cards")
    val cards: List<Card>, //2
    @Json
    val winPercentage: Float?

)
