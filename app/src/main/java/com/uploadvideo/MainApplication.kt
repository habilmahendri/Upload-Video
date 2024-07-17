package com.uploadvideo

import android.app.Application
import android.content.Context
import com.uploadvideo.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appComponent)
        }
    }
    companion object {
        var instance: MainApplication? = null
            private set
        val context: Context?
            get() = instance
    }
}