package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.UserSettingsSearchByPostalCode
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*




class FavoritesActivity : BaseActivity(), Observer {

    var viewModel = FavoritesViewModel()


    fun loadGuideDetail(selectedGuide : String){

        val intent = Intent(this, guidesActivity::class.java)
        intent.putExtra("guide", selectedGuide)
        startActivity(intent)
    }

    fun loadBenefitDetail(selectedBenefit : String){

        val intent = Intent(this, BenefitsActivity::class.java)
        intent.putExtra("benefit", selectedBenefit)
        startActivity(intent)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_favorites)


        viewModel.addObserver(this)
        if(!viewModel.getInstallation()){
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.layout1, FavoritesInstallationsNoneFragment())

            // Complete the changes added above
            ft.commit()
        }

        var ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.layout2, FavoritesGuidesListFragment())

        // Complete the changes added above
        ft.commit()


        ft = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.layout3, FavoritesBenefitsListFragment())

        // Complete the changes added above
        ft.commit()






    }


    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_favorites);
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is FavoritesViewModel -> {
                if (arg is Boolean){


                    // Begin the transaction
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    // Replace the contents of the container with the new fragment
                    ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    ft.replace(R.id.layout1, FavoritesInstallationsFragment())

                    // Complete the changes added above
                    ft.commit()
                   //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}