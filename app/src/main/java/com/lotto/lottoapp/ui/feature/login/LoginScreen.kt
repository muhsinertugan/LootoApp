package com.lotto.lottoapp.ui.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.lotto.lottoapp.core.components.CustomInputField
import com.lotto.lottoapp.ui.constants.Buttons
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.constants.Placeholders
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {
    val scope = rememberCoroutineScope()
    val userLoginInput by viewModel.userInput.collectAsState()
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
        modifier = Modifier.height(600.dp)
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        CustomInputField(
            fieldName = Constants.EMAIL,
            placeholderText = Placeholders.EMAIL_PLACEHOLDER,
            text = userLoginInput.email,
            onFieldValueChange = { newValue ->
                viewModel.updateField("email", newValue)
            },
            isError = errorState.value.success
        )

        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = Buttons.SEND_BTN,
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable {
                    scope.launch {
                        viewModel.onClick(navController = navController)

                    }
                }
                .clip(RoundedCornerShape(8.dp))
                .background(color = CustomPurple)
                .padding(vertical = 16.dp, horizontal = 30.dp)
        )
    }


}




