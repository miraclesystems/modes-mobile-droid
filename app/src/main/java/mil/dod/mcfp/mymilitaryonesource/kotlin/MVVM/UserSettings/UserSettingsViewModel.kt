package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings

import android.util.Log
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.*

import com.google.gson.Gson
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.BASE_URL
import java.util.*

class UserSettingsViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var loadingLocations = false
    var loadingLocationsByPostal = false

    var model = LocationsModel(null, null)



    fun getInstallationsByPostal(postalCode : String, distance : Int){

        val urlString =  BASE_URL + "/getInstallationContactsbyDistance/" + postalCode + "/" + distance
        val webserviceConnector = WebServiceConnector(urlString, this )
        webserviceConnector.get()

        loadingLocationsByPostal = true

    }


    fun getInstallations(){

        loadingLocations = true
        //loadingLocationsByPostal = false

        var webServiceConnection = WebServiceConnector(
            BASE_URL + "/getInstallations/",this)
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

        if(loadingLocations){
            loadingLocations = false

            val locationsModel : LocationsModel = gson.fromJson(jsonString, LocationsModel::class.java)
                model = locationsModel
            }
        else{
            loadingLocationsByPostal = false
            model = LocationsModel(null, null)
            model.items = ArrayList<Location?>()

           // val data = try JSONDecoder().decode(LocationsByPostalCode.self, from: JSONData
            val data : LocationsByPostalCode = gson.fromJson(jsonString, LocationsByPostalCode::class.java)
            for(item in data.items!!){
                var location = Location(null, null, null, null, null, null,
                    null, null, null, null, null, null)


                if(item!!.SETY_ID == 1 && item!!.SETY_NAME.equals(" Location")) {
                    location.id = item!!.INST_ID
                    location.name = item!!.INST_NAME

                    model.items!!.add(location)
                }


            }

            setChanged() //Inherited from Observable()
            notifyObservers(dataLoaded)
            dataLoaded = true

        }




    }

    override fun onError(errorString: String) {

        Log.d("debug", errorString)
    }



}