package com.lotto.lottoapp.ui.feature.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.lotto.lottoapp.model.response.game.Game
import com.lotto.lottoapp.ui.feature.home.HomeScreenViewModel
import com.lotto.lottoapp.ui.utils.calculateRemainingTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FutureDraws(
    gameState: Game,
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .border(
                width = 0.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .width(360.dp)
            .height(210.dp)
            .clickable {
                viewModel.navigateToGame(
                    navController = navController,
                    gameId = gameState._id
                )
            }
    )
    {

        AsyncImage(
            model = gameState.image,
            contentDescription = gameState.description,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp), verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
                    .padding(12.dp)
            ) {
                Text(
                    text = "${gameState.prize} $",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Box {
                Text(
                    text = "remains  ${calculateRemainingTime(gameState.nextDrawDate)}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }

}