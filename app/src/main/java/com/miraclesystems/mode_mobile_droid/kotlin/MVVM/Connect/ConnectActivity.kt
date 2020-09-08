package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.miraclesystems.mode_mobile_droid.R
import com.miraclesystems.mode_mobile_droid.R.id
import com.miraclesystems.mode_mobile_droid.R.layout.activity_connect
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import kotlinx.android.synthetic.main.activity_connect.*


class ConnectActivity : BaseActivity() {







    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(activity_connect)

        var phoneNum =  getResources().getString(R.string.phone_num)
        var urlString =  getResources().getString(R.string.onesource_url)
        var urlInternational =  getResources().getString(R.string.call_int)
        var urlFacebook =  getResources().getString(R.string.facebook)
        var urlYoutube =  getResources().getString(R.string.youtube)
        var urlTwitter =  getResources().getString(R.string.twitter)
        var urlInstagram =  getResources().getString(R.string.instagram)
        var urlPinterest =  getResources().getString(R.string.pinterest)




        fun callPhone(){

            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNum")
            startActivity(callIntent)

        }


        button_call.setOnClickListener {

            if (checkPermission(
                    Manifest.permission.CALL_PHONE)
            ) {
                callPhone()
            }
        }





        button_web.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlString")
            startActivity(browserIntent)
        }

        button_call_int.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlInternational")
            startActivity(browserIntent)
        }

        facebook.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlFacebook")
            startActivity(browserIntent)
        }

        twitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlTwitter")
            startActivity(browserIntent)
        }

        youtube.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlYoutube")
            startActivity(browserIntent)
        }

        instagram.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlInstagram")
            startActivity(browserIntent)
        }

        pinterest.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlPinterest")
            startActivity(browserIntent)
        }




    }




    val PERMISSION_ID = 42
    private fun checkPermission(vararg perm:String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(this!!.applicationContext,it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if(perm.toList().any {
                    ActivityCompat.
                    shouldShowRequestPermissionRationale(this!!, it)}
            ) {
                val dialog = AlertDialog.Builder(this!!.applicationContext, R.style.Theme_AppCompat_Light)

                    .setTitle("Permission")
                    .setMessage("Permission needed!")
                    .setPositiveButton("OK", {id, v ->
                        ActivityCompat.requestPermissions(
                            this!!, perm, PERMISSION_ID)
                    })
                    .setNegativeButton("No", {id, v -> })
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(this!!, perm, PERMISSION_ID)
            }
            return false
        }
        return true
    }



    override fun onResume() {
        super.onResume()
        setSelected(id.navigation_connect);

    }

}