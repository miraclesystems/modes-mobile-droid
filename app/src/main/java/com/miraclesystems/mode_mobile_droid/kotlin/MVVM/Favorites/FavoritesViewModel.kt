package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites

import android.util.Log
import android.view.Display
import com.google.gson.Gson
import java.util.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings.LocationsModel
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.BASE_URL
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.ModesDb
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.PreferencesUtil



class FavoritesViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var locationsModel : LocationsModel? = null

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


}

