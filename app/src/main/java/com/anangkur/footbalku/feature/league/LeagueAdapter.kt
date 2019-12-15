package com.anangkur.footbalku.feature.league

import android.view.View
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseAdapter
import com.anangkur.footbalku.data.model.League
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(private val actionListener: LeagueActionListener): BaseAdapter<League>() {

    override val layout: Int
        get() = R.layout.item_league

    override fun bind(data: League, itemView: View, position: Int) {
        itemView.iv_league.setImageResource(data.image)
        itemView.tv_title_league.text = data.title
        itemView.tv_desc_league.text = data.description
        itemView.card_league.setOnClickListener { actionListener.onClickItem(data) }
    }

}