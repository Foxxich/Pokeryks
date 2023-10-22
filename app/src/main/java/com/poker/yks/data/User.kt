package com.poker.yks.data

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val coins: Double,
    val wins: Int,
    val loses: Int,
    val isVIP: Boolean
)