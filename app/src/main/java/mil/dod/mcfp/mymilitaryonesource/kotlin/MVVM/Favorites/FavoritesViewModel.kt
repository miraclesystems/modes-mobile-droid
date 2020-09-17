package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites

import android.util.Log
import com.google.gson.Gson
import java.util.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.LocationsInfo
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings.LocationsModel
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.BASE_URL
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.ModesDb
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil



class FavoritesViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false
    var gettingLocationInfo = false
    var emailIdLoaded = false
    var gettingEmailId = false

    var emailId : String = ""

    var locationsModel : LocationsModel? = null


    fun getInstallationEmailId(){
        //https://apps.pre.militaryonesource.mil/omos/pre-production/getInstallationContactsbyDirectory/3145/1

        gettingEmailId = true

        val id = PreferencesUtil.getValueString("installation")
        val urlString =  BASE_URL + "/getInstallationContactsbyDirectory/"+ id +"/1"
        val webserviceConnector = WebServiceConnector(urlString, this )
        webserviceConnector.get()


    }


    fun getInstallation(): Boolean{

        gettingLocationInfo = true
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

    fun getFavoriteBenefits():MutableList<FavoriteItem>{

        var results = ModesDb.getBenefitsFavorites()

        var list = mutableListOf<FavoriteItem>()

        while(results!!.moveToNext()){

            var item = FavoriteItem(null, null, null)
            item.ID = results.getInt(results.getColumnIndex("ID"))
            item.name = results.getString(results.getColumnIndex("Benefit"))
            item.type = "BENEFIT"

            list.add(item)

        }

        return list

    }

    fun getFavoriteGuides():MutableList<FavoriteItem>{

        var results = ModesDb.getGuideFavorites()

        var list = mutableListOf<FavoriteItem>()

        while(results!!.moveToNext()){

            var item = FavoriteItem(null, null, null)
            item.ID = results.getInt(results.getColumnIndex("ID"))
            item.name = results.getString(results.getColumnIndex("Guide"))
            item.type = "GUIDE"

            list.add(item)

        }

        return list

    }



    override fun onSuccess(jsonString: String) {


        if(gettingLocationInfo) {
            val gson = Gson()
            locationsModel = gson.fromJson(jsonString, LocationsModel::class.java)
            Log.d("debug", jsonString)
            dataLoaded = true
            setChanged()
            notifyObservers(dataLoaded)
            gettingLocationInfo = false
        }
        else{
            val gson = Gson()
            val locationsInfo  = gson.fromJson(jsonString, LocationsInfo::class.java)
            val locationEmailnfo = locationsInfo.items?.get(0)
            emailId = locationEmailnfo!!.CONT_ID.toString()
            Log.d("debug", jsonString)
            emailIdLoaded = true
            setChanged()
            notifyObservers(emailIdLoaded)
            gettingEmailId = false
        }
    }

    override fun onError(errorString: String) {
        Log.d("debug", errorString)
    }


}

