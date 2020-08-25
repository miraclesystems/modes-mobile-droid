package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import java.util.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.*


class HomeViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var model = HomeModel()

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
