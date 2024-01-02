package com.poker.yks.ui.screens.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.poker.yks.R
import com.poker.yks.ui.screens.SharedViewModel

@Composable
fun ShopScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val shopViewModel: ShopViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pokeryks),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(600.dp)
                .background(Color(0xFF6D4C41), shape = RoundedCornerShape(10.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Shop",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Become a VIP or Buy Coins!",
                color = Color.White
            )

            Button(
                onClick = {
                    shopViewModel.processVipBuying(context, sharedViewModel.getPlayerInfo().username)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = "Buy VIP", color = Color.White)
            }
            val coinOptions = listOf(50, 100, 500)
            coinOptions.forEach { amount ->
                Button(
                    onClick = {
                        shopViewModel.processTokensBuying(context, sharedViewModel.getPlayerInfo().username,
                            amount.toString()
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(text = "Buy $amount Coins", color = Color.White)
                }
            }
        }
    }
}
