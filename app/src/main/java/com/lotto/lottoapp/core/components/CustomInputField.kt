package com.lotto.lottoapp.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.ui.theme.Inter



@Composable
 fun CustomInputField(fieldName: String, placeholderText: String  ) {
    var text by remember { mutableStateOf("") }

    Column(    verticalArrangement = Arrangement.Center, modifier = Modifier.zIndex(1000F)
    ) {
        Text(text = fieldName, color = Color.White, style = TextStyle(fontFamily = Inter), modifier = Modifier.padding(vertical =  10.dp, horizontal = 5.dp))

        OutlinedTextField(
            modifier= Modifier
                .border(0.75.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .width(320.dp)
                .height(50.dp)
                .clickable { println("Hello") },
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = placeholderText, color = Color.White, style = TextStyle(fontFamily = Inter) ) },
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            singleLine = true,

            // leadingIcon = {
            //     if (fieldName == "Email"){
            //         Icon(
            //             painter = painterResource(id = R.drawable.mail_icon),
            //             contentDescription = "mail icon",
            //             tint = Color.White,
            //             modifier = Modifier.scale(0.7F)
            //         )
            //     }
            //  },

        )
    }

 }





