package com.anangkur.footbalku.feature.leagueDetail.nextMatch

import android.view.View
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseAdapter
import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.feature.leagueDetail.MatchActionListener
import com.anangkur.footbalku.util.convertStringToDate
import com.anangkur.footbalku.util.convertStringToTime
import kotlinx.android.synthetic.main.item_match_next.view.*

class NextMatchAdapter(private val actionListener: MatchActionListener): BaseAdapter<Event>(){
    override val layout: Int
        get() = R.layout.item_match_next

    override fun bind(data: Event, itemView: View, position: Int) {
        itemView.tv_title_match.text = data.strEvent
        itemView.tv_date_match.text = convertStringToDate(data.dateEvent?:"", data.strTime?:"")
        itemView.tv_time_match.text = convertStringToTime(data.dateEvent?:"", data.strTime?:"")
        itemView.setOnClickListener { actionListener.onClickMatch(data) }
    }

}