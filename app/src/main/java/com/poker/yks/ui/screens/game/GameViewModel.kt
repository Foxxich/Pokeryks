package com.poker.yks.ui.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.poker.yks.data.game.BeginGame
import com.poker.yks.data.game.EndGame
import com.poker.yks.data.game.Greeting
import com.poker.yks.data.game.Move
import com.poker.yks.data.game.Ping
import com.poker.yks.data.game.PlayerInfoDTO
import com.poker.yks.data.game.PokerGame
import com.poker.yks.data.game.UpdateTable
import com.poker.yks.rest.room.WebSocketClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

class GameViewModel : ViewModel() {
    private lateinit var webSocketClient: WebSocketClient
    lateinit var pokerGame: PokerGame
    var isPokerGameInitialized = false
    private val _nextPlayer = MutableStateFlow<String>("")
    val nextPlayer = _nextPlayer.asStateFlow()
    var wasTriggered = false

    lateinit var playerInfo: PlayerInfoDTO
    var isConnected = false
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
                    xd = determineDataType<UpdateTable>(message) as UpdateTable
                    Timber.tag("updating").d("updating1")
                    val me = xd.playersInGame.find { it.nick == playerInfo.player_nick }!!
                    Timber.tag("updating2").d("updating1")
                    if (!::pokerGame.isInitialized) {
                        Timber.tag("updating200").d(xd.toString())
                        pokerGame = PokerGame(
                            xd,
                            me.nick,
                            me.tokens,
                            playerInfo.vip
                        )
                        Timber.tag("updating201").d(pokerGame.toString())
                        isPokerGameInitialized = true
                        try {
                            pokerGame.refreshTable(xd)
                            Timber.tag("updating202").d(pokerGame.toString())
                            _nextPlayer.update { pokerGame.playerInMove }
                            Timber.tag("updating203").d(pokerGame.toString())
                        } catch (e: Exception) {
                            Timber.tag("updating222").d(pokerGame.toString())
                        }
                    } else {
                        Timber.tag("updating3").d("updating1")
                        pokerGame.refreshTable(xd)
                        Timber.tag("updating31").d(_nextPlayer.value.toString())
                        _nextPlayer.update { pokerGame.playerInMove }
                        Timber.tag("updating32").d(_nextPlayer.value.toString())
                    }
                    Timber.tag("updating4").d("updating1")
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

                "Ping" -> {
                    xd = determineDataType<Ping>(message)
                    isConnected = true
                }

                else -> {
                    xd = Greeting(message)
                }

            }

            Log.d("socketCheck89", xd.toString())
//            Timber.tag("socketCheck2").i(receivedDataType.toString())


        }

    }

    fun isMyTurn(): Boolean {
        return pokerGame.isMyTurn()
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
        return moshi.adapter(Greeting::class.java)
    }

    private fun toJson(obj: Any): String =
        moshi.adapter(obj.javaClass).toJson(obj)


    fun createWebSocketConnection(url: String) {
        if (!wasTriggered) {
            webSocketClient = WebSocketClient.getInstance()
            webSocketClient.setSocketUrl(url)
            webSocketClient.setListener(socketListener)
            webSocketClient.connect()
            GlobalScope.launch {
                delay(250)
                Log.d("playerinfo", playerInfo.toString())
                val json = toJson(playerInfo)
                Log.d("playerinfojson", json)
                sendMessage(playerInfo)

            }
        }


    }

    fun sendMessage(message: Any) {

        webSocketClient.sendMessage(toJson(message))
    }

    fun exitGame() {
        webSocketClient.disconnect()
    }

    fun move(move: Move): Boolean {
        if (pokerGame.isMoveValid(move)) {
            sendMessage(move)
            return true
        }

        return false
    }

}

