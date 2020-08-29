package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.app.Application
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.BASE_URL
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PROD_BASE_URL
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.STAGING_BASE_URL

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : App {
            return instance as App
        }
    }

    override fun onCreate() {
        super.onCreate()

        // set the url base
        //BASE_URL = STAGING_BASE_URL
        BASE_URL = PROD_BASE_URL
    }
}