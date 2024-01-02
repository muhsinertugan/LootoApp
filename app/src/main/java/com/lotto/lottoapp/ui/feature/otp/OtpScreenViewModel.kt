package com.lotto.lottoapp.ui.feature.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.login.LoginOtpData
import com.lotto.lottoapp.model.response.register.RegisterOtpData
import com.lotto.lottoapp.model.response.register.User
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import com.lotto.lottoapp.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(
    private val loginRegisterService: LoginRegisterApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {
    private var _errorState = MutableStateFlow(
        OtpScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )

    var errorState = _errorState.asStateFlow()

    private var _resendState = MutableStateFlow(
        OtpScreenContract.ResendState(
            isResend = false, isRunning = true

        )
    )

    var resendState = _resendState.asStateFlow()

    fun updateResendState() {
        viewModelScope.launch(Dispatchers.Main) {
            _resendState.value = OtpScreenContract.ResendState(isResend = false, isRunning = false)
        }

    }

    private fun updateErrorState(newState: OtpScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }

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


    private val _userLoginOtpState = MutableStateFlow(
        OtpScreenContract.LoginUserState(
            data = LoginOtpData(email = "", token = ""),
            message = "",
            success = false
        )
    )


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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    handleResponse(loginRegisterService.postRegisterOtp(registerOtpRequest = registerOtpRequest))

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val registerOtpResponse = response.data
                            sharedPreferencesUtil.saveData(
                                key = "userToken",
                                data = registerOtpResponse.data.token
                            )
                            val newState = OtpScreenContract.RegisterUserState(
                                data = registerOtpResponse.data,
                                message = registerOtpResponse.message,
                                success = registerOtpResponse.success
                            )
                            updateState(newState)

                            navController.navigate(NavigationItems.App.route) {
                                popUpTo(NavigationItems.Auth.route) {
                                    inclusive = true
                                }
                            }
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                OtpScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )
                            }?.let { updateErrorState(it) }

                        }
                    }

                }
            } catch (e: Exception) {
                OtpScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }


    fun postLoginOtp(loginOtpRequest: LoginOtpRequest, navController: NavController) {
        viewModelScope.launch {
            try {
                val response =
                    handleResponse(loginRegisterService.postLoginOtp(loginOtpRequest = loginOtpRequest))

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val loginOtpRequestResponse = response.data
                            sharedPreferencesUtil.saveData(
                                key = "userToken",
                                data = loginOtpRequestResponse.data.token
                            )
                            val newState = OtpScreenContract.LoginUserState(
                                data = loginOtpRequestResponse.data,
                                message = loginOtpRequestResponse.message,
                                success = loginOtpRequestResponse.success
                            )
                            updateState(newState)

                            navController.navigate(NavigationItems.App.route) {
                                popUpTo(NavigationItems.Auth.route) {
                                    inclusive = true
                                }
                            }
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                OtpScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )
                            }?.let { updateErrorState(it) }

                        }
                    }

                }

            } catch (e: Exception) {
                OtpScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }

    }

    fun resendOtp(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = handleResponse(
                    loginRegisterService.postLogin(loginRequest)
                )

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            _resendState.value = OtpScreenContract.ResendState(
                                isResend = true,
                                isRunning = true
                            )
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                // Handle error, update error state or show an error message
                                updateErrorState(
                                    OtpScreenContract.ErrorState(
                                        code = it.code,
                                        message = it.message,
                                        success = it.success,
                                        id = UUID.randomUUID().toString()
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                OtpScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }


        }
    }


    fun resendOtp(registerRequest: RegisterRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = handleResponse(
                    loginRegisterService.postRegister(registerRequest)
                )

                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            _resendState.value = OtpScreenContract.ResendState(
                                isResend = true,
                                isRunning = true
                            )
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                updateErrorState(
                                    OtpScreenContract.ErrorState(
                                        code = it.code,
                                        message = it.message,
                                        success = it.success,
                                        id = UUID.randomUUID().toString()
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                OtpScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }


        }
    }


}