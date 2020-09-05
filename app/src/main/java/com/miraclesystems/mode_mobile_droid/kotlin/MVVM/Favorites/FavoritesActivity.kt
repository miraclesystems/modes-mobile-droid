package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.FavoritesViewModel
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*




class FavoritesActivity : BaseActivity(), Observer {

    var viewModel = FavoritesViewModel()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_favorites)


        viewModel.addObserver(this)

        viewModel.getValue()
    }


    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_favorites);
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is FavoritesViewModel -> {
                if (arg is Boolean){

                   //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}