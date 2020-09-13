package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)



        button_back.setOnClickListener {

            super.onBackPressed()
        }


    }


}