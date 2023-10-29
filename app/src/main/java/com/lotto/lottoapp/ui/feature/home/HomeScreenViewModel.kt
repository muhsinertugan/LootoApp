package com.lotto.lottoapp.ui.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.draws.RecentDrawsApi
import com.lotto.lottoapp.model.data.games.GamesListApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gamesListApi: GamesListApi,
    private val recentDrawsApi: RecentDrawsApi
) :
    ViewModel() {
    init {
       // viewModelScope.launch { initHomeScreen() }
    }

    var gamesState by mutableStateOf(
        HomeScreenContract.GamesListState(
            games = listOf(), success = false
        )
    )

    var recentDrawsState by mutableStateOf(
        HomeScreenContract.RecentDrawsState(recentDraws = listOf(), count = 0, success = false)
    )

    private suspend fun getGames() {

        val games = gamesListApi.getGamesList()
        gamesState =
            gamesState.copy(games = games.body()!!.gameList, success = games.body()!!.success)

    }

    private suspend fun getRecentDraws() {

        val recentDraws = recentDrawsApi.getRecentDraws()
        recentDrawsState =
            recentDrawsState.copy(
                recentDraws = recentDraws.body()!!.draws,
                count = recentDraws.body()!!.count,
                success = recentDraws.body()!!.success
            )

    }

    private suspend fun initHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            getGames()
            getRecentDraws()
        }
    }

}