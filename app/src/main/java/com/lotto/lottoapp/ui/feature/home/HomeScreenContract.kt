package com.lotto.lottoapp.ui.feature.home

import com.lotto.lottoapp.model.response.draw.Draw
import com.lotto.lottoapp.model.response.game.Game
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenContract {

    data class GamesListState(
        val games: List<Game> = listOf(),
        val success: Boolean,
    ) : StateFlow<GamesListState> {
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
        val success: Boolean,
    ) : StateFlow<RecentDrawsState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<RecentDrawsState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<RecentDrawsState>): Nothing {
            _state.collect(collector)
        }

        override val value: RecentDrawsState
            get() = _state.value

    }

    data class ErrorState(
        val code: Int,
        val message: String,
        val success: Boolean,
        val id: String,
    ) : StateFlow<ErrorState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<ErrorState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ErrorState>): Nothing {
            _state.collect(collector)
        }

        override val value: ErrorState
            get() = _state.value

    }

}