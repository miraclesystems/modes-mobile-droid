package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Utils

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.App


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

        return db.rawQuery("SELECT * FROM benefits", null)

    }

    /**
     * get the benefits by the audience whhich setup in the user setttings
     */
    fun getBenefitsByAudience(audience : String): Cursor?{
        return db.rawQuery("SELECT * FROM benefits WHERE audience LIKE '%" + audience + "%'", null)

    }

    fun getBenefitsByKeyWordSearch(searchTerm : String): Cursor?{

        return db.rawQuery("SELECT * FROM benefits WHERE keywords LIKE '%" + searchTerm + "%'", null)
    }

    fun getGuidesByAudience(audience: String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE audience LIKE '%" + audience + "%'", null)
    }

    fun getGuidesByKeyWordSearch(searchTerm : String): Cursor?{

        return db.rawQuery("SELECT * FROM guides WHERE [MilLife Guide Topic Keywords] LIKE '%" + searchTerm + "%'", null)
    }





}