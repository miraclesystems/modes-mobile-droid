package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import java.util.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.Benefit
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsModel
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils.ModesDb


class BenefitsViewModel : Observable(), WebServiceConnectorDelegate {

    var dataLoaded = false



    var selectedCategory = ""
    var selectedBenefit = ""
    var model = BenefitsModel()

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



    fun getCategories(): MutableList<String>{


        var list = mutableListOf<String>()

        var results = ModesDb.getBenefitCategories()

        while(results!!.moveToNext()){


            var category = results.getString(results.getColumnIndex("Category"))

            list.add(category)

        }

        return list


/*
        var list = arrayListOf<String>("New to MilLife*",
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

        return list

 */
    }

    fun getAllBenefits(): MutableList<Benefit>{

        var list = mutableListOf<Benefit>()
        var results = ModesDb.getAllBenefits()

        while(results!!.moveToNext()){


            var benefit = Benefit(null,null,null,null,null,null,null,null, false)


            benefit.Benefit = results.getString(results.getColumnIndex("Benefit"))
            benefit.ShortDescription = results.getString(results.getColumnIndex("Short Description"))
            benefit.LongDescription = results.getString(results.getColumnIndex("Long Description"))
            benefit.ButtonText = results.getString(results.getColumnIndex("Button Text"))
            benefit.BenefitLink = results.getString(results.getColumnIndex("Benefit Link"))

            list.add(benefit)
        }
        return list
    }

    fun getBenifitsByCategory(): MutableList<Benefit>{

        var list = mutableListOf<Benefit>()
        var results = ModesDb.getBenefitByCategory(this.selectedCategory)

        while(results!!.moveToNext()){


            var benefit = Benefit(null,null,null,null,null,null,null,null, false)

            benefit.Benefit = results.getString(results.getColumnIndex("Benefit"))
            benefit.ShortDescription = results.getString(results.getColumnIndex("Short Description"))

            list.add(benefit)
        }
        return list
    }

    fun getSelectedBenefit(): Benefit{

        var benefit = Benefit(null,null,null,null,null,null,null,null, false)

        var results = ModesDb.getBenefitByName(this.selectedBenefit)

        results!!.moveToFirst()
        benefit.ID = results.getInt(results.getColumnIndex("ID"))
        benefit.Benefit = results.getString(results.getColumnIndex("Benefit"))
        benefit.ShortDescription = results.getString(results.getColumnIndex("Short Description"))
        benefit.LongDescription = results.getString(results.getColumnIndex("Long Description"))
        benefit.ButtonText = results.getString(results.getColumnIndex("Button Text"))
        benefit.BenefitLink = results.getString(results.getColumnIndex("Benefit Link"))
        benefit.favorite = results.getString(results.getColumnIndex("favorite")).equals("1")
        if(benefit.favorite == null)
            benefit.favorite = false


        return benefit
    }
}

