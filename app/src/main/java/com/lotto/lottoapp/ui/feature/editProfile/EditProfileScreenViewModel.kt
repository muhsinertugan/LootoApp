package com.lotto.lottoapp.ui.feature.editProfile

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.profile.ProfileApi
import com.lotto.lottoapp.model.request.EditProfileRequest
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.general.CityResponseItem
import com.lotto.lottoapp.model.response.general.SerializableCityState
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.ui.feature.splash.SplashScreenContract
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import com.lotto.lottoapp.utils.TimeUtil
import com.lotto.lottoapp.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditProfileScreenViewModel @Inject constructor(
    private val profileApi: ProfileApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
    private val timeUtil: TimeUtil,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.Main) {
            val editProfileData: EditProfileScreenContract.UserState =
                sharedPreferencesUtil.loadData("userData")
            updateEditProfileState(
                newState = EditProfileScreenContract.UserState(
                    birthDate = timeUtil.convertDateFormat(editProfileData.birthDate),
                    city = editProfileData.city,
                    email = editProfileData.email,
                    lastName = editProfileData.lastName,
                    name = editProfileData.name,
                    phoneNumber = editProfileData.phoneNumber
                )
            )
            val cities: SerializableCityState =
                sharedPreferencesUtil.loadData("cities")
            updateCityState(cities)
        }
    }

    private var _errorState = MutableStateFlow(
        EditProfileScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: EditProfileScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }

    private var _cityState = MutableStateFlow(
        SplashScreenContract.CityState(
            cities = listOf(
                CityResponseItem(
                    __v = 0,
                    _id = "",
                    code = 0,
                    latitude = "",
                    longitude = "",
                    name = "",
                    population = 0,
                    region = ""
                )
            ),
            isLoading = false
        )
    )

    val cityState = _cityState.asStateFlow()

    private fun updateCityState(newState: SerializableCityState) {
        viewModelScope.launch(Dispatchers.Main) {
            _cityState.value =
                SplashScreenContract.CityState(cities = newState.cities, isLoading = false)
        }
    }


    private var _editProfileState = MutableStateFlow(
        EditProfileScreenContract.UserState(
            birthDate = "", city = CityResponseItem(
                __v = 0,
                _id = "",
                code = 0,
                latitude = "",
                longitude = "",
                name = "",
                population = 0,
                region = ""
            ), email = "", lastName = "", name = "", phoneNumber = ""
        )

    )

    val editProfileState = _editProfileState.asStateFlow()

    private fun updateEditProfileState(newState: EditProfileScreenContract.UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _editProfileState.value = newState
        }
    }


    private fun patchProfile(editedInputs: EditProfileRequest, navController: NavHostController) {
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


                            val userCity: CityResponseItem = cityState.value.cities.find {
                                return@find editProfileResponse.user.cityId == it._id
                            }!!
                            val newState = EditProfileScreenContract.UserState(
                                birthDate = timeUtil.convertDateFormat(editProfileResponse.user.birthDate),
                                city = userCity,
                                email = editProfileResponse.user.email,
                                lastName = editProfileResponse.user.lastName,
                                name = editProfileResponse.user.name,
                                phoneNumber = editProfileResponse.user.phoneNumber
                            )
                            viewModelScope.launch(Dispatchers.Main) {
                                updateEditProfileState(newState)
                                navController.navigate(NavigationItems.App.Profile.route)
                            }
                        }
                    }
                }
            } catch (_: Exception) {

            }
            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = handleResponse(
                    profileApi.patchProfile(
                        token = userToken, patchProfileBody = editedInputs
                    )
                )
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val editProfileResponse = response.data

                            val userCity: CityResponseItem = cityState.value.cities.find {
                                return@find editProfileResponse.user.cityId == it._id
                            }!!
                            val newState = EditProfileScreenContract.UserState(
                                birthDate = timeUtil.convertDateFormat(editProfileResponse.user.birthDate),
                                city = userCity,
                                email = editProfileResponse.user.email,
                                lastName = editProfileResponse.user.lastName,
                                name = editProfileResponse.user.name,
                                phoneNumber = editProfileResponse.user.phoneNumber
                            )
                            viewModelScope.launch(Dispatchers.Main) {
                                updateEditProfileState(newState)
                                navController.navigate(NavigationItems.App.Profile.route)
                            }
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                EditProfileScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }?.let { updateErrorState(it) }
                        }
                    }
                }
            } catch (e: Exception) {
                EditProfileScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }

        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(date: Long): String {
        val simpleDate = SimpleDateFormat("dd/M/yyyy")
        val formatted = Date(date)
        return simpleDate.format(formatted)
    }


    fun updateField(fieldName: String, value: String) {
        val currentInput = _editProfileState.value
        val updatedInput = when (fieldName) {
            "name" -> currentInput.copy(name = value)
            "lastName" -> currentInput.copy(lastName = value)
            "phoneNumber" -> currentInput.copy(phoneNumber = value)
            "cityId" -> currentInput.copy(
                city = CityResponseItem(
                    __v = editProfileState.value.city.__v,
                    _id = value,
                    code = editProfileState.value.city.code,
                    latitude = editProfileState.value.city.latitude,
                    longitude = editProfileState.value.city.longitude,
                    name = editProfileState.value.city.name,
                    population = editProfileState.value.city.population,
                    region = editProfileState.value.city.region
                )
            )

            else -> currentInput
        }
        _editProfileState.value = updatedInput
    }

    fun updateField(fieldName: String, value: Long) {

        val currentInput = _editProfileState.value
        val updatedInput = when (fieldName) {
            "birthDate" -> currentInput.copy(birthDate = getDateTime(value))
            else -> currentInput
        }

        _editProfileState.value = updatedInput
    }

    fun editProfile(navController: NavHostController) {
        patchProfile(
            editedInputs = EditProfileRequest(
                birthDate = editProfileState.value.birthDate,
                cityId = editProfileState.value.city._id,
                lastName = editProfileState.value.lastName,
                name = editProfileState.value.name,
                phoneNumber = editProfileState.value.phoneNumber
            ),
            navController = navController
        )
    }
}