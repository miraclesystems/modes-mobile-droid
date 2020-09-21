package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import kotlinx.android.synthetic.main.activity_user_settings.*
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.HomeActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil
import java.util.*
import kotlin.collections.ArrayList


class UserSettingsActivity : AppCompatActivity(), Observer {

    public var city = ""
    var logo : ImageView? = null

    var listNames = ArrayList<String>()

    var page1Completed = false
    var page2Completed = false
    var page3Completed = false
    public var viewModel = UserSettingsViewModel()

    var currentLocation : Location? = null

    var pageNumber = 1

    private var locationManager : LocationManager? = null
    var locationListener : LocationListener? = null

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()

        resetFocus()

    }

    override fun onPostResume() {
        super.onPostResume()

        resetFocus()
        //imageView.requestFocus()
    }

    fun resetFocus(){
        /*
        imageView.isFocusable = true
        imageView.isFocusableInTouchMode = true
        imageView.requestFocus()
        imageView.requestFocusFromTouch()

         */
    }
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
                if(isLocationEnabled(locationManager!!))
                {
                    locationManager?.requestSingleUpdate(
                        LocationManager.GPS_PROVIDER,
                        locationListener!!,
                        null
                    );
                    //getLocationDetails()
                    Log.d("debug", "stop")
                }
                else
                {
                    Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }

            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        setContentView(R.layout.activity_user_settings)

// Create persistent LocationManager reference
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?



        locationListener = object : LocationListener {

            override fun onLocationChanged(p0: Location) {

                Log.d("debug", "location changed")
                currentLocation = p0
                locationManager?.removeUpdates(locationListener!!)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                Log.d("debug", "location changed")
            }

            override fun onProviderEnabled(provider: String) {
                Log.d("debug", "location changed")
            }

            override fun onProviderDisabled(provider: String) {
                Log.d("debug", "location changed")
            }
        }
        //locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER , 0L, 0f, locationListener!!)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 42)

        }
        else {
            locationManager?.requestSingleUpdate(
                LocationManager.GPS_PROVIDER,
                locationListener!!,
                null
            );
        }


        viewModel.getInstallations()
        viewModel.addObserver(this)


        val page=intent.getIntExtra("pageNumber", 0)
        //loadSearch()

        if(page == 2){
            loadPage2()
        }
        else{
            loadPage1()
        }


        imageView.setOnClickListener {

            // do nothing
        }

        button_back.setOnClickListener(){
            Log.d("debug", "onclick")
            pageNumber = pageNumber - 1
            if(pageNumber <= 0)
                pageNumber = 1

            when (pageNumber){
                1 -> loadPage1()
                2 -> loadPage2()
            }
        }

        button_skipAll.setOnClickListener(){
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(R.anim.slide_left, R.anim.slide_right);

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

        resetFocus()
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

        //resetFocus()
    }


    fun loadPage1(){

        pager_page1.setBackgroundResource(R.drawable.selector1_higlighted)

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

        resetFocus()
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

        resetFocus()
    }

    fun loadPage3(){
        pageNumber = 3


            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            // Replace the contents of the container with the new fragment
            ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
            //ft.replace(R.id.fragment_placeholder, UserSettingsBranchFragment())
            ft.replace(R.id.fragment_placeholder, UserSettingsBranchFragment())
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit()
            resetFocus()


    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is UserSettingsViewModel -> {
                if (arg is Boolean) {


                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}