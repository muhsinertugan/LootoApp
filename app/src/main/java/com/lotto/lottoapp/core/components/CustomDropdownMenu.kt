package com.lotto.lottoapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
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
import com.lotto.lottoapp.ui.feature.register.RegisterScreenContract
import com.lotto.lottoapp.ui.theme.Inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(state : RegisterScreenContract.State, fieldName: String, placeholderText: String){
    var expanded by remember { mutableStateOf(false) }
    var cityNameText by remember { mutableStateOf("") }

Column( verticalArrangement = Arrangement.Center, modifier = Modifier.zIndex(1000F)) {
    Text(text = fieldName, color = Color.White, style = TextStyle(fontFamily = Inter), modifier = Modifier.padding(vertical =  10.dp, horizontal = 5.dp))

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded } ) {
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .border(0.75.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
            .width(320.dp)
            .height(50.dp)
            .background(color = Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
           Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxSize()) {
               Text(text = placeholderText)
           }
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            state.cities?.map { city ->
                DropdownMenuItem(
                    text = { Text(city.name) },
                    onClick = {
                        cityNameText = city.name
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
}