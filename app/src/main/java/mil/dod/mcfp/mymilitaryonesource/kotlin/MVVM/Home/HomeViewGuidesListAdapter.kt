package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home

import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.home_view_guides_card_layout.view.*
import kotlinx.android.synthetic.main.home_view_guides_card_layout.view.image
import kotlinx.android.synthetic.main.layout_guides_list.view.*
import mil.dod.mcfp.mymilitaryonesource.R
import java.io.InputStream
import java.lang.Exception


class HomeViewGuidesListAdapter(private val context: Activity, private val title: Array<String>,  private val imgid: Array<String>)
    : ArrayAdapter<String>(context, R.layout.home_view_guides_card_layout, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.home_view_guides_card_layout, null, true)

        rowView.name.setText(title[position])
        //rowView.image.setImageResource(imgid[position])

        try {
            val ims: InputStream = this.context!!.getAssets()
                .open("small-images/" + imgid[position]!! + "-200x200.jpg")
            // load image as Drawable
            // load image as Drawable
            val d =
                Drawable.createFromStream(ims, null)
            // set image to ImageView
            // set image to ImageView
            rowView.image.setImageDrawable(d)
            ims.close()
        }
        catch (e : Exception){
            Log.d("debug ", e.localizedMessage)
        }

        return rowView
    }
}
