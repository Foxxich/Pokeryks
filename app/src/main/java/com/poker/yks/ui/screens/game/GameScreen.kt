package com.poker.yks.ui.screens.game

//import androidx.compose.foundation.layout.RowScopeInstance.weight

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.poker.yks.R
import com.poker.yks.data.game.Move
import com.poker.yks.data.login.toPlayerInfoDTO
import com.poker.yks.ui.screens.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val gameViewModel: GameViewModel = viewModel()
    gameViewModel.playerInfo = sharedViewModel.getPlayerInfo().toPlayerInfoDTO()
    val context = LocalContext.current
    var shouldRunOnce by remember { mutableStateOf(true) }
    var riskAmount by remember {
        mutableIntStateOf(0)
    }
    if (shouldRunOnce) {
        gameViewModel.createWebSocketConnection("ws://10.0.2.2:${sharedViewModel.serverIp}/ws/socket-server")
        shouldRunOnce = false
    }
    val updating = gameViewModel.nextPlayer.collectAsState()
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

                    .weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = if (gameViewModel.isPokerGameInitialized) gameViewModel.pokerGame.myTokens.toString() else "",
                        color = Color.Yellow,
                        fontSize = 26.sp
                    )
                    Text(text = gameViewModel.playerInfo.player_nick)
                }
            }
            Box(
                modifier = Modifier
//                .fillMaxSize()

                    .weight(8f, true),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .width(500.dp)
                        .height(200.dp)
                        .background(Color(0xFF163020)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .width(500.dp)
                                .height(200.dp)
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically

//                        .background(Color(0xFF163020))
                        ) {

                            Image(
                                painter = if (
                                    gameViewModel.isPokerGameInitialized
                                ) {
                                    painterResource(
                                        gameViewModel.pokerGame.getTableCard(0)
                                    )
                                } else {
                                    painterResource(id = R.drawable.backcard)
                                },
                                contentDescription = null,
                                modifier = Modifier.weight(1f)
                            )

                            Image(
                                painter = if (
                                    gameViewModel.isPokerGameInitialized
                                ) {
                                    painterResource(
                                        gameViewModel.pokerGame.getTableCard(1)
                                    )
                                } else {
                                    painterResource(id = R.drawable.backcard)
                                },
                                contentDescription = null,
                                modifier = Modifier.weight(1f)
                            )

                            Image(
                                painter = if (
                                    gameViewModel.isPokerGameInitialized
                                ) {
                                    painterResource(
                                        gameViewModel.pokerGame.getTableCard(2)
                                    )
                                } else {
                                    painterResource(id = R.drawable.backcard)
                                },
                                contentDescription = null,
                                modifier = Modifier.weight(1f)
                            )

                            Image(
                                painter = if (
                                    gameViewModel.isPokerGameInitialized
                                ) {
                                    painterResource(
                                        gameViewModel.pokerGame.getTableCard(3)
                                    )
                                } else {
                                    painterResource(id = R.drawable.backcard)
                                },
                                contentDescription = null,
                                modifier = Modifier.weight(1f)
                            )

                            Image(
                                painter = if (
                                    gameViewModel.isPokerGameInitialized
                                ) {
                                    painterResource(
                                        gameViewModel.pokerGame.getTableCard(4)
                                    )
                                } else {
                                    painterResource(id = R.drawable.backcard)
                                },
                                contentDescription = null,
                                modifier = Modifier.weight(1f)
                            )
                            Column {
                                Text(text = updating.value, fontSize = 12.sp)
                                Text(
                                    text = if (gameViewModel.isPokerGameInitialized) {
                                        "Last Call: ${gameViewModel.pokerGame.lastCall}"
                                    } else "",
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = if (gameViewModel.isPokerGameInitialized) {
                                        "Total tokens: ${gameViewModel.pokerGame.tokensOnTable}"
                                    } else "",
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = if (gameViewModel.isPokerGameInitialized) {
                                        "Total wins: ${gameViewModel.pokerGame.winPercentage}%"
                                    } else "",
                                    fontSize = 12.sp
                                )
                            }

                        }


                    }

                }

                // Outer Boxes
                Box(
                    modifier = Modifier
                        .width(600.dp)
                        .fillMaxHeight()

                        .clip(RoundedCornerShape(10.dp))
                ) {
                    // Top Outer Box
                    Box(
                        modifier = Modifier
                            .width(200.dp)
//                            .height(120.dp)
                            .background(Color.Red)
                            .align(Alignment.TopCenter)

                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Column {
                            if (gameViewModel.isPokerGameInitialized && gameViewModel.pokerGame.playersInGame.size > 0) {
                                Text(
                                    text = if (gameViewModel.pokerGame.playersInGame.size > 0) {
                                        val nick = gameViewModel.pokerGame.playersInGame[0].nick
                                        val tokens =
                                            gameViewModel.pokerGame.playersInGame[0].tokens.toString()
                                        val text = "$nick  $tokens"
                                        text
                                    } else ""
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    (if (gameViewModel.pokerGame.playersInGame.size > 0) {


                                        gameViewModel.pokerGame.getPlayerCards(
                                            gameViewModel.pokerGame.playersInGame[0].nick,
                                            0
                                        )?.let {
                                            painterResource(
                                                it
                                            )
                                        }

                                    } else {
                                        null
                                    })?.let {
                                        Log.w("pomocy", it.toString())
                                        Image(

                                            painter = it,
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp, 75.dp)
                                        )
                                    }
                                    (if (gameViewModel.isPokerGameInitialized) {


                                        gameViewModel.pokerGame.getPlayerCards(
                                            gameViewModel.pokerGame.playersInGame[0].nick,
                                            1
                                        )?.let {
                                            painterResource(
                                                it
                                            )
                                        }

                                    } else {
                                        null
                                    })?.let {
                                        Image(
                                            //                                painter = painterResource(R.drawable.backcard),
                                            painter = it,
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp, 75.dp)
                                        )
                                    }
                                }
                            }

                        }
                    }
                    //nick money card card


                    // Bottom Outer Box
                    Box(
                        modifier = Modifier
                            .width(200.dp)
//                            .height(120.dp)
                            .background(Color.Blue)
                            .align(Alignment.BottomCenter)

                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Column {
                            if (gameViewModel.isPokerGameInitialized && gameViewModel.pokerGame.playersInGame.size > 1) {
                                Text(
                                    text = if (gameViewModel.isPokerGameInitialized) {
                                        val nick = gameViewModel.pokerGame.playersInGame[1].nick
                                        val tokens =
                                            gameViewModel.pokerGame.playersInGame[1].tokens.toString()
                                        val text = "$nick  $tokens"
                                        text
                                    } else ""
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    (if (gameViewModel.isPokerGameInitialized) {


                                        gameViewModel.pokerGame.getPlayerCards(
                                            gameViewModel.pokerGame.playersInGame[1].nick,
                                            0
                                        )?.let {
                                            painterResource(
                                                it
                                            )
                                        }

                                    } else {
                                        null
                                    })?.let {
                                        Image(
                                            //                                painter = painterResource(R.drawable.backcard),
                                            painter =

                                            it,


                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp, 75.dp)
                                        )
                                    }
                                    (if (gameViewModel.isPokerGameInitialized) {

                                        gameViewModel.pokerGame.getPlayerCards(
                                            gameViewModel.pokerGame.playersInGame[1].nick,
                                            1
                                        )?.let {
                                            painterResource(
                                                it
                                            )
                                        }

                                    } else {
                                        null
                                    })?.let {
                                        Image(
                                            //                                painter = painterResource(R.drawable.backcard),
                                            painter = it,
                                            contentDescription = null,
                                            modifier = Modifier.size(30.dp, 75.dp)
                                        )
                                    }
                                }


                            }
                        }
                        //nick money card card

                    }
                }

                // Left Outer Box
                Box(
                    modifier = Modifier
                        .width(120.dp)
//                        .height(120.dp)

                        .background(Color.Green)
                        .align(Alignment.CenterStart)

                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Column {
                        if (gameViewModel.isPokerGameInitialized && gameViewModel.pokerGame.playersInGame.size > 2) {
                            //nick money card card
                            Text(
                                text = if (gameViewModel.isPokerGameInitialized) {
                                    val nick = gameViewModel.pokerGame.playersInGame[2].nick
                                    val tokens =
                                        gameViewModel.pokerGame.playersInGame[2].tokens.toString()
                                    val text = "$nick $tokens"
                                    text
                                } else "",
                                fontSize = 10.sp
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                (if (gameViewModel.isPokerGameInitialized) {


                                    gameViewModel.pokerGame.getPlayerCards(
                                        gameViewModel.pokerGame.playersInGame[2].nick,
                                        0
                                    )?.let {
                                        painterResource(
                                            it
                                        )
                                    }

                                } else {
                                    null
                                })?.let {
                                    Image(
                                        //                                painter = painterResource(R.drawable.backcard),
                                        painter =

                                        it,


                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp, 75.dp)
                                    )
                                }
                                (if (gameViewModel.isPokerGameInitialized) {


                                    gameViewModel.pokerGame.getPlayerCards(
                                        gameViewModel.pokerGame.playersInGame[2].nick,
                                        1
                                    )?.let {
                                        painterResource(
                                            it
                                        )
                                    }

                                } else {
                                    null
                                })?.let {
                                    Image(
                                        painter = it,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp, 75.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // Right Outer Box
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .background(Color.Yellow)
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    //nick money card card
                    Column {
                        if (gameViewModel.isPokerGameInitialized && gameViewModel.pokerGame.playersInGame.size > 3) {
                            Text(
                                text = if (gameViewModel.isPokerGameInitialized) {
                                    val nick = gameViewModel.pokerGame.playersInGame[3].nick
                                    val tokens =
                                        gameViewModel.pokerGame.playersInGame[3].tokens.toString()
                                    val text = "$nick$tokens"
                                    text
                                } else ""
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                (if (gameViewModel.isPokerGameInitialized) {


                                    gameViewModel.pokerGame.getPlayerCards(
                                        gameViewModel.pokerGame.playersInGame[3].nick,
                                        0
                                    )?.let {
                                        painterResource(
                                            it
                                        )
                                    }
                                } else {
                                    null
                                })?.let {
                                    Image(
                                        painter = it,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp, 75.dp)
                                    )
                                }
                                (if (gameViewModel.isPokerGameInitialized) {


                                    gameViewModel.pokerGame.getPlayerCards(
                                        gameViewModel.pokerGame.playersInGame[3].nick,
                                        1
                                    )?.let {
                                        painterResource(
                                            it
                                        )
                                    }

                                } else {
                                    null
                                })?.let {
                                    Image(
                                        painter = it,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp, 75.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()

                    .weight(2f, false),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            gameViewModel.exitGame()
                            navController.popBackStack()
                        },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "Go to Menu")
                    }
                    Button(
                        onClick = {
                            if (!gameViewModel.isMyTurn()) {
                                Toasting("Not Your Turn", context)
                                return@Button
                            }
                            val move = Move(
                                moveType = "Fold",
                                amount = 0,
                                nick = gameViewModel.playerInfo.player_nick
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
                                moveType = "Call",
                                amount = 0,
                                nick = gameViewModel.playerInfo.player_nick
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
                                amount = riskAmount,
                                nick = gameViewModel.playerInfo.player_nick
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
                    Button(
                        onClick = {
                            if (riskAmount == 0) return@Button
                            riskAmount -= 50
                        },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "-50", color = Color.Blue)
                    }
                    Text(
                        text = riskAmount.toString(),
                        style = TextStyle(background = Color(0xFF6D4C41))
                    )
                    Button(
                        onClick = { riskAmount += 50 },
                        modifier = Modifier.padding(16.dp), // Dark wood color for the table
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D4C41))
                    ) {
                        Text(text = "+50")
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