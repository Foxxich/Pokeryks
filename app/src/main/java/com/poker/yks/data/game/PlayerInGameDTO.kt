package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerInGameDTO(
    @Json(name = "player_nick")
    val nick: String,
    @Json
    val tokens: Int,
    @Json(name = "card_1")
    val card1: CardDto?,
    @Json(name = "card_2")
    val card2: CardDto?,
    @Json
    val winPercentage: Float?

)

fun PlayerInGameDTO.toPlayerInGame(): PlayerInGame {
    return PlayerInGame(
        nick = nick,
        tokens = tokens,
        card1 = card1?.toCard(),
        card2 = card2?.toCard(),
        winPercentage = winPercentage
    )
}
