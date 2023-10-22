package com.poker.yks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poker.yks.screens.ExtraScreen
import com.poker.yks.screens.LoginScreen
import com.poker.yks.screens.RegistrationScreen
import com.poker.yks.screens.StartGameScreen
import com.poker.yks.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "start") {
            composable("start") { StartScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("registration") { RegistrationScreen(navController) }
            composable("start_game") { StartGameScreen(navController) }
            composable("extra") { ExtraScreen(navController) }
        }
    }
}