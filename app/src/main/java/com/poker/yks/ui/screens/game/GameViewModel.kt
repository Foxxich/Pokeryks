package com.poker.yks.ui.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.poker.yks.rest.room.WebSocketClient

class GameViewModel : ViewModel() {
    private lateinit var webSocketClient : WebSocketClient
    private val socketListener = object : WebSocketClient.SocketListener {
        override fun onMessage(message: String) {
            Log.e("socketCheck onMessage", message)
        }

    }

    fun createWebSocketConnection(url:String){

        webSocketClient = WebSocketClient.getInstance()
        webSocketClient.setSocketUrl(url)
        webSocketClient.setListener(socketListener)
        webSocketClient.connect()
    }
    fun sendMessage(message:String){
        webSocketClient.sendMessage(message)
    }

}
