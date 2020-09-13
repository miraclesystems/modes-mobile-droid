package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.home_view_guides_card_layout.view.*
import mil.dod.mcfp.mymilitaryonesource.R


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
