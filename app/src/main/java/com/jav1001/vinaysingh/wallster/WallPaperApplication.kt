package com.jav1001.vinaysingh.wallster

import android.app.Application
class WallPaperApplication: Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }
}