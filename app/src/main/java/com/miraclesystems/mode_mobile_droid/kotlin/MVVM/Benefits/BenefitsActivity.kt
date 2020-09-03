package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect.ConnectActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.BenefitsViewModel
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*




class BenefitsActivity : BaseActivity(), Observer {

    var viewModel = BenefitsViewModel()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_benefits)



        viewModel.addObserver(this)

        viewModel.getValue()
    }


    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_benefits);
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is BenefitsViewModel -> {
                if (arg is Boolean){

                    //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}