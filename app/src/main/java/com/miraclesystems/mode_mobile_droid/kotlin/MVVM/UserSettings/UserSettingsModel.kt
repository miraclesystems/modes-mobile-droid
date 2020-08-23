package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.UserSettings
import com.google.gson.annotations.SerializedName;


data class LocationsModel (
    @SerializedName("next")
    val next: Pages?,
    @SerializedName("items")
    val items: List<Location?>?
)

data class Pages(
    @SerializedName("\$ref")
    val ref: String
)

data class Location(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("branch")
    val branch: String,
    @SerializedName("stat_id")
    val stat_id: String,
    @SerializedName("coun_id")
    val coun_id: String,
    @SerializedName("name_seo")
    val name_seo: String
)

