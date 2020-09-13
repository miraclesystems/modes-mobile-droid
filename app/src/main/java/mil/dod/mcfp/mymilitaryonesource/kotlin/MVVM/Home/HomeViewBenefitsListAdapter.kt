package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.home_benefits_grid_item_layout.view.*
import mil.dod.mcfp.mymilitaryonesource.R


class HomeViewBenefitsListAdapter(private val context: Activity, private val title: Array<String>)
    : ArrayAdapter<String>(context, R.layout.home_benefits_grid_item_layout, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.home_benefits_grid_item_layout, null, true)

        rowView.title.setText(title[position])

        return rowView
    }
}