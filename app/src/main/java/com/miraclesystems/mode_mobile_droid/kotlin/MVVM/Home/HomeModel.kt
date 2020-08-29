package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home


class HomeModel {

    var value : String = "Hello World"
}


data class HomePageCardModel(

    var id : Int?,
    var cardType : String?,
    var cardTitle : String?,
    var recommended : Boolean
)