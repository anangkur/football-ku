package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.prevMatch

import android.view.View
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseAdapter
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.util.setImageUrl
import kotlinx.android.synthetic.main.item_match_prev.view.*

class PrevMatchAdapter: BaseAdapter<Event>(){
    override val layout: Int
        get() = R.layout.item_match_prev

    override fun bind(data: Event, itemView: View) {
        itemView.iv_match.setImageUrl(data.strThumb?:"")
        itemView.tv_title_match.text = data.strEvent
        itemView.tv_date_match.text = "${data.strDate} ${data.strTime}"
        itemView.tv_score_match.text = "${data.intHomeScore} : ${data.intAwayScore}"
    }

}