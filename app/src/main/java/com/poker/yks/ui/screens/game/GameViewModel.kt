package com.poker.yks.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.rest.room.StockApi
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val stockApi: StockApi,
) : ViewModel() {
    sealed class SocketEvent {
        data class ScriptEvent(val data: String) : SocketEvent()
    }

    private val _socketEvent = MutableSharedFlow<SocketEvent>()
    val socketEvent = _socketEvent.asSharedFlow()
    private val connectionEventChannel = Channel<WebSocket.Event>()
    val connectionEvent = connectionEventChannel.receiveAsFlow().flowOn(Dispatchers.IO)

    init {
        observeEvents()
        fetchSocketResponse()
    }

    fun observeEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            stockApi.observeEvents().collect { event ->
                connectionEventChannel.send(event)
                Timber.e("event message $event")
            }
        }
    }

    fun fetchSocketResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            stockApi.observeScriptInfo().collect { data ->
                _socketEvent.emit(SocketEvent.ScriptEvent(data))
            }
        }
    }

    fun sendBaseModel(data: String) {
        viewModelScope.launch(Dispatchers.IO) {
            stockApi.sendBroadCast(data)
        }
    }
}
