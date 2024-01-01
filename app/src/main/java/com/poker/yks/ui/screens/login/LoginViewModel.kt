package com.poker.yks.ui.screens.login

import android.util.Log
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

//    private val _player = MutableStateFlow(LoginResponse("", 0, 0))
//    val player = _player.asStateFlow()

    private val _status = MutableStateFlow(false)
    val status = _status.asStateFlow()

    fun loginToGame(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val loginPost = repository.postLoginCredentials(
                    LoginRequest(
                        email = email,
                        password = password,
                        endpoint = ""
                    )
                )
                _status.update { loginPost.isSuccessful }
            }
        }
    }
}