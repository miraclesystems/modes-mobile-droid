package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM

import android.app.Application
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.*


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




        //PreferencesUtil.init(this.applicationContext)
        // get teh database
        var dbHelper = ActsDbHelper(this.applicationContext)
        var db = dbHelper.readableDatabase

        ModesDb.setHelper(dbHelper)

        print("debug")
    }
}