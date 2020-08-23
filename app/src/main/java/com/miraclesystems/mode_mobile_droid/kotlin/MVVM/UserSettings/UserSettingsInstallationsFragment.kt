package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.fragment_user_settings_description.view.*
import kotlinx.android.synthetic.main.fragment_user_settings_installation.*
import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.*
import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.button_page1
import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.button_page2
import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.button_page3


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_user_settings_installation.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsInstallationsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var listNames = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =
            inflater.inflate(R.layout.fragment_user_settings_installation, container, false)



        view.spinner.visibility = View.INVISIBLE
        view.search_installations.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            /*
            if (hasFocus) {
                //got focus
                var userSettingSctivity = activity as UserSettingsActivity
                userSettingSctivity.loadPage3()
            } else {
                var userSettingSctivity = activity as UserSettingsActivity
                userSettingSctivity.loadPage3()
            }

            */
        })



        view.search_installations.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                //view.spinner.visibility = View.VISIBLE
                // Do something
                var userSettingsActivity = activity as UserSettingsActivity
                var model = userSettingsActivity.viewModel.model

                var filteredList =
                    model.items!!.filter { it!!.name.contains(view.search_installations.text.toString(), ignoreCase = true) }

                listNames.clear()

                for (item in filteredList) {
                    listNames.add(item!!.name)

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

        view.button_page1.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage1()
        }


        view.button_page3.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage3()
        }
        return view
    }

    companion object {
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