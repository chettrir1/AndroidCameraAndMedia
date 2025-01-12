package com.raju.androidcameraandmedia

import android.app.Application
import com.raju.androidcameraandmedia.di.videoPlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(videoPlayerModule)
        }
    }
}