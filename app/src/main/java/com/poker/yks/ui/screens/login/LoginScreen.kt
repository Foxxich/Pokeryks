package com.poker.yks.ui.screens.login


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.poker.yks.R
import com.poker.yks.navigation.Screen
import com.poker.yks.ui.screens.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel()

    val player = loginViewModel.player.collectAsState()
    val status = loginViewModel.status.collectAsState()
    when (status.value) {
        //udane
        in 200..299 -> {
            Log.i("playerowisko loginscreen", player.value.toString())
            sharedViewModel.setPlayerInfo(player.value)
            Log.i("playerowisko", sharedViewModel.getPlayerInfo().toString())
            Log.i("playerowisko", sharedViewModel.toString())
            navController.navigate(Screen.MainScreen.route)
        }
        //początek
        0 -> {
        }

        2 -> {
            Log.i("playerowisko loginscreen", player.value.toString())
            sharedViewModel.setPlayerInfo(player.value)
            Log.i("playerowisko", sharedViewModel.getPlayerInfo().toString())
            Log.i("playerowisko", sharedViewModel.toString())
            navController.navigate(Screen.MainScreen.route)
        }
        //nieudane
        else -> {
//            Toast.makeText(context, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
            Log.i("playerowisko loginscreen", player.value.toString())
            sharedViewModel.setPlayerInfo(player.value)
            Log.i("playerowisko", sharedViewModel.getPlayerInfo().toString())
            Log.i("playerowisko", sharedViewModel.toString())
            navController.navigate(Screen.MainScreen.route)

        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 190.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    isFormValid = true
                },
                label = { Text("Username", color = Color.Black) },
                modifier = Modifier
                    .width(300.dp)
//                    .border(2.dp, Color.Black)
                    .let {
                        if (status.value != 0) {
                            val background = it.background(color = Color.Green)
                            background
                        } else {
                            it.background(
                                color = colorResource(id = R.color.light_grass),
                                shape = RoundedCornerShape(16.dp)
                            )
                        }
                    },
                isError = !isFormValid && username.isBlank(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black, // Change the border color when focused
                    unfocusedBorderColor = Color.Transparent // Change the border color when unfocused
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    isFormValid = true
                },

                label = { Text("Password", color = Color.Black) },
                modifier = Modifier
                    .width(300.dp)
                    .let {
                        if (status.value != 0) {
                            it.background(color = Color.Green, shape = RoundedCornerShape(16.dp))
                        } else {
                            it.background(
                                color = colorResource(id = R.color.light_grass),
                                shape = RoundedCornerShape(16.dp)
                            )
                        }
                    },

                isError = !isFormValid && password.isBlank(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black, // Change the border color when focused
                    unfocusedBorderColor = Color.Transparent // Change the border color when unfocused
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (username.isBlank() || password.isBlank()) {
                            isFormValid = false
                        } else {
                            // Handle login logic here
//

                            loginViewModel.loginToGame(username, password)
                        }
                    }
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (username.isBlank() || password.isBlank()) {
                        isFormValid = false
                    } else {
                        // Handle login logic here
                        loginViewModel.loginToGame(username, password)
                    }
                },
                enabled = username.isNotBlank() && password.isNotBlank(),
                modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(

                    colorResource(id = R.color.light_grass)
                ),
            ) {
                Text("Login", color = Color.Black)
            }
        }
    }

}



