package com.lotto.lottoapp.ui.feature.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.games.GameApi
import com.lotto.lottoapp.model.response.game.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val gameApi: GameApi,
    private val savedStateHandle: SavedStateHandle,
) :
    ViewModel() {

    init {
        viewModelScope.launch {
            getGames(gameId = savedStateHandle["gameId"]!!)
        }
    }

    private var _selectedState = MutableStateFlow(
        GameScreenContract.SelectedNumber(
            selectedNumber = arrayOfNulls<Int>(5)
        )
    )

    var selectedState = _selectedState.asStateFlow()



    private fun updateSelectedState(newState: GameScreenContract.SelectedNumber) {
        viewModelScope.launch(Dispatchers.Main) {
            _selectedState.value = newState
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
                status = ""
            )
        )
    )
    var gameState = _gameState.asStateFlow()

    private fun updateGameState(newState: GameScreenContract.GameState) {
        viewModelScope.launch(Dispatchers.Main) {
            _gameState.value = newState
        }
    }

    private suspend fun getGames(gameId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = gameApi.getGame(gameId)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val gameResponse = response.body()

                        if (gameResponse != null) {
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
                                    status = gameResponse.game.status
                                )
                            )
                            updateGameState(newState)
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }
    }


    fun selectNumber(selected: Int) {
       val myState =  _selectedState.value.selectedNumber + selected
        val newState = GameScreenContract.SelectedNumber(
            selectedNumber = myState
        )
        updateSelectedState(newState)
    }

}