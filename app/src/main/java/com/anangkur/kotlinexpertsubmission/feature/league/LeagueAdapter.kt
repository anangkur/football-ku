package com.anangkur.kotlinexpertsubmission.feature.league

import android.view.View
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseAdapter
import com.anangkur.kotlinexpertsubmission.data.model.League
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter: BaseAdapter<League>() {

    override val layout: Int
        get() = R.layout.item_league

    override fun bind(data: League, itemView: View) {
        itemView.iv_league.setImageResource(data.image)
        itemView.tv_title_league.text = data.title
        itemView.tv_desc_league.text = data.description
    }

}