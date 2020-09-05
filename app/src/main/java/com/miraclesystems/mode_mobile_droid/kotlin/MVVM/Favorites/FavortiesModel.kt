package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Favorites

import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.WebServiceConnectorDelegate
import java.util.*


class FavortiesModel {

    var value : String = "Hello World"
}

data class FavoriteItem(
    var ID : Int?,
    var name : String?,
    var type : String?
)