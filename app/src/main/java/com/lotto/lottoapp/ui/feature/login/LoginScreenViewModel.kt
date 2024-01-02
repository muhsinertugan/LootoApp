package com.lotto.lottoapp.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.response.login.LoginData
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.navigation.Paths
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val loginRegisterService: LoginRegisterApi) :
    ViewModel() {

    private var _errorState = MutableStateFlow(
        LoginScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: LoginScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }

    private var _userState = MutableStateFlow(
        LoginScreenContract.UserState(
            data = LoginData(email = ""),
            message = "",
            success = false,
        )
    )

    private fun updateState(newState: LoginScreenContract.UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userState.value = newState
        }

    }

    private var _userInput = MutableStateFlow(
        LoginRequest(
            email = "",
        )
    )

    val userInput = _userInput.asStateFlow()


    fun updateField(fieldName: String, value: String) {
        val currentInput = _userInput.value

        val updatedInput = when (fieldName) {
            "email" -> currentInput.copy(email = value)
            else -> currentInput
        }
        _userInput.value = updatedInput
    }


    private fun postLogin(loginRequest: LoginRequest, navController: NavHostController) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    handleResponse(loginRegisterService.postLogin(loginRequest = loginRequest))
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val loginResponse = response.data

                            val newState = LoginScreenContract.UserState(
                                data = loginResponse.data,
                                message = loginResponse.message,
                                success = loginResponse.success
                            )
                            updateState(newState)
                            navController.navigate("${NavigationItems.Auth.Otp.route}/${loginRequest.email}")
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                LoginScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }?.let { updateErrorState(it) }
                            when (response.response?.code) {
                                ErrorCodes.USER_NOT_FOUND.code -> {
                                    navController.navigate(NavigationItems.Auth.Register.route)
                                }

                            }
                        }
                    }
                }
            } catch (e: Exception) {
                updateState(
                    LoginScreenContract.UserState(
                        message = "Error: ${e.message}", success = false, data = null
                    )
                )
            }


        }
    }

    fun onClick(navController: NavHostController) {
        postLogin(
            navController = navController,
            loginRequest = LoginRequest(
                email = _userInput.value.email,
            )
        )
    }


}


