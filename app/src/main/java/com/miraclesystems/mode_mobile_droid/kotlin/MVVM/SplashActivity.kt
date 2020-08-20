package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.miraclesystems.mode_mobile_droid.R

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen, change accordingly
    private val SPLASH_TIME_OUT:Long = 6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start BaseActivity

            startActivity(Intent(this,BaseActivity::class.java))
            // close the splash screen activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}