package com.lotto.lottoapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lotto.lottoapp.ui.theme.Inter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    fieldName: String,
    placeholderText: String,
    text: String?,
    onFieldValueChange: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier.zIndex(1000F)
    ) {
        Text(
            text = fieldName,
            color = Color.White,
            style = TextStyle(fontFamily = Inter),
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
        )

        Button(
            onClick = {
                showDialog = !showDialog
            }, modifier = Modifier
                .border(0.75.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .width(320.dp)
                .height(50.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = (if (datePickerState.selectedDateMillis == null) placeholderText else text)!!,
                    color = Color(92, 92, 92),
                    style = TextStyle(fontFamily = Inter)
                )
            }
        }
        Box(modifier = Modifier.background(color = Color.White)) {

            if (showDialog) {
                DatePickerDialog(
                    tonalElevation = 5000.dp,
                    onDismissRequest = {
                        showDialog = false
                        datePickerState.selectedDateMillis?.let { onFieldValueChange(it) }
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog = false
                            datePickerState.selectedDateMillis?.let { onFieldValueChange(it) }
                        }) {
                            Text("Ok")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }

}





