package com.poker.yks.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.Status
import com.poker.yks.data.registration.RegistrationRequest
import com.poker.yks.data.registration.RegistrationResponse
import com.poker.yks.repository.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RegistrationViewModel : ViewModel() {
    private val repository = RegistrationRepository()
    val player = MutableStateFlow(RegistrationResponse(0, "", 0, 0))

    private val _status = MutableStateFlow(Status.NOT_EXISTING)
    val status = _status.asStateFlow()

    fun createAccount(password: String, userName: String, email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.postRegistrationCredentials(
                        RegistrationRequest(
                            login = userName,
                            password = password,
                            userName = userName,
                            email = email,
                            endpoint = ""
                        )
                    )
                    if (response.isSuccessful && response.body() != null && response.body()!!.status == 1) {
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