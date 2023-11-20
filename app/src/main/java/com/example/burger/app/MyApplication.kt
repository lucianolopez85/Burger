package com.example.burger.app

import android.app.Application
import com.example.burger.di.appModule
import com.example.burger.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                networkModule,
                appModule
            )
        }
    }
}