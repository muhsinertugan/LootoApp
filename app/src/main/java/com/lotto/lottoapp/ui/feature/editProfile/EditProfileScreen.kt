package com.lotto.lottoapp.ui.feature.editProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.core.components.CustomDatePicker
import com.lotto.lottoapp.core.components.CustomDropdownMenu
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    val scope = rememberCoroutineScope()
    val editProfileScreenViewModel: EditProfileScreenViewModel = hiltViewModel()
    val profileData = editProfileScreenViewModel.editProfileState.collectAsState()
    val cityState = editProfileScreenViewModel.cityState.collectAsState()

    val errorState = editProfileScreenViewModel.errorState.collectAsState()


    LaunchedEffect(errorState.value.id) {
        if (!errorState.value.success && errorState.value.code != 0) {
            keyboardController?.hide()
            snackbarHostState.showSnackbar(
                message = errorState.value.message,
                withDismissAction = true,
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(700.dp)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {


        CustomInputField(
            fieldName = Constants.NAME,
            placeholderText = profileData.value.name,
            text = profileData.value.name,
            onFieldValueChange = { newValue ->
                editProfileScreenViewModel.updateField("name", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.SURNAME,
            placeholderText = profileData.value.lastName,
            text = profileData.value.lastName,
            onFieldValueChange = { newValue ->
                editProfileScreenViewModel.updateField("lastName", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.PHONE,
            placeholderText = profileData.value.phoneNumber,
            text = profileData.value.phoneNumber,
            onFieldValueChange = { newValue ->
                editProfileScreenViewModel.updateField("phoneNumber", newValue)
            },
            isError = false
        )

        CustomDropdownMenu(
            fieldName = Constants.CITY,
            state = cityState,
            selectedCity = profileData.value.city.name
        ) { newValue ->
            editProfileScreenViewModel.updateField("cityId", newValue)
        }

        CustomDatePicker(
            fieldName = Constants.BIRTHDATE,
            placeholderText = profileData.value.birthDate,
            text = profileData.value.birthDate

        ) { newValue: Long ->
            editProfileScreenViewModel.updateField("birthDate", newValue)
        }


        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = Buttons.SUBMIT_BTN,
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable {
                    scope.launch {
                        editProfileScreenViewModel.editProfile(navController)
                    }
                }
                .clip(RoundedCornerShape(8.dp))
                .background(color = CustomPurple)
                .padding(vertical = 16.dp, horizontal = 136.dp)
        )


    }
}
