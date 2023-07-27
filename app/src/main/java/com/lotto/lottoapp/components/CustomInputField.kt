package com.lotto.lottoapp.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.lotto.lottoapp.ui.theme.Inter


@Composable
fun CustomInputField(fieldName: String ) {
    var text by remember { mutableStateOf("") }
    Column(    verticalArrangement = Arrangement.Center,
        ) {
        Text(text = fieldName, color = Color.White, style = TextStyle(fontFamily = Inter), modifier = Modifier.padding(vertical =  10.dp, horizontal = 5.dp))
        androidx.compose.material3.OutlinedTextField(
            modifier= Modifier.border(0.85.dp, color = Color.White, shape = RoundedCornerShape(12.dp)) ,
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Enter your email", color = Color.White, style = TextStyle(fontFamily = Inter), ) },
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(color = Color.White),
            // leadingIcon =

        )
    }
}

