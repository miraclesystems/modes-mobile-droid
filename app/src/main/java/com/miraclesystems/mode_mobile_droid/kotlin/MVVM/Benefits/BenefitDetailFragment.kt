package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.fragment_benefit_detail.view.*
import kotlinx.android.synthetic.main.fragment_benefit_detail.view.button_back
import kotlinx.android.synthetic.main.fragment_benefit_detail.view.button_favorite
import kotlinx.android.synthetic.main.fragment_benefit_detail.view.button_more_benefits
import kotlinx.android.synthetic.main.fragment_guides_detail.*
import kotlinx.android.synthetic.main.fragment_guides_detail.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BenefitDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BenefitDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var benefit : Benefit? = null
    var favorite = false

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
        var view : View =  inflater.inflate(R.layout.fragment_benefit_detail, container, false)

        var benefitsActivity = activity as BenefitsActivity
        benefit = benefitsActivity.viewModel.getSelectedBenefit()

        view.benefit_name.text = benefit!!.Benefit
        view.short_description.text = benefit!!.ShortDescription
        view.long_description.text = benefit!!.LongDescription
        view.button_more_benefits.text = benefit!!.ButtonText


        view.button_more_benefits.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(benefit!!.BenefitLink)
            startActivity(intent)
        }


        view.button_back.setOnClickListener {
            var transaction = benefitsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            benefitsActivity.supportFragmentManager.beginTransaction().remove(this).commit()

        }

        view.button_favorite.setOnClickListener {

            if(!favorite) {
                button_favorite.setBackgroundResource(R.drawable.favorite_selected)
                favorite = true
            }
            else{
                button_favorite.setBackgroundResource(R.drawable.favorite_unselected)
                favorite = false
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
         * @return A new instance of fragment BenefitDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BenefitDetailFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}