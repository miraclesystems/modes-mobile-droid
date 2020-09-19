package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_benefit_detail.view.*
import kotlinx.android.synthetic.main.fragment_benefits_categories_list.*
import kotlinx.android.synthetic.main.fragment_benefits_list.view.*
import kotlinx.android.synthetic.main.layout_categories_gridview.view.*
import mil.dod.mcfp.mymilitaryonesource.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BenefitsCatagoriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BenefitsCategoriesListFragment : Fragment() {
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

    override fun onResume(){
        super.onResume()
        var benefitsActivity = activity as BenefitsActivity
        var adapter = CategoriesAdapter(benefitsActivity.applicationContext, benefitsActivity.viewModel.getCategories())

        listBenefits.adapter = adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View =  inflater.inflate(R.layout.fragment_benefits_categories_list, container, false)


        var benefitsActivity = activity as BenefitsActivity
        var adapter = CategoriesAdapter(benefitsActivity.applicationContext, benefitsActivity.viewModel.getCategories())

        view.listBenefits.adapter = adapter


        view.listBenefits.setOnItemClickListener { adapterView, view, i, l ->

            benefitsActivity.viewModel.selectedCategory = view.category.text.toString()
            //benefitsActivity.viewModel.selectedCategory = benefitsActivity.viewModel.getCategories()[i]
            benefitsActivity.loadBenefitsByCategory()

            var transaction = benefitsActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            benefitsActivity.supportFragmentManager.beginTransaction().remove(this).commit()

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
         * @return A new instance of fragment BenefitsCatagoriesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BenefitsCategoriesListFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    class CategoriesAdapter : BaseAdapter {
        var categoriesList =  listOf<String>()
        var context: Context? = null

        constructor(context: Context, list: List<String>) : super() {
            this.context = context
            this.categoriesList = list
        }

        override fun getCount(): Int {
            return categoriesList.size
        }

        override fun getItem(position: Int): Any {
            return categoriesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val category = this.categoriesList[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var grid = inflator.inflate(R.layout.layout_categories_gridview, parent, false)
            grid.category.text = category

            return grid
        }
    }
}