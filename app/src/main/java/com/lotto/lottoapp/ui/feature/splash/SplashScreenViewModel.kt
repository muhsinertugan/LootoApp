package com.lotto.lottoapp.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.general.GeneralApi
import com.lotto.lottoapp.model.response.general.SerializableCityState
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val cityApi: GeneralApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCities()
        }
    }

    private var _cityState = MutableStateFlow(
        SplashScreenContract.CityState(cities = listOf(), isLoading = true)
    )

    val cityState = _cityState.value

    private suspend fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = cityApi.getCities()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val citiesResponse = response.body()
                        if (citiesResponse != null) {
                            if (citiesResponse.success) {
                                val newState = SplashScreenContract.CityState(
                                    cities = citiesResponse.data,
                                    isLoading = false
                                )

                                _cityState.value = newState
                                sharedPreferencesUtil.saveData(
                                    "cities",
                                    SerializableCityState(
                                        cities = citiesResponse.data,
                                        isLoading = false
                                    )
                                )
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                //TODO: handle error
            }

        }
    }

}