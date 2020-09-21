package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_guides_detail.*
import kotlinx.android.synthetic.main.fragment_guides_detail.view.*
import kotlinx.android.synthetic.main.layout_benefits.view.*
import kotlinx.android.synthetic.main.layout_guides_list.view.*
import kotlinx.android.synthetic.main.layout_guides_list.view.image
import kotlinx.android.synthetic.main.listview_item.view.*
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.HomeActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.UserSettingsLoadingActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.DynamicListHeight
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.ModesDb
import java.io.InputStream
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GuidesDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GuidesDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var model : Guide? = null

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
        var view : View = inflater.inflate(R.layout.fragment_guides_detail, container, false)

        var guidesActivity = activity as guidesActivity
        model = guidesActivity.viewModel.getGuide()

        if(model!!.favorite!!){
            view.button_favorite.setBackgroundResource(R.drawable.favorite_selected)
        }
        else{
            view.button_favorite.setBackgroundResource(R.drawable.favorite_unselected)
        }



        // get input stream
        // get input stream

        try {
            val ims: InputStream = guidesActivity.getAssets()
                .open("large-images/" + model!!.GuideImage + "-1000x500.jpg")
            // load image as Drawable
            // load image as Drawable
            val d =
                Drawable.createFromStream(ims, null)
            // set image to ImageView
            // set image to ImageView
            view.image.setImageDrawable(d)
            ims.close()
        }
        catch (e : Exception){

        }




        view.guide_name.text = model!!.Guide
        view.over_view.text = model!!.Overview
        view.guide_header.text = model!!.GuideHeader

        var listArticles = mutableListOf<Article>()
        for (x in 0 until 4){

            when(x){
                0->{
                    var article = Article(model!!.Article1Text, model!!.Article1URL, model!!.Article1Image!! )
                    listArticles.add(0, article)
                }
                1->{
                    var article = Article(model!!.Article2Text, model!!.Article2URL, model!!.Article2Image!! )
                    listArticles.add(1, article)
                }
                2->{
                    var article = Article(model!!.Article3Text, model!!.Article3URL, model!!.Article3Image!! )
                    listArticles.add(2, article)
                }
                3->{
                    var article = Article(model!!.Article4Text, model!!.Article4URL, model!!.Article4Image!! )
                    listArticles.add(3, article)
                }
            }
        }


        view.articles_header.text = model!!.ArticleHeader
        view.button_more_articles.text = model!!.MoreArticlesText
        view.button_more_articles.setContentDescription("${model!!.MoreArticlesText} opening in external browser ")

        model!!.listArticles = listArticles
        var articlesListAdapter = ArticlesListAdapter(guidesActivity.applicationContext, model!!.listArticles!!)
        view.listArticles.adapter = articlesListAdapter

        view.listArticles.setOnItemClickListener { adapterView, view, i, l ->

            var url = model!!.listArticles?.get(i)?.url
            if (url != null) {
                guidesActivity.loadInAppBrowser(url)
            }

        }


        view.button_more_benefits.text  = model!!.MoreBenefitsText
        var benefitsAdapter = BenefitsAdapter(guidesActivity.applicationContext, model!!.listRelatedBenefits!!)

        view.listBenefits.adapter = benefitsAdapter
        DynamicListHeight.setListViewHeightBasedOnChildren(view.listBenefits)


        var relatedWebsitesAdapter = RelatedWebsitesAdapter(guidesActivity.applicationContext, model!!.RelatedWebsitesText!!)
        view.listWebsites.adapter = relatedWebsitesAdapter



        view.expertsHeader.text = model!!.ExpertsHeader
        var expertsAdapter = ExpertsAdapters(guidesActivity.applicationContext, model!!.ExpertsText!!)
        view.listExperts.adapter = expertsAdapter


        view.hereareafew.text = model!!.ExpertsHeader1
        view.button_more_articles.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(model!!.MoreArticlesURL)
            startActivity(intent)

        }

        view.button_more_benefits.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(model!!.MoreBenefitsURL)
            startActivity(intent)
        }

        view.listWebsites.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(model!!.RelatedWebsitesURL?.get(i)?.trim())
            startActivity(intent)
        }

        view.listBenefits.setOnItemClickListener { adapterView, view, i, l ->

            var benefit = model?.listRelatedBenefits?.get(i)?.benefit
            guidesActivity.loadBenefitDetail(benefit!!, true)

        }

        view.button_back.setOnClickListener {

            //var HomeActivity = activity as HomeActivity

            if(!guidesActivity.standAlone){
                //guidesActivity.loadCategories()
                var transaction = guidesActivity.supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                guidesActivity.supportFragmentManager.beginTransaction().remove(this).commit()
                guidesActivity.forceResume()
            }
            else{

                getActivity()!!.finish()


            }


            //guidesActivity.supportFragmentManager.beginTransaction().remove(this).commit()
            //guidesActivity.loadGuideList()


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

            ModesDb.setGuideFavorite(favorite, model!!.ID!!)
        }

        view.button_call.setOnClickListener {

            try {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:8003429647"))
                startActivity(intent)

            } catch (e: Exception) {
                e.printStackTrace()

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
         * @return A new instance of fragment GuidesDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuidesDetailFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class ArticlesListAdapter : BaseAdapter {
        var articles =  listOf<Article>()
        var context: Context? = null

        constructor(context: Context, list: List<Article>) : super() {
            this.context = context
            this.articles = list
        }

        override fun getCount(): Int {
            return articles.size
        }

        override fun getItem(position: Int): Any {
            return articles[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val article = this.articles[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_articles_list, parent, false)
            item.guide.text = article.name

            try {
                val ims: InputStream = this.context!!.getAssets()
                    .open("small-images/" + article.image + "-200x200.jpg")
                // load image as Drawable
                // load image as Drawable
                val d =
                    Drawable.createFromStream(ims, null)
                // set image to ImageView
                // set image to ImageView
                item.image.setImageDrawable(d)
                ims.close()
            }
            catch (e : Exception){
                Log.d("debug ", e.localizedMessage)
            }

            return item
        }
    }

    class BenefitsAdapter : BaseAdapter {
        var benefits =  listOf<RelatedBenefit>()
        var context: Context? = null

        constructor(context: Context, list: List<RelatedBenefit>) : super() {
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
            item.Title.text = benefit.benefit
            item.description.text = benefit.description

            return item
        }
    }


    class RelatedWebsitesAdapter : BaseAdapter {
        var titles =  listOf<String>()
        var context: Context? = null

        constructor(context: Context, list: List<String>) : super() {
            this.context = context
            this.titles = list
        }

        override fun getCount(): Int {
            return titles.size
        }

        override fun getItem(position: Int): Any {
            return titles[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val title = this.titles[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_related_websites, parent, false)
            item.Title.paintFlags = item.Title.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            item.Title.text = title

            return item
        }
    }

    class ExpertsAdapters : BaseAdapter{
        var titles =  listOf<String>()
        var context: Context? = null

        constructor(context: Context, list: List<String>) : super() {
            this.context = context
            this.titles = list
        }

        override fun getCount(): Int {
            return titles.size
        }

        override fun getItem(position: Int): Any {
            return titles[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val title = this.titles[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var item = inflator.inflate(R.layout.layout_experts_text_items, parent, false)

            if(position == 0){
                item.label.setTypeface(null, Typeface.BOLD)
            }
            item.label.text = title


            return item
        }
    }
}