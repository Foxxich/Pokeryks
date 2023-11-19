package com.poker.yks.ui.screens.chooseServer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.serverStatus.ServerStatus
import com.poker.yks.repository.ServerStatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class ChooseServerViewModel : ViewModel() {

    private val repository = ServerStatusRepository()

    private val _serversStatus = MutableStateFlow<List<ServerStatus>>(emptyList())
    val serverStatus = _serversStatus.asStateFlow()

    private val _dummyServer = MutableStateFlow<List<ServerStatus>>(emptyList())
    val dummyServerList = _dummyServer.asStateFlow()
    fun getServersStatus() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remoteServersStatus = repository.getServersStatus()

                if (remoteServersStatus.isSuccessful) {
                    val serverStatusList = remoteServersStatus.body()
                    if (serverStatusList != null) {
                        _serversStatus.update { serverStatusList }
                    }
                }
            }
        }
    }

    fun getDummyServerList(){
        Log.i("listownik0","0")
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val list = mutableListOf<ServerStatus>()
                val random = Random(System.currentTimeMillis())
                Log.i("listownik0","1"+"hej1 ")
                list.apply {
                    add(ServerStatus("1.1.1.1",3))
                    add(ServerStatus("1.1.1.2",random.nextInt()%8))
                    add(ServerStatus("1.1.1.3",3))
                    add(ServerStatus("1.1.1.4",0))
                }
                Log.i("listownik0","2"+"hej2 ")
                _dummyServer.update { list }
                Log.i("listownik",list.toString())
            }
        }
    }
}