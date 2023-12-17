package com.poker.yks.ui.screens.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.poker.yks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadeboardScreen(navController: NavController) {

    val leaderboardViewModel: LeaderboardViewModel = viewModel()

    val players = leaderboardViewModel.players.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(400.dp)
                .background(
                    color = colorResource(id = R.color.light_tree)
                ),
            contentAlignment = Alignment.TopCenter
        ) {
//            LazyColumn(modifier = Modifier.fillMaxWidth()){
//                itemsIndexed(items = players.value){index , player ->
//                    Row(
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .border(width = 2.dp, color = Color.Black)
//                            .fillMaxWidth()
//                            .height(40.dp),
//                        horizontalArrangement = Arrangement.SpaceAround,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "hello man $player")
//                    }
//                    
//                }
//            }
            Text(text = players.value.toString())
            Button(
                onClick = { leaderboardViewModel.getLeadearboard() },
            ) {
                Text(text = "refresh")
            }
        }
    }
}