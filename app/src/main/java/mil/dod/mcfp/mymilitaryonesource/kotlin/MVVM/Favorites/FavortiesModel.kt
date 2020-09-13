package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Favorites


class FavortiesModel {

    var value : String = "Hello World"
}

data class FavoriteItem(
    var ID : Int?,
    var name : String?,
    var type : String?
)