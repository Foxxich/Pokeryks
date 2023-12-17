package com.poker.yks.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.R
import com.poker.yks.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.light_tree))
                        .width(100.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "20 000", fontSize = 20.sp)
                }
                Box(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.light_tree))
                        .width(150.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Vip Not Active", color = Color.Red, fontSize = 20.sp)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 38.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nick", fontSize = 30.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(Screen.ChooseServerScreen.route) },
                    modifier = Modifier.width(200.dp), colors = ButtonDefaults.buttonColors(

                        colorResource(id = R.color.light_grass)
                    )
                ) {
                    Text(text = "Play Game", color = colorResource(id = R.color.black))
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.ShopScreen.route)
                    },
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(

                        colorResource(id = R.color.light_grass)
                    )
                ) {
                    Text(text = "Shop", color = colorResource(id = R.color.black))
                }
                Button(
                    onClick = { navController.navigate(Screen.LeaderboardScreen.route) },
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(

                        colorResource(id = R.color.light_grass)
                    )
                ) {
                    Text(text = "Leaderboard", color = colorResource(id = R.color.black))
                }


            }


        }
    }

}

@Composable
@Preview(device = Devices.AUTOMOTIVE_1024p)
fun Czek() {
    MainScreen(navController = rememberNavController())
}