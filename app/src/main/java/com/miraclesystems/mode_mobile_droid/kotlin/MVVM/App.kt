package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.R
import android.app.Application
import android.content.SharedPreferences
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.ActsDbHelper
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.BASE_URL
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.ModesDb
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PROD_BASE_URL


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




        // get teh database
        var dbHelper = ActsDbHelper(this.applicationContext)
        var db = dbHelper.readableDatabase

        ModesDb.setHelper(dbHelper)

        print("debug")
    }
}