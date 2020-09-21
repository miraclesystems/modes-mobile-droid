package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.R.*
import mil.dod.mcfp.mymilitaryonesource.R.layout.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.BaseActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.WebviewActivity
import kotlinx.android.synthetic.main.activity_guides.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsCategoriesListFragment
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsListFragment


class guidesActivity : BaseActivity() {

    var standAlone : Boolean = false

    var viewModel : GuidesViewModel = GuidesViewModel()
    var guide : Guide? = null

    var categoriesShown = true

    var stopped = false


    override var myPageRefIndex = 1
    override fun onStop() {
        super.onStop()
        stopped = true
    }

    fun loadInAppBrowser(url : String){


        /*
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
         */

        val intent = Intent(baseContext, WebviewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)


    }


    fun loadGuidesListByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
        ft.replace(id.main_container, GuieesListGuidesByCategoryFragement())

        ft.commit()
    }

    fun loadCategories(){
        buttonCategories.setBackgroundResource(drawable.category_selector_box)
        buttonCategories.setTextColor(Color.WHITE)
        buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        buttonAll.setBackgroundResource(0)
        buttonAll.layoutParams.height = 100
        buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonAll.setTextColor(Color.parseColor("#194867"))

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
        ft.replace(id.fragment_container, GuidesCategoriesListFragment())

        ft.commit()
    }

    fun loadGuideDetail(){

        this.standAlone = intent?.getBooleanExtra("standAlone", false)!!
        guide = viewModel.getGuide()
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
       // ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, GuidesDetailFragment())

        ft.commit()
    }



    fun loadGuideList(){

        guide = viewModel.getGuide()

        buttonAll.setBackgroundResource(drawable.category_selector_box)


        buttonAll.setTextColor(Color.WHITE)
        buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // buttonCategories.setBackgroundResource(0)

        buttonCategories.setBackgroundResource(drawable.category_selector_box_u)

        buttonCategories.layoutParams.height = 140
        //buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonCategories.setTextColor(Color.parseColor("#194867"))


        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, GuidesListFragment())

        ft.commit()
    }

    fun loadBenefitDetail(selectedBenefit : String, standAlone : Boolean){

        val intent = Intent(this, BenefitsActivity::class.java)

        intent.putExtra("standAlone", standAlone)
        intent.putExtra("benefit", selectedBenefit)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.M)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(activity_guides)


        this.standAlone = intent?.getBooleanExtra("standAlone", false)!!
        var selectedGuide : String? = intent?.getStringExtra("guide")



        Log.d("selected_guide",selectedGuide.toString())

        if(selectedGuide != null){
            Log.d("selected_guide_if",selectedGuide.toString())
            viewModel.selectedGuide = selectedGuide
            loadGuideDetail()
        }

        else {
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesCategoriesListFragment())

            ft.commit()
        }

        buttonAll.setOnClickListener {

            Log.d("degug", "button all clicked")
            //buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))

            categoriesShown = false
            buttonAll.setBackgroundResource(drawable.category_selector_box)


            buttonAll.setTextColor(Color.WHITE)
            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            // buttonCategories.setBackgroundResource(0)

            buttonCategories.setBackgroundResource(drawable.category_selector_box_u)

            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            //buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonCategories.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesListFragment())

            ft.commit()




        }

        buttonCategories.setOnClickListener {
            Log.d("degug", "button categories clicked")

            categoriesShown = true
            buttonCategories.setBackgroundResource(drawable.category_selector_box)



            buttonCategories.setTextColor(Color.WHITE)
            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonAll.setBackgroundResource(drawable.category_selector_box_u)


            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            //buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonAll.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(id.fragment_container, GuidesCategoriesListFragment())

            ft.commit()

        }
    }




    fun forceResume(){
        onResume()
    }
    override fun onResume() {
        super.onResume()
        setSelected(id.navigation_milife);


        if(stopped){
            stopped = false
            return
        }
        if(viewModel.selectedGuide == null || viewModel.selectedGuide.length == 0 || categoriesShown ) {
            // Begin the transaction
            loadCategories()
        }
        else if (viewModel.selectedCategory != null && viewModel.selectedCategory.length != 0){
            loadGuidesListByCategory()
        }
        else{

           loadGuideList()
        }

    }

}