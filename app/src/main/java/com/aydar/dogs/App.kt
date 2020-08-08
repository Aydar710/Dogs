package com.aydar.dogs

import android.app.Application
import com.aydar.data.di.dataModule
import com.aydar.featuredoglist.di.dogListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                dataModule,
                dogListModule
            )
        }

        super.onCreate()
    }
}