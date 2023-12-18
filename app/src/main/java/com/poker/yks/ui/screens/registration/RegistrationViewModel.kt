package com.poker.yks.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.registration.RegistrationRequest
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

    private val _status = MutableStateFlow(0)
    val status = _status.asStateFlow()

    fun createAccount(password: String, userName: String, email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val reg = repository.postRegistrationCredentials(
                    RegistrationRequest(
                        login = userName,
                        password = password,
                        nick = userName,
                        email = email,
                        endpoint = ""
                    )
                )
                Timber.tag("REGISTRATION_TAG").i(reg.toString())
                Timber.tag("REGISTRATION_TAG").i(reg.isSuccessful.toString())
                _status.update { 1 }
            }
        }
    }
}