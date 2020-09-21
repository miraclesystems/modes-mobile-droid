package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_home.view.*
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.fragment_home_view_topic.*
import kotlinx.android.synthetic.main.fragment_home_view_topic.view.*
import kotlinx.android.synthetic.main.fragment_home_view_topic.view.button_search
import kotlinx.android.synthetic.main.home_benefits_grid_item_layout.view.*
import kotlinx.android.synthetic.main.home_view_guides_card_layout.view.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.UserSettingsLoadingActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeViewTopicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeViewTopicFragment : Fragment() {
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

        var homeActivity = activity as HomeActivity
        textHeader.setText("Showing results for \"" + homeActivity.topic + "\"")

        super.onResume()



        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // JS: For some reason when this view is created a keyboard is shown, not sure why so I'm hiding it
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)


        // Inflate the layout for this fragment
       var view : View =  inflater.inflate(R.layout.fragment_home_view_topic, container, false)


        var homeViewActivity = activity as HomeActivity

        if(homeViewActivity.viewModel.getGuides(homeViewActivity.topic).toTypedArray().count() == 0){

            view.millifeHeader.visibility = View.INVISIBLE
            view.millifeIcon.visibility = View.INVISIBLE
            view.listGuides.layoutParams.height = 0
            view.millifeHeader.layoutParams.height = 0
            view.millifeIcon.layoutParams.height = 0


        }
        else {
            val adapter = HomeViewGuidesListAdapter(
                homeViewActivity,
                homeViewActivity.viewModel.getGuides(homeViewActivity.topic).toTypedArray(),
                homeViewActivity.viewModel.getGuideImages(homeViewActivity.topic).toTypedArray()
            )
            view.listGuides.adapter = adapter
            view.listGuides.setOnItemClickListener() { adapterView, view, position, id ->
                val itemAtPos = adapterView.getItemAtPosition(position)
                val itemIdAtPos = adapterView.getItemIdAtPosition(position)

                homeViewActivity.loadGuideDetail(view.name.text.toString(), true)
                //Toast.makeText(homeViewActivity.applicationContext, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
            }
        }



        view.gridBenefits.adapter = HomeViewBenefitsListAdapter(homeViewActivity, homeViewActivity.viewModel.getBenefits(homeViewActivity.topic).toTypedArray())
        view.gridBenefits.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)


            homeViewActivity.loadBenefitDetail(view.title.text.toString())
            //Toast.makeText(homeViewActivity.applicationContext, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }



        view.button_search.setOnClickListener {

            var homeActivity = activity as HomeActivity
            var transaction = homeActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            homeActivity.supportFragmentManager.beginTransaction().remove(this).commit()
            homeViewActivity.loadSearch()
        }


        view.button_call.setOnClickListener {
            homeViewActivity.callSupport()
        }


        view.button_onesource.setOnClickListener {
            val url = "https://www.militaryonesource.mil/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
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
         * @return A new instance of fragment HomeViewTopicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeViewTopicFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}