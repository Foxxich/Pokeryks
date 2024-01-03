package com.poker.yks.ui.screens.shop

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.shop.TokensRequest
import com.poker.yks.data.shop.VipRequest
import com.poker.yks.repository.ShopRepository
import com.poker.yks.ui.screens.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ShopViewModel : ViewModel() {

    private val shopRepository = ShopRepository()
    fun processVipBuying(context: Context, sharedViewModel: SharedViewModel) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val response = shopRepository.postVipCredentials(
                            VipRequest(username = sharedViewModel.nick, vip = "1", endpoint = "")
                        )
                        response.body()?.let {
                            sharedViewModel.vip = it.vip
                        }
                    } catch (e: Exception) {
                        Timber.tag("LoginViewModel").e(e, "Error logging in")
                    }
                }
            }
            Toast.makeText(context, "Purchase successful!", Toast.LENGTH_SHORT).show()
        }
    }

    fun processTokensBuying(context: Context, sharedViewModel: SharedViewModel, tokens: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val response = shopRepository.postTokensCredentials(
                            TokensRequest(
                                username = sharedViewModel.nick,
                                tokens = tokens,
                                endpoint = ""
                            )
                        )
                        response.body()?.let {
                            sharedViewModel.money = it.money
                        }
                    } catch (e: Exception) {
                        Timber.tag("LoginViewModel").e(e, "Error logging in")
                    }
                }
            }
            Toast.makeText(context, "Purchase successful!", Toast.LENGTH_SHORT).show()
        }
    }
}
