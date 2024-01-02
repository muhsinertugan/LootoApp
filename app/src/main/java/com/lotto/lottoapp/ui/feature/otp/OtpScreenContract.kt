package com.lotto.lottoapp.ui.feature.otp

import com.lotto.lottoapp.model.response.login.LoginOtpData
import com.lotto.lottoapp.model.response.register.RegisterOtpData
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OtpScreenContract {

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

    data class ResendState(
        val isResend: Boolean,
        val isRunning: Boolean,
    ) : StateFlow<ResendState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<ResendState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<ResendState>): Nothing {
            _state.collect(collector)
        }

        override val value: ResendState
            get() = _state.value

    }


    data class LoginUserState(
        val data: LoginOtpData?,
        val message: String,
        val success: Boolean,
    ) : StateFlow<LoginUserState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<LoginUserState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<LoginUserState>): Nothing {
            _state.collect(collector)
        }

        override val value: LoginUserState
            get() = _state.value
    }

    data class RegisterUserState(
        val data: RegisterOtpData?,
        val message: String,
        val success: Boolean,
    ) : StateFlow<RegisterUserState> {
        private val _state = MutableStateFlow(this)
        override val replayCache: List<RegisterUserState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<RegisterUserState>): Nothing {
            _state.collect(collector)
        }

        override val value: RegisterUserState
            get() = _state.value
    }

}