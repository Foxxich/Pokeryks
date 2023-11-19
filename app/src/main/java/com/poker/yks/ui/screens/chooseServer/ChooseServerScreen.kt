package com.poker.yks.ui.screens.chooseServer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.data.serverStatus.ServerStatus
import com.poker.yks.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseServerScreen(navController: NavController) {

    val chooseServerViewModel: ChooseServerViewModel = viewModel()

    val serverList = chooseServerViewModel.serverStatus.collectAsState()
    val dummyServerList = chooseServerViewModel.dummyServerList.collectAsState()
    chooseServerViewModel.getDummyServerList()
//    chooseServerViewModel.getDummyServerList()
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .padding(16.dp)
//                    .background(Color.Red),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Row(


                    modifier = Modifier
//                        .background(Color.Blue)
                        .padding(16.dp),
//
                ) {
                    LazyColumn(
                        modifier = Modifier
//                        .fillMaxSize()
//                        .weight(1f)
                            .fillMaxWidth()
//                            .padding(32.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Log.i("listownia", dummyServerList.value.toString())
                        itemsIndexed(items = dummyServerList.value) { index, server ->
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                                    .let {
                                        if (server.occupation == 0){
                                            it.background(Color.Gray)
                                        }
                                        else{
                                            it.background(Color.Red)
                                        }
                                    }
                                    
                            )

                            {
                                Log.i("listownia", server.toString())
                                Text(text = "IP ${server.ip}", fontSize = 24.sp, modifier = Modifier.weight(1f))
                                Text(text = "Occupancy ${server.occupation}", fontSize = 24.sp, modifier = Modifier.weight(1f))
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(20.dp)
                                    .background(Color.Green)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
//                    .fillMaxWidth()
                        .background(Color.Green)
                )
                Button(
                    onClick = {
                        chooseServerViewModel.getDummyServerList()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "odśwież liste")

                }
                Button(onClick = { navController.navigate(Screen.MainScreen.route)}) {
                    Text(text = "cofnij do menu")
                    
                }


            }


        }
    )
}


@Composable
@Preview
fun Servers() {
    ChooseServerScreen(navController = rememberNavController())
}