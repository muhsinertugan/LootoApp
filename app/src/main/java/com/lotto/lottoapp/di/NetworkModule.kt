package com.lotto.lottoapp.di


import com.lotto.lottoapp.model.data.draws.RecentDrawsService
import com.lotto.lottoapp.model.data.games.GameService
import com.lotto.lottoapp.model.data.games.GamesListService
import com.lotto.lottoapp.model.data.general.GeneralService
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CitiesAPIProvider {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        return Retrofit.Builder()
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://sea-turtle-app-qpyzd.ondigitalocean.app/api/v1/")
            .build()
    }

    @Provides
    @Singleton
    fun provideCityApiService(retrofit: Retrofit): GeneralService {
        return retrofit.create(GeneralService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRegisterService(retrofit: Retrofit): LoginRegisterService {
        return retrofit.create(LoginRegisterService::class.java)
    }

    @Provides
    @Singleton
    fun provideGamesListService(retrofit: Retrofit): GamesListService {
        return retrofit.create(GamesListService::class.java)
    }
    @Provides
    @Singleton
    fun provideRecentDrawsService(retrofit: Retrofit): RecentDrawsService {
        return retrofit.create(RecentDrawsService::class.java)
    }

    @Provides
    @Singleton
    fun provideGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }

}