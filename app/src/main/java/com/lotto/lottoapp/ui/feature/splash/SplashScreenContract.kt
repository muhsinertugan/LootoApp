package com.lotto.lottoapp.ui.feature.splash

import com.lotto.lottoapp.model.response.general.CityResponseItem
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashScreenContract {
    data class CityState(
        val cities: List<CityResponseItem>,
        val isLoading: Boolean,
    ) : StateFlow<CityState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<CityState>
            get() = _state.replayCache

        override suspend fun collect(collector: FlowCollector<CityState>): Nothing {
            _state.collect(collector)
        }

        override val value: CityState
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