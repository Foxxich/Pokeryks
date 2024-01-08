package com.poker.yks.ui.screens.chooseServer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.serverStatus.ServerStatusRequest
import com.poker.yks.repository.ServerStatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChooseServerViewModel : ViewModel() {

    private val serverStatusRepository = ServerStatusRepository()

    private val _server = MutableStateFlow("")
    val server = _server.asStateFlow()

    val _dummyServerList = MutableStateFlow<List<ServerStatusRequest>>(emptyList())
    val dummyServerList = _dummyServerList.asStateFlow()

    fun chooseServer(serverId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                serverStatusRepository.getServerSpecificConnection(serverId)
            }
        }
    }

    fun getDummyServerList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                serverStatusRepository.getServersStatus().body()?.let { statusRequests ->
                    _dummyServerList.update { statusRequests }
                }
            }
        }
    }

}