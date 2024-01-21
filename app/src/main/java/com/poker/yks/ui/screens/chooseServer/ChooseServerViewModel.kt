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

class ChooseServerViewModel : ViewModel() {

    private val serverStatusRepository = ServerStatusRepository()
    private val _dummyServers = MutableStateFlow<List<ServerStatusResponse>>(emptyList())
    val dummyServers = _dummyServers.asStateFlow()

    init {
        getDummyServerList()
    }

    fun getDummyServerList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = serverStatusRepository.getServersStatus().body()
            response?.let {
                withContext(Dispatchers.Main) {
                    _dummyServers.value = it
                }
            }
        }
    }
}
