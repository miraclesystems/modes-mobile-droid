package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Benefits


class BenefitsModel {

    var value : String = "Hello World"
}


data class Benefit(

    var ID : Int?,
    var Category : String?,
    var Benefit : String?,
    var CategoriesPrimary : String?,
    var ShortDescription: String?,
    var LongDescription: String?,
    var ButtonText : String?,
    var BenefitLink : String?,
    var favorite: Boolean


)