package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_home.*
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.HomeActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.HomeViewModel
import java.util.*

class UserSettingsLoadingActivity : AppCompatActivity(), Observer {

    private val LOADING_TIME:Long = 3000




        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            setContentView(R.layout.activity_usersettings_loading)

            val imageView: ImageView = findViewById(R.id.gif)
            Glide.with(this).load(R.drawable.modes_loading).into(imageView)

            Handler().postDelayed({
                startActivity(Intent(this, HomeActivity::class.java))
                // close this activity
                finish()
            }, LOADING_TIME)

        }


        override fun onResume() {
            super.onResume()
            //setSelected(R.id.navigation_home);

        }




        override fun update(o: Observable?, arg: Any?) {
            when (o){
                is HomeViewModel -> {
                    if (arg is Boolean) {


                    }
                }
                else -> println(o?.javaClass.toString())
            }
        }
    }


