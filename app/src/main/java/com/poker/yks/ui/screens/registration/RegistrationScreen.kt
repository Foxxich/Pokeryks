package com.poker.yks.ui.screens.registration

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.poker.yks.data.Status
import com.poker.yks.navigation.Screen
import com.poker.yks.ui.screens.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val registrationViewModel: RegistrationViewModel = viewModel()
    registrationViewModel.status.collectAsState()

    val loginStatus by registrationViewModel.status.collectAsState()

    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            Status.SUCCESS -> {
                sharedViewModel.setPlayerInfo(registrationViewModel.player.value)
                navController.navigate(Screen.MainScreen.route)
            }

            Status.FAIL -> {
                if (email.isNotBlank() && password.isNotBlank()) {
                    Toast.makeText(context, "Incorrect data!", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {

            }
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
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isFormValid && username.isBlank(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isFormValid = true
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isFormValid && email.isBlank(),
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
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isFormValid && password.isBlank(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isFormValid = true
                    },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = !isFormValid && confirmPassword.isBlank(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Done
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (username.isBlank()
                            || email.isBlank()
                            || password.isBlank()
                            || confirmPassword.isBlank()
                            || password != confirmPassword
                        ) {
                            isFormValid = false
                        } else {
                            registrationViewModel.createAccount(
                                userName = username,
                                email = email,
                                password = password
                            )
                        }
                    },
                    enabled = username.isNotBlank()
                            && email.isNotBlank()
                            && password.isNotBlank()
                            && confirmPassword.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    )
}
