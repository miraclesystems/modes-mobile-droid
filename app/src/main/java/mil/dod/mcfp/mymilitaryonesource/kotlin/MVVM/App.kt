package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.*


class App : Application() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

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


        FirebaseApp.initializeApp(this.applicationContext)
        firebaseAnalytics = Firebase.analytics


        firebaseAnalytics.setUserProperty("buildType", "debug")
        //PreferencesUtil.init(this.applicationContext)
        // get teh database
        var dbHelper = ActsDbHelper(this.applicationContext)
        var db = dbHelper.readableDatabase

        ModesDb.setHelper(dbHelper)

        print("debug")
    }
}