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
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRegisterService {
    @POST("auth/login/email")
    suspend fun postLogin(@Body login: LoginRequest): Response<LoginResponseItem>
    @POST("auth/login/email/otp")
    suspend fun postLoginOtp(@Body loginOtp : LoginOtpRequest):Response<LoginOtpResponseItem>
    @POST("auth/register/email")
    suspend fun postRegister(@Body register: RegisterRequest ):Response<RegisterResponseItem>
    @POST("auth/register/email/otp")
    suspend fun postRegisterOtp(@Body registerOtp: RegisterOtpRequest):Response<RegisterOtpResponseItem>
}