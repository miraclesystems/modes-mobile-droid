package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.UserSettingsActivity

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen, change accordingly
    private val SPLASH_TIME_OUT:Long = 2000
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