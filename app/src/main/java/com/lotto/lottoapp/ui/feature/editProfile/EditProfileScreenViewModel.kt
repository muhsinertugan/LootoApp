package com.lotto.lottoapp.ui.feature.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.profile.ProfileApi
import com.lotto.lottoapp.model.request.EditProfileRequest
import com.lotto.lottoapp.model.response.profile.User
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val profileApi: ProfileApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,

    ) : ViewModel() {


    private var _userState = MutableStateFlow(
        EditProfileScreenContract.UserState(
            user = User(
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
        )
    )

    private var userState = _userState.asStateFlow()


    private fun updateUserState(newState: EditProfileScreenContract.UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userState.value = newState
        }
    }

    private fun patchProfile(editedInputs: EditProfileRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = profileApi.patchProfile(
                    token = userToken, patchProfileBody = editedInputs
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val editProfileResponse = response.body()

                        if (editProfileResponse != null) {
                            val newState = EditProfileScreenContract.UserState(
                                user = User(
                                    __v = editProfileResponse.data.__v,
                                    _id = editProfileResponse.data._id,
                                    activated = editProfileResponse.data.activated,
                                    balance = editProfileResponse.data.balance,
                                    birthDate = editProfileResponse.data.birthDate,
                                    cityId = editProfileResponse.data.cityId,
                                    createdAt = editProfileResponse.data.createdAt,
                                    email = editProfileResponse.data.email,
                                    expireAt = editProfileResponse.data.expireAt,
                                    lastName = editProfileResponse.data.lastName,
                                    name = editProfileResponse.data.name,
                                    phoneNumber = editProfileResponse.data.phoneNumber,
                                    privacyPolicy = editProfileResponse.data.privacyPolicy
                                )
                            )
                            updateUserState(newState)
                        }
                    }
                }
            } catch (e: Exception) {

            }

        }
    }

}