package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.FavoritesViewModel
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*




class FavoritesActivity : AppCompatActivity(), Observer {

    var viewModel = FavoritesViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.setSelectedItemId(R.id.navigation_favorites);

        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){


                R.id.navigation_milife -> {
                    val intent = Intent(this, guidesActivity::class.java)
                    it.setChecked(true)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_home-> {
                    val intent = Intent(this, HomeActivity::class.java)
                    it.setChecked(true)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_benefits-> {
                    val intent = Intent(this, BenefitsActivity::class.java)
                    it.setChecked(true)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites-> {
                    val intent = Intent(this, FavoritesActivity::class.java)
                    it.setChecked(true)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }

        viewModel.addObserver(this)

        viewModel.getValue()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is FavoritesViewModel -> {
                if (arg is Boolean){

                    this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}