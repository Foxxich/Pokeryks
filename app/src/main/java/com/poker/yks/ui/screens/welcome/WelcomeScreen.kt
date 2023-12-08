package com.poker.yks.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.poker.yks.R
import com.poker.yks.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {

            Box(modifier = Modifier.fillMaxSize())
            {
                Image(
                    painter = painterResource(id = R.drawable.pokeryks),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
                Box(){

                }
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 160.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Button(
                        modifier = Modifier
                            .width(180.dp),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.light_grass)
                        ),
                        onClick = { navController.navigate(Screen.LoginScreen.route) }
                    ) {
                        Text("Go to Login",color = Color.Black)
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(
                        modifier = Modifier
                            .width(180.dp),
                            colors = ButtonDefaults.buttonColors(
                                colorResource(id = R.color.light_grass)
                            ),
                        onClick = { navController.navigate(Screen.RegistrationScreen.route) }) {
                        Text("Go to Registration",color = Color.Black)
                    }

                }

            }
        }



//@Composable
//@Preview
//fun Dupnik(){
//    StartScreen()
//}
