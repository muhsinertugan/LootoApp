package com.lotto.lottoapp.model.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRegisterApi @Inject constructor(private val service: LoginRegisterService){

    suspend fun postLogin(mailAddress: String): Any = service.postLogin(mailAddress)

    suspend fun postRegister(): Any = service.postRegister()

    suspend fun postLoginOtp(): Any = service.postLoginOtp()

    suspend fun postRegisterOtp(): Any = service.postRegisterOtp()

}