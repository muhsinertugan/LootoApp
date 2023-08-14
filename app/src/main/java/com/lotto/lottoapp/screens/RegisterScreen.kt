package com.lotto.lottoapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.components.CustomInputField
import com.lotto.lottoapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(){
    val horizontalGradient = Brush.horizontalGradient(
        colors = listOf(  Color(204,0,255), Color(128, 1,255) ),
    )


    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(700.dp)
            .padding(top = 10.dp)
    ) {
        CustomInputField(fieldName = "Email", placeholderText = "Enter your email")
        CustomInputField(fieldName = "Name", placeholderText = "Enter your name")
        CustomInputField(fieldName = "Surname", placeholderText = "Enter your surname")
        CustomInputField(fieldName = "Phone", placeholderText = "Enter your phone number")
        CustomInputField(fieldName = "City", placeholderText = "Select your city")
        CustomInputField(fieldName = "Birthdate", placeholderText = "Select your birthdate")

        Spacer(modifier = Modifier.height(90.dp))

        Text(
            text = "Submit",
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier
                .clickable(onClick = {})
                .clip(RoundedCornerShape(8.dp))
                .background(brush = horizontalGradient)
                .padding(vertical = 16.dp, horizontal = 136.dp)
        )
    }
}

