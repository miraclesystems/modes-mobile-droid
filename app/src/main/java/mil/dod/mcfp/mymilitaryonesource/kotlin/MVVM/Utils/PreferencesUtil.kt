package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.fragment_user_settings_installation.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.App

object PreferencesUtil {

    private  val NAME = "_PREFERENCES"
    private  val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    init {
        preferences = App.applicationContext().getSharedPreferences(NAME, MODE)
        firebaseAnalytics = Firebase.analytics
    }

    fun init(context: Context) {
        preferences = App.applicationContext().getSharedPreferences(NAME, MODE)

    }


    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    fun save(KEY_NAME: String, value: String) {


        var fbKey = ""
        if(KEY_NAME == "USER_DESCRIPTION"){
            fbKey = "audienceType"
        }
        else if(KEY_NAME == "installation"){
            fbKey = "installationName"
        }
        else if(KEY_NAME == "branch"){
            fbKey = "branchName"
        }


        firebaseAnalytics.setUserProperty(fbKey, value)
        val prefsEditor: SharedPreferences.Editor = preferences.edit()
        with(prefsEditor) {
            putString(KEY_NAME, value)
            commit()
        }


    }
    fun clearAll() {
        val editor: SharedPreferences.Editor = preferences.edit()

        editor.clear();
        editor.commit()
    }

   fun getValueString(KEY_NAME: String): String? {
        return preferences.getString(KEY_NAME, null)
    }


    var isFirstTimeLaunched: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)

        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    }

