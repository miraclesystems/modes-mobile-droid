package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Connect

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.miraclesystems.mode_mobile_droid.R.id
import com.miraclesystems.mode_mobile_droid.R.layout.activity_connect
import com.miraclesystems.mode_mobile_droid.kotlin.MVVM.BaseActivity
import kotlinx.android.synthetic.main.activity_connect.*


class ConnectActivity : BaseActivity() {







    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(activity_connect)

        val phoneNum =  num_st.getText()
        var urlString =  onesource_st.getText()


        button_call.setOnClickListener {

            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNum")
            startActivity(callIntent)
        }




        if (!urlString.startsWith("http://") && !urlString.startsWith("https://"))
            urlString = "http://" + urlString;


        button_web.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.data = Uri.parse("$urlString")
            startActivity(browserIntent)
        }



    }





    override fun onResume() {
        super.onResume()
        setSelected(id.navigation_connect);

    }

}