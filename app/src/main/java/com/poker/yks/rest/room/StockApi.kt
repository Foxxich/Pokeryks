package com.poker.yks.rest.room

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface StockApi {
    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendBroadCast(baseModel: String): Boolean

    @Receive
    fun observeScriptInfo(): Flow<String>
}