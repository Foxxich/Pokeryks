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
    Log.d("cipeczka", card!!)
    val string = card.split("_")
    Log.d("cipeczka", string[0]+string[1])
    try {
        Log.d("karta", GlobalCardsMap.getInstance()[card]!!.toString())
    }
    catch (e: Exception){
        Log.d("smuteg",card)
    }
    return Card(
        color = string[0],
        type = string[1],
        image = GlobalCardsMap.getInstance()[card]!!
    )
}