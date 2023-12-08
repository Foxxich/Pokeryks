package com.poker.yks.ui.screens

import androidx.lifecycle.ViewModel
import com.poker.yks.data.login.LoginResponse

class SharedViewModel : ViewModel() {
    private var nick: String = ""
    private var money: Int = 0
    private var vip: Int = 0


    fun getPlayerInfo(): LoginResponse {
        return LoginResponse(nick, money, vip)
    }
    fun setPlayerInfo(loginResponse: LoginResponse){
        nick = loginResponse.nick
        money = loginResponse.money
        vip = loginResponse.vip
    }

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared")
    }
}