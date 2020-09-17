package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


object ModesDb {

    private lateinit var dbHelper : ActsDbHelper
    private lateinit var db : SQLiteDatabase


    init{

    }

    fun setHelper(helper : ActsDbHelper){
        dbHelper = helper
        db = dbHelper.readableDatabase
    }


    fun getAllBenefits(): Cursor?{

        return db.rawQuery("SELECT * FROM benefits ORDER BY RANDOM()\n", null)

    }




    /**
     * get the benefits by the audience whhich setup in the user setttings
     */
    fun getBenefitsByAudience(audience : String): Cursor?{
        return db.rawQuery("SELECT * FROM benefits WHERE audience LIKE '%" + audience + "%'  ORDER BY RANDOM() LIMIT 4", null)

    }

    fun getBenefitsByKeyWordSearch(searchTerm : String): Cursor?{

        return db.rawQuery("SELECT * FROM benefits WHERE keywords LIKE '%" + searchTerm + "%'", null)
    }

    fun getGuidesByAudience(audience: String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE audience LIKE '%" + audience + "%'  ORDER BY RANDOM() LIMIT 4", null)
    }

    fun getGuidesByKeyWordSearch(searchTerm : String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE [MilLife Guide Topic Keywords] LIKE '%" + searchTerm + "%'", null)
    }


    fun getAllGuiides(): Cursor?{

        return db.rawQuery("SELECT * FROM guides", null)
    }

    fun getGuidesByCategory(category : String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE category LIKE '%" + category + "%'",null)
    }



    fun getGuideByName(guide : String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE guide LIKE '%" + guide + "%'",null)
    }


    fun getBenefitById(id : String): Cursor?{
        return db.rawQuery("SELECT * FROM benefits where ID=" + id,null)
    }


    fun getBenefitCategories(): Cursor?{
        return db.rawQuery("SELECT DISTINCT category from benefits", null)
    }

    fun getBenefitByCategory(category : String): Cursor?{

        return db.rawQuery("SELECT * from benefits WHERE category LIKE '%" + category+ "%'", null)
    }

    fun getBenefitByName(name : String):Cursor?{

        return db.rawQuery("SELECT * from benefits WHERE benefit LIKE '%" + name + "%'", null)
    }

    fun setGuideFavorite(favorite : Boolean, id: Int){

        var value = ""
        if(favorite){
            value = "1"
        }
        else{
            value = "0"
        }

        db.execSQL("UPDATE guides set favorite = " + value + " where id = " + id )
    }

    fun setGuideFavorite(favorite : Boolean, name: String){

        var value = ""
        if(favorite){
            value = "1"
        }
        else{
            value = "0"
        }
        db.execSQL("UPDATE guides set favorite = " + value + " where guide = '" + name  +"'")
    }

    fun getGuideFavorites():Cursor?{
        return db.rawQuery("SELECT * from guides WHERE favorite = 1", null)
    }

    fun setBenefitsFavorite(favorite : Boolean, id: Int){

        var value = ""
        if(favorite){
            value = "1"
        }
        else{
            value = "0"
        }
        db.execSQL("UPDATE benefits set favorite = " + value + " where id = " + id )
    }

    fun setBenefitsFavorite(favorite : Boolean, name: String){

        var value = ""
        if(favorite){
            value = "1"
        }
        else{
            value = "0"
        }
        db.execSQL("UPDATE benefits set favorite = " + value + " where benefit = '" + name  +"'")
    }
    fun getBenefitsFavorites():Cursor?{
        return db.rawQuery("SELECT * from benefits WHERE favorite = 1", null)
    }


}