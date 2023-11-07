package com.lotto.lottoapp.ui.feature.game

import com.lotto.lottoapp.model.response.game.Game
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameScreenContract {

    data class GameState(
        val game: Game,

    ) : StateFlow<GameState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<GameState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<GameState>): Nothing {
            _state.collect(collector)
        }

        override val value: GameState
            get() = _state.value

    }

    data class SelectedNumber(
        val selectedNumber: Array<Int?>,

        ) : StateFlow<SelectedNumber> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<SelectedNumber>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<SelectedNumber>): Nothing {
            _state.collect(collector)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SelectedNumber

            if (!selectedNumber.contentEquals(other.selectedNumber)) return false
            if (_state != other._state) return false

            return true
        }

        override fun hashCode(): Int {
            var result = selectedNumber.contentHashCode()
            result = 31 * result + _state.hashCode()
            return result
        }

        override val value: SelectedNumber
            get() = _state.value

    }
}