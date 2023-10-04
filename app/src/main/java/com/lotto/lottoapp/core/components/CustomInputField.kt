package com.lotto.lottoapp.core.components

import PhoneMaskVisualTransformation
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.ui.theme.Inter


@Composable
fun CustomInputField(
    fieldName: String,
    placeholderText: String,
    text: String?,
    onFieldValueChange: (String) -> Unit,
    isError: Boolean
) {

    var errorBorderColor = if (isError){
        Color.Red
    } else{
        Color.White
    }

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.zIndex(1000F)
    ) {
        Text(
            text = fieldName,
            color = Color.White,
            style = TextStyle(fontFamily = Inter),
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
        )

        if (text != null) {
            OutlinedTextField(
                modifier = Modifier
                    .background(
                        color = Color.White, shape = RoundedCornerShape(size = 12.dp)
                    )
                    .border(2.dp, color = errorBorderColor, shape = RoundedCornerShape(12.dp))
                    .width(320.dp)
                    .height(50.dp),
                value = text,
                onValueChange = { onFieldValueChange(it) },
                placeholder = {
                    Text(
                        text = placeholderText,
                        color = Color(92, 92, 92),
                        style = TextStyle(fontFamily = Inter)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                textStyle = TextStyle(color = Color(92, 92, 92), fontWeight = FontWeight(600)),
                singleLine = true,

                keyboardOptions = if (fieldName == "Birthday" || fieldName == "Phone") {
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword
                    )
                } else {
                    KeyboardOptions.Default
                },
                visualTransformation = when (fieldName) {

                    "Phone" -> {
                        PhoneMaskVisualTransformation()
                    }

                    else -> {
                        VisualTransformation.None
                    }
                }

            )
        }
    }

}





