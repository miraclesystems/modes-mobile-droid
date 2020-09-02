package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import java.util.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.*


class FavoritesViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var model = FavortiesModel()

    fun getValue(){
        dataLoaded = true
        setChanged() //Inherited from Observable()
        notifyObservers(dataLoaded)
    }

    override fun onSuccess(jsonString: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorString: String) {
        TODO("Not yet implemented")
    }


}

