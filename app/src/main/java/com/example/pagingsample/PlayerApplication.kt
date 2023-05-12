package com.example.pagingsample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PlayerApplication :Application() {

    override fun onCreate() {
        super.onCreate()
    }
}