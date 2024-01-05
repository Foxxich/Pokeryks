package com.poker.yks.data.login

import com.poker.yks.data.game.PlayerInfoDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "username")
    val username: String,
    @Json(name = "money")
    var money: Int,
    @Json(name = "vip")
    var vip: Int,
)

fun LoginResponse.toPlayerInfoDTO(): PlayerInfoDTO{
    return PlayerInfoDTO(
        player_nick = username,
        tokens = money,
        vip = vip
    )
}