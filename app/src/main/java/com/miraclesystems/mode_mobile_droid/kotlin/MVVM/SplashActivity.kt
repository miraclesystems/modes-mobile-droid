package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.UserSettingsActivity

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen, change accordingly
    private val SPLASH_TIME_OUT:Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start BaseActivity

           // startActivity(Intent(this,BaseActivity::class.java))

            /// TODO: Check the user preferences to determine if UserSettings needs to presented

            startActivity(Intent(this, UserSettingsActivity::class.java))
            //startActivity(Intent(this, HomeActivity::class.java))

            //startActivity(Intent(this, guidesActivity::class.java))

            // close the splash screen activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}