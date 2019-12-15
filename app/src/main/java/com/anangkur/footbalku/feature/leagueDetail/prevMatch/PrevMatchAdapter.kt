package com.anangkur.footbalku.feature.leagueDetail.prevMatch

import android.annotation.SuppressLint
import android.view.View
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseAdapter
import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.feature.leagueDetail.MatchActionListener
import com.anangkur.footbalku.util.convertStringToDate
import com.anangkur.footbalku.util.convertStringToTime
import com.anangkur.footbalku.util.setImageUrl
import kotlinx.android.synthetic.main.item_match_prev.view.*

class PrevMatchAdapter(private val actionListener: MatchActionListener): BaseAdapter<Event>(){
    override val layout: Int
        get() = R.layout.item_match_prev

    @SuppressLint("SetTextI18n")
    override fun bind(data: Event, itemView: View, position: Int) {
        itemView.iv_match.setImageUrl(data.strThumb?:"")
        itemView.tv_title_match.text = data.strEvent
        itemView.tv_date_match.text = convertStringToDate(data.dateEvent?:"", data.strTime?:"")
        itemView.tv_time_match.text = convertStringToTime(data.dateEvent?:"", data.strTime?:"")
        itemView.tv_score_match.text = "${data.intHomeScore} : ${data.intAwayScore}"
        itemView.setOnClickListener { actionListener.onClickMatch(data) }
    }

}