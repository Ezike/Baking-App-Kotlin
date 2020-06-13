package com.example.eziketobenna.bakingapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BakingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
