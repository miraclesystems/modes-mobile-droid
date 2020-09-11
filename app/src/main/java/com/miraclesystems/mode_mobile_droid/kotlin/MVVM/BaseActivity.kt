package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.HomeActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.postDelayed
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect.ConnectActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.AboutActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.SettingsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.WebviewActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base.view.*
import kotlinx.android.synthetic.main.activity_connect.*
import kotlinx.android.synthetic.main.drawer_menu_custom.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.inside_menu_icon
import kotlinx.android.synthetic.main.nav_header.view.*


open class BaseActivity : AppCompatActivity() {

    open var myPageRefIndex:Int = 0

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
        /*
        val headerview = navigationSideView.getHeaderView(0)
        val insidemenuicon = headerview.inside_menu_icon
        insidemenuicon.setOnClickListener(){
            mDrawerLayout.closeDrawers()
        }
        */



        //SideMenu Actions
        inside_menu_icon.setOnClickListener(){
            mDrawerLayout.closeDrawers()
        }

        val closeMenuDelay:Long = 250 //mS
        txtHome.setOnClickListener(){
            if (myPageRefIndex == 0) {
                //do not do it
                Log.d("Clicked: ", "Home, while on Home")
            } else {
                mDrawerLayout.closeDrawers()
                Handler(Looper.getMainLooper()).postDelayed({
                    val ii2 = Intent(this@BaseActivity, HomeActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }, closeMenuDelay)
            }
        }
        txtGuides.setOnClickListener(){
            if (myPageRefIndex == 1) {
                //do not do it
                Log.d("Clicked: ", "Guides, while on Guides")
            } else {
                mDrawerLayout.closeDrawers()
                Handler(Looper.getMainLooper()).postDelayed({
                    val ii2 = Intent(this@BaseActivity, guidesActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }, closeMenuDelay)
            }
        }
        txtBenefits.setOnClickListener(){
            if (myPageRefIndex == 2) {
                //do not do it
                Log.d("Clicked: ", "Benefits, while on Benefits")
            } else {
                mDrawerLayout.closeDrawers()
                Handler(Looper.getMainLooper()).postDelayed({
                    val ii2 = Intent(this@BaseActivity, BenefitsActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }, closeMenuDelay)
            }
        }
        txtFavorites.setOnClickListener(){
            if (myPageRefIndex == 3) {
                //do not do it
                Log.d("Clicked: ", "Favorites, while on Favorites")
            } else {
                mDrawerLayout.closeDrawers()
                Handler(Looper.getMainLooper()).postDelayed({
                    val ii2 = Intent(this@BaseActivity, FavoritesActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }, closeMenuDelay)
            }
        }
        txtConnect.setOnClickListener(){
            if (myPageRefIndex == 4) {
                //do not do it
                Log.d("Clicked: ", "Connect, while on Connect")
            } else {
                mDrawerLayout.closeDrawers()
                Handler(Looper.getMainLooper()).postDelayed({
                    val ii2 = Intent(this@BaseActivity, ConnectActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }, closeMenuDelay)
            }
        }
        txtAbout.setOnClickListener(){
            if (myPageRefIndex == 5) {
                //do not do it
                Log.d("Clicked: ", "About, while on About")
            } else {
                //mDrawerLayout.closeDrawers()
               // val ii2 = Intent(this@BaseActivity, AboutActivity::class.java)
               // startActivity(ii2)
               // overridePendingTransition(0, 0)
                val intent = Intent(baseContext, AboutActivity::class.java)
                startActivity(intent)

            }
        }
        txtSettings.setOnClickListener(){
            if (myPageRefIndex == 5) {
                //do not do it
                Log.d("Clicked: ", "Settings, while on Settings")
            } else {
                /*
                mDrawerLayout.closeDrawers()
                val ii2 = Intent(this@BaseActivity, ConnectActivity::class.java)
                startActivity(ii2)
                overridePendingTransition(0, 0)
                 */
                val intent = Intent(baseContext, SettingsActivity::class.java)
                //intent.putExtra("URL", url)
                startActivity(intent)
            }
        }




        txtFeedback.setOnClickListener(){
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("https://www.militaryonesource.mil/")
            startActivity(browserIntent)
        }




        //Military OneSource Network Actions
        btn_NetLink1.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.american_forces_travel))
            startActivity(browserIntent)
        }
        btn_NetLink2.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.blog_brigade))
            startActivity(browserIntent)
        }
        btn_NetLink3.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.efmp_and_me))
            startActivity(browserIntent)
        }
        btn_NetLink4.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.military_installations))
            startActivity(browserIntent)
        }
        btn_NetLink5.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.member_connect))
            startActivity(browserIntent)
        }
        btn_NetLink6.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.military_spouse))
            startActivity(browserIntent)
        }
        btn_NetLink7.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.millife_learning))
            startActivity(browserIntent)
        }
        btn_NetLink8.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.my_caa))
            startActivity(browserIntent)
        }
        btn_NetLink9.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.plan_my_move))
            startActivity(browserIntent)
        }
        btn_NetLink10.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.spouse_education_career))
            startActivity(browserIntent)
        }
        btn_NetLink11.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.education_directory))
            startActivity(browserIntent)
        }
        btn_NetLink12.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.military_state_policy_source))
            startActivity(browserIntent)
        }
        btn_NetLink13.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.military_onesource))
            startActivity(browserIntent)
        }
        btn_NetLink14.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.plan_my_deployment))
            startActivity(browserIntent)
        }



        //Social Media Links at Bottom
        btn_Facebook.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.facebook))
            startActivity(browserIntent)
        }
        btn_Twitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.twitter))
            startActivity(browserIntent)
        }
        btn_YouTube.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.youtube))
            startActivity(browserIntent)
        }
        btn_Instagram.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.instagram))
            startActivity(browserIntent)
        }
        btn_Pinterest.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse(getString(R.string.pinterest))
            startActivity(browserIntent)
        }






        /*
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
                    val ii2 = Intent(this@BaseActivity, guidesActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }
                R.id.nav_Benefits -> {
                    //Beneftis
                    Log.d("Clicked: ", "Benefits")
                    val ii2 = Intent(this@BaseActivity, BenefitsActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }
                R.id.nav_Favorites -> {
                    //Beneftis
                    Log.d("Clicked: ", "Favorites")
                    val ii2 = Intent(this@BaseActivity, FavoritesActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }
                R.id.nav_Connect -> {
                    //Beneftis
                    Log.d("Clicked: ", "Favorites")
                    val ii2 = Intent(this@BaseActivity, ConnectActivity::class.java)
                    startActivity(ii2)
                    overridePendingTransition(0, 0)
                }



            }



            true
        }
        */

        btn_SideMenu.setOnClickListener(){


            if ( mDrawerLayout.isDrawerOpen(GravityCompat.END) ) {
                mDrawerLayout.closeDrawer(Gravity.RIGHT)
            } else {
                mDrawerLayout.openDrawer(Gravity.RIGHT)
            }

        }

        @Override fun onClick(view: View) {
            Log.d("Clicked: ", "onClick")

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