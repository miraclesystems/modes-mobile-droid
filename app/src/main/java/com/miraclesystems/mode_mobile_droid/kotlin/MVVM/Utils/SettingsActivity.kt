package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.UserSettingsActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        button_back.setOnClickListener {

            super.onBackPressed()
        }

        txtEditPersonal.setOnClickListener {

            //super.onBackPressed()

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, UserSettingsActivity::class.java))
            }, 10)


        }


    }


}