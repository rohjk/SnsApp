package com.jake.bucketplace.snsapp

import android.app.Application
import com.jake.bucketplace.snsapp.di.AppComponent
import com.jake.bucketplace.snsapp.di.DaggerAppComponent

class SnsApplication: Application() {

    val appComponent : AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}