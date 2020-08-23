package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings

import android.util.Log
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.*

import com.google.gson.Gson
import java.util.*

class UserSettingsViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false


    var model = LocationsModel(null, null)


    fun getInstallations(){

        var webServiceConnection = WebServiceConnector("https://pre.militaryonesource.mil/omos/pre-production/getInstallations/",this)
        webServiceConnection.get()
    }
    fun getValue(){
        dataLoaded = true
        setChanged() //Inherited from Observable()
        notifyObservers(dataLoaded)
    }

    override fun onSuccess(jsonString: String) {
        Log.d("debug", jsonString)

        val gson = Gson()

        val locationsModel : LocationsModel = gson.fromJson(jsonString, LocationsModel::class.java)

        if(locationsModel!=null)
        {
            model = locationsModel
        }
    }

    override fun onError(errorString: String) {

        Log.d("debug", errorString)
    }



}