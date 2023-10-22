package com.poker.yks.rest

import com.poker.yks.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataFetcher {
    fun fetchUsers() {
        val call = ApiClient.apiService.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    // Handle the list of users
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Handle the error
            }
        })
    }
}