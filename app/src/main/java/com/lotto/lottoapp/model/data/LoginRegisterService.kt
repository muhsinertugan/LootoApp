package com.lotto.lottoapp.model.data

import retrofit2.http.POST

interface LoginRegisterService {
    @POST("login/email")
    suspend fun postLogin(mailAddress: String): Any

    @POST("login/email/otp")
    suspend fun postLoginOtp():Any
    @POST("register/email")
    suspend fun postRegister():Any

    @POST("register/email/otp")
    suspend fun postRegisterOtp():Any

}