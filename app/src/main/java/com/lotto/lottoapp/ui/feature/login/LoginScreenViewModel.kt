package com.lotto.lottoapp.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.LoginRegisterApi
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.response.login.LoginData
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
            code = "",
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

    var userState = _userState.asStateFlow()

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
                val response = loginRegisterService.postLogin(loginRequest = loginRequest)

                withContext(Dispatchers.Main){
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {

                            if (loginResponse.success) {
                                val newState = LoginScreenContract.UserState(
                                    data = loginResponse.data,
                                    message = loginResponse.message,
                                    success = loginResponse.success
                                )
                                updateState(newState)

                                navController.navigate("otp_screen/${loginRequest.email}")
                            } else{
                                val newState = LoginScreenContract.ErrorState(
                                    code = loginResponse.code,
                                    message = loginResponse.message,
                                    success = loginResponse.success
                                )
                                updateErrorState(newState)
                                    when(loginResponse.code){
                                        "2023" -> navController.navigate("register_screen")
                                    }

                            }
                        } else {
                            updateState(
                                LoginScreenContract.UserState(
                                    message = "Null response body", success = false, data = null
                                )
                            )
                        }
                    } else {
                        updateState(
                            LoginScreenContract.UserState(
                                message = "Unsuccessful response: ${response.code()}",
                                success = false,
                                data = null
                            )
                        )
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