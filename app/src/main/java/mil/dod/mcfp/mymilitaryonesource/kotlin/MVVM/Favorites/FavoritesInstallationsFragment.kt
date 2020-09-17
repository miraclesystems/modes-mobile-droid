package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorites_installations.*
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.fragment_favorites_installations.view.*

import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesInstallationsFragment : Fragment(), Observer {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var emailUrl : String = ""
    var webSiteUrl : String = ""



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

        var favoritesActivity = activity as FavoritesActivity

        favoritesActivity.viewModel.addObserver(this)

        var view : View =  inflater.inflate(R.layout.fragment_favorites_installations, container, false)

        view.location_name.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.name
        view.location_type.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.branch
        view.address_1.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.address_line1
        view.address_2.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.city + ", " + favoritesActivity.viewModel.locationsModel?.items?.get(0)?.stat_id + " " +
                favoritesActivity.viewModel.locationsModel?.items?.get(0)?.postal_code

        emailUrl = "https://apps.militaryonesource.mil/MOS/f?p=AMS:5:0::::P5_APP_NAME,P5_MSG_TYPE,P5_EID:MilitaryINSTALLATIONS,Installation%20Address," + favoritesActivity.viewModel.locationsModel?.items?.get(0)?.id.toString()

        webSiteUrl = "https://installations.militaryonesource.mil/military-installation/" + favoritesActivity.viewModel.locationsModel?.items?.get(0)?.name_seo
        //view.email.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.email_address1
        //view.website.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.url1
        view.phone.text = favoritesActivity.viewModel.locationsModel?.items?.get(0)?.phone1


        view.email.setOnClickListener {
            favoritesActivity.openBrowser(emailUrl)
        }


        view.website.setOnClickListener {
            favoritesActivity.openBrowser(webSiteUrl)
        }

        view.phone.setOnClickListener {

            favoritesActivity.makeCall(phone.text.toString())
        }
        return view
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is FavoritesViewModel -> {
                if (arg is Boolean){
                    val favoritesActivity = this.activity as FavoritesActivity

                    Log.d("Debug", "set email")
                    emailUrl = "https://apps.militaryonesource.mil/MOS/f?p=AMS:5:0::::P5_APP_NAME,P5_MSG_TYPE,P5_EID:MilitaryINSTALLATIONS,Installation%20Address," + favoritesActivity.viewModel.emailId
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesInstallationsFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}