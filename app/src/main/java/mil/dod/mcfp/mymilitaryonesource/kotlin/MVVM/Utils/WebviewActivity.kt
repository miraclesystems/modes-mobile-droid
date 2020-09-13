package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var url : String? = intent?.getStringExtra("URL")

        if (url != null) {
            webView.loadUrl(url)
            webView.scrollTo(0, 500)
        }

        button_back.setOnClickListener {

            super.onBackPressed()
        }


    }


}