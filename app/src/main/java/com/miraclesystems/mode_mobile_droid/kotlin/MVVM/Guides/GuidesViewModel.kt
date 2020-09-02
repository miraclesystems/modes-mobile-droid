package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Guides

import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils.ModesDb

class GuidesViewModel {


    var selectedCategory = ""
    var categories = listOf<String>("New to MilLife*",
            "Moving & Housing",
            "Deployment",
            "Transition Assistance",
            "Pandemic & Disaster Preparedness",
            "Survivors & Casualty Assistance",
            "Relationships",
            "Military Family Life",
            "Financial & Legal",
            "Personal Development & Employment",
            "Confidential Help & Support",
            "Health & Wellness*",
            "Recreation & Travel",
            "Shopping & Exclusive Offers",
            "National Guard & Reserve")




    fun getGuideCategories():List<String>{
        return categories
    }

    fun getAllGuides():MutableList<String>{

        var list = arrayListOf<String>()

        var results = ModesDb.getAllGuiides()


        while(results!!.moveToNext()){

            var id = results.getInt(results.getColumnIndex("ID"))
            var guide = results.getString(results.getColumnIndex("Guide"))

            list.add(guide)

        }

        return list
    }


    fun getGuiesByCategory(category : String): MutableList<String>{

        var list = arrayListOf<String>()

        var results = ModesDb.getGuidesByCategory(category)


        while(results!!.moveToNext()){

            var id = results.getInt(results.getColumnIndex("ID"))
            var guide = results.getString(results.getColumnIndex("Guide"))

            list.add(guide)

        }

        return list
    }


}