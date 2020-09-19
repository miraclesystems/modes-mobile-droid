package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.BaseActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides.guidesActivity
import java.util.*




class FavoritesActivity : BaseActivity(), Observer {

    var viewModel = FavoritesViewModel()

    override var myPageRefIndex = 3

    fun openBrowser(urlString : String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(urlString)
        startActivity(openURL)
    }

    fun makeCall(phoneNumber : String){
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
        startActivity(intent)
    }

    fun loadGuideDetail(selectedGuide : String){

        val intent = Intent(this, guidesActivity::class.java)
        intent.putExtra("guide", selectedGuide)
        intent.putExtra("standAlone", true)
        startActivity(intent)
    }

    fun loadBenefitDetail(selectedBenefit : String){

        val intent = Intent(this, BenefitsActivity::class.java)
        intent.putExtra("benefit", selectedBenefit)
        intent.putExtra("standAlone", true)

        startActivity(intent)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_favorites)


        viewModel.addObserver(this)
        if(!viewModel.getInstallation()){
            // Begin the transaction
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            // Replace the contents of the container with the new fragment
            //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.layout1, FavoritesInstallationsNoneFragment())

            // Complete the changes added above
            ft.commit()
        }

        var ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.layout2, FavoritesGuidesListFragment())

        // Complete the changes added above
        ft.commit()


        ft = supportFragmentManager.beginTransaction()
        // Replace the contents of the container with the new fragment
        //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.layout3, FavoritesBenefitsListFragment())

        // Complete the changes added above
        ft.commit()






    }


    override fun onResume() {
        super.onResume()
        setSelected(R.id.navigation_favorites);
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is FavoritesViewModel -> {
                if (arg is Boolean){

                    viewModel.deleteObserver(this)

                    viewModel.getInstallationEmailId()
                    // Begin the transaction
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    // Replace the contents of the container with the new fragment
                    //ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    ft.replace(R.id.layout1, FavoritesInstallationsFragment())

                    // Complete the changes added above
                    ft.commit()
                   //this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}