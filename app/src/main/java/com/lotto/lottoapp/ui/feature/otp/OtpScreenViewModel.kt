package com.lotto.lottoapp.ui.feature.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.response.login.LoginOtpData
import com.lotto.lottoapp.model.response.register.RegisterOtpData
import com.lotto.lottoapp.model.response.register.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(
    private val loginRegisterService: LoginRegisterApi,
) : ViewModel() {


    private val _userRegisterOtpState = MutableStateFlow(
        OtpScreenContract.RegisterUserState(
            data = RegisterOtpData(
                token = "", user = User(
                    __v = 0,
                    _id = "",
                    activated = false,
                    balance = 0,
                    birthDate = "",
                    cityId = "",
                    createdAt = "",
                    email = "",
                    expireAt = "",
                    lastName = "",
                    name = "",
                    phoneNumber = "",
                    privacyPolicy = false
                )
            ), message = "", success = false
        )
    )

    private var userRegisterOtpState = _userRegisterOtpState.asStateFlow()

    private val _userLoginOtpState = MutableStateFlow(
        OtpScreenContract.LoginUserState(
            data = LoginOtpData(email = "", token = ""),
            message = "",
            success = false
        )
    )

    private var userLoginOtpState = _userLoginOtpState.asStateFlow()


    private fun updateState(newState: OtpScreenContract.LoginUserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userLoginOtpState.value = newState
        }
    }

    private fun updateState(newState: OtpScreenContract.RegisterUserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userRegisterOtpState.value = newState
        }
    }

    fun postRegisterOtp(registerOtpRequest: RegisterOtpRequest, navController: NavController) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    loginRegisterService.postRegisterOtp(registerOtpRequest = registerOtpRequest)
                }
                if (response.isSuccessful) {
                    val registerOtpResponse = response.body()
                    if (registerOtpResponse != null) {
                        //TODO: Handle success false cases for register and login UI and logic both.
                        val newState = OtpScreenContract.RegisterUserState(
                            data = registerOtpResponse.data,
                            message = registerOtpResponse.message,
                            success = registerOtpResponse.success
                        )
                        updateState(newState)
                        if (registerOtpResponse.success) {
                            navController.navigate("home_screen")
                        }
                    } else {
                        updateState(
                            OtpScreenContract.RegisterUserState(
                                message = "Null response body", success = false, data = null
                            )
                        )
                    }


                } else {
                    updateState(
                        OtpScreenContract.RegisterUserState(
                            message = "Unsuccessful response: ${response.code()}",
                            success = false,
                            data = null
                        )
                    )
                }
            } catch (e: Exception) {
                updateState(
                    OtpScreenContract.RegisterUserState(
                        message = "Error: ${e.message}", success = false, data = null
                    )
                )
            }
        }
    }

    fun postLoginOtp(loginOtpRequest: LoginOtpRequest, navController: NavController) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    loginRegisterService.postLoginOtp(loginOtpRequest = loginOtpRequest)
                }
                if (response.isSuccessful) {
                    val loginOtpResponse = response.body()
                    if (loginOtpResponse != null) {
                        //TODO: Handle success false cases for register and login UI and logic both.
                        val newState = OtpScreenContract.LoginUserState(
                            data = loginOtpResponse.data,
                            message = loginOtpResponse.message,
                            success = loginOtpResponse.success
                        )
                        updateState(newState)
                        if (loginOtpResponse.success) {
                            navController.navigate("home_screen")
                        }
                    } else {
                        updateState(
                            OtpScreenContract.LoginUserState(
                                message = "Null response body", success = false, data = null
                            )
                        )
                    }


                } else {
                    updateState(
                        OtpScreenContract.LoginUserState(
                            message = "Unsuccessful response: ${response.code()}",
                            success = false,
                            data = null
                        )
                    )
                }
            } catch (e: Exception) {
                updateState(
                    OtpScreenContract.LoginUserState(
                        message = "Error: ${e.message}", success = false, data = null
                    )
                )
            }
        }
    }


}