package com.lotto.lottoapp.ui.feature.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.core.components.CustomDatePicker
import com.lotto.lottoapp.core.components.CustomDropdownMenu
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.navigation.Paths
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.constants.Placeholders
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(

    navController: NavHostController,
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    val scope = rememberCoroutineScope()

    val userRegisterInput = viewModel.userInput.collectAsState()
    val userRegisterState = viewModel.userState.collectAsState()
    val cityState = viewModel.cityState.collectAsState()
    val errorState = viewModel.errorState.collectAsState()
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
        modifier = Modifier
            .height(700.dp)
            .padding(top = 10.dp)
    ) {

        CustomInputField(
            fieldName = Constants.EMAIL,
            placeholderText = Placeholders.EMAIL_PLACEHOLDER,
            text = userRegisterInput.value.email,
            onFieldValueChange = { newValue ->
                viewModel.updateField("email", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.NAME,
            placeholderText = Placeholders.NAME_PLACEHOLDER,
            text = userRegisterInput.value.name,
            onFieldValueChange = { newValue ->
                viewModel.updateField("name", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.SURNAME,
            placeholderText = Placeholders.SURNAME_PLACEHOLDER,
            text = userRegisterInput.value.lastName,
            onFieldValueChange = { newValue ->
                viewModel.updateField("lastName", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = Constants.PHONE,
            placeholderText = Placeholders.PHONE_PLACEHOLDER,
            text = userRegisterInput.value.phoneNumber,
            onFieldValueChange = { newValue ->
                viewModel.updateField("phoneNumber", newValue)
            },
            isError = false
        )
        CustomDropdownMenu(
            fieldName = Constants.CITY, state = cityState
        ) { newValue ->
            viewModel.updateField("cityId", newValue)
        }
        CustomDatePicker(
            fieldName = Constants.BIRTHDATE,
            placeholderText = Placeholders.BIRTHDATE_PLACEHOLDER,
            text = userRegisterInput.value.birthDate
        ) { newValue: Long ->
            viewModel.updateField("birthDate", newValue)
        }


        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = Buttons.SUBMIT_BTN,
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable {
                    scope.launch {
                        viewModel.onClick(navController = navController)
                    }
                    if (userRegisterState.value.success) {
                        navController.navigate(Paths.OTP_SCREEN)
                    }
                }
                .clip(RoundedCornerShape(8.dp))
                .background(color = CustomPurple)
                .padding(vertical = 16.dp, horizontal = 136.dp)
        )


    }
}
