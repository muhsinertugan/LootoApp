package com.lotto.lottoapp.ui.feature.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.games.GameApi
import com.lotto.lottoapp.model.data.tickets.TicketApi
import com.lotto.lottoapp.model.request.BuyTicketRequest
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.game.Game
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
class GameScreenViewModel @Inject constructor(
    private val gameApi: GameApi,
    private val ticketApi: TicketApi,
    private val savedStateHandle: SavedStateHandle,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getGames(gameId = savedStateHandle["gameId"]!!)
        }
    }

    private var _errorState = MutableStateFlow(
        GameScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )


    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: GameScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }


    private var _alertDialogState =
        MutableStateFlow(GameScreenContract.AlertDialogState(open = false))

    var alertDialogState = _alertDialogState.asStateFlow()

    fun updateAlertDialogState(value: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            _alertDialogState.value = GameScreenContract.AlertDialogState(open = value)
        }

    }


    private var _gameState = MutableStateFlow(
        GameScreenContract.GameState(
            game = Game(
                __v = 0,
                _id = "",
                columnPrice = 0,
                createdAt = "",
                cronExpression = "",
                cronExpressionDescription = "",
                currency = "",
                description = "",
                extraNumbers = 0,
                gameCode = "",
                gameType = "",
                image = "",
                maximumNumber = 0,
                mininumNumber = 0,
                name = "",
                nextDrawDate = "",
                prize = 0,
                requriedNumbers = 0,
                status = "",
                lastDrawDate = ""
            )
        )
    )
    var gameState = _gameState.asStateFlow()

    private var _selectedNumbersState = MutableStateFlow(
        GameScreenContract.SelectedNumbers(
            selectedNumbers = emptyArray()
        )
    )

    var selectedNumbersState = _selectedNumbersState.asStateFlow()


    private var _column = MutableStateFlow(
        GameScreenContract.Column(column = selectedNumbersState.value, isReady = false)
    )

    var column = _column.asStateFlow()


    private var _columns = MutableStateFlow(
        GameScreenContract.Columns(columns = listOf(column.value))
    )

    var columns = _columns.asStateFlow()

    private fun updateSelectedNumbersState(newState: GameScreenContract.SelectedNumbers) {
        viewModelScope.launch(Dispatchers.Main) {
            _selectedNumbersState.value = newState
        }
    }

    private fun updateColumn(newState: GameScreenContract.SelectedNumbers, isReady: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            val newColumn = GameScreenContract.Column(
                column = newState, isReady = isReady
            )
            _column.value = newColumn
        }
    }

    private fun updateColumns(newState: GameScreenContract.Column, isReady: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            val nullColumn = GameScreenContract.Column(
                column = GameScreenContract.SelectedNumbers(
                    selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                ), isReady = false
            )

            val currentColumns = _columns.value.columns.toMutableList()
            val existingNullColumn = columns.value.columns.lastOrNull {
                it.column.selectedNumbers.contains(null)
            }
            currentColumns.set(index = currentColumns.lastIndex, element = newState)
            if (isReady && existingNullColumn == null) {
                currentColumns.add(nullColumn)
            }
            val newColumnsState = GameScreenContract.Columns(columns = currentColumns)
            _columns.value = newColumnsState
        }
    }


    fun selectNumber(selected: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val currentSelectedNumbersState = _selectedNumbersState.value
            val currentSelectedNumber = currentSelectedNumbersState.selectedNumbers.toMutableList()

            val nullIndex = currentSelectedNumber.indexOf(null)

            if (nullIndex != -1) {
                currentSelectedNumber[nullIndex] = selected
            } else {
                for (i in 0 until currentSelectedNumber.size) {
                    if (currentSelectedNumber[i] == null) {
                        currentSelectedNumber[i] = selected
                        break
                    }
                }
            }

            val newState = GameScreenContract.SelectedNumbers(
                selectedNumbers = currentSelectedNumber.toTypedArray()
            )
            updateSelectedNumbersState(newState)

            val updatedColumn = _column.value.copy(column = newState)
            updateColumn(newState, false)
            updateColumns(updatedColumn, false)
        }
    }


    fun removeNumber(selected: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val currentSelectedNumbersState = _selectedNumbersState.value
            val currentSelectedNumber = currentSelectedNumbersState.selectedNumbers.toMutableList()

            for (i in 0 until currentSelectedNumber.size) {
                if (currentSelectedNumber[i] == selected) {
                    currentSelectedNumber[i] = null
                    break
                }
            }

            val newState = GameScreenContract.SelectedNumbers(
                selectedNumbers = currentSelectedNumber.toTypedArray()
            )
            updateSelectedNumbersState(newState)

            val updatedColumn = _column.value.copy(column = newState)
            updateColumn(newState, false)
            updateColumns(updatedColumn, false)
        }
    }


    fun addColumnToTicket() {
        viewModelScope.launch(Dispatchers.Main) {
            val existingNullColumn = columns.value.columns.lastOrNull {
                it.column.selectedNumbers.contains(null)
            }
            if (existingNullColumn == null) {
                val updatedColumn = column.value.copy(isReady = true)
                updateColumn(column.value.column, true)
                updateColumns(newState = updatedColumn, isReady = true)

                updateSelectedNumbersState(
                    newState = GameScreenContract.SelectedNumbers(
                        selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                    )
                )

                updateColumn(
                    newState = GameScreenContract.SelectedNumbers(
                        selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                    ), isReady = false
                )
            }
        }
    }


    private fun updateGameState(newState: GameScreenContract.GameState) {
        viewModelScope.launch(Dispatchers.Main) {
            _gameState.value = newState
        }
    }

    private suspend fun getGames(gameId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = handleResponse(gameApi.getGame(gameId))

                withContext(Dispatchers.Main) {

                    when (response) {
                        is ApiResponse.Success -> {
                            val gameResponse = response.data
                            val newState = GameScreenContract.GameState(
                                game = Game(
                                    __v = gameResponse.game.__v,
                                    _id = gameResponse.game._id,
                                    columnPrice = gameResponse.game.columnPrice,
                                    createdAt = gameResponse.game.createdAt,
                                    cronExpression = gameResponse.game.cronExpression,
                                    cronExpressionDescription = gameResponse.game.cronExpressionDescription,
                                    currency = gameResponse.game.currency,
                                    description = gameResponse.game.description,
                                    extraNumbers = gameResponse.game.extraNumbers,
                                    gameCode = gameResponse.game.gameCode,
                                    gameType = gameResponse.game.gameType,
                                    image = gameResponse.game.image,
                                    maximumNumber = gameResponse.game.maximumNumber,
                                    mininumNumber = gameResponse.game.mininumNumber,
                                    name = gameResponse.game.name,
                                    nextDrawDate = gameResponse.game.nextDrawDate,
                                    prize = gameResponse.game.prize,
                                    requriedNumbers = gameResponse.game.requriedNumbers,
                                    status = gameResponse.game.status,
                                    lastDrawDate = gameResponse.game.lastDrawDate
                                )
                            )
                            updateGameState(newState)

                            viewModelScope.launch(Dispatchers.Main) {
                                _selectedNumbersState.value = GameScreenContract.SelectedNumbers(
                                    selectedNumbers = arrayOfNulls(gameResponse.game.requriedNumbers)
                                )
                                updateColumn(
                                    GameScreenContract.SelectedNumbers(
                                        selectedNumbers = arrayOfNulls(gameResponse.game.requriedNumbers)
                                    ), false
                                )
                                updateColumns(
                                    GameScreenContract.Column(
                                        GameScreenContract.SelectedNumbers(
                                            selectedNumbers = arrayOfNulls(gameResponse.game.requriedNumbers)
                                        ), false
                                    ), false
                                )
                            }
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                GameScreenContract.ErrorState(
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
                GameScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }


    suspend fun buyTicket() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val columnNumbers = columns.value.columns.map {
                    it.column.selectedNumbers
                }

                val userToken = sharedPreferencesUtil.loadData<String>("userToken")

                val response = handleResponse(
                    ticketApi.postBuyTicket(
                        ticket = BuyTicketRequest(
                            game = gameState.value.game._id,
                            numbers = columnNumbers
                        ), token = userToken
                    )
                )

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            _selectedNumbersState.value = GameScreenContract.SelectedNumbers(
                                selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                            )
                            _column.value = GameScreenContract.Column(
                                GameScreenContract.SelectedNumbers(
                                    selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                                ), false
                            )
                            _columns.value = GameScreenContract.Columns(
                                listOf(
                                    GameScreenContract.Column(
                                        GameScreenContract.SelectedNumbers(
                                            selectedNumbers = arrayOfNulls(gameState.value.game.requriedNumbers)
                                        ), false
                                    )
                                )
                            )

                            updateAlertDialogState(true)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                GameScreenContract.ErrorState(
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
                GameScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }

        }


    }

}