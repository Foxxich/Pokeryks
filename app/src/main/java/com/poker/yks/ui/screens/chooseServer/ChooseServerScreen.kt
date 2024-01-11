package com.poker.yks.ui.screens.chooseServer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.R
import com.poker.yks.navigation.Screen
import com.poker.yks.ui.screens.SharedViewModel

@Composable
fun ChooseServerScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val chooseServerViewModel: ChooseServerViewModel = viewModel()
    val dummyServers = chooseServerViewModel.dummyServers.collectAsState()
    chooseServerViewModel.getDummyServerList()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )


        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(600.dp)
                    .padding(top = 30.dp)
                    .background(color = colorResource(id = R.color.light_tree)),
                verticalArrangement = Arrangement.Top,

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Name", color = Color.Red, fontSize = 24.sp)
                    Text(text = "Occupancy", color = Color.Red, fontSize = 24.sp)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    itemsIndexed(items = dummyServers.value.values.toList()) { _, server ->
                        Row(modifier = Modifier
                            .padding(5.dp)
                            .border(width = 2.dp, color = Color.Black)
                            .fillMaxWidth()
                            .height(40.dp)
                            .clickable {
                                //TODO: manage in appropriate way
                            }
                            .let {
                                if (server.occupation == 0) {
                                    it.background(Color.Gray)
                                } else {
                                    it.background(Color.Red)
                                }
                            },
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        )

                        {
                            Text(
                                text = "IP ${server.ip}",
                                fontSize = 24.sp,
                            )
                            Text(
                                text = "Occupancy ${server.occupation}",
                                fontSize = 24.sp,
                            )
                        }
                    }
                }

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        chooseServerViewModel.getDummyServerList()
                    }, modifier = Modifier.width(300.dp)
                ) {
                    Text(text = "Update list")

                }
                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route) },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(text = "Return to Menu")
                }
            }
        }
    }
}

@Composable
@Preview
fun Servers() {
    ChooseServerScreen(navController = rememberNavController(), sharedViewModel = SharedViewModel())
}