package com.poker.yks.data.game

import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Greeting (
    @Json
    val hello: String
)