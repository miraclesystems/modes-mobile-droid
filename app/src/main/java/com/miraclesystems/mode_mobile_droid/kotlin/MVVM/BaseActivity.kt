package com.miraclesystems.mode_mobile_droid.kotlin.MVVM

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.activity_base.*
import org.w3c.dom.Text
import java.util.*

open class BaseActivity : AppCompatActivity(), Observer {

    var viewModel = BaseViewModel()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)



        viewModel.addObserver(this)

        viewModel.getValue()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is BaseViewModel -> {
                if (arg is Boolean){

                    this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}