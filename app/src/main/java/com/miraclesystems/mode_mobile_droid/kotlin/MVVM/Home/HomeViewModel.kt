package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import com.miraclesystems.mode_mobile_droid.R
import java.util.*
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.*
import kotlin.collections.ArrayList


class HomeViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false


    var listSuggestedTopics =   mutableListOf<String>()
    var model = HomeModel()


    fun getSuggestedCards():List<HomePageCardModel>{
        var list = listOf(
            HomePageCardModel(1, "BENEFITS", "Accesss to Ancestry Lirary", recommended = true),
            HomePageCardModel(1, "MILLIFE GUIDES", "Moving in the Military", recommended = false),
            HomePageCardModel(1, "CONNECT", "Speak with a consultant 24/7", recommended = false),
            HomePageCardModel(1, "MILLIFE GUIDES", "Another Random Guide", recommended = false),
            HomePageCardModel(1, "ABOUT US", "Give us your feedback", recommended = false)
            )

        return list

    }

    fun getBenefits():MutableList<String>{
        var list = arrayListOf<String>(
            "Temporary Logding Allowance - TLA",
            "Temporary Logding Expense - TLE",
            "Shipping Household Good",
            "Temporary Logding Allowance - TLA",
            "Temporary Logding Expense - TLE",
            "Shipping Household Good",
            "Temporary Logding Allowance - TLA",
            "Temporary Logding Expense - TLE",
            "Shipping Household Good"
        )
        return list

    }
    fun getGuides(): MutableList<String>{

        var list = arrayListOf<String>(
            "Moving in the Military",
            "OCONUS Moves",
            "Housing"
        )
        return list
    }

    fun getGuideImages(): MutableList<Int>{

        val imageId = arrayListOf<Int>(
            R.drawable.guides_image_placeholder,
            R.drawable.guides_image_placeholder,
            R.drawable.guides_image_placeholder
        )

        return imageId

    }

    fun getTopics(topic : String):MutableList<String>{

        var list = arrayListOf<String>()
        list.add("Moving")
        list.add("OCONUS")
        list.add("Housing")
        list.add("Household Goods")
        list.add("PCS")

        return list


    }

    fun getSuggestedTopic():MutableList<String>{

        listSuggestedTopics.add("COVID-19")
        listSuggestedTopics.add("Divorce")
        listSuggestedTopics.add("Relationships")
        listSuggestedTopics.add("Finances")
        listSuggestedTopics.add("MilTax")
        listSuggestedTopics.add("Parenting & Child Care")
        listSuggestedTopics.add("MWR Digital Library")
        listSuggestedTopics.add("PCS")
        listSuggestedTopics.add("Deployment")
        listSuggestedTopics.add("Survivor Benefit")
        listSuggestedTopics.add("National Guard")
        listSuggestedTopics.add("Counseling")
        listSuggestedTopics.add("MyCAA")


        return listSuggestedTopics

    }

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

