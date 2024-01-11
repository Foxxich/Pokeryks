package com.poker.yks.ui.screens.chooseServer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.serverStatus.ServerStatusResponse
import com.poker.yks.repository.ServerStatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.immutableListOf

class ChooseServerViewModel : ViewModel() {

    private val serverStatusRepository = ServerStatusRepository()
    private val _dummyServers = MutableStateFlow<Map<String, ServerStatusResponse>>(emptyMap())
    val dummyServers = _dummyServers.asStateFlow()

    fun getDummyServerList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val tempMap = mutableMapOf<String, ServerStatusResponse>()

                SERVERS.forEach { serverUrl ->
                    val response = serverStatusRepository.getServersStatus(serverUrl).body()
                    response?.let { statusResponse ->
                        tempMap[serverUrl] = statusResponse
                    }
                }
                _dummyServers.value = tempMap.toMap()
            }
        }
    }


    companion object {
        var SERVERS = immutableListOf(
            "http://10.0.2.2:8002/"
        )
    }

}