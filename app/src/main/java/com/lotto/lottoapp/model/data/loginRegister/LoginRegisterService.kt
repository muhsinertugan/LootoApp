package com.lotto.lottoapp.model.data.loginRegister

import com.lotto.lottoapp.di.ENDPOINTS
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
    @POST(ENDPOINTS.AUTH_LOGIN_URL)
    suspend fun postLogin(@Body login: LoginRequest): Response<LoginResponseItem>

    @POST(ENDPOINTS.OTP_LOGIN_URL)
    suspend fun postLoginOtp(@Body loginOtp: LoginOtpRequest): Response<LoginOtpResponseItem>

    @POST(ENDPOINTS.AUTH_REGISTER_URL)
    suspend fun postRegister(@Body register: RegisterRequest): Response<RegisterResponseItem>

    @POST(ENDPOINTS.OTP_REGISTER_URL)
    suspend fun postRegisterOtp(@Body registerOtp: RegisterOtpRequest): Response<RegisterOtpResponseItem>
}