package com.lotto.lottoapp.ui.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.navigation.NavigationItems
import com.lotto.lottoapp.ui.feature.profile.components.LogoutButton
import com.lotto.lottoapp.ui.feature.profile.components.ProfileBalanceSection
import com.lotto.lottoapp.ui.feature.profile.components.ProfileDataColumn
import com.lotto.lottoapp.ui.feature.profile.components.ProfileEditButton


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {

    val profileViewModel: ProfileScreenViewModel = hiltViewModel()

    val selectableAmounts = profileViewModel.selectableAmounts.collectAsState()
    val selectedAmount = profileViewModel.selectedAmount.collectAsState()
    val profileState = profileViewModel.profileState.collectAsState()

    val errorState = profileViewModel.errorState.collectAsState()


    LaunchedEffect(errorState.value.id) {
        if (!errorState.value.success && errorState.value.code != 0) {
            keyboardController?.hide()
            snackbarHostState.showSnackbar(
                message = errorState.value.message,
                withDismissAction = true,
            )
        }
    }


    fun handleAddBalance(balance: Int) {
        profileViewModel.addBalance(balance)
    }

    fun handleUpdateBalance(balance: ProfileScreenContract.SelectableAmountState) {
        profileViewModel.updateBalance(
            state = balance
        )
    }

    fun handleLogout(navController: NavHostController) {
        profileViewModel.handleLogout()
        navController.navigate(NavigationItems.Auth.route) {
            popUpTo(0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        ProfileBalanceSection(
            selectableAmounts = selectableAmounts,
            selectedAmount = selectedAmount,
            handleAddBalance = { handleAddBalance(it) },
            handleUpdateBalance = { handleUpdateBalance(it) })
        Spacer(modifier = Modifier.height(48.dp))
        Column {
            ProfileEditButton(navController = navController)
            ProfileDataColumn(profileState)
            Spacer(modifier = Modifier.height(12.dp))

            LogoutButton(handleLogout = {

                handleLogout(navController = navController)

            })
        }
    }
}


