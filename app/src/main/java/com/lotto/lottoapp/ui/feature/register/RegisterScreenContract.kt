package com.lotto.lottoapp.ui.feature.register

import com.lotto.lottoapp.model.CityItem

class RegisterScreenContract {
    data class State(
        val cities: List<CityItem>? = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}