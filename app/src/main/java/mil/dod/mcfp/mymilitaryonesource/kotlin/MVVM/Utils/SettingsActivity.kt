package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        button_back.setOnClickListener {

            super.onBackPressed()
        }

        txtEditPersonal.setOnClickListener {

            super.onBackPressed()
        }


    }


}