package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.gms.location.LocationServices
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.UserSettingsActivity
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_home_search.*
import kotlinx.android.synthetic.main.fragment_home_search.searchList
import kotlinx.android.synthetic.main.fragment_home_search.search_text
import kotlinx.android.synthetic.main.fragment_home_search.view.*
import kotlinx.android.synthetic.main.fragment_home_search.view.button_back
import kotlinx.android.synthetic.main.fragment_home_search.view.searchList
import kotlinx.android.synthetic.main.fragment_user_setttings_search.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var loadTopics = false
    var Me = this
    var selectedTopic = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        if(!loadTopics){
            sectionHeader.setText("SUGGESTED TOPICS")
        }
        super.onResume()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_home_search, container, false)

        view.button_back.setOnClickListener {
            var homeActivity = activity as HomeActivity
            var transaction = homeActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            homeActivity.supportFragmentManager.beginTransaction().remove(this).commit()
        }




        view.search_text.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.count() >= 3){
                    Log.d("dbug", "text entered -->" + s)

                    var topic = s.toString()
                    sectionHeader.setText("TOPICS RELATED TO \"" + topic + "\"")
                    selectedTopic = topic as String

                    var homeActivity = activity as HomeActivity
                    val adapter = ArrayAdapter(activity!!.applicationContext,
                        R.layout.listview_item, homeActivity.viewModel.getTopics(topic))


                    searchList.adapter = adapter

                    searchList.requestLayout()

                    loadTopics = true

                }
            }
        })

        var homeActivity = activity as HomeActivity
        val adapter = ArrayAdapter(activity!!.applicationContext,
            R.layout.listview_item, homeActivity.viewModel.getSuggestedTopic())


        view.searchList.adapter = adapter

        view.searchList.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long
            ){
                if(!loadTopics){
                    loadTopics = true
                    var topic = homeActivity.viewModel.getSuggestedTopic()[position]
                    sectionHeader.setText("TOPICS RELATED TO \"" + topic + "\"")
                    selectedTopic = topic

                    var homeActivity = activity as HomeActivity
                    val adapter = ArrayAdapter(activity!!.applicationContext,
                        R.layout.listview_item, homeActivity.viewModel.getTopics(topic))


                    searchList.adapter = adapter

                    searchList.requestLayout()
                }
                else{



                    var transaction = homeActivity.supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    homeActivity.supportFragmentManager.beginTransaction().remove(Me).commit()

                    var topic = homeActivity.viewModel.getTopics(selectedTopic)[position]
                    homeActivity.topic = topic
                    homeActivity.loadViewTopic()
                    loadTopics = false
                }


            }
        }






        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_text.setFocusableInTouchMode(true);
        search_text.requestFocus();
        showSoftKeyboard(search_text)
    }


    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeSearchFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}