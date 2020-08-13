package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import java.util.*


class BaseViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false

    var model = BaseModel()

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