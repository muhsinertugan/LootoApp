package com.lotto.lottoapp.ui.feature.game.components.ticketColumn

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lotto.lottoapp.ui.feature.game.GameScreenContract
import com.lotto.lottoapp.ui.theme.CustomGrayV2
import com.lotto.lottoapp.ui.theme.CustomPurple

@Composable
fun TicketColumn(column: GameScreenContract.Column){

Log.d("column", column.toString())
    column.column.selectedNumbers.map {columnNumbers ->
        val readyButtonColors = if(columnNumbers !== null ) CustomPurple else CustomGrayV2
        val selectedNumber = if (columnNumbers !== null) columnNumbers.toString() else ""
        Row(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(readyButtonColors),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = selectedNumber,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            )

        }
    }


}