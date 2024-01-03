package com.poker.yks.data.game

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardDto(
    @Json
    val color: String,
    @Json
    val type: String,
)

fun CardDto.toCard(): Card {
    val string = color + "_" + type
    return Card(
        color = color,
        type = type,
        image = GlobalCardsMap.getInstance()[string]!!
    )
}