package com.poker.yks.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.Status
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.data.login.LoginResponse
import com.poker.yks.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    val player = MutableStateFlow(LoginResponse("", 0, 0))

    private val _status = MutableStateFlow(Status.NOT_EXISTING)
    val status = _status.asStateFlow()

    fun loginToGame(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.postLoginCredentials(
                        LoginRequest(email = email, password = password, endpoint = "")
                    )
                    if (response.isSuccessful && response.body() != null) {
                        player.update { response.body()!! }
                        _status.update { Status.SUCCESS }
                    } else {
                        _status.update { Status.FAIL }
                    }
                } catch (e: Exception) {
                    Timber.tag("LoginViewModel").e(e, "Error logging in")
                    _status.update { Status.FAIL }
                }
            }
        }
    }
}
