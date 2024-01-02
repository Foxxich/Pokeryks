package com.poker.yks.ui.screens.shop

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.shop.ShopRequest
import com.poker.yks.repository.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ShopViewModel : ViewModel() {

    private val repository = ShopRepository()

    fun processFakePayment(context: Context, email: String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        repository.postShopCredentials(
                            ShopRequest(email = email, vip = "1", endpoint = "")
                        )
                    } catch (e: Exception) {
                        Timber.tag("LoginViewModel").e(e, "Error logging in")
                    }
                }
            }
            Toast.makeText(context, "Purchase successful!", Toast.LENGTH_SHORT).show()
        }
    }
}
