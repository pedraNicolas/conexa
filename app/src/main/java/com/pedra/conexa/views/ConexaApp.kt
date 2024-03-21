package com.pedra.conexa.views

import android.app.Application
import com.pedra.core.CoreModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConexaApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CoreModule.init(this)
    }
}