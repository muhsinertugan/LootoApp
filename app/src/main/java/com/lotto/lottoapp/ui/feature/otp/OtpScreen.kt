package com.lotto.lottoapp.ui.feature.otp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.navigation.Paths
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.feature.otp.components.CustomCountDownTimer
import com.lotto.lottoapp.ui.feature.otp.components.ResendBtn
import com.lotto.lottoapp.ui.theme.CustomGray
import com.lotto.lottoapp.ui.theme.CustomLightGray
import com.lotto.lottoapp.ui.theme.CustomPurple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpScreen(
    navController: NavHostController,
    userLoginInput: LoginRequest?,
    userRegisterInput: RegisterRequest?,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController?,
) {

    val scope = rememberCoroutineScope()
    val otpScreenViewModel: OtpScreenViewModel = hiltViewModel()
    var otpValue by remember { mutableStateOf("") }
    var remainingTime by remember { mutableFloatStateOf(Constants.TOTAL_DURATION) }
    val isResend = otpScreenViewModel.resendState.collectAsState()
    val userMail = if (userLoginInput !== null) userLoginInput.email else userRegisterInput?.email
    val errorState = otpScreenViewModel.errorState.collectAsState()

    val prevBackStackEntry = navController.previousBackStackEntry?.destination?.route


    val inputColor = if (isResend.value.isRunning) CustomPurple else CustomGray

    fun handleOtpCall() {
        if (userRegisterInput?.cityId == null) {
            if (userLoginInput != null) {
                otpScreenViewModel.postLoginOtp(
                    navController = navController,
                    loginOtpRequest = LoginOtpRequest(
                        email = userLoginInput.email,
                        otp = otpValue
                    )
                )
            }
        } else {
            otpScreenViewModel.postRegisterOtp(
                navController = navController,
                registerOtpRequest = RegisterOtpRequest(
                    birthDate = userRegisterInput.birthDate,
                    cityId = userRegisterInput.cityId,
                    email = userRegisterInput.email,
                    lastName = userRegisterInput.lastName,
                    name = userRegisterInput.name,
                    phoneNumber = userRegisterInput.phoneNumber,
                    otp = otpValue
                )
            )
        }
    }

    LaunchedEffect(isResend.value.isRunning) {
        if (isResend.value.isRunning) {
            while (remainingTime > 0) {
                delay(1000)
                remainingTime--
            }
            otpScreenViewModel.updateResendState()
            remainingTime = Constants.TOTAL_DURATION
        }
    }

    LaunchedEffect(errorState.value.id) {
        if (!errorState.value.success && errorState.value.message != "") {

            keyboardController?.hide()
            snackbarHostState.showSnackbar(
                message = errorState.value.message,
                withDismissAction = true,
            )
        }
    }
    Box(modifier = Modifier.padding(vertical = 90.dp)) {
        BasicTextField(
            enabled = isResend.value.isRunning,
            value = otpValue,
            onValueChange = {
                if (it.length <= 4) {
                    otpValue = it
                }

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    repeat(4) { index ->

                        val otpChar = when {
                            index >= otpValue.length -> ""
                            else -> otpValue[index].toString()
                        }
                        Text(
                            modifier = Modifier
                                .width(60.dp)
                                .height(90.dp)
                                .border(
                                    1.dp,
                                    inputColor,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(
                                    color = inputColor,
                                    RoundedCornerShape(12.dp)
                                ),
                            text = otpChar,

                            style = MaterialTheme.typography.displayLarge,
                            color = CustomLightGray,
                            textAlign = TextAlign.Center,
                        )

                        if (index < 3) {
                            Spacer(modifier = Modifier.width(22.dp))
                        }
                    }
                }
            }


        )
    }

    if (isResend.value.isRunning) {
        Button(
            onClick = {
                scope.launch {
                    handleOtpCall()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            enabled = isResend.value.isRunning,
            modifier = Modifier
                .width(96.dp)
                .height(96.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.tickcircle),
                contentDescription = "confirm button",
                modifier = Modifier.scale(1.75F)
            )

        }


        CustomCountDownTimer(
            totalDuration = Constants.TOTAL_DURATION,
            remainingTime = remainingTime
        )

        Text(
            text = "We sent a 4-digit code $userMail check your mailbox",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
            )
        )
    } else ResendBtn(handleResend = {

        when (prevBackStackEntry) {
            Paths.LOGIN_SCREEN -> {
                if (userLoginInput != null) {
                    otpScreenViewModel.resendOtp(loginRequest = userLoginInput)
                }
            }

            Paths.REGISTER_SCREEN -> {
                if (userRegisterInput != null) {
                    otpScreenViewModel.resendOtp(
                        registerRequest = userRegisterInput
                    )
                }
            }
        }

    })
}