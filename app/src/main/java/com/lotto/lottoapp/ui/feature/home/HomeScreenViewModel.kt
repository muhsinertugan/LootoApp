package com.lotto.lottoapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lotto.lottoapp.model.data.draws.RecentDrawsApi
import com.lotto.lottoapp.model.data.games.GamesListApi
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.navigation.Paths
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
class HomeScreenViewModel @Inject constructor(
    private val gamesListApi: GamesListApi,
    private val recentDrawsApi: RecentDrawsApi,
) : ViewModel() {



    init {
        viewModelScope.launch(Dispatchers.IO) {
            initHomeScreen()

        }
    }

    private var _errorState = MutableStateFlow(
        HomeScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: HomeScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }


    private var _gamesState = MutableStateFlow(
        HomeScreenContract.GamesListState(
            games = listOf(), success = false
        )
    )

    var gamesState = _gamesState.asStateFlow()

    private var _recentDrawsState = MutableStateFlow(
        HomeScreenContract.RecentDrawsState(recentDraws = listOf(), count = 0, success = false)
    )

    var recentDrawsState = _recentDrawsState.asStateFlow()
    private fun updateGamesListState(newState: HomeScreenContract.GamesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            _gamesState.value = newState
        }
    }

    private fun updateRecentDrawsState(newState: HomeScreenContract.RecentDrawsState) {
        viewModelScope.launch(Dispatchers.Main) {
            _recentDrawsState.value = newState
        }

    }


    private suspend fun getGames() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = handleResponse(gamesListApi.getGamesList())

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val gamesListResponse = response.data
                            val newState = HomeScreenContract.GamesListState(
                                games = gamesListResponse.gameList,
                                success = gamesListResponse.success
                            )
                            updateGamesListState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                HomeScreenContract.ErrorState(
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
                HomeScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }

    private suspend fun getRecentDraws() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = handleResponse(recentDrawsApi.getRecentDraws())

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {

                            val recentDrawsResponse = response.data
                            val newState = HomeScreenContract.RecentDrawsState(
                                recentDraws = recentDrawsResponse.draws,
                                count = recentDrawsResponse.count,
                                success = recentDrawsResponse.success
                            )

                            updateRecentDrawsState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                HomeScreenContract.ErrorState(
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
                HomeScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }

    }


    private suspend fun initHomeScreen() {
        getGames()
        getRecentDraws()
    }

    fun navigateToGame(navController: NavController, gameId: String) {
        navController.navigate("${Paths.GAME_SCREEN}/${gameId}")
    }


}