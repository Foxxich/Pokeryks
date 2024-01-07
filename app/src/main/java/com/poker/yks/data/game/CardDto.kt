package com.poker.yks.data.game

import android.util.Log
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardDto(
    @Json
    val card: String?,
)
fun CardDto.toCard(): Card{

    val string = card!!.split("_")

    try {

    }
    catch (e: Exception){

    }
    return Card(
        color = string[0],
        type = string[1],
        image = GlobalCardsMap.getInstance()[card]!!
    )
}