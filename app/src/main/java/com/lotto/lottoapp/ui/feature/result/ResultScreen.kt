package com.lotto.lottoapp.ui.feature.result

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.theme.CustomGreen
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import com.lotto.lottoapp.utils.CurrencyFormatVisualTransformation
import com.lotto.lottoapp.utils.TimeUtil
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ResultScreen(
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {

    val scope = rememberCoroutineScope()

    val resultScreenViewModel: ResultScreenViewModel = hiltViewModel()
    val resultsTitle = arrayOf("Date", "Numbers", "Result")
    val singleTicketSearch = resultScreenViewModel.singleTicket.collectAsState()
    val userTicketState = resultScreenViewModel.userTicketsState.collectAsState()
    val searchedTicket = resultScreenViewModel.userSingleTicketSearch.collectAsState()

    val errorState = resultScreenViewModel.errorState.collectAsState()


    LaunchedEffect(errorState.value.id) {
        if (!errorState.value.success && errorState.value.code != 0) {
            keyboardController?.hide()
            snackbarHostState.showSnackbar(
                message = errorState.value.message,
                withDismissAction = true,
            )
        }
    }

    val userTickets = userTicketState.value.ticketsResponse.data.tickets.flatMap { ticket ->
        ticket.blocks.map { ticketNumbers ->

            ResultScreenContract.ScreenTicketsList(
                ticketNumbers.createdAt,
                ticketNumbers.numbers.joinToString("-"),
                ticketNumbers.isWinner,
            )
        }
    }.reversed()


    val time = TimeUtil()
    val currencyFormatVisualTransformation = CurrencyFormatVisualTransformation()

    val winnerColor = if (singleTicketSearch.value.ticket.isWinner) CustomGreen else Color.Red
    val winnerText =
        if (singleTicketSearch.value.ticket.isWinner) "${
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append(
                        currencyFormatVisualTransformation.filter(
                            AnnotatedString(
                                singleTicketSearch.value.ticket.prize.toString()
                            )
                        ).text
                    )
                }
            }
        } cr." else "You Lose!"

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (singleTicketSearch.value.ticket.success) {
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = singleTicketSearch.value.ticket.ticketCode,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 13.44.sp
                )
                Text(
                    text = "Hit ${singleTicketSearch.value.ticket.guessedNumbers} number",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "You earn",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = winnerText,
                    style = MaterialTheme.typography.displaySmall,
                    color = winnerColor,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Wanna check again?",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomInputField(
                fieldName = "",
                placeholderText = "XX-XX-XX-XX-XXXXX",
                text = searchedTicket.value.search,
                onFieldValueChange = {
                    resultScreenViewModel.updateUserSingleTicketSearch(
                        newState = ResultScreenContract.UserSingleTicketSearch(
                            search = it
                        )
                    )
                },
                isError = false
            )
            Spacer(modifier = Modifier.padding(vertical = 40.dp))
            Text(
                text = Buttons.SUBMIT_BTN,
                style = Typography.titleMedium.copy(color = Color.White),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = CustomPurple)
                    .padding(vertical = 16.dp, horizontal = 36.dp)
                    .clickable {
                        scope.launch {
                            if (searchedTicket.value.search !== "") {
                                resultScreenViewModel.getSingleTicketResult(
                                    ticketNumber = searchedTicket
                                )
                                keyboardController?.hide()
                            }
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        if (!singleTicketSearch.value.ticket.success) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .zIndex(-1f)
                        .background(color = Color.White)
                        .width(120.dp)
                        .height(1.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "Your Tickets",
                    style = Typography.titleLarge.copy(color = Color.White),
                    textAlign = TextAlign.Center,
                )
                Box(
                    modifier = Modifier
                        .zIndex(-1f)
                        .background(color = Color.White)
                        .width(120.dp)
                        .height(1.dp)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                resultsTitle.map { title ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight(600)
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,

                            )
                    }
                }

            }

            LazyColumn {
                items(userTickets) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (it.createdAt !== "") time.convertDateFormat(it.createdAt) else "",
                                style = MaterialTheme.typography.titleSmall,

                                color = Color.White,
                                textAlign = TextAlign.Center

                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.numbers,
                                style = MaterialTheme.typography.titleSmall,

                                color = Color.White,
                                textAlign = TextAlign.Center

                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(if (it.isWinner) Color.Green else Color.Red)
                                .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(

                                text = if (it.isWinner) "Win" else "Lost",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.White,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                }

            }
        }
    }
}


