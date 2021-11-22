package com.uriolus.mvvbeers.presentation

import android.app.Application
import com.uriolus.mvvbeers.data.di.dataModule
import com.uriolus.mvvbeers.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(featureModule, dataModule)
        }
    }
}