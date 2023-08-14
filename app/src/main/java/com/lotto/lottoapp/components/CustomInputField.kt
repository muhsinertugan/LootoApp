package com.lotto.lottoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.R
import com.lotto.lottoapp.ui.theme.Inter
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat



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
            leadingIcon = {
                if (fieldName == "Email"){
                    Icon(
                        painter = painterResource(id = R.drawable.mail_icon),
                        contentDescription = "mail icon",
                        tint = Color.White,
                        modifier = Modifier.scale(0.7F)
                    )
                }
             },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,


        )
    }

 }

@ExperimentalMaterial3Api
@Composable
fun BDayPicker() {

        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString = simpleDateFormat.format(datePickerState.selectedDateMillis)
        val dateToString =  dateString.format(datePickerState.selectedDateMillis)

        Column(modifier = Modifier
            .fillMaxSize()
            .scale(0.8F)
            .zIndex(10F), verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.fillMaxSize()) {
                DatePicker(state = datePickerState, modifier = Modifier
                    .padding(16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .zIndex(2F)
                    .align(
                        Alignment.Center
                    ))
            }
        }
}




