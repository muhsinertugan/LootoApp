package com.lotto.lottoapp.ui.feature.profile


import com.lotto.lottoapp.model.response.profile.User
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileScreenContract {

    data class ProfileState(
        val user: User
    ) : StateFlow<ProfileState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<ProfileState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ProfileState>): Nothing {
            _state.collect(collector)
        }

        override val value: ProfileState
            get() = _state.value

    }


    data class BalanceState(
        val amount: Int
    ) : StateFlow<BalanceState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<BalanceState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<BalanceState>): Nothing {
            _state.collect(collector)
        }

        override val value: BalanceState
            get() = _state.value

    }

    data class SelectableAmountState(
        val title: String,
        val isSelected: Boolean,
        val amount: Int
    ): StateFlow<SelectableAmountState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<SelectableAmountState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<SelectableAmountState>): Nothing {
            _state.collect(collector)
        }

        override val value: SelectableAmountState
            get() = _state.value

    }




}