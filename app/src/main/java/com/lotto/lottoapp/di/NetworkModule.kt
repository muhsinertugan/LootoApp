package com.lotto.lottoapp.di


import com.lotto.lottoapp.model.data.balance.BalanceService
import com.lotto.lottoapp.model.data.draws.RecentDrawsService
import com.lotto.lottoapp.model.data.games.GameService
import com.lotto.lottoapp.model.data.games.GamesListService
import com.lotto.lottoapp.model.data.general.GeneralService
import com.lotto.lottoapp.model.data.loginRegister.LoginRegisterService
import com.lotto.lottoapp.model.data.profile.ProfileService
import com.lotto.lottoapp.model.data.tickets.TicketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



object ENDPOINTS{
    const val BASE_URL = "https://sea-turtle-app-qpyzd.ondigitalocean.app/api/v1/"
    const val GAMES_URL = "games"
    const val DRAWS_URL= "draws/recents"
    const val CITIES_URL = "cities"
    const val AUTH_LOGIN_URL="auth/login/email"
    const val OTP_LOGIN_URL="auth/login/email/otp"
    const val AUTH_REGISTER_URL="auth/register/email"
    const val OTP_REGISTER_URL="auth/register/email/otp"
    const val PROFILE_URL = "profile/user"
    const val TICKETS_URL="tickets"
    const val BALANCE_URL="balance"
    const val BALANCE_ADD_URL="balance/add"
}


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        return Retrofit.Builder()
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ENDPOINTS.BASE_URL)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GeneralServiceModule {

    @Provides
    @Singleton
    fun provideGeneralService(retrofit: Retrofit): GeneralService {
        return retrofit.create(GeneralService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object LoginRegisterServiceModule {

    @Provides
    @Singleton
    fun provideLoginRegisterService(retrofit: Retrofit): LoginRegisterService {
        return retrofit.create(LoginRegisterService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RecentDrawsServiceModule {

    @Provides
    @Singleton
    fun provideRecentDrawsService(retrofit: Retrofit): RecentDrawsService {
        return retrofit.create(RecentDrawsService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GamesListServiceModule {

    @Provides
    @Singleton
    fun provideGamesListService(retrofit: Retrofit): GamesListService {
        return retrofit.create(GamesListService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GameServiceModule {

    @Provides
    @Singleton
    fun provideGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object TicketServiceModule {

    @Provides
    @Singleton
    fun provideGameService(retrofit: Retrofit): TicketService {
        return retrofit.create(TicketService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ProfileServiceModule {
    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object BalanceServiceModule {
    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): BalanceService {
        return retrofit.create(BalanceService::class.java)
    }
}