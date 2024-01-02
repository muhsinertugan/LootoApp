package com.lotto.lottoapp.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.balance.BalanceApi
import com.lotto.lottoapp.model.data.profile.ProfileApi
import com.lotto.lottoapp.model.request.BalanceRequest
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.general.CityResponseItem
import com.lotto.lottoapp.model.response.general.SerializableCityState
import com.lotto.lottoapp.model.response.profile.User
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import com.lotto.lottoapp.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileApi: ProfileApi,
    private val balanceApi: BalanceApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.Main) {
            initProfile()
        }
    }

    private var _errorState = MutableStateFlow(
        ProfileScreenContract.ErrorState(
            code = 0,
            message = "",
            success = false,
            id = ""
        )
    )

    var errorState = _errorState.asStateFlow()

    private fun updateErrorState(newState: ProfileScreenContract.ErrorState) {
        viewModelScope.launch(Dispatchers.Main) {
            _errorState.value = newState
        }

    }

    private var _userState = MutableStateFlow(
        ProfileScreenContract.UserState(
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


    private var _profileState = MutableStateFlow(
        ProfileScreenContract.ProfileState(
            title = ProfileScreenContract.ProfileTitles(),
            data = ProfileScreenContract.UserData(
                birthDate = "",
                city = CityResponseItem(
                    __v = 0,
                    _id = "",
                    code = 0,
                    latitude = "",
                    longitude = "",
                    name = "",
                    population = 0,
                    region = ""
                ),
                email = "",
                lastName = "",
                name = "",
                phoneNumber = ""
            )
        )
    )

    var profileState = _profileState.asStateFlow()


    private fun updateProfileState(
        userState: StateFlow<ProfileScreenContract.UserState>,
    ) {
        val cities: SerializableCityState = sharedPreferencesUtil.loadData("cities")

        val userCity = cities.cities.filter {
            it._id == userState.value.user.cityId
        }

        val newProfileState = ProfileScreenContract.ProfileState(
            title = ProfileScreenContract.ProfileTitles(),
            data = ProfileScreenContract.UserData(
                birthDate = userState.value.user.birthDate,
                city = userCity[0],
                email = userState.value.user.email,
                lastName = userState.value.user.lastName,
                name = userState.value.user.name,
                phoneNumber = userState.value.user.phoneNumber
            )
        )

        viewModelScope.launch(Dispatchers.Main) {
            sharedPreferencesUtil.saveData("userData", newProfileState.data)
            _profileState.value = newProfileState
        }

    }


    private val _balanceState = MutableStateFlow(
        ProfileScreenContract.BalanceState(amount = 0)
    )

    var balanceState = _balanceState.asStateFlow()
    private fun updateUserState(newState: ProfileScreenContract.UserState) {
        viewModelScope.launch(Dispatchers.Main) {
            _userState.value = newState
        }
    }

    private val _selectableAmounts = MutableStateFlow(
        listOf(
            ProfileScreenContract.SelectableAmountState(
                title = Constants.TEN_K, isSelected = false, amount = 10000
            ),
            ProfileScreenContract.SelectableAmountState(
                title = Constants.TWENTY_K, isSelected = false, amount = 20000
            ),
            ProfileScreenContract.SelectableAmountState(
                title = Constants.THIRTY_K, isSelected = false, amount = 30000
            ),

            )
    )

    var selectableAmounts = _selectableAmounts.asStateFlow()

    private val _selectedAmount = MutableStateFlow(
        ProfileScreenContract.SelectableAmountState(
            title = Constants.TEN_K, isSelected = false, amount = 10000
        ),


        )

    var selectedAmount = _selectedAmount.asStateFlow()


    private fun updateBalanceState(newState: ProfileScreenContract.BalanceState) {
        viewModelScope.launch(Dispatchers.Main) {
            _balanceState.value = newState
        }
    }

    private fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {


            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = handleResponse(profileApi.getProfile(token = userToken))
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val profileResponse = response.data

                            val newState = ProfileScreenContract.UserState(
                                user = User(
                                    __v = profileResponse.data.__v,
                                    _id = profileResponse.data._id,
                                    activated = profileResponse.data.activated,
                                    balance = profileResponse.data.balance,
                                    birthDate = profileResponse.data.birthDate,
                                    cityId = profileResponse.data.cityId,
                                    createdAt = profileResponse.data.createdAt,
                                    email = profileResponse.data.email,
                                    expireAt = profileResponse.data.expireAt,
                                    lastName = profileResponse.data.lastName,
                                    name = profileResponse.data.name,
                                    phoneNumber = profileResponse.data.phoneNumber,
                                    privacyPolicy = profileResponse.data.privacyPolicy
                                )
                            )
                            updateUserState(newState)
                            updateProfileState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                ProfileScreenContract.ErrorState(
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
                ProfileScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }

        }
    }


    fun addBalance(addedBalance: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = handleResponse(
                    balanceApi.addBalance(
                        balanceAmount = BalanceRequest(amount = addedBalance), token = userToken
                    )
                )
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val addBalanceResponse = response.data

                            val newState =
                                ProfileScreenContract.BalanceState(amount = addBalanceResponse.balance)
                            updateBalanceState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                ProfileScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }
                        }
                    }
                }
            } catch (e: Exception) {
                ProfileScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = handleResponse(balanceApi.getBalance(userToken))
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            val balanceResponse = response.data
                            val newState =
                                ProfileScreenContract.BalanceState(amount = balanceResponse.balance)
                            updateBalanceState(newState)
                        }

                        is ApiResponse.Error -> {
                            response.response?.let {
                                ProfileScreenContract.ErrorState(
                                    code = it.code,
                                    message = it.message,
                                    success = it.success,
                                    id = UUID.randomUUID().toString()
                                )

                            }
                        }
                    }
                }
            } catch (e: Exception) {
                ProfileScreenContract.ErrorState(
                    code = 500,
                    message = e.message.toString(),
                    success = false,
                    id = UUID.randomUUID().toString()
                )
            }
        }
    }

    fun updateBalance(state: ProfileScreenContract.SelectableAmountState) {
        viewModelScope.launch(Dispatchers.Main) {
            _selectedAmount.value = state
        }

    }

    fun handleLogout() {
        sharedPreferencesUtil.deleteData("userToken")
    }

    private fun initProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            getProfile()
        }
    }


}