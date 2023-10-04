package com.lotto.lottoapp.model.data

import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.model.response.login.LoginOtpResponseItem
import com.lotto.lottoapp.model.response.login.LoginResponseItem
import com.lotto.lottoapp.model.response.register.RegisterOtpResponseItem
import com.lotto.lottoapp.model.response.register.RegisterResponseItem
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRegisterApi @Inject constructor(private val service: LoginRegisterService){

    suspend fun postLogin(loginRequest: LoginRequest): Response<LoginResponseItem> = service.postLogin(login = loginRequest)

    suspend fun postLoginOtp(loginOtpRequest: LoginOtpRequest): Response<LoginOtpResponseItem> = service.postLoginOtp(loginOtp = loginOtpRequest)

    suspend fun postRegister(registerRequest: RegisterRequest): Response<RegisterResponseItem> = service.postRegister(register = registerRequest)

    suspend fun postRegisterOtp(registerOtpRequest: RegisterOtpRequest): Response<RegisterOtpResponseItem> = service.postRegisterOtp(registerOtp = registerOtpRequest)

}