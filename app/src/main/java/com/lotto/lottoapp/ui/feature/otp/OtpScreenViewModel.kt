package com.lotto.lottoapp.ui.feature.otp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterApi
import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.response.login.LoginOtpData
import com.lotto.lottoapp.model.response.register.RegisterOtpData
import com.lotto.lottoapp.model.response.register.User
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(
    private val loginRegisterService: LoginRegisterApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
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
                val response = withContext(Dispatchers.IO) {
                    loginRegisterService.postLoginOtp(loginOtpRequest = loginOtpRequest)
                }
                if (response.isSuccessful) {
                    val loginOtpResponse = response.body()
                    if (loginOtpResponse != null) {


                        sharedPreferencesUtil.saveData(
                            key = "userToken",
                            data = loginOtpResponse.data.token
                        )

                        //TODO: Handle success false cases for register and login UI and logic both.
                        val newState = OtpScreenContract.LoginUserState(
                            data = loginOtpResponse.data,
                            message = loginOtpResponse.message,
                            success = loginOtpResponse.success
                        )
                        updateState(newState)
                        if (loginOtpResponse.success) {
                            withContext(Dispatchers.Main) {
                                navController.navigate(NavigationItems.App.route) {
                                    popUpTo(NavigationItems.Auth.route) {
                                        inclusive = true
                                    }
                                }
                            }


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
                Log.d("error", e.message.toString())
                updateState(
                    OtpScreenContract.LoginUserState(
                        message = "Error: ${e.message}", success = false, data = null
                    )
                )
            }
        }
    }

}