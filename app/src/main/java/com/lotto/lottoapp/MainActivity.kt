package com.lotto.lottoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lotto.lottoapp.app.LottoApp
import com.lotto.lottoapp.ui.theme.LottoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoAppTheme {

                LottoApp()
            }
        }
    }
}



