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

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    private val _player = MutableStateFlow<LoginResponse>(LoginResponse(0, "", 0, 0))
    val player = _player.asStateFlow()
    fun loginToGame(loginCredential: LoginRequest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remote = repository.postLoginCredentials(loginCredential)
                if (remote.isSuccessful) {
                    val data = remote.body()
                    if (data != null) {
                        _player.update { data }
                    }
                }
            }
        }
    }
}