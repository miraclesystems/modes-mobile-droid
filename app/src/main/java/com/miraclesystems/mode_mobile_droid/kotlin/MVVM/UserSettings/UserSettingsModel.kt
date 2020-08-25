package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings
import com.google.gson.annotations.SerializedName;


data class LocationsModel (
    @SerializedName("next")
    val next: Pages?,
    @SerializedName("items")
    var items: ArrayList<Location?>?
)

data class Pages(
    @SerializedName("\$ref")
    val ref: String?
)

data class Location(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("branch")
    val branch: String?,
    @SerializedName("stat_id")
    val stat_id: String?,
    @SerializedName("coun_id")
    val coun_id: String?,
    @SerializedName("name_seo")
    val name_seo: String?
)


data class LocationsByPostalCode(

    @SerializedName("items")
    var items: List<LocationByPostalCode?>?
)


data class LocationByPostalCode(

    @SerializedName("INST_ID")
    val INST_ID : Int?,
    @SerializedName("INST_NAME")
    val INST_NAME : String?
)

