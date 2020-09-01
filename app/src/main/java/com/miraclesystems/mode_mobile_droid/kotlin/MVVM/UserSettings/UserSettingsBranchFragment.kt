package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_user_settings.*
import kotlinx.android.synthetic.main.fragment_user_settings_branch.*
import kotlinx.android.synthetic.main.fragment_user_settings_branch.view.*

import kotlinx.android.synthetic.main.fragment_user_settings_installation.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserSettingsBranch.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsBranchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()

        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.button_skip.setText("Done")


        if(userSettingsActivity.pager_page3 != null) {


            userSettingsActivity.pager_page3.setBackgroundResource(R.drawable.selector3_highlighted)
        }

    }


    override fun onStop() {var userSettingsActivity = activity as UserSettingsActivity
        super.onStop()

        userSettingsActivity.button_skip.setText(R.string.skip_question)
        if(userSettingsActivity.pager_page3 != null) {

            if (userSettingsActivity.page3Completed) {
                userSettingsActivity.pager_page3.setBackgroundResource(R.drawable.selector_checked)
            } else {
                userSettingsActivity.pager_page3.setBackgroundResource(R.drawable.selector3)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_user_settings_branch, container, false)

        var branches : Array<String> = arrayOf("Select a Branch", "Army", "Marine Corps", "Navy", "Air-Force", "Coast Guard", "National Guard and reserve")
        // Initializing an ArrayAdapter


        view.branch.setOnClickListener {
            branch_spinner.visibility = View.VISIBLE
            branch_spinner.performClick()
        }

        view.branch_spinner.adapter = ArrayAdapter<String>(activity!!.applicationContext, android.R.layout.simple_list_item_1, branches)

        view.branch_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                branch_spinner.visibility = View.INVISIBLE

                if(branch != null) {
                    branch.setText(branches[position])


                    var userSettingsActivity = activity as UserSettingsActivity

                    if(branches[position] != "Select a Branch" ) {
                        Log.d(branches[position],"debugpos")

                        userSettingsActivity.page3Completed = true
                        PreferencesUtil.save("branch", branches[position])
                    }

                }

            }

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
         * @return A new instance of fragment UserSettingsBranch.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserSettingsBranchFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}