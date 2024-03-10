package com.cs4520.assignment4

import android.app.Application
import com.cs4520.assignment4.data.AppContainer
import com.cs4520.assignment4.data.AppDataContainer

class MainApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}