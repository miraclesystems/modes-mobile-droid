package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*




class MilifeActivity : AppCompatActivity(), Observer {

    var viewModel = MilifeViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_milife)



        viewModel.addObserver(this)

        viewModel.getValue()
    }

    override fun update(o: Observable?, arg: Any?) {
        when (o){
            is HomeViewModel -> {
                if (arg is Boolean){

                    this.label1.text = this.viewModel.model.value
                }
            }
            else -> println(o?.javaClass.toString())
        }
    }
}