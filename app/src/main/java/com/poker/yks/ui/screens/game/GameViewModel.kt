package com.poker.yks.ui.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.poker.yks.data.game.BeginGame
import com.poker.yks.data.game.EndGame
import com.poker.yks.data.game.Greeting
import com.poker.yks.data.game.Move
import com.poker.yks.data.game.UpdateTable
import com.poker.yks.rest.room.WebSocketClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject

class GameViewModel : ViewModel() {
    private lateinit var webSocketClient: WebSocketClient
    var counter = 0
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val socketListener = object : WebSocketClient.SocketListener {
        override fun onMessage(message: String) {
            Log.d("socketCheck", message)
//            Timber.tag("socketCheck").e(message)
            val xd: Any?
            val jsonObject = JSONObject(message)
            val className = jsonObject.getString("type")
            Log.d("socketreceive", className)
            when (className) {
                "Greeting" -> {
                    xd = determineDataType<Greeting>(message)
                }

                "UpdateTable" -> {
                    xd = determineDataType<UpdateTable>(message)
                }

                "BeginGame" -> {
                    xd = determineDataType<BeginGame>(message)
                }

                "EndGame" -> {
                    xd = determineDataType<EndGame>(message)
                }

                "Move" -> {
                    xd = determineDataType<Move>(message)
                }

                else -> {
                    xd = Greeting(message)
                }

            }

            Log.d("socketCheck89", xd.toString())
//            Timber.tag("socketCheck2").i(receivedDataType.toString())


        }

    }

    private inline fun <reified T : Any> determineDataType(message: String): Any? {
        try {
            val adapter = moshi.adapter(T::class.java)

            return adapter.fromJson(message)

        } catch (e: Exception) {
            // Log error message
            Log.e("socketCheck99", e.toString())
        }

        // Return default greeting if no matching message type is found
        return DataType.GreetingResult(Greeting("hello"))
    }

    private fun toJson(obj: Any): String =
        moshi.adapter(obj.javaClass).toJson(obj)


    fun createWebSocketConnection(url: String) {

        webSocketClient = WebSocketClient.getInstance()
        webSocketClient.setSocketUrl(url)
        webSocketClient.setListener(socketListener)
        webSocketClient.connect()
    }

    fun sendMessage(message: Any) {

        webSocketClient.sendMessage(toJson(message))
    }

    fun exitGame() {
        webSocketClient.disconnect()
    }

}


sealed class DataType {
    data class GreetingResult(val greeting: Greeting) : DataType()
    data class BeginGameResult(val beginGame: BeginGame) : DataType()
    data class UpdateTableResult(val updateTable: UpdateTable) : DataType()
    data class EndGameResult(val endGame: EndGame) : DataType()

}

