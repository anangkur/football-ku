package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch

import android.view.View
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseAdapter
import com.anangkur.kotlinexpertsubmission.data.model.Event
import kotlinx.android.synthetic.main.item_match_next.view.*

class NextMatchAdapter: BaseAdapter<Event>(){
    override val layout: Int
        get() = R.layout.item_match_next

    override fun bind(data: Event, itemView: View) {
        itemView.tv_title_match.text = data.strEvent
        itemView.tv_date_match.text = "${data.strDate} ${data.strTime}"
    }

}