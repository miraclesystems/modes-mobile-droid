package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.UserSettings
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
    @SerializedName( "phone1")
    val phone1 : String?,
    @SerializedName("url1")
    val url1 : String?,
    @SerializedName("email_address1")
    val email_address1 : String?,
    @SerializedName ("address_line1")
    val address_line1 : String?,
    @SerializedName("city")
    val city : String?,
    @SerializedName("stat_id")
    val stat_id: String?,
    @SerializedName("postal_code")
    val postal_code: String?,
    @SerializedName("coun_id")
    val coun_id: String?,
    @SerializedName("name_seo")
    val name_seo: String?

)

data class LocationsInfo(

    @SerializedName("items")
    var items: List<LocationEmailInfo?>?
)
data class LocationEmailInfo(

    @SerializedName("CONT_ID")
    var CONT_ID: String?
)

data class LocationsByPostalCode(

    @SerializedName("items")
    var items: List<LocationByPostalCode?>?
)


data class LocationByPostalCode(

    @SerializedName("INST_ID")
    val INST_ID : Int?,
    @SerializedName("INST_NAME")
    val INST_NAME : String?,
    @SerializedName("SETY_NAME")
    val SETY_NAME : String?,
    @SerializedName("SETY_ID")
    val SETY_ID : Int?
)

