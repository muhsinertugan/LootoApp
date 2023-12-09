package com.lotto.lottoapp.ui.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.general.GeneralApi
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.model.response.register.RegisterData
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val cityApi: GeneralApi,
    private val loginRegisterService: LoginRegisterApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    init {
        viewModelScope.launch {
            getCities()
        }
    }

    private var _errorState = MutableStateFlow(
        RegisterScreenContract.ErrorState(
            code = "",
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


    private var _cityState = MutableStateFlow(
        RegisterScreenContract.CityState(cities = listOf(), isLoading = true)
    )

    val cityState = _cityState.asStateFlow()


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


    private suspend fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = cityApi.getCities()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val citiesResponse = response.body()
                        if (citiesResponse != null) {
                            if (citiesResponse.success) {
                                val newState = RegisterScreenContract.CityState(
                                    cities = citiesResponse.data,
                                    isLoading = false
                                )

                                _cityState.value = newState
                                sharedPreferencesUtil.saveData("cities", newState.cities.toString())
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                //TODO: handle error
            }

        }
    }

    private fun postRegister(registerRequest: RegisterRequest, navController: NavHostController) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = loginRegisterService.postRegister(registerRequest = registerRequest)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        if (registerResponse != null) {
                            //TODO: Handle success false cases for register and login UI and logic both.

                            if (registerResponse.success) {
                                val newState = RegisterScreenContract.UserState(
                                    data = registerResponse.data,
                                    message = registerResponse.message,
                                    success = registerResponse.success
                                )
                                updateState(newState)
                                navController.navigate("${NavigationItems.Auth.Otp.route}/${registerRequest.email}/${registerRequest.email}/${registerRequest.email}/${registerRequest.email}/${registerRequest.email}/${registerRequest.email}")
                            } else {
                                val newState = RegisterScreenContract.ErrorState(
                                    code = registerResponse.code,
                                    message = registerResponse.message,
                                    success = registerResponse.success
                                )
                                updateErrorState(newState)
                                TODO("Handle Error Cases")

                            }

                        } else {
                            updateState(
                                RegisterScreenContract.UserState(
                                    message = "Null response body", success = false, data = null
                                )
                            )
                        }

                    } else {
                        updateState(
                            RegisterScreenContract.UserState(
                                message = "Unsuccessful response: ${response.code()}",
                                success = false,
                                data = null
                            )
                        )
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


