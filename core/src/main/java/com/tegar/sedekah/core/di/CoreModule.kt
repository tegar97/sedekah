package com.tegar.sedekah.core.di

import androidx.room.Room
import com.tegar.sedekah.core.data.local.LocalDataSource
import com.tegar.sedekah.core.data.local.room.SedekahDatabase
import com.tegar.sedekah.core.data.remote.RemoteDataSource
import com.tegar.sedekah.core.data.remote.network.ApiService
import com.tegar.sedekah.core.data.repository.CampaignRepository
import com.tegar.sedekah.core.domain.repository.ICampaignRepository
import com.tegar.sedekah.core.utils.AppExecutors
import com.tegar.sedekah.core.data.local.prefence.SettingPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.tegar.sedekah.core.data.repository.AuthRepository
import com.tegar.sedekah.core.domain.repository.IAuthRepository
import com.tegar.sedekah.core.data.local.prefence.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor

val databaseModule = module {
    factory { get<SedekahDatabase>().campaignDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            SedekahDatabase::class.java, "sedekah.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    factory<Interceptor> {
        Interceptor { chain ->
            val req = chain.request()

            val token = runBlocking {
                get<IAuthRepository>().getSession().first().token
            }

            val requestHeaders = if (token?.isNotBlank() == true) {
                req.newBuilder()
                    .addHeader("Authorization", token)
                    .build()
            } else {
                req
            }
            chain.proceed(requestHeaders)
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(get<Interceptor>())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.4:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val settingPreferencesModule = module {
    single {
        SettingPreferences(androidContext().dataStore)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICampaignRepository> {
        CampaignRepository(
            get(),
            get(),
            get()
        )
    }
    single<IAuthRepository> {
        AuthRepository(get() , androidContext().dataStore)

    }
}