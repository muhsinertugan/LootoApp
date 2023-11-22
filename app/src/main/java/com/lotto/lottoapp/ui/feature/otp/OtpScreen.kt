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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.model.request.LoginOtpRequest
import com.lotto.lottoapp.model.request.LoginRequest
import com.lotto.lottoapp.model.request.RegisterOtpRequest
import com.lotto.lottoapp.model.request.RegisterRequest
import com.lotto.lottoapp.ui.constants.Constants
import com.lotto.lottoapp.ui.feature.otp.components.CustomCountDownTimer
import com.lotto.lottoapp.ui.theme.CustomGray
import com.lotto.lottoapp.ui.theme.CustomLightGray
import com.lotto.lottoapp.ui.theme.CustomPurple
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    navController: NavHostController,
    userLoginInput: LoginRequest,
    userRegisterInput: RegisterRequest
) {


    val otpScreenViewModel: OtpScreenViewModel = hiltViewModel()

    var otpValue by remember { mutableStateOf("") }
    var remainingTime by remember { mutableFloatStateOf(Constants.TOTAL_DURATION) }
    var isRunning by remember { mutableStateOf(true) }


    val inputColor = if (isRunning) CustomPurple else CustomGray

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000)
                remainingTime--
            }
            isRunning = false
        }
    }


    Box(modifier = Modifier.padding(vertical = 90.dp)) {
        BasicTextField(
            enabled = isRunning,
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

    Button(
        onClick = {
            if (userRegisterInput.cityId == null) {
                otpScreenViewModel.postLoginOtp(
                    navController = navController,
                    loginOtpRequest = LoginOtpRequest(
                        email = userLoginInput.email,
                        otp = otpValue
                    )
                )

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

        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        enabled = isRunning,
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


    CustomCountDownTimer(totalDuration = Constants.TOTAL_DURATION, remainingTime = remainingTime)

}