package com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.miraclesystems.mode_mobile_droid.R
import kotlinx.android.synthetic.main.home_cards_items.*
import kotlinx.android.synthetic.main.home_cards_items.view.*


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

        //setOnClickListener { listener(item) }
    }
}


class HomeViewCardsListAdapter(val items: List<HomePageCardModel>, val context: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_cards_items, parent, false))
    }

    override fun onBindViewHolder(
        holder: com.miraclesystems.mode_mobile_droid.kotlin.MVVM.Home.ViewHolder,
        position: Int
    ) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }
}