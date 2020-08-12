package com.aydar.dogs

import android.app.Application
import com.aydar.common.di.commonModule
import com.aydar.data.di.dataModule
import com.aydar.featurebreedphoto.di.breedPhotoModule
import com.aydar.featuredoglist.di.dogListModule
import com.aydar.featurefavouriephotos.di.favouritePhotosModule
import com.aydar.featurefavourites.di.favouritesModule
import com.aydar.featuresubbreeds.di.subbreedsModule
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
                dogListModule,
                breedPhotoModule,
                subbreedsModule,
                favouritesModule,
                favouritePhotosModule,
                commonModule
            )
        }

        super.onCreate()
    }
}