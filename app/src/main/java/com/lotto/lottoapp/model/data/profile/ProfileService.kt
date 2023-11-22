package com.lotto.lottoapp.model.data.profile

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ProfileService {
    @PATCH(ENDPOINTS.PROFILE_URL)
    suspend fun patchProfile(
        @Body body: RegisterRequest,
        @Header("Authorization") token: String
    ): Response<>

    @GET(ENDPOINTS.PROFILE_URL)
    suspend fun getProfile(@Header("Authorization") token: String): Response<>

}