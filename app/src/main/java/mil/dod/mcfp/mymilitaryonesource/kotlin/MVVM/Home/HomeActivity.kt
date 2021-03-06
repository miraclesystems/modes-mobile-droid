package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.BaseActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides.guidesActivity
import java.util.*


class HomeActivity : BaseActivity(), Observer {

    //var standAlone : Boolean = false

    var viewModel = HomeViewModel()

    var topic = ""

    var Me = this

    var reloadData = false


    override var myPageRefIndex = 0


    @RequiresApi(Build.VERSION_CODES.M)
    fun callSupport(){
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "800-342-9647"))
        startActivity(intent)


    }
    fun loadGuideDetail(selectedGuide: String, standAlone: Boolean){

        val intent = Intent(this, guidesActivity::class.java)
        intent.putExtra("standAlone", standAlone)
        intent.putExtra("guide", selectedGuide)
        startActivity(intent)

    }
    fun loadBenefitDetail(selectedBenefit: String, standAlone: Boolean){
        val intent = Intent(this, BenefitsActivity::class.java)
        intent.putExtra("standAlone", standAlone)
        intent.putExtra("benefit", selectedBenefit)
        startActivity(intent)
    }
    fun loadViewTopic() {
        // Begin the transaction
        // Begin the transaction
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment

        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.fragment_container, HomeViewTopicFragment())

        ft.commit()

    }

    fun loadSearch() {

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        // Replace the contents of the container with the new fragment


        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.container, HomeSearchFragment())


        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit()


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_home);

        val bundle: Bundle? = intent.extras
        reloadData = intent.getBooleanExtra("ReloadData", false)

        button_search.setOnClickListener {
            // Begin the transaction
            // Begin the transaction


            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            // Replace the contents of the container with the new fragment


            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);



            ft.replace(R.id.container, HomeSearchFragment())
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.addToBackStack(null)


            ft.commit()


        }


        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        list_recycler_view.layoutManager = mLayoutManager
        list_recycler_view.itemAnimator = DefaultItemAnimator()
        list_recycler_view.adapter  = HomeViewCardsListAdapter(viewModel.getSuggestedCards(), Me) as RecyclerView.Adapter<*>
        viewModel.addObserver(this)


    }









    override fun onResume() {
      super.onResume()
        setSelected(R.id.navigation_home);

    }




    fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is HomeViewModel -> {
                if (arg is Boolean) {


                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}