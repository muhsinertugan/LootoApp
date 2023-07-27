package com.lotto.lottoapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lotto.lottoapp.components.BackgroundImage
import com.lotto.lottoapp.components.CustomInputField

@Composable
fun LoginScreen() {
    BackgroundImage()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomInputField(fieldName = "Email")
        Button(onClick = {  }) {
             Text(text = "Button")

    }
}

}



@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}


