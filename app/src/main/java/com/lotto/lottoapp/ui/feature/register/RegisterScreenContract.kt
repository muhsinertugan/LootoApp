package com.lotto.lottoapp.ui.feature.register

import com.lotto.lottoapp.model.response.register.RegisterData
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterScreenContract {


    data class UserState(
        val data: RegisterData?,
        val message: String,
        val success: Boolean,
    ) : StateFlow<UserState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<UserState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<UserState>): Nothing {
            _state.collect(collector)
        }

        override val value: UserState
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