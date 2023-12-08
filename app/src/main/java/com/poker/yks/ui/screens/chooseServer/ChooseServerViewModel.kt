package com.poker.yks.ui.screens.chooseServer

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.data.serverStatus.ServerStatus
//import com.poker.yks.repository.RoomRepository
import com.poker.yks.repository.ServerStatusRepository
import com.poker.yks.rest.room.RoomClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class ChooseServerViewModel : ViewModel() {

    private val serverStatusRepository = ServerStatusRepository()
//    private val roomRepository = RoomRepository()

    private val _server = MutableStateFlow<String>("")
    val server = _server.asStateFlow()

    private val _dummyServer = MutableStateFlow<String>("")
    val dummyServer = _dummyServer.asStateFlow()

    private val _serversStatus = MutableStateFlow<List<ServerStatus>>(emptyList())
    val serverStatus = _serversStatus.asStateFlow()

    private val _dummyServerList = MutableStateFlow<List<ServerStatus>>(emptyList())
    val dummyServerList = _dummyServerList.asStateFlow()
    fun getServersStatus() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remoteServersStatus = serverStatusRepository.getServersStatus()

                if (remoteServersStatus.isSuccessful) {
                    val serverStatusList = remoteServersStatus.body()
                    if (serverStatusList != null) {
                        _serversStatus.update { serverStatusList }
                    }
                }
            }
        }
    }

    fun chooseServer(server: ServerStatus,loginResponse: LoginResponse,context:Context){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                if (server.occupation>3){
                    Toast.makeText(context,"Server is full!",Toast.LENGTH_SHORT).show()
                    return@withContext
                }
//                roomRepository.joinServer(server.ip, loginResponse)
//                _server.update { server.ip }
                _dummyServer.update { server.ip }
            }
        }
    }

    fun getDummyServerList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val list = mutableListOf<ServerStatus>()
                val random = Random(System.currentTimeMillis())
                list.apply {
                    add(ServerStatus("1.1.1.1",3))
                    add(ServerStatus("1.1.1.2",random.nextInt()%8))
                    add(ServerStatus("1.1.1.3",3))
                    add(ServerStatus("1.1.1.4",0))
                }
                _dummyServerList.update { list }
            }
        }
    }

}