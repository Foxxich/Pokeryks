package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PlayerInGame(
    val nick: String,
    val tokens: Int,
    val card1: Card?,
    val card2: Card?,
    val winPercentage: Float?

)
fun PlayerInGame.toDTO(): PlayerInGameDTO{
    return PlayerInGameDTO(
        nick = nick,
        tokens = tokens,
        card1 = card1?.toCardDto(),
        card2 = card2?.toCardDto(),
        winPercentage
    )
}