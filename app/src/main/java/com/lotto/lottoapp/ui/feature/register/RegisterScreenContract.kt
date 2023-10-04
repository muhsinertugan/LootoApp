package com.lotto.lottoapp.ui.feature.register

import com.lotto.lottoapp.model.CityItem
import com.lotto.lottoapp.model.response.register.RegisterData
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterScreenContract {


    data class CityState(
        val cities: List<CityItem>? = listOf(),
        val isLoading : Boolean = true
    )

    data class UserState(
        val data: RegisterData?,
        val message: String,
        val success: Boolean
    ) : StateFlow<UserState> {

        private val _state = MutableStateFlow(this)
        override val replayCache: List<UserState>
            get() = _state.replayCache
        override suspend fun collect(collector: FlowCollector<RegisterScreenContract.UserState>): Nothing {
            _state.collect(collector)
        }
        override val value: UserState
            get() = _state.value
    }

    sealed class Effect {
        object DataWasLoaded : Effect()
    }


}