package com.lotto.lottoapp.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lotto.lottoapp.model.data.balance.BalanceApi
import com.lotto.lottoapp.model.data.profile.ProfileApi
import com.lotto.lottoapp.model.request.BalanceRequest
import com.lotto.lottoapp.model.response.general.CityResponseItem
import com.lotto.lottoapp.model.response.general.SerializableCityState
import com.lotto.lottoapp.model.response.profile.User
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                val response = profileApi.getProfile(token = userToken)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val profileResponse = response.body()

                        if (profileResponse != null) {
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
                    }
                }
            } catch (e: Exception) {

            }

        }
    }


    fun addBalance(addedBalance: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = balanceApi.addBalance(
                    balanceAmount = BalanceRequest(amount = addedBalance), token = userToken
                )
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val addBalanceResponse = response.body()

                        if (addBalanceResponse != null) {
                            val newState =
                                ProfileScreenContract.BalanceState(amount = addBalanceResponse.balance)
                            updateBalanceState(newState)
                        }
                    }
                }


            } catch (e: Exception) {

            }
        }
    }

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userToken = sharedPreferencesUtil.loadData<String>("userToken")
                val response = balanceApi.getBalance(userToken)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val balanceResponse = response.body()

                        if (balanceResponse != null) {
                            val newState =
                                ProfileScreenContract.BalanceState(amount = balanceResponse.balance)
                            updateBalanceState(newState)
                        }
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    fun updateBalance(state: ProfileScreenContract.SelectableAmountState) {
        viewModelScope.launch(Dispatchers.Main) {
            _selectedAmount.value = state
        }

    }

    fun handleLogout(navController: NavHostController) {
        sharedPreferencesUtil.deleteData("userToken")
    }

    private fun initProfile() {
        viewModelScope.launch(Dispatchers.Main) {
            getProfile()
        }
    }


}