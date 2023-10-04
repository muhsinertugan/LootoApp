package com.lotto.lottoapp.ui.feature.login

import com.lotto.lottoapp.model.response.login.LoginData
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginScreenContract {

    data class UserState(
        val data: LoginData?,
        val message: String,
        val success: Boolean
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

    sealed class Effect {
        object DataFetched : Effect()
    }
}

