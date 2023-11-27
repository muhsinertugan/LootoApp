package com.lotto.lottoapp.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lotto.lottoapp.model.data.draws.RecentDrawsApi
import com.lotto.lottoapp.model.data.games.GamesListApi
import com.lotto.lottoapp.navigation.Paths
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gamesListApi: GamesListApi,
    private val recentDrawsApi: RecentDrawsApi,
    sharedPreferencesUtil: SharedPreferencesUtil
) :
    ViewModel() {
    init {
        val userToken = sharedPreferencesUtil.loadData(key = "userToken")

        viewModelScope.launch(Dispatchers.IO) { initHomeScreen() }
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
                val response = gamesListApi.getGamesList()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val gamesListResponse = response.body()

                        if (gamesListResponse != null) {
                            val newState = HomeScreenContract.GamesListState(
                                games = gamesListResponse.gameList,
                                success = gamesListResponse.success
                            )
                            updateGamesListState(newState)
                        }
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    private suspend fun getRecentDraws() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = recentDrawsApi.getRecentDraws()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val recentDrawsResponse = response.body()

                        if (recentDrawsResponse != null) {
                            val newState = HomeScreenContract.RecentDrawsState(
                                recentDraws = recentDrawsResponse.draws,
                                count = recentDrawsResponse.count,
                                success = recentDrawsResponse.success
                            )

                            updateRecentDrawsState(newState)
                        }
                    }
                }
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun initHomeScreen() {
        getGames()
        getRecentDraws()
    }

    fun navigateToGame(navController: NavController, gameId: String){
        navController.navigate("${Paths.GAME_SCREEN}/${gameId}")
    }


}