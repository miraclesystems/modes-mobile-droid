package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings
import com.google.gson.annotations.SerializedName;

class UserSettingsModel {

    data class LocationsModel (
        @SerializedName("Pages")  val next: Pages? = null,
        @SerializedName("Location") val items: List<Location?>? = null
    )

    data class Pages(
        @SerializedName("\$ref")
        val ref: String
    )

    data class Location(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("branch") val branch: String,
        @SerializedName("stat_id") val stat_id: String,
        @SerializedName("coun_id") val coun_id: String,
        @SerializedName("name_seo") val name_seo: String
    )


    data class BranchesModel(
        @SerializedName("branches") var branches: List<String> = listOf("Army", "Navy", "Air Force", "Marines")
    )

}

