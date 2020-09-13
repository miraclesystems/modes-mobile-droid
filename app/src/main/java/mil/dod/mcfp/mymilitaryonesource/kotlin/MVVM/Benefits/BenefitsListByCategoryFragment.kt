package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import mil.dod.mcfp.mymilitaryonesource.R

import kotlinx.android.synthetic.main.fragment_benefits_llst_by_category.view.*
import kotlinx.android.synthetic.main.layout_benefits.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BenefitsListByCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BenefitsListByCategoryFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(R.layout.fragment_benefits_llst_by_category, container, false)

        var benefitsActivity = activity as BenefitsActivity


        view.category_name.text = benefitsActivity.viewModel.selectedCategory
        var adapter = BenefitsListAdapter(benefitsActivity.applicationContext, benefitsActivity.viewModel.getBenifitsByCategory())
        view.listBenefits.adapter = adapter



        view.button_back.setOnClickListener {

            var transaction = benefitsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            benefitsActivity.supportFragmentManager.beginTransaction().remove(this).commit()

            benefitsActivity.loadCategories()

        }

        view.listBenefits.setOnItemClickListener { adapterView, view, i, l ->

            benefitsActivity.viewModel.selectedBenefit = benefitsActivity.viewModel.getAllBenefits()[i].Benefit!!
            var transaction = benefitsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            benefitsActivity.supportFragmentManager.beginTransaction().remove(this).commit()

            benefitsActivity.loadBenefitDetail()
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
         * @return A new instance of fragment BenefitsListByCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BenefitsListByCategoryFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class BenefitsListAdapter : BaseAdapter {
        var benefits =  listOf<Benefit>()
        var context: Context? = null

        constructor(context: Context, list: List<Benefit>) : super() {
            this.context = context
            this.benefits = list
        }

        override fun getCount(): Int {
            return benefits.size
        }

        override fun getItem(position: Int): Any {
            return benefits[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val benefit = this.benefits[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_benefits, parent, false)
            item.Title.text = benefit.Benefit
            item.description.text = benefit.ShortDescription

            return item
        }
    }
}