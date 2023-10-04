package com.lotto.lottoapp.di


import com.lotto.lottoapp.model.data.GeneralService
import com.lotto.lottoapp.model.data.LoginRegisterService
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

}