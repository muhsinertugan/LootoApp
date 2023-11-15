package com.lotto.lottoapp.ui.feature.game

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto.lottoapp.ui.feature.game.components.ticketColumn.TicketColumn
import com.lotto.lottoapp.ui.theme.CustomGrayV2
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography

@Composable
fun GameScreen(

    viewModel: GameScreenViewModel = hiltViewModel()
) {

    val game = viewModel.gameState.collectAsState()
    val columns = viewModel.columns.collectAsState()

    Log.d("columns", columns.value.toString())

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.FixedSize(32.dp),
            verticalItemSpacing = 12.dp,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp),
            content = {
                items(game.value.game.maximumNumber) {
                    Row(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(CustomPurple)
                            .clickable { viewModel.selectNumber(it + 1) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = (it + 1).toString(),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )

                    }
                }
            })


        Column(
            modifier = Modifier
                .background(CustomGrayV2)
                .verticalScroll(rememberScrollState())
        ) {
            columns.value.columns.map { column ->
                val readyButtonColors = if(!column.column.selectedNumbers.contains(null) ) CustomPurple else CustomGrayV2
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 20.dp, bottom = 10.dp)
                ) {

                        TicketColumn(column)

                    Box(
                        modifier = Modifier
                            .rotate(270f)
                            .padding(top = 20.dp)
                            .background(readyButtonColors)
                            .clickable { if (!column.column.selectedNumbers.contains(null)) viewModel.addColumnToTicket() }

                    ) {
                        Text(text = "Ready", color = Color.White)
                    }

                }

            }

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Submit",
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {

                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .padding(vertical = 12.dp, horizontal = 24.dp)

                )
            }
        }


    }

}

