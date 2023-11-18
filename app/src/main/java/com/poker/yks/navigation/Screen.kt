package com.poker.yks.navigation

sealed class Screen(val route: String) {
    object StartScreen : Screen("welcome_screen")
    object LoginScreen : Screen("login_screen")
    object RegistrationScreen: Screen("registration_screen")
    object ShopScreen : Screen("shop_screen")
    object ChooseServerScreen : Screen("choose_server_screen")
    object GameScreen : Screen("game_screen")
    object StartGameScreen : Screen("start_game_screen")
    object LeaderboardScreen : Screen("leaderboard_screen")
    object MainScreen : Screen("main_screen")
    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}