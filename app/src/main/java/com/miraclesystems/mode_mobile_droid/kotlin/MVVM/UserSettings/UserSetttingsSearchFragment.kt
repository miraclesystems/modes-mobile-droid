package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_user_setttings_search.*
import kotlinx.android.synthetic.main.fragment_user_setttings_search.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class UserSetttingsSearchFragment : Fragment(), Observer {
    private val hideHandler = Handler()

    private var listNames = ArrayList<String>()


    var fusedLocationClient: FusedLocationProviderClient? = null

    private var Me = this
    @Suppress("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        val flags =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        activity?.window?.decorView?.systemUiVisibility = flags
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        fullscreenContentControls?.visibility = View.VISIBLE
    }
    private var visible: Boolean = false
    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    private var dummyButton: Button? = null
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    val PERMISSION_ID = 42
    private fun checkPermission(vararg perm: String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(activity!!.applicationContext, it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if(perm.toList().any {
                    ActivityCompat.
                    shouldShowRequestPermissionRationale(activity!!, it)}
            ) {
                val dialog = AlertDialog.Builder(activity!!.applicationContext)
                    .setTitle("Permission")
                    .setMessage("Permission needed!")
                    .setPositiveButton("OK", { id, v ->
                        ActivityCompat.requestPermissions(
                            activity!!, perm, PERMISSION_ID
                        )
                    })
                    .setNegativeButton("No", { id, v -> })
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(activity!!, perm, PERMISSION_ID)
            }
            return false
        }
        return true
    }


    override fun update(o: Observable?, arg: Any?) {

        if(pbLoading != null) {
            pbLoading.visibility = ProgressBar.GONE
        }

        pbLoading.visibility = ProgressBar.VISIBLE
        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.viewModel.deleteObserver(this)
        when (o){
            is UserSettingsViewModel -> {
                if (arg is Boolean) {

                    var userSettingsActivity = activity as UserSettingsActivity
                    var model = userSettingsActivity.viewModel.model

                    var filteredList = model.items

                    listNames.clear()

                    for (item in filteredList!!) {
                        Log.d(item.toString(), "item")
                        listNames.add(item!!.name!!)


                    }

                    listNames.add(0, "")

                    // Initializing an ArrayAdapter
                    val adapter = ArrayAdapter(
                        userSettingsActivity.applicationContext, // Context
                        android.R.layout.simple_spinner_item, // Layout
                        listNames // Array
                    )

                    // Set the drop down view resource
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)


                    userSettingsActivity.listNames = listNames
                    userSettingsActivity.loadSearchByPostalCode()

                    /*
                    // Finally, data bind the spinner object with dapter
                    spinner.adapter = adapter;

                    // Set an on item selected listener for spinner object
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            //TODO("Not yet implemented")
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            // Display the selected item text on text view
                            search_installations.setText(listNames.get(position))
                            spinner.visibility = View.INVISIBLE
                        }


                    }


                    spinner.visibility = View.VISIBLE
                    spinner.performClick()
                    */

                    Log.d("debug", "stop")
                    true
                } else {
                    false
                }

            }
            else -> println(o?.javaClass.toString())
        }
    }


    fun getLastLocation() {


        pbLoading.visibility = ProgressBar.VISIBLE
        var userSettingsActivity = activity as UserSettingsActivity

        fusedLocationClient!!.lastLocation
            .addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful && task.result != null) {
                    Log.d(ContentValues.TAG, "getLastLocation")
                    val geocoder = Geocoder(activity!!.applicationContext, Locale.ENGLISH)
                    val addresses = geocoder.getFromLocation(
                        task.result.latitude,
                        task.result.longitude,
                        1
                    )
                    userSettingsActivity.city = addresses[0].locality
                    val zipcode = addresses[0].postalCode
                    Log.d("debug", addresses[0].postalCode)

                    var userSettingsActivity = activity as UserSettingsActivity
                    userSettingsActivity.viewModel.getInstallationsByPostal(zipcode, 25)

                } else {
                    Log.w(ContentValues.TAG, "getLastLocation:exception", task.exception)

                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view: View = inflater.inflate(R.layout.fragment_user_setttings_search, container, false)




        fusedLocationClient = LocationServices.
        getFusedLocationProviderClient(activity as Activity)
        listNames.clear()

        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.viewModel.addObserver(this)

        view.button_location.setOnClickListener(){

            if (checkPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                getLastLocation()
            }

        }


        view.button_back.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            var transaction = userSettingsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            userSettingsActivity.supportFragmentManager.beginTransaction().remove(this).commit()
        }

        view.search_text.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                //view.spinner.visibility = View.VISIBLE
                // Do something
                var userSettingsActivity = activity as UserSettingsActivity
                var model = userSettingsActivity.viewModel.model

                var filteredList =
                    model.items!!.filter { it!!.name!!.contains(
                        view.search_text.text.toString(),
                        ignoreCase = true
                    ) }

                listNames.clear()

                for (item in filteredList) {
                    listNames.add(item!!.name!!)

                }

                listNames.add(0, "")


                val adapter = ArrayAdapter(
                    activity!!.applicationContext,
                    R.layout.listview_item, listNames
                )


                view.searchList.adapter = adapter

                view.searchList.onItemClickListener = object : AdapterView.OnItemClickListener {

                    override fun onItemClick(
                        parent: AdapterView<*>, view: View,
                        position: Int, id: Long
                    ) {

                        // value of item that is clicked
                        val itemValue = searchList.getItemAtPosition(position) as String


                        PreferencesUtil.save("installation", itemValue)


                        var userSettingsActivity = activity as UserSettingsActivity
                        userSettingsActivity.page2Completed = true
                        var transaction = userSettingsActivity.supportFragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                        userSettingsActivity.supportFragmentManager.beginTransaction().remove(Me).commit()

                        userSettingsActivity.loadPage3()

                        /*
                        // Toast the values
                        Toast.makeText(
                            activity!!.applicationContext,
                            "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG
                        )
                            .show()

                         */
                    }
                }

                /*
                // Initializing an ArrayAdapter
                val adapter = ArrayAdapter(
                    userSettingsActivity.applicationContext, // Context
                    android.R.layout.simple_spinner_item, // Layout
                    listNames // Array
                )

                // Set the drop down view resource
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

                // Finally, data bind the spinner object with dapter
                spinner.adapter = adapter;

                // Set an on item selected listener for spinner object
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        //TODO("Not yet implemented")
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        // Display the selected item text on text view
                        search_installations.setText(listNames.get(position))
                        spinner.visibility = View.INVISIBLE
                    }


                }


                spinner.visibility = View.VISIBLE
                spinner.performClick()
                */

                Log.d("debug", "stop")
                true
            } else {
                false
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_text.setFocusable(true);
        search_text.setFocusableInTouchMode(true);
        search_text.requestFocus();
        showSoftKeyboard(search_text)


        visible = true
        fusedLocationClient = LocationServices.
        getFusedLocationProviderClient(activity as Activity)

    }


    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            search_text.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)

        }

    }

    override fun onResume() {
        super.onResume()



        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.viewModel.getInstallations()



        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }






    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Clear the systemUiVisibility flag
        activity?.window?.decorView?.systemUiVisibility = 0
        show()
    }

    override fun onDestroy() {
        super.onDestroy()
        dummyButton = null
        fullscreenContent = null
        fullscreenContentControls = null
    }

    private fun toggle() {
        if (visible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        fullscreenContentControls?.visibility = View.GONE
        visible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @Suppress("InlinedApi")
    private fun show() {
        // Show the system bar
        fullscreenContent?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        visible = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}