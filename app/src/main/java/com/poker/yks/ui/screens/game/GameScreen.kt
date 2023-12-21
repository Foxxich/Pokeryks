package com.poker.yks.ui.screens.game

//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.R
import com.poker.yks.ui.screens.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val gameViewModel: GameViewModel = viewModel()
    gameViewModel.createWebSocketConnection("http://192.168.0.103:8002")
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "200000$", color = Color.Yellow, fontSize = 26.sp)
                    Text(text = "hej2")
                }
            }
            Box(
                modifier = Modifier
//                .fillMaxSize()
                    .padding(16.dp)
                    .weight(3f, true),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(150.dp)
                        .background(Color.Gray)
                )

                // Outer Boxes
                Box(
                    modifier = Modifier
                        .width(450.dp)
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    // Top Outer Box
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(40.dp)
                            .background(Color.Red)
                            .align(Alignment.TopCenter)
                    )

                    // Bottom Outer Box
                    Box(
                        modifier = Modifier
//                        .fillMaxWidth()
                            .width(60.dp)
                            .height(40.dp)
                            .background(Color.Blue)
                            .align(Alignment.BottomCenter)
                    )

                    // Left Outer Box
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(60.dp)

                            .background(Color.Green)
                            .align(Alignment.CenterStart)
                    )

                    // Right Outer Box
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(60.dp)
                            .background(Color.Yellow)
                            .align(Alignment.CenterEnd)
                    )
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxSize()

                    .weight(1f, false),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.width(400.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Fold")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Call 50")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Bet")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Table() {
    GameScreen(navController = rememberNavController(), sharedViewModel = SharedViewModel())
}

@Composable
fun BottomOptions() {
    Row {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Fold")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Call 50")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Bet")

        }
    }
}

@Preview
@Composable
//@Preview
fun Begging() {
    val configuration = LocalConfiguration.current
    Text(text = configuration.toString())
    BottomOptions()
}