package com.poker.yks.ui.screens.leaderboard

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

    init {
        getLeadearboard() // Inicjalizacja danych przy tworzeniu ViewModel
    }

    fun getLeadearboard() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val remote = repository.getLeaderboardList()
                if (remote.isSuccessful) {
                    val data = remote.body()
                    if (data != null) {
                        _players.update { data }
                    }
                }
            }
        }
    }
}