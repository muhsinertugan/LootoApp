package com.lotto.lottoapp.model.data.profile

import com.lotto.lottoapp.di.ENDPOINTS
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.model.response.profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ProfileService {
    @PATCH(ENDPOINTS.PROFILE_URL)
    suspend fun patchProfile(
        @Body body: RegisterRequest,
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @POST(ENDPOINTS.PROFILE_URL)
    suspend fun getProfile(@Header("Authorization") token: String): Response<ProfileResponse>

}