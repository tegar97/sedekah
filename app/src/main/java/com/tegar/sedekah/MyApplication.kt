package com.tegar.sedekah

import android.app.Application
import com.tegar.sedekah.core.di.databaseModule
import com.tegar.sedekah.core.di.networkModule
import com.tegar.sedekah.core.di.repositoryModule
import com.tegar.sedekah.core.di.settingPreferencesModule
import com.tegar.sedekah.di.useCaseModule
import com.tegar.sedekah.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(

                    databaseModule,
                    networkModule,
                    repositoryModule,
                    settingPreferencesModule,
                    useCaseModule,
                    viewModelModule,

                )
            )
        }
    }
}