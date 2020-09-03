package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import kotlinx.android.synthetic.main.activity_user_settings.*
import java.util.*
import kotlin.collections.ArrayList


class UserSettingsActivity : AppCompatActivity(), Observer {

    public var city = ""

    var listNames = ArrayList<String>()

    var page1Completed = false
    var page2Completed = false
    var page3Completed = false
    public var viewModel = UserSettingsViewModel()

    var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)

        viewModel.getInstallations()
        viewModel.addObserver(this)

        //loadSearch()
        loadPage1()


        button_back.setOnClickListener(){
            Log.d("debug", "onclick")
            pageNumber = pageNumber - 1
            if(pageNumber <= 0)
                pageNumber = 1

            when (pageNumber){
                1->loadPage1()
                2->loadPage2()
            }
        }

        button_skipAll.setOnClickListener(){
            startActivity(Intent(this, HomeActivity::class.java))
        }



        button_skip.setOnClickListener(){
            pageNumber = pageNumber + 1
            if(pageNumber > 3) {
                startActivity(Intent(this, HomeActivity::class.java))

            }
            else {
                when (pageNumber) {
                    1 -> loadPage1()
                    2 -> loadPage2()
                    3 -> loadPage3()

                }
            }
        }
    }


    fun loadSearchByPostalCode(){
        // Begin the transaction
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment

        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main, UserSettingsSearchByPostalCode())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }

    fun loadSearch(){

        // Begin the transaction
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment

        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main, UserSetttingsSearchFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }


    fun loadPage1(){

        pager_page1.setBackgroundResource( R.drawable.selector1_higlighted)

        pager_page2.setBackgroundResource(R.drawable.selector2)

        pageNumber = 1
        button_back.visibility = View.INVISIBLE
        // Begin the transaction
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_placeholder, UserSettingsDescriptionFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }

    fun loadPage2(){
        pager_page3.setBackgroundResource(R.drawable.selector3)

        pageNumber = 2
        button_back.visibility = View.VISIBLE
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_placeholder, UserSettingsInstallationsFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }

    fun loadPage3(){
        pageNumber = 3
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment
        ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
        ft.replace(R.id.fragment_placeholder, UserSettingsBranchFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is UserSettingsViewModel -> {
                if (arg is Boolean){


                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}