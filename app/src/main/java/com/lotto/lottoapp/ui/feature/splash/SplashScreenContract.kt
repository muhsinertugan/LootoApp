package com.lotto.lottoapp.ui.feature.splash

import com.lotto.lottoapp.model.response.general.CityResponseItem
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashScreenContract {
    data class CityState(
        val cities: List<CityResponseItem>,
        val isLoading: Boolean
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

}