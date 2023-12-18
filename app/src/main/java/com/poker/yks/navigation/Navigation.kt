package com.poker.yks.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poker.yks.ui.screens.SharedViewModel
import com.poker.yks.ui.screens.chooseServer.ChooseServerScreen
import com.poker.yks.ui.screens.game.GameScreen
import com.poker.yks.ui.screens.leaderboard.LeaderboardScreen
import com.poker.yks.ui.screens.login.LoginScreen
import com.poker.yks.ui.screens.main.MainScreen
import com.poker.yks.ui.screens.registration.RegistrationScreen
import com.poker.yks.ui.screens.shop.ShopScreen
import com.poker.yks.ui.screens.welcome.WelcomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(navController = navController, sharedViewModel)
        }
        composable(route = Screen.ShopScreen.route) {
            ShopScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) { entry ->
            LoginScreen(navController = navController, sharedViewModel)
        }
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(navController = navController)
        }
        composable(route = Screen.ChooseServerScreen.route) {
            ChooseServerScreen(navController = navController, sharedViewModel)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.LeaderboardScreen.route) {
            LeaderboardScreen(navController = navController)
        }
    }


}