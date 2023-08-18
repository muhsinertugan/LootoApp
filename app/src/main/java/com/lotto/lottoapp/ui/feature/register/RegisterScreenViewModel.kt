package com.lotto.lottoapp.ui.feature.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.GeneralRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(private val remoteSource: GeneralRemoteSource) :
    ViewModel() {

    var state by mutableStateOf(
        RegisterScreenContract.State(
            cities = listOf(),
            isLoading = true
        )
    )

    private var effects = Channel<RegisterScreenContract.Effect>(UNLIMITED)

    init {
        // viewModelScope.launch { getCities() }
    }

    private suspend fun getCities()= viewModelScope.launch {

        viewModelScope.launch {
            val cities = remoteSource.getCities()
            state = state.copy(cities = cities, isLoading = false)
            effects.send(RegisterScreenContract.Effect.DataWasLoaded)
        }
    }
}