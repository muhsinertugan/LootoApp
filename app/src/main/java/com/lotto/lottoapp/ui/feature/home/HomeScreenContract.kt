package com.lotto.lottoapp.ui.feature.home

import com.lotto.lottoapp.model.response.home.Draw
import com.lotto.lottoapp.model.response.home.Game
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenContract {

    data class GamesListState(
        val games: List<Game> = listOf(),
        val success: Boolean
    ) : StateFlow<GamesListState>{
        private val _state = MutableStateFlow(this)
        override val replayCache: List<GamesListState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<GamesListState>): Nothing {
            _state.collect(collector)
        }

        override val value: GamesListState
            get() = _state.value

    }

    data class RecentDrawsState(
        val recentDraws: List<Draw> = listOf(),
        val count: Int,
        val success: Boolean
    ): StateFlow<RecentDrawsState>{
        private val _state = MutableStateFlow(this)
        override val replayCache: List<RecentDrawsState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<RecentDrawsState>): Nothing {
            _state.collect(collector)
        }

        override val value: RecentDrawsState
            get() = _state.value

    }

}