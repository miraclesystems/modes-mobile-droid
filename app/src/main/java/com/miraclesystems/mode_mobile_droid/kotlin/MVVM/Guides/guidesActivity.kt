package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_guides.*

class guidesActivity : AppCompatActivity() {

    var viewModel : GuidesViewModel = GuidesViewModel()

    fun loadGuidesListByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, GuieesListGuidesByCategoryFragement())

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
        ft.replace(R.id.fragment_container, GuidesCategoriesListFragment())

        ft.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guides)

        //Bottom Navigation
        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.setSelectedItemId(R.id.navigation_milife);
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
                /*
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
                 */

            }
            false

        }




        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, GuidesCategoriesListFragment())

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
            ft.replace(R.id.fragment_container, GuidesListFragment())

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
            ft.replace(R.id.fragment_container, GuidesCategoriesListFragment())

            ft.commit()

        }
    }
}