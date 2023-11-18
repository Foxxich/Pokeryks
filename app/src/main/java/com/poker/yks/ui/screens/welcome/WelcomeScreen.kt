package com.poker.yks.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.poker.yks.R
import com.poker.yks.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeryks),
                    contentDescription = null,
                )
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.RegistrationScreen.route) }) {
                    Text("Go to Registration")
                }
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                    Text("Go to Login")
                }
            }
        }
    )
}

//@Composable
//@Preview
//fun Dupnik(){
//    StartScreen()
//}
