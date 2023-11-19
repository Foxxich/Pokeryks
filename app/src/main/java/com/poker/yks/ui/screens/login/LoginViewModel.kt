package com.poker.yks.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    private val _player = MutableStateFlow<LoginResponse>(LoginResponse( "", 0, 0))
    val player = _player.asStateFlow()

    private val _status = MutableStateFlow(0)
    val status = _status.asStateFlow()

    private var endpoint = ""
    fun loginToGame(login: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                endpoint = generateRandomString(30)
//                val loginCredential = LoginRequest(login,password,endpoint)
//                val remote = repository.postLoginCredentials(loginCredential)
//                if (remote.isSuccessful) {
//                    val data = remote.body()
//                    if (data != null) {
//                        _player.update { data }
//                        _status.update { remote.code() }
//                    }
//                }
//                else {
//                    _status.update { remote.code() }
//                }
                _status.update { randomLogin() }
            }
        }
    }
}
private fun generateRandomString(length: Int): String {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') // You can customize the character pool as needed
    val random = Random(System.currentTimeMillis())

    return (1..length)
        .map { charPool[random.nextInt(0, charPool.size)] }
        .joinToString("")
}

private fun randomLogin(): Int{
    return Random.nextInt()%2 +1
}