package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.home_view_guides_card_layout.view.*


class HomeViewGuidesListAdapter(private val context: Activity, private val title: Array<String>,  private val imgid: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.home_view_guides_card_layout, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.home_view_guides_card_layout, null, true)

        rowView.name.setText(title[position])
        rowView.image.setImageResource(imgid[position])

        return rowView
    }
}
