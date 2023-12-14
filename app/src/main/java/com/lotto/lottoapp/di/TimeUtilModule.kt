package com.lotto.lottoapp.di

import com.lotto.lottoapp.utils.TimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TimeUtilModule {

    @Provides
    fun provideTimeUtil(): TimeUtil {
        return TimeUtil()
    }
}
