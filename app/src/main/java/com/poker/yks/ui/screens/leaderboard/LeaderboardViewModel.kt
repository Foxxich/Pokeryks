package com.poker.yks.ui.screens.leaderboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poker.yks.data.LeaderboardUser
import com.poker.yks.repository.LeaderboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LeaderboardViewModel : ViewModel() {

    private val repository = LeaderboardRepository()

    private val _players = MutableStateFlow<List<LeaderboardUser>>(emptyList())
    val players = _players.asStateFlow()

    fun getLeadearboard() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                Log.i("herrrrre", "0")
                val remote = repository.getLeaderboardList()
                Log.i("herrrrre", "1")
                if (remote.isSuccessful) {
                    Log.i("herrrrre", "12")
                    val data = remote.body()
                    Log.i("herrrrre", "13")
                    if (data != null) {
                        _players.update { data }
                    }


                }
            }
        }
    }
}