package com.poker.yks.rest.room

import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

interface StockApi {
    @Receive
    fun observeEvents(): Flow<WebSocket.Event>

    @Send
    fun sendBroadCast(baseModel: String): Boolean

    @Receive
    fun observeScriptInfo(): Flow<String>
}