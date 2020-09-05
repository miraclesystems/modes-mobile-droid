package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.UserSettingsActivity
import kotlinx.android.synthetic.main.fragment_guides_detail.*
import kotlinx.android.synthetic.main.fragment_guides_detail.view.*
import kotlinx.android.synthetic.main.layout_benefits.view.*
import kotlinx.android.synthetic.main.layout_guides_list.view.*
import kotlinx.android.synthetic.main.listview_item.view.*

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


        view.guide_name.text = model!!.Guide
        view.over_view.text = model!!.Overview
        view.guide_header.text = model!!.GuideHeader

        var listArticles = mutableListOf<Article>()
        for (x in 0 until 3){

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

        model!!.listArticles = listArticles
        var articlesListAdapter = ArticlesListAdapter(guidesActivity.applicationContext, model!!.listArticles!!)
        view.listArticles.adapter = articlesListAdapter

        view.listArticles.setOnItemClickListener { adapterView, view, i, l ->

            var url = model!!.listArticles?.get(i)?.url
            if (url != null) {
                guidesActivity.loadInAppBrowser(url)
            }

        }


        var benefitsAdapter = BenefitsAdapter(guidesActivity.applicationContext, model!!.listRelatedBenefits!!)
        view.listBenefits.adapter = benefitsAdapter

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
            intent.data = Uri.parse(model!!.RelatedWebsitesURL?.get(i))
            startActivity(intent)
        }

        view.listExperts.setOnItemClickListener { adapterView, view, i, l ->



        }

        view.button_back.setOnClickListener {
            var transaction = guidesActivity.supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            guidesActivity.supportFragmentManager.beginTransaction().remove(this).commit()

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
            var item = inflator.inflate(R.layout.layout_guides_list, parent, false)
            item.guide.text = article.name

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
            item.label.text = title


            return item
        }
    }
}