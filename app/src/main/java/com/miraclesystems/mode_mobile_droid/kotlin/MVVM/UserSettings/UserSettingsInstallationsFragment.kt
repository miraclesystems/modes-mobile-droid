package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseViewModel
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_user_settings.*
import kotlinx.android.synthetic.main.fragment_user_settings_installation.*
import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.*
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_user_settings_installation.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsInstallationsFragment : Fragment(), Observer {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listNames = ArrayList<String>()


    var fusedLocationClient: FusedLocationProviderClient? = null



    override fun onResume() {
        super.onResume()

        var userSettingsActivity = activity as UserSettingsActivity
        if(userSettingsActivity.pager_page2 != null) {

            if (userSettingsActivity.page2Completed) {
                userSettingsActivity.pager_page2.setBackgroundResource(R.drawable.selector_checked)
                userSettingsActivity.pager_page2.setText("")
            } else {
                userSettingsActivity.pager_page2.setBackgroundResource(R.drawable.selector_highlighted)
                userSettingsActivity.pager_page2.setText("2")
            }

        }
    }


    override fun onStop() {
        super.onStop()
        var userSettingsActivity = activity as UserSettingsActivity
        if(userSettingsActivity.pager_page2 != null) {

            if (userSettingsActivity.page2Completed) {
                userSettingsActivity.pager_page2.setBackgroundResource(R.drawable.selector_checked)
                userSettingsActivity.pager_page2.setText("")
            } else {
                userSettingsActivity.pager_page2.setBackgroundResource(R.drawable.selector)
                userSettingsActivity.pager_page2.setText("2")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        fusedLocationClient = LocationServices.
        getFusedLocationProviderClient(activity as Activity)

        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.viewModel.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {

        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.viewModel.deleteObserver(this)
        when (o){
            is UserSettingsViewModel -> {
                    if (arg is Boolean){

                        var userSettingsActivity = activity as UserSettingsActivity
                        var model = userSettingsActivity.viewModel.model

                        var filteredList = model.items

                        listNames.clear()

                        for (item in filteredList!!) {
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




    val PERMISSION_ID = 42
    private fun checkPermission(vararg perm:String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(activity!!.applicationContext,it) ==
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
                    .setPositiveButton("OK", {id, v ->
                        ActivityCompat.requestPermissions(
                            activity!!, perm, PERMISSION_ID)
                    })
                    .setNegativeButton("No", {id, v -> })
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(activity!!, perm, PERMISSION_ID)
            }
            return false
        }
        return true
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =
            inflater.inflate(R.layout.fragment_user_settings_installation, container, false)


        var userSettingsActivity = activity as UserSettingsActivity



        fun getLastLocation() {
            fusedLocationClient!!.lastLocation
                .addOnCompleteListener(activity!!) { task ->
                    if (task.isSuccessful && task.result != null) {
                        Log.d(TAG, "getLastLocation")
                        val geocoder = Geocoder(activity!!.applicationContext, Locale.ENGLISH)
                        val addresses = geocoder.getFromLocation(task.result.latitude, task.result.longitude, 1)
                        userSettingsActivity.city = addresses[0].locality
                        val zipcode = addresses[0].postalCode
                        Log.d("debug", addresses[0].postalCode)

                        var userSettingsActivity = activity as UserSettingsActivity
                        userSettingsActivity.viewModel.getInstallationsByPostal(zipcode, 50)

                    } else {
                        Log.w(TAG, "getLastLocation:exception", task.exception)

                    }
                }
        }


        view.buttonUseLocation.setOnClickListener{ view ->

            if (checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
            ) {
                getLastLocation()
            }
        }

        view.button_location.setOnClickListener(){
            if (checkPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)
            ) {
                getLastLocation()
            }
        }

        //view.spinner.visibility = View.INVISIBLE
        view.search_installations.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->

        })



        view.search_installations.setOnClickListener{view ->
            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadSearch()
        }


        view.search_installations.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                //view.spinner.visibility = View.VISIBLE
                // Do something
                var userSettingsActivity = activity as UserSettingsActivity
                var model = userSettingsActivity.viewModel.model

                var filteredList =
                    model.items!!.filter { it!!.name!!.contains(view.search_installations.text.toString(), ignoreCase = true) }

                listNames.clear()

                for (item in filteredList) {
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
                Log.d("debug", "stop")

                 */
                true
            } else {
                false
            }
        }



        /*
        view.search_installations.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Fires right before text is changing
            }

            override fun afterTextChanged(s: Editable) {

                if(s.length >= 3) {

            }
        })


         */

        /*
        view.button_page1.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage1()
        }


        view.button_page3.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage3()
        }
        */

        return view
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_user_settings_installation.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserSettingsInstallationsFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}