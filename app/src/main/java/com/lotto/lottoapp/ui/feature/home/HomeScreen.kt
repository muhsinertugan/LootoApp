package com.lotto.lottoapp.ui.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.feature.home.components.FutureDraws
import com.lotto.lottoapp.ui.feature.home.components.RecentDraws


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavHostController,
) {




    val gamesState = viewModel.gamesState.collectAsState().value
    val recentDrawsState = viewModel.recentDrawsState.collectAsState().value


    val gamesPagerState = rememberPagerState(pageCount = {
        gamesState.games.size
    })
    val recentDrawsPagerState = rememberPagerState(pageCount = {
        recentDrawsState.count
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


        HorizontalPager(
            state = gamesPagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 12.dp,
            pageSize = PageSize.Fixed(360.dp)
        ) { page ->
            FutureDraws(gameState = gamesState.games[page], navController= navController )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = Constants.RESULTS,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalPager(
                state = recentDrawsPagerState,
                contentPadding = PaddingValues(horizontal = 24.dp),
                pageSpacing = 12.dp,
                pageSize = PageSize.Fixed(360.dp)
            ) { page -> RecentDraws(recentDrawsState = recentDrawsState.recentDraws[page]) }
        }

    }
}