package com.lotto.lottoapp.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle, style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            )
        },
        text = {
            Text(
                text = dialogText, style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp

                )
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Close")
            }
        },

        )
}