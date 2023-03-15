package com.yukaida.exampleapplication

import android.app.Application
import android.content.Context

class MyApp : Application() {

    companion object {
        private lateinit var apContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        apContext = this
    }


}