package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Settings

import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import mil.dod.mcfp.mymilitaryonesource.R
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites.FavoritesInstallationsFragment
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites.FavoritesViewModel
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.LocationsModel
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.BASE_URL
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.WebServiceConnector
import java.util.*

import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.WebServiceConnectorDelegate

class SettingsViewModel  : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var locationsModel : LocationsModel? = null

    override fun onSuccess(jsonString: String) {

        val gson = Gson()
        locationsModel =  gson.fromJson(jsonString, LocationsModel::class.java)
        Log.d("debug", jsonString)
        dataLoaded = true
        setChanged()
        notifyObservers(dataLoaded)
    }

    override fun onError(errorString: String) {
        Log.d("debug", errorString)
    }


    fun getInstallation(): Boolean{

        var retval = false

        val id = PreferencesUtil.getValueString("installation")
        if(id == null){
            return false
        }
        else {

            val urlString =  BASE_URL + "/getInstallationInfo/" + id
            val webserviceConnector = WebServiceConnector(urlString, this )
            webserviceConnector.get()

            return true
        }
    }



}