package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_user_settings_installation.*
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_user_settings_search_by_postal_code.*
import kotlinx.android.synthetic.main.fragment_user_settings_search_by_postal_code.searchList
import kotlinx.android.synthetic.main.fragment_user_setttings_search.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserSettingsSearchByPostalCode.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserSettingsSearchByPostalCode : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    var Me = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }






    }

    override fun onResume() {
        super.onResume()

        var userSettingsActivty = activity as UserSettingsActivity
        city.setText(userSettingsActivty.city)


        val adapter = ArrayAdapter(activity!!.applicationContext,
            R.layout.listview_item, userSettingsActivty.listNames)


        searchList.adapter = adapter

        searchList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ) {

                // value of item that is clicked
                val itemValue = searchList.getItemAtPosition(position) as String


                PreferencesUtil.save("installation", itemValue)

                Log.d("installation_name", PreferencesUtil.getValueString("installation").toString())



                var userSettingsActivity = activity as UserSettingsActivity
                userSettingsActivity.page2Completed = true
                var transaction = userSettingsActivity.supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                userSettingsActivity.supportFragmentManager.beginTransaction().remove(Me).commit()

                userSettingsActivity.loadPage3()

            }
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(
            R.layout.fragment_user_settings_search_by_postal_code,
            container,
            false
        )

        view.button_back.setOnClickListener { view ->

            var userSettingsActivity = activity as UserSettingsActivity
            var transaction = userSettingsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            userSettingsActivity.supportFragmentManager.beginTransaction().remove(this).commit()
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
         * @return A new instance of fragment UserSettingsSearchByPostalCode.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserSettingsSearchByPostalCode()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}