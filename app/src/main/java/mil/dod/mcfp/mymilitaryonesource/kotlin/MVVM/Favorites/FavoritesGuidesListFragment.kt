package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.ModesDb
import kotlinx.android.synthetic.main.fragment_favoirties_guides_list.view.*
import kotlinx.android.synthetic.main.layout_favorite_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesGuidesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesGuidesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var list: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun reloadList(){
        var favoritesActivity = activity as FavoritesActivity
        var adapter = GuidesFavoriteListAdapter(
            favoritesActivity.applicationContext,
            favoritesActivity.viewModel.getFavoriteGuides()
        )
        adapter.fragment = this
        list?.adapter  = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_favoirties_guides_list, container, false)

        var favoritesActivity = activity as FavoritesActivity
        var adapter = GuidesFavoriteListAdapter(favoritesActivity.applicationContext, favoritesActivity.viewModel.getFavoriteGuides())
        view.listFavorites.adapter = adapter
        adapter.fragment = this
        list = view.listFavorites


        view.listFavorites.setOnItemClickListener { adapterView, view, i, l ->


            var guide = favoritesActivity.viewModel.getFavoriteGuides()[i]
            favoritesActivity.loadGuideDetail(guide.name!!)

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
         * @return A new instance of fragment FavoritesGuidesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesGuidesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class GuidesFavoriteListAdapter : BaseAdapter {
        var items =  listOf<FavoriteItem>()
        var context: Context? = null
        var fragment: FavoritesGuidesListFragment? = null

        constructor(context: Context, list: List<FavoriteItem>) : super() {
            this.context = context
            this.items = list
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val title = this.items[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_favorite_item, parent, false)
            item.favorite_name.text = title.name



            item.button_favorite.setOnClickListener {


                ModesDb.setGuideFavorite(false, item.favorite_name.text as String)

                fragment?.reloadList()

                Log.d("debug", (item.favorite_name.text as String?)!!)
            }


            return item


            return item
        }
    }
}