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
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import com.lotto.lottoapp.R
import com.lotto.lottoapp.ui.feature.otp.components.CustomCountDownTimer

@Composable
fun OtpScreen(
    navController: NavHostController
){

 var otpValue by remember{ mutableStateOf("") }

Box (modifier = Modifier.padding(vertical = 90.dp)){
    BasicTextField(
        value = otpValue,
        onValueChange = {
            if(it.length <=4) {
                otpValue = it
            }

        },
        keyboardOptions =  KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox ={
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                repeat(4){ index->

                    val char = when{
                        index>= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }
                    Text(
                        modifier = Modifier
                            .width(60.dp)
                            .height(90.dp)
                            .border(
                                1.dp,
                                Color(red = 79, green = 79, blue = 79),
                                RoundedCornerShape(8.dp)
                            )
                            .background(
                                color = Color(red = 79, green = 79, blue = 79),
                                RoundedCornerShape(8.dp)
                            )
                        ,
                        text = char,

                        style= MaterialTheme.typography.displayLarge,
                        color =  Color(red = 233, green = 233, blue = 233),
                        textAlign = TextAlign.Center,
                    )

                    if (index < 3){
                        Spacer(modifier = Modifier.width(22.dp))
                    }
                }
            }
        }


    )
}

    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), modifier = Modifier
        .width(96.dp)
        .height(96.dp)
        ) {

            Image(painter = painterResource(id = R.drawable.tickcircle), contentDescription = "confirm button", modifier = Modifier.scale(1.75F))

    }

    CustomCountDownTimer()
    
}