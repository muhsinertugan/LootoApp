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

    data class SelectedNumbers(
        val selectedNumbers: Array<Int?>,

        ) : StateFlow<SelectedNumbers> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<SelectedNumbers>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<SelectedNumbers>): Nothing {
            _state.collect(collector)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as SelectedNumbers

            if (!selectedNumbers.contentEquals(other.selectedNumbers)) return false
            return _state == other._state
        }

        override fun hashCode(): Int {
            var result = selectedNumbers.contentHashCode()
            result = 31 * result + _state.hashCode()
            return result
        }

        override val value: SelectedNumbers
            get() = _state.value

    }

    data class Column(
        val column: SelectedNumbers,
        val isReady: Boolean,

        ) : StateFlow<Column> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<Column>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<Column>): Nothing {
            _state.collect(collector)
        }

        override val value: Column
            get() = _state.value

    }

    data class Columns(
        val columns: List<Column>,

        ) : StateFlow<Columns> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<Columns>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<Columns>): Nothing {
            _state.collect(collector)
        }

        override val value: Columns
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

    data class AlertDialogState(
        val open: Boolean,
    ) : StateFlow<AlertDialogState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<AlertDialogState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<AlertDialogState>): Nothing {
            _state.collect(collector)
        }

        override val value: AlertDialogState
            get() = _state.value

    }

}