package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import com.miraclesystems.mode_mobile_droid.R.*
import com.miraclesystems.mode_mobile_droid.R.layout.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.WebviewActivity
import kotlinx.android.synthetic.main.activity_guides.*


class guidesActivity : BaseActivity() {


    var viewModel : GuidesViewModel = GuidesViewModel()
    var guide : Guide? = null

    fun loadInAppBrowser(url : String){


        /*
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
         */

        val intent = Intent(baseContext, WebviewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)


    }


    fun loadGuidesListByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(anim.slide_up, anim.slide_down);
        ft.replace(id.main_container, GuieesListGuidesByCategoryFragement())

        ft.commit()
    }

    fun loadCategories(){
        buttonCategories.setBackgroundResource(drawable.category_selector_box)
        buttonCategories.setTextColor(Color.WHITE)
        buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        buttonAll.setBackgroundResource(0)
        buttonAll.layoutParams.height = 100
        buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonAll.setTextColor(Color.parseColor("#194867"))

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(anim.slide_up, anim.slide_down);
        ft.replace(id.fragment_container, GuidesCategoriesListFragment())

        ft.commit()
    }

    fun loadGuideDetail(){


        guide = viewModel.getGuide()
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, GuidesDetailFragment())

        ft.commit()
    }



    fun loadGuideList(){

        guide = viewModel.getGuide()
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, GuidesListFragment())

        ft.commit()
    }

    fun loadBenefitDetail(selectedBenefit : String){

        val intent = Intent(this, BenefitsActivity::class.java)
        intent.putExtra("benefit", selectedBenefit)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.M)

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(activity_guides)

        var selectedGuide : String? = intent?.getStringExtra("guide")

        if(selectedGuide != null){
            viewModel.selectedGuide = selectedGuide
            loadGuideDetail()
        }

        else {
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesCategoriesListFragment())

            ft.commit()
        }

        buttonAll.setOnClickListener {

            Log.d("degug", "button all clicked")
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))

            buttonAll.setBackgroundResource(drawable.category_selector_box)
            buttonAll.setTextColor(Color.WHITE)
            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonCategories.setBackgroundResource(0)
            buttonCategories.layoutParams.height = 100
            buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonCategories.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesListFragment())

            ft.commit()




        }

        buttonCategories.setOnClickListener {
            Log.d("degug", "button categories clicked")

            buttonCategories.setBackgroundResource(drawable.category_selector_box)
            buttonCategories.setTextColor(Color.WHITE)
            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonAll.setBackgroundResource(0)
            buttonAll.layoutParams.height = 100
            buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonAll.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesCategoriesListFragment())

            ft.commit()

        }
    }

    override fun onResume() {
        super.onResume()
        setSelected(id.navigation_milife);


    }

}