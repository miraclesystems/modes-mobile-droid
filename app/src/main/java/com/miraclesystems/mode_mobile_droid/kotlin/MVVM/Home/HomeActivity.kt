package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits.BenefitsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites.FavoritesActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides.guidesActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*


class HomeActivity : AppCompatActivity(), Observer {


    var viewModel = HomeViewModel()

    var topic = ""

    var Me = this
    fun loadViewTopic() {
        // Begin the transaction
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment

        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, HomeViewTopicFragment())

        ft.commit()

    }

    fun loadSearch() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment

        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.container, HomeSearchFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Bottom Navigation
        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home-> {
                    val intent = Intent(this, HomeActivity::class.java)
                    it.setChecked(true)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_milife-> {
                    val intent = Intent(this, guidesActivity::class.java)
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



        button_search.setOnClickListener {
            // Begin the transaction
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            // Replace the contents of the container with the new fragment

            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.container, HomeSearchFragment())
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit()

        }


        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        list_recycler_view.layoutManager = mLayoutManager
        list_recycler_view.itemAnimator = DefaultItemAnimator()
        list_recycler_view.adapter  = HomeViewCardsListAdapter(viewModel.getSuggestedCards(), Me) as RecyclerView.Adapter<*>

        viewModel.addObserver(this)

       // viewModel.getValue()




    }

    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is HomeViewModel -> {
                if (arg is Boolean) {


                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}