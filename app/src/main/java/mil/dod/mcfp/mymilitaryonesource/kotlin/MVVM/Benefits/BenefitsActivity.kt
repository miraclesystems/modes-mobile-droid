package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.BaseActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.BenefitsViewModel
import kotlinx.android.synthetic.main.activity_guides.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides.GuidesCategoriesListFragment
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides.GuidesListFragment
import java.util.*




class BenefitsActivity : BaseActivity(), Observer {

    var viewModel = BenefitsViewModel()
    var categoriesShown = true

    override var myPageRefIndex = 2
    var standAlone : Boolean = false
    var stopped = false

    fun loadBenefitsByCategory(){
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitsListByCategoryFragment())

        ft.commit()
    }

    fun loadCategories(){
        buttonCategories.setBackgroundResource(R.drawable.category_selector_box)
        buttonCategories.setTextColor(Color.WHITE)
        buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        buttonAll.setBackgroundResource(0)
        buttonAll.layoutParams.height = 100
        buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
        buttonAll.setTextColor(Color.parseColor("#194867"))

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

        ft.commit()
    }

    fun loadBenefitDetail(){

        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.main_container, BenefitDetailFragment())

        ft.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_benefits)
        categoriesShown = true

        this.standAlone = intent?.getBooleanExtra("standAlone", false)!!
        var selectedBenefit : String? = intent?.getStringExtra("benefit")

        if(selectedBenefit != null){
            viewModel.selectedBenefit = selectedBenefit
            loadBenefitDetail()
        }

        else {
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())
            ft.commit()
        }


        buttonAll.setOnClickListener {

            categoriesShown = false


            buttonAll.setBackgroundResource(R.drawable.category_selector_box)


            buttonAll.setTextColor(Color.WHITE)
            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            // buttonCategories.setBackgroundResource(0)

            buttonCategories.setBackgroundResource(R.drawable.category_selector_box_u)

            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            //buttonCategories.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonCategories.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsListFragment())

            ft.commit()




        }

        buttonCategories.setOnClickListener {
            Log.d("degug", "button categories clicked")

            categoriesShown = true
            buttonCategories.setBackgroundResource(R.drawable.category_selector_box)



            buttonCategories.setTextColor(Color.WHITE)
            buttonCategories.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            buttonAll.setBackgroundResource(R.drawable.category_selector_box_u)


            buttonAll.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            //buttonAll.setBackgroundColor(Color.parseColor("#D6DDE2"))
            buttonAll.setTextColor(Color.parseColor("#194867"))

            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
            ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())

            ft.commit()

        }

    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)

    }


    override fun onStop() {
        super.onStop()
        stopped = true
    }
    fun forceResume(){
        onResume()
    }
    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_benefits);


        if(stopped)
        {
            return
        }
        if(!standAlone){

            if (viewModel.selectedBenefit == null || viewModel.selectedBenefit.length == 0 || categoriesShown) {
                // Begin the transaction
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                // Replace the contents of the container with the new fragment
                //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                ft.replace(R.id.fragment_container, BenefitsCategoriesListFragment())
                ft.commit()
            } else {

                // Begin the transaction
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                // Replace the contents of the container with the new fragment
                //ft.setCustomAnimations(anim.slide_up, anim.slide_down);
                ft.replace(R.id.fragment_container, BenefitsListFragment())

                ft.commit()
            }
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is BenefitsViewModel -> {
                if (arg is Boolean){

                    //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}