package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.util.Log
import mil.dod.mcfp.mymilitaryonesource.R
import java.util.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.ModesDb
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.PreferencesUtil


class HomeViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false



    var listSuggestedTopics =   mutableListOf<String>()
    var model = HomeModel()
    var audience = PreferencesUtil.getValueString("USER_DESCRIPTION")


    fun getSuggestedCards():List<HomePageCardModel>{

        var list =  mutableListOf<HomePageCardModel>()

        ///testing code
        var result = ModesDb.getBenefitsByAudience("spouse")

        //Log.d("resul",result!!.count.toString())

        while(result!!.moveToNext()){


            var id = result.getInt(result.getColumnIndex("ID"))
            var benefit = result.getString(result.getColumnIndex("Benefit"))

            //list.shuffle()

            list.add(HomePageCardModel(id, "BENEFITS", benefit, recommended = true))


        }



        result = ModesDb.getGuidesByAudience("spouse")
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var guide = result.getString(result.getColumnIndex("Guide"))

            list.add(HomePageCardModel(id, "MILLIFE GUIDES", guide, recommended = true))

        }

        list.add(HomePageCardModel(1, "CONNECT", "Speak with a consultant 24/7", recommended = false))
        list.add(HomePageCardModel(1, "ABOUT US", "Give us your feedback", recommended = false))

        /*
        var list = listOf(
            HomePageCardModel(1, "BENEFITS", "Accesss to Ancestry Lirary", recommended = true),
            HomePageCardModel(1, "MILLIFE GUIDES", "Moving in the Military", recommended = false),
            HomePageCardModel(1, "CONNECT", "Speak with a consultant 24/7", recommended = false),
            HomePageCardModel(1, "MILLIFE GUIDES", "Another Random Guide", recommended = false),
            HomePageCardModel(1, "ABOUT US", "Give us your feedback", recommended = false)
            )

         */

        return list

    }

    fun getBenefits(topic: String):MutableList<String>{
        var list = arrayListOf<String>()

        var result = ModesDb.getBenefitsByKeyWordSearch(topic)
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var benefit = result.getString(result.getColumnIndex("Benefit"))

            list.add(benefit)

        }


        return list

    }
    fun getGuides(topic: String): MutableList<String>{

        var list = arrayListOf<String>(
        )

        var result = ModesDb.getGuidesByKeyWordSearch(topic)
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var guide = result.getString(result.getColumnIndex("Guide"))
            list.add(guide)

        }

        return list
    }

    fun getGuideImages(topic: String): MutableList<String>{




        var list = arrayListOf<String>()

        var result = ModesDb.getGuidesByKeyWordSearch(topic)
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var guide = result.getString(result.getColumnIndex("Guide Image"))
            list.add(guide)

        }

        return list



    }

    fun getTopics(topic : String):MutableList<String>{

        var list = arrayListOf<String>()
        /*
        list.add("Moving")
        list.add("OCONUS")
        list.add("Housing")
        list.add("Household Goods")
        list.add("PCS")


         */


        var result = ModesDb.getGuidesByKeyWordSearch(topic)
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var keywords = result.getString(result.getColumnIndex("MilLife Guide Topic Keywords"))



            var keywords_array = keywords.split(',')

            for (keyword in keywords_array){

                if(keyword.contains(topic)) {
                    list.add(keyword.trim())
                }
            }
        }

        result = ModesDb.getBenefitsByKeyWordSearch(topic)
        while(result!!.moveToNext()){

            var id = result.getInt(result.getColumnIndex("ID"))
            var keywords = result.getString(result.getColumnIndex("Keywords"))



            var keywords_array = keywords.split(',')

            for (keyword in keywords_array){

                if(keyword.contains(topic)) {
                    list.add(keyword.trim())
                }
            }
        }



        if(list.count() < 1){
            return list
        }
        else {

            var unique = list.distinct() as MutableList
            return unique
        }


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

