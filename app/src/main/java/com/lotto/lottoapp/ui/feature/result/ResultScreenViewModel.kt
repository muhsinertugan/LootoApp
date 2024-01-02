package com.lotto.lottoapp.ui.feature.result

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.tickets.TicketApi
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.tickets.SingleTicketResponse
import com.lotto.lottoapp.model.response.tickets.Tickets
import com.lotto.lottoapp.model.response.tickets.UserTicketsResponse
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import com.lotto.lottoapp.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val ticketApi: TicketApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {

    init {
        getUserTickets()
    }

    private var _errorState = MutableStateFlow(
        ResultScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: ResultScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

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
                val response = handleResponse(
                    ticketApi.getTicketResult(
                        userToken,
                        ticketNumber = ticketNumber.value.search
                    )
                )
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val ticketsResponse = response.data

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

                        is ApiResponse.Error -> {
                            response.response?.let {
                                ResultScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }?.let { updateErrorState(it) }
                        }
                    }
                }
            } catch (e: Exception) {
                ResultScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }

        }
    }

    private fun getUserTickets() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = handleResponse(ticketApi.getUserTickets(userToken))
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val ticketsResponse = response.data

                            val newState = ResultScreenContract.UserTicketsList(
                                ticketsResponse = UserTicketsResponse(
                                    data = ticketsResponse.data,
                                    message = ticketsResponse.message,
                                    success = ticketsResponse.success
                                )
                            )
                            updateUserTicketsState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                ResultScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }
                        }
                    }
                }
            } catch (e: Exception) {
                ResultScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }
}