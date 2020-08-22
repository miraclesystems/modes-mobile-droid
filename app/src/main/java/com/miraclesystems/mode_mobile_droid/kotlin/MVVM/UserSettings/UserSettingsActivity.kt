package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.miraclesystems.mode_mobile_droid.R


class UserSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)


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
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
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
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_placeholder, UserSettingsBranchFragment())
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()
    }
}