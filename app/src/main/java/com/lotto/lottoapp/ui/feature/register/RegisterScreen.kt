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
import com.lotto.lottoapp.ui.theme.CustomPurple
import com.lotto.lottoapp.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(

    navController: NavHostController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {


    val userRegisterInput by viewModel.userInput.collectAsState()
    val userRegisterState by viewModel.userState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(700.dp)
            .padding(top = 10.dp)
    ) {

        CustomInputField(
            fieldName = "Email",
            placeholderText = "Enter your email",
            text = userRegisterInput.email,
            onFieldValueChange = { newValue ->
                viewModel.updateField("email", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = "Name",
            placeholderText = "Enter your name",
            text = userRegisterInput.name,
            onFieldValueChange = { newValue ->
                viewModel.updateField("name", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = "Surname",
            placeholderText = "Enter your surname",
            text = userRegisterInput.lastName,
            onFieldValueChange = { newValue ->
                viewModel.updateField("lastName", newValue)
            },
            isError = false
        )
        CustomInputField(
            fieldName = "Phone",
            placeholderText = "Enter your phone",
            text = userRegisterInput.phoneNumber,
            onFieldValueChange = { newValue ->
                viewModel.updateField("phoneNumber", newValue)
            },
            isError = false
        )
        CustomDropdownMenu(
            fieldName = "City", state = viewModel.cityState,
            onFieldValueChange = { newValue ->
                viewModel.updateField("cityId", newValue)
            }
        )
        CustomDatePicker(
            fieldName = "Date",
            placeholderText = "Enter your birthday",
            text = userRegisterInput.birthDate
        ) { newValue: Long ->
            viewModel.updateField("birthDate", newValue)
        }


        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Submit",
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable {
                    CoroutineScope(Dispatchers.Default).launch {
                        viewModel.onClick(navController = navController)
                    }
                    if (userRegisterState.value.success) {
                        navController.navigate("otp_screen")

                    }
                }
                .clip(RoundedCornerShape(8.dp))
                .background(color = CustomPurple)
                .padding(vertical = 16.dp, horizontal = 136.dp)
        )


    }
}
