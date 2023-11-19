package com.poker.yks.ui.screens.login


import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.poker.yks.data.login.LoginRequest
import com.poker.yks.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

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
            navController.navigate(Screen.MainScreen.route)
        }
        //początek
        0 -> {
        }
        2 -> {
            navController.navigate(Screen.MainScreen.route)
        }
        //nieudane
        else -> {
            Toast.makeText(context, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isFormValid = true
                    },
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .let {
                            if (status.value != 0) {
                                it.background(color = Color.Red)
                            } else {
                                it
                            }
                        }
                            ,
                    isError = !isFormValid && username.isBlank(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isFormValid = true
                    },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .let {
                            if (status.value != 0) {
                                it.background(color = Color.Red)
                            } else {
                                it
                            }
                        }
            ,
                    isError = !isFormValid && password.isBlank(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Done
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
            }
        }
    )
}
