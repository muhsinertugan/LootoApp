package com.lotto.lottoapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lotto.lottoapp.navigation.Index
import com.lotto.lottoapp.ui.theme.LottoAppTheme
import com.lotto.lottoapp.utils.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoAppTheme {
                Index(sharedPreferencesUtil = SharedPreferencesUtil(context = this))
            }
        }
    }
}


