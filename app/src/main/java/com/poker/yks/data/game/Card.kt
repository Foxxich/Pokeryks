package com.poker.yks.data.game



data class Card(
    val color: String,
    val type: String,
    val image: Int
)
fun Card.toCardDto(): CardDto{
    return CardDto(
        card = color + "_" + type
    )
}