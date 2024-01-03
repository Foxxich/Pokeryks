package com.poker.yks.ui.screens.game

//import androidx.compose.foundation.layout.RowScopeInstance.weight
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.R
import com.poker.yks.data.game.Move
import com.poker.yks.ui.screens.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val gameViewModel: GameViewModel = viewModel()
    val context = LocalContext.current
//    gameViewModel.createWebSocketConnection("ws://10.0.2.2:8000/ws/socket-server")
    gameViewModel.createWebSocketConnection("ws://192.168.0.107:8000/ws/socket-server")
//    gameViewModel.createWebSocketConnection("wss://ws.postman-echo.com/raw")
//    gameViewModel.createWebSocketConnection("wss://demo.piesocket.com/v3/channel_123?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self")
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
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically

//                        .background(Color(0xFF163020))
                    ) {
                        Image(
                            painter = painterResource(R.drawable.backcard),
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )

                        Image(
                            painter = painterResource(R.drawable.backcard),
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )

                        Image(
                            painter = painterResource(R.drawable.backcard),
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )

                        Image(
                            painter = painterResource(R.drawable.backcard),
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )

                        Image(
                            painter = painterResource(R.drawable.backcard),
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

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
                        onClick = {
                            if (!gameViewModel.isMyTurn()) {
                                Toasting("Not Your Turn", context)
                                return@Button
                            }
                            val move = Move(
                                moveType = "fold",
                                amount = 0,
                                nick = "masterchlop"
                            )
                            val result = gameViewModel.move(move = move)
                            if (!result) {
                                Toasting("incorrect move", context)
                            }
                        },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "Fold")
                    }
                    Button(
                        onClick = {
                            if (!gameViewModel.isMyTurn()) {
                                Toasting("Not Your Turn", context)
                                return@Button
                            }
                            val move = Move(
                                moveType = "call",
                                amount = 50,
                                nick = "masterchlop"
                            )
                            val result = gameViewModel.move(move = move)
                            if (!result) {
                                Toasting("incorrect move", context)
                            }
                        },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "Call")
                    }
                    Button(
                        onClick = {
                            if (!gameViewModel.isMyTurn()) {
                                Toasting("Not Your Turn", context)
                                return@Button
                            }
                            val move = Move(
                                moveType = "Bet",
                                amount = 50,
                                nick = "masterchlop"
                            )
                            val result = gameViewModel.move(move = move)
                            if (!result) {
                                Toasting("incorrect move", context)
                            }
                        },
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

fun Toasting(string: String, context: Context) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
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