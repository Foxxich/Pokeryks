package com.poker.yks.data.registration

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistrationResponse(
    @Json(name = "message")
    val message: String
)
