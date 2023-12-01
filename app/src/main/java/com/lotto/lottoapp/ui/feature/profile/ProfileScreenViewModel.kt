package com.lotto.lottoapp.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lotto.lottoapp.model.data.balance.BalanceApi
import com.lotto.lottoapp.model.data.profile.ProfileApi
import com.lotto.lottoapp.model.request.BalanceRequest
import com.lotto.lottoapp.model.response.profile.User
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileApi: ProfileApi,
    private val balanceApi: BalanceApi,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {



    private var _userState = MutableStateFlow(
        ProfileScreenContract.ProfileState(
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

    var userState = _userState.asStateFlow()


    private val _balanceState = MutableStateFlow(
        ProfileScreenContract.BalanceState(amount = 0)
    )

    var balanceState = _balanceState.asStateFlow()
    private fun updateProfileState(newState: ProfileScreenContract.ProfileState) {
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

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userToken = sharedPreferencesUtil.loadData("userToken")
                val response = profileApi.getProfile(token = userToken)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val profileResponse = response.body()

                        if (profileResponse != null) {
                            val newState = ProfileScreenContract.ProfileState(
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
                val userToken = sharedPreferencesUtil.loadData("userToken")
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
                val userToken = sharedPreferencesUtil.loadData("userToken")
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


}