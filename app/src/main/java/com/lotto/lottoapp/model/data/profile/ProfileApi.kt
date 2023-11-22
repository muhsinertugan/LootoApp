package com.lotto.lottoapp.model.data.profile

import com.lotto.lottoapp.model.request.RegisterRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileApi @Inject constructor(private val service: ProfileService) {
    suspend fun patchProfile(token: String, patchProfileBody: RegisterRequest): Response<> =
        service.patchProfile(
            token = "token $token",
            body = patchProfileBody
        )

    suspend fun getProfile(token: String): Response<> = service.getProfile(token = "token $token")
}