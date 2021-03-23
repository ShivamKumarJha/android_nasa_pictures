package com.shivamkumarjha.nasapictures.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.shivamkumarjha.nasapictures.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(applicationContext)
        }
    }
}