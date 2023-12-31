package com.lotto.lottoapp.ui.feature.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lotto.lottoapp.model.response.draw.Draw
import com.lotto.lottoapp.utils.CurrencyFormatVisualTransformation
import com.lotto.lottoapp.utils.TimeUtil

@Composable
fun RecentDraws(recentDrawsState: Draw) {

    val time = TimeUtil()
    val currencyFormatVisualTransformation = CurrencyFormatVisualTransformation()

    Box(
        modifier = Modifier
            .border(
                width = 0.dp, color = Color.Black, shape = RoundedCornerShape(size = 20.dp)
            )
            .width(360.dp)
            .height(210.dp)
        //.background(Color.Black)
    ) {

        AsyncImage(
            model = recentDrawsState.game.image,
            contentDescription = recentDrawsState.game.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterEnd)
                .padding(12.dp)
        ) {
            Text(
                text = time.convertDateFormat(recentDrawsState.createdAt), style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Text(
                    text = "${
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 24.sp)) {
                                append(
                                    currencyFormatVisualTransformation.filter(
                                        AnnotatedString(
                                            recentDrawsState.game.prize.toString()
                                        )
                                    ).text
                                )
                            }
                        }
                    } cr.", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Text(
                    text = "30-36-23-33-44-55", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}

