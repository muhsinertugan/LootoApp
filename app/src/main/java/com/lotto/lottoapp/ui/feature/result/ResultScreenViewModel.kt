package com.lotto.lottoapp.ui.feature.result

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.tickets.TicketApi
import com.lotto.lottoapp.model.response.tickets.SingleTicketResponse
import com.lotto.lottoapp.model.response.tickets.Tickets
import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val ticketApi: TicketApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {

    init {
        getUserTickets()
    }

    private var _userTicketsState = MutableStateFlow(
        ResultScreenContract.UserTicketsList(
            ticketsResponse = UserTicketsResponse(
                data = Tickets(
                    tickets = listOf()
                ),
                message = "",
                success = false
            )
        )
    )

    var userTicketsState = _userTicketsState.asStateFlow()

    private fun updateUserTicketsState(newState: ResultScreenContract.UserTicketsList) {
        viewModelScope.launch(Dispatchers.Main) {
            _userTicketsState.value = newState
        }
    }


    private var _userSingleTicketSearch = MutableStateFlow(
        ResultScreenContract.UserSingleTicketSearch(
            search = ""
        )
    )

    var userSingleTicketSearch = _userSingleTicketSearch.asStateFlow()

    fun updateUserSingleTicketSearch(newState: ResultScreenContract.UserSingleTicketSearch) {
        viewModelScope.launch(Dispatchers.Main) {
            _userSingleTicketSearch.value = newState
        }
    }


    private var _singleTicket = MutableStateFlow(
        ResultScreenContract.SingleTicket(
            ticket = SingleTicketResponse(
                currency = "",
                game = "",
                guessedNumbers = 0,
                isWinner = false,
                prize = 0,
                success = false,
                ticketCode = ""
            )
        )
    )

    var singleTicket = _singleTicket.asStateFlow()


    private fun updateSingleTicketsState(newState: ResultScreenContract.SingleTicket) {
        viewModelScope.launch(Dispatchers.Main) {
            _singleTicket.value = newState
        }
    }

    fun getSingleTicketResult(ticketNumber: State<ResultScreenContract.UserSingleTicketSearch>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response =
                    ticketApi.getTicketResult(userToken, ticketNumber = ticketNumber.value.search)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val ticketsResponse = response.body()
                        if (ticketsResponse != null) {
                            if (ticketsResponse.success) {
                                val newState = ResultScreenContract.SingleTicket(
                                    ticket = SingleTicketResponse(
                                        currency = ticketsResponse.currency,
                                        game = ticketsResponse.game,
                                        guessedNumbers = ticketsResponse.guessedNumbers,
                                        isWinner = ticketsResponse.isWinner,
                                        prize = ticketsResponse.prize,
                                        success = ticketsResponse.success,
                                        ticketCode = ticketsResponse.ticketCode
                                    )
                                )
                                updateSingleTicketsState(newState)
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                //TODO: handle error
            }

        }
    }

    private fun getUserTickets() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = ticketApi.getUserTickets(userToken)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val ticketsResponse = response.body()
                        if (ticketsResponse != null) {
                            if (ticketsResponse.success) {
                                val newState = ResultScreenContract.UserTicketsList(
                                    ticketsResponse = UserTicketsResponse(
                                        data = ticketsResponse.data,
                                        message = ticketsResponse.message,
                                        success = ticketsResponse.success
                                    )
                                )
                                updateUserTicketsState(newState)

                            }
                        }
                    }

                }
            } catch (e: Exception) {
                //TODO: handle error
            }

        }
    }
}