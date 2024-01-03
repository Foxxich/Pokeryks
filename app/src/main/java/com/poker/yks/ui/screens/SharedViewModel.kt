package com.poker.yks.ui.screens

import androidx.lifecycle.ViewModel
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.data.registration.RegistrationResponse

class SharedViewModel : ViewModel() {
    var nick: String = ""
    var money: Int = 0
    var vip: Int = 0


    fun getPlayerInfo(): LoginResponse {
        return LoginResponse(nick, money, vip)
    }

    fun setPlayerInfo(loginResponse: LoginResponse) {
        nick = loginResponse.username
        money = loginResponse.money
        vip = loginResponse.vip
    }

    fun setPlayerInfo(registrationResponse: RegistrationResponse) {
        nick = registrationResponse.username.toString()
        money = registrationResponse.money!!
        vip = registrationResponse.vip!!
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}