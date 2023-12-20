package com.lotto.lottoapp.ui.feature.register

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.general.CityResponseItem
import com.lotto.lottoapp.model.response.general.SerializableCityState
import com.lotto.lottoapp.model.response.register.RegisterData
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.ui.feature.splash.SplashScreenContract
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import com.lotto.lottoapp.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val loginRegisterService: LoginRegisterApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val cities: SerializableCityState =
                sharedPreferencesUtil.loadData("cities")
            updateCityState(cities)
        }
    }


    private var _cityState = MutableStateFlow(
        SplashScreenContract.CityState(
            cities = listOf(
                CityResponseItem(
                    __v = 0,
                    _id = "",
                    code = 0,
                    latitude = "",
                    longitude = "",
                    name = "",
                    population = 0,
                    region = ""
                )
            ),
            isLoading = false
        )
    )

    val cityState = _cityState.asStateFlow()

    private fun updateCityState(newState: SerializableCityState) {
        viewModelScope.launch(Dispatchers.Main) {
            _cityState.value =
                SplashScreenContract.CityState(cities = newState.cities, isLoading = false)
        }
    }


    private var _errorState = MutableStateFlow(
        RegisterScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: RegisterScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }

    private var _userInput = MutableStateFlow(
        RegisterRequest(
            email = "",
            name = "",
            lastName = "",
            phoneNumber = "",
            birthDate = "",
            cityId = "",
        )
    )

    val userInput = _userInput.asStateFlow()
    fun updateField(fieldName: String, value: String) {

        val currentInput = _userInput.value
        val updatedInput = when (fieldName) {
            "email" -> currentInput.copy(email = value)
            "name" -> currentInput.copy(name = value)
            "lastName" -> currentInput.copy(lastName = value)
            "phoneNumber" -> currentInput.copy(phoneNumber = value)
            "cityId" -> currentInput.copy(cityId = value)
            else -> currentInput
        }
        _userInput.value = updatedInput
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(date: Long): String {
        val simpleDate = SimpleDateFormat("dd/M/yyyy")
        val formatted = Date(date)
        return simpleDate.format(formatted)
    }

    fun updateField(fieldName: String, value: Long) {

        val currentInput = _userInput.value
        val updatedInput = when (fieldName) {
            "birthDate" -> currentInput.copy(birthDate = getDateTime(value))
            else -> currentInput
        }
        _userInput.value = updatedInput
    }


    private var _userState = MutableStateFlow(
        RegisterScreenContract.UserState(
            data = RegisterData(
                createdAt = "", email = "", emailIdentifier = "", expiresAt = "", type = ""
            ),
            message = "",
            success = false,
        )
    )

    var userState = _userState.asStateFlow()


    private fun updateState(newState: RegisterScreenContract.UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userState.value = newState
        }

    }


    private fun postRegister(registerRequest: RegisterRequest, navController: NavHostController) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    handleResponse(loginRegisterService.postRegister(registerRequest = registerRequest))

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val registerResponse = response.data

                            val newState = RegisterScreenContract.UserState(
                                data = registerResponse.data,
                                message = registerResponse.message,
                                success = registerResponse.success
                            )
                            updateState(newState)

                            navController.navigate(
                                "${NavigationItems.Auth.Otp.route}/${registerRequest.email}/${registerRequest.name}/${registerRequest.lastName}/${registerRequest.phoneNumber}/${registerRequest.cityId}/${
                                    URLEncoder.encode(
                                        registerRequest.birthDate,
                                        "UTF-8"
                                    )
                                }"
                            )


                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                RegisterScreenContract.ErrorState(
                                    code = it.code, message = it.message, success = false
                                )

                            }?.let { updateErrorState(it) }

                        }
                    }

                }
            } catch (e: Exception) {
                updateState(
                    RegisterScreenContract.UserState(
                        message = "Error: ${e.message}", success = false, data = null
                    )
                )
            }
        }


    }

    fun onClick(navController: NavHostController) {
        postRegister(
            navController = navController, registerRequest = RegisterRequest(
                name = _userInput.value.name,
                lastName = _userInput.value.lastName,
                email = _userInput.value.email,
                phoneNumber = _userInput.value.phoneNumber,
                cityId = _userInput.value.cityId,
                birthDate = _userInput.value.birthDate
            )
        )
    }
}


