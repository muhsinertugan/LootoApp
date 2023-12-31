package com.lotto.lottoapp.ui.feature.game

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto.lottoapp.core.components.CustomAlertDialog
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.feature.game.components.TicketColumn
import com.lotto.lottoapp.ui.theme.CustomGray
import com.lotto.lottoapp.ui.theme.CustomGrayV2
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameScreen(
    viewModel: GameScreenViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    val scope = rememberCoroutineScope()
    val game by viewModel.gameState.collectAsState()
    val columns by viewModel.columns.collectAsState()
    val selectedNumbers by viewModel.selectedNumbersState.collectAsState()
    val errorState = viewModel.errorState.collectAsState()
    val alertDialogState = viewModel.alertDialogState.collectAsState()


    LaunchedEffect(errorState.value.id) {
        if (!errorState.value.success && errorState.value.code != 0) {
            keyboardController?.hide()
            snackbarHostState.showSnackbar(
                message = errorState.value.message,
                withDismissAction = true,
            )
        }
    }

    when {

        alertDialogState.value.open -> {
            CustomAlertDialog(
                onDismissRequest = {
                    scope.launch {
                        viewModel.updateAlertDialogState(false)
                    }
                },
                onConfirmation = {
                    scope.launch {
                        viewModel.updateAlertDialogState(false)
                    }
                },
                dialogTitle = "Congregations!\nYou have successfully purchased a ticket.",
                dialogText = "You can check your results from the Results tab.",
            )
        }
    }



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
                    val selectedNumber =
                        if (!selectedNumbers.selectedNumbers.contains(it + 1)) CustomPurple else CustomGray

                    Row(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(selectedNumber)
                            .clickable {
                                if (!selectedNumbers.selectedNumbers.contains(it + 1)) viewModel.selectNumber(
                                    it + 1
                                ) else viewModel.removeNumber(it + 1)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
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
            columns.columns.map { column ->
                val readyButtonColors =
                    if (!column.column.selectedNumbers.contains(null)) CustomPurple else CustomGrayV2
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
                            .clickable { if (!column.column.selectedNumbers.contains(null) && !column.isReady) viewModel.addColumnToTicket() }

                    ) {
                        if (!column.column.selectedNumbers.contains(null)) Text(
                            text = Buttons.READY_BTN,
                            color = Color.White
                        )
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
                    text = Buttons.SUBMIT_BTN,
                    style = Typography.titleMedium.copy(color = Color.White),
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                viewModel.buyTicket()

                            }
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = CustomPurple)
                        .padding(vertical = 12.dp, horizontal = 24.dp)

                )
            }
        }


    }

}

