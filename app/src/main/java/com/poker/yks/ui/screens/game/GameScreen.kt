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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//    val gameViewModel: GameViewModel = viewModel()
//    val socketEvent = gameViewModel.socketEvent.collectAsState(initial = GameViewModel.SocketEvent.ScriptEvent("hejo"))
//    val coroutineScope = rememberCoroutineScope()
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
                        .width(500.dp)
                        .height(200.dp)
                        .padding(16.dp)
                        .background(Color(0xFF163020)),
                )

                // Outer Boxes
                Box(
                    modifier = Modifier
                        .width(450.dp)
                        .fillMaxHeight()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    // Top Outer Box
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(40.dp)
                            .background(Color.Red)
                            .align(Alignment.TopCenter)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    // Bottom Outer Box
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(40.dp)
                            .background(Color.Blue)
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    // Left Outer Box
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(60.dp)

                            .background(Color.Green)
                            .align(Alignment.CenterStart)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    // Right Outer Box
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(60.dp)
                            .background(Color.Yellow)
                            .align(Alignment.CenterEnd)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
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
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                        ) {
                        Text(text = "Fold")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "Call 50")
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "Bet")
                    }
                }
            }
        }
    }
}
//private fun listenToSocketEvents() = lifecycleScope.launchWhenStarted {
//    viewModel.socketEvent.collect { event ->
//        when (event) {
//            is HomeViewModel.SocketEvent.ScriptEvent -> {
//                // Show the value using event.data
//            }
//            else -> Unit
//        }
//    }
//}
//private fun listenToConnectionEvents() = lifecycleScope.launchWhenStarted {
//    viewModel.connectionEvent.collect { event ->
//        when (event) {
//            is WebSocket.Event.OnConnectionOpened<*> -> {
//                Timber.d("OnConnectionOpened")
//            }
//            is WebSocket.Event.OnConnectionFailed -> {
//                event.throwable.printStackTrace()
//            }
//            is WebSocket.Event.OnConnectionClosed -> {
//                Timber.d("OnConnectionClosed")
//            }
//            else -> Unit
//        }
//    }
//}

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