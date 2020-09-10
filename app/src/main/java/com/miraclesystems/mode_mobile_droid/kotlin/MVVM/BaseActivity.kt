package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect.ConnectActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base.view.*
import kotlinx.android.synthetic.main.nav_header.view.*


open class BaseActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val prevNav = selectedNav
                val currentNav: Int = item.getItemId()

                if (currentNav == prevNav) return false
                when (item.getItemId()) {
                    R.id.navigation_home -> {
                        val ii = Intent(this@BaseActivity, HomeActivity::class.java)
                        startActivity(ii)
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.navigation_milife -> {
                        if (prevNav != R.id.navigation_home) finish()
                        val ii2 = Intent(this@BaseActivity, guidesActivity::class.java)
                        startActivity(ii2)
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.navigation_benefits -> {
                        if (prevNav != R.id.navigation_home) finish()
                        val ii3 = Intent(this@BaseActivity, BenefitsActivity::class.java)
                        startActivity(ii3)
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.navigation_favorites -> {
                        if (prevNav != R.id.navigation_home) finish()
                        val ii4 = Intent(this@BaseActivity, FavoritesActivity::class.java)
                        startActivity(ii4)
                        overridePendingTransition(0, 0)
                        return true
                    }
                    R.id.navigation_connect -> {
                        if (prevNav != R.id.navigation_home) finish()
                        val ii5 = Intent(this@BaseActivity, ConnectActivity::class.java)
                        startActivity(ii5)
                        overridePendingTransition(0, 0)
                        return true
                    }

                }
                return false
            }
        }

    var navigationView: BottomNavigationView? = null

    //sidemenu
    private lateinit var mDrawerLayout: DrawerLayout
    var navigationSideView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        navigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigationView!!.setItemIconTintList(null);
        navigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val navigationSideView: NavigationView = findViewById(R.id.navigationSideView)

        //Setup for Close Button inside the Right Menu
        val headerview = navigationSideView.getHeaderView(0)
        val insidemenuicon = headerview.inside_menu_icon
        insidemenuicon.setOnClickListener(){
            mDrawerLayout.closeDrawers()
        }

        navigationSideView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            Log.d("menuItem.Id: ", menuItem.itemId.toString())
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // home
                    Log.d("Clicked: ", "Home")
                    /*
                    if (navItemIndex != 0) {
                        navItemIndex = 0
                        loadHomeFragment()
                    }
                    */
                    val ii = Intent(this@BaseActivity, HomeActivity::class.java)
                    startActivity(ii)
                    overridePendingTransition(0, 0)

                }
                R.id.nav_Guides -> {
                    //Guides
                    Log.d("Clicked: ", "MilLife Guides")
                    /*
                    if (navItemIndex != 1) {
                        navItemIndex = 1
                        //dosomething
                    }
                    */

                    val ii2 = Intent(this@BaseActivity, guidesActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                    
                }
                R.id.nav_Connect -> {



                }

            }



            true
        }


        btn_SideMenu.setOnClickListener(){


            if ( mDrawerLayout.isDrawerOpen(GravityCompat.END) ) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT)
            }

        }










    }



    @RequiresApi(Build.VERSION_CODES.M)
    fun setContentLayout(layoutID: Int): View {
        val contentLayout = findViewById<View>(R.id.base_container) as FrameLayout
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(layoutID, contentLayout, true)
    }




    fun setSelected(optionID: Int) {
        navigationView!!.menu.findItem(optionID).isChecked = true
        getSharedPreferences(packageName, MODE_PRIVATE).edit().putInt("selectedNav", optionID)
            .commit()
    }

    val selectedNav: Int
        get() = getSharedPreferences(packageName, MODE_PRIVATE).getInt(
            "selectedNav",
            R.id.navigation_home
        )

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }





}