package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect.ConnectActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.BenefitsViewModel
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_guides.*
import java.util.*




class BenefitsActivity : BaseActivity(), Observer {

    var viewModel = BenefitsViewModel()

    fun loadBenefitsByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitsListByCategoryFragment())

        ft.commit()
    }

    fun loadCategories(){
        buttonCategories.setBackgroundResource(R.drawable.category_selector_box)
        buttonCategories.setTextColor(Color.WHITE)
        buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        buttonAll.setBackgroundResource(0)
        buttonAll.layoutParams.height = 100
        buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonAll.setTextColor(Color.parseColor("#194867"))

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

        ft.commit()
    }

    fun loadBenefitDetail(){

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitDetailFragment())

        ft.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_benefits)

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())
        ft.commit()


        buttonAll.setOnClickListener {

            Log.d("degug", "button all clicked")
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))

            buttonAll.setBackgroundResource(R.drawable.category_selector_box)
            buttonAll.setTextColor(Color.WHITE)
            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonCategories.setBackgroundResource(0)
            buttonCategories.layoutParams.height = 100
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonCategories.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsListFragment())

            ft.commit()




        }

        buttonCategories.setOnClickListener {
            Log.d("degug", "button categories clicked")

            buttonCategories.setBackgroundResource(R.drawable.category_selector_box)
            buttonCategories.setTextColor(Color.WHITE)
            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonAll.setBackgroundResource(0)
            buttonAll.layoutParams.height = 100
            buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonAll.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

            ft.commit()

        }


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