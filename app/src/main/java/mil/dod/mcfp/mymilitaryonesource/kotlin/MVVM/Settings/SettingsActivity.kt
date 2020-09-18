package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.activity_settings.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites.FavoritesInstallationsFragment
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites.FavoritesViewModel
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.UserSettingsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil
import java.util.*

class SettingsActivity : AppCompatActivity(), Observer {

    var settingsViewModel : SettingsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        txtBranch.setText(
            PreferencesUtil.getValueString(
                "branch"
            )
        )



        txtInstallation.setText(
            PreferencesUtil.getValueString(
                "installation_name"
            )
        )

        txtRole.setText(
            PreferencesUtil.getValueString(
                "USER_DESCRIPTION"
            )
        )

        button_back.setOnClickListener {

            super.onBackPressed()
        }

        txtEditPersonal.setOnClickListener {

            startActivity(Intent(this, UserSettingsActivity::class.java))
            super.onBackPressed()
        }


        settingsViewModel = SettingsViewModel()
        settingsViewModel?.addObserver(this)
        if(!settingsViewModel!!.getInstallation()){

            txtInstallation.setText("")
        }



    }


    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is SettingsViewModel -> {
                if (arg is Boolean){


                    txtInstallation.setText(settingsViewModel?.locationsModel?.items?.get(0)?.name)
                    // Begin the transaction

                }
            }
            else -> println(o?.javaClass.toString())
        }
    }


}