package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home


import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_benefits_grid_item_layout.view.*
import mil.dod.mcfp.mymilitaryonesource.R
import kotlinx.android.synthetic.main.home_cards_items.view.*
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Guides.guidesActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Benefits.BenefitsActivity
import mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Connect.ConnectActivity


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    fun bind(item: HomePageCardModel) = with(itemView) {


        itemView.header.setText(item.cardType)
        if (item.recommended == false) {
            itemView.buttonRecommended.visibility = View.INVISIBLE
            itemView.buttontext.visibility = View.INVISIBLE
        }

        when (item.cardType) {
            "BENEFITS" -> {
                itemView.icon.setImageResource(R.drawable.benefits_focused_ic)
                itemView.header.setText("BENEFITS")
            }
            "MILLIFE GUIDES" -> {
                itemView.icon.setImageResource(R.drawable.milife_focused_ic)
                itemView.header.setText("MILLIFE GUIDES")
            }
            "CONNECT" -> {
                itemView.icon.setImageResource(R.drawable.connect_focused_ic)
                itemView.header.setText("CONNECT")
            }
            "ABOUT US" -> {
                itemView.icon.setImageResource(R.drawable.connect_focused_ic)
                itemView.header.setText("ABOUT US")
            }

        }

        itemView.icon.scaleType = ImageView.ScaleType.FIT_XY
        itemView.content.setText(item.cardTitle)
        itemView.cardlayout.setOnClickListener{

        }

    }



}


class HomeViewCardsListAdapter(val items: List<HomePageCardModel>, val context: Context): RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_cards_items, parent, false))
    }

    override fun onBindViewHolder(
        holder: mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Home.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])

        holder.itemView.cardlayout.setOnClickListener{
            /*
            val intent = Intent(HomeActivity, HomeActivity::class.java)
            intent("benefit", HomeActivity)
            startActivity(HomeActivity)
             */
            //items[position].cardTitle?.let { it1 -> Log.d("clicked", it1) }

            val cardType = items[position].cardType
            val cardTitle = items[position].cardTitle
            val activity = holder.itemView.context as Activity

            when (cardType) {
                "BENEFITS" -> {
                    val intent = Intent(activity, BenefitsActivity::class.java)
                    intent.putExtra("benefit", cardTitle)
                    context.startActivity(intent)
                }
                "MILLIFE GUIDES" -> {
                    val intent = Intent(activity, BenefitsActivity::class.java)
                    intent.putExtra("guide", cardTitle)
                    context.startActivity(intent)
                }
                "CONNECT" -> {
                    val intent = Intent(activity, ConnectActivity::class.java)
                    context.startActivity(intent)
                }
                "ABOUT US" -> {
                    // TODO: 9/14/20
                }
            }

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


}