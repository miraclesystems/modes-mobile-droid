package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_user_settings.*
import kotlinx.android.synthetic.main.fragment_user_settings_description.*
import kotlinx.android.synthetic.main.fragment_user_settings_description.view.*
import kotlinx.android.synthetic.main.layout_benefits.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserSettingsDescription.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsDescriptionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        print("debug")
    }

    override fun onResume() {
        super.onResume()

        var userSettingsActivity = activity as UserSettingsActivity

        userSettingsActivity.pager_page1.setBackgroundResource(R.drawable.selector1_higlighted)




        var selectedUserDescription =  PreferencesUtil.getValueString("USER_DESCRIPTION")

        when (selectedUserDescription) {
            "member" -> button_serviceMember.setSelected(true)
            "spouse" -> button_spouse.setSelected(true)
            "family" -> button_family.setSelected(true)
            "other" -> button_other.setSelected(true)
        }

    }


    override fun onStop() {
        super.onStop()
        var userSettingsActivity = activity as UserSettingsActivity

        if(userSettingsActivity.pager_page1 != null) {

            if (userSettingsActivity.page1Completed) {
                userSettingsActivity.pager_page1.setBackgroundResource(R.drawable.selector_checked)
            } else {
                userSettingsActivity.pager_page1.setBackgroundResource(R.drawable.selector1)

            }

        }
    }
    fun page2(description : String){


        PreferencesUtil.save("USER_DESCRIPTION", description)
        var userSettingsActivity = activity as UserSettingsActivity
        userSettingsActivity.page1Completed = true
        userSettingsActivity.loadPage2()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_user_settings_description, container, false)


        view.button_serviceMember.setOnClickListener { view ->
            button_serviceMember.setSelected(true)
            button_family.setSelected(false)
            button_spouse.setSelected(false)
            button_other.setSelected(false)
            page2("Service Member")
        }

        view.button_family.setOnClickListener { view ->
            button_family.setSelected(true)
            button_serviceMember.setSelected(false)
            button_spouse.setSelected(false)
            button_other.setSelected(false)
            page2("Family Member")

        }

        view.button_spouse.setOnClickListener { view ->
            button_spouse.setSelected(true)
            button_family.setSelected(false)
            button_serviceMember.setSelected(false)
            button_other.setSelected(false)
            page2("Military Spouse")

        }
        view.button_other.setOnClickListener{
            button_other.setSelected(true)
            button_family.setSelected(false)
            button_serviceMember.setSelected(false)
            button_spouse.setSelected(false)
            page2("Other")
        }


        /*
        view.button_page2.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage2()
        }

        view.button_page3.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            userSettingsActivity.loadPage3()
        }

         */

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserSettingsDescription.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserSettingsDescriptionFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}