package com.lotto.lottoapp.model.data.profile

import com.lotto.lottoapp.model.request.EditProfileRequest
import com.lotto.lottoapp.model.response.profile.EditProfileResponse
import com.lotto.lottoapp.model.response.profile.ProfileResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileApi @Inject constructor(private val service: ProfileService) {
    suspend fun patchProfile(
        token: String,
        patchProfileBody: EditProfileRequest,
    ): Response<EditProfileResponse> =
        service.patchProfile(
            token = "token $token",
            body = patchProfileBody
        )

    suspend fun getProfile(token: String): Response<ProfileResponse> =
        service.getProfile(token = "token $token")
}