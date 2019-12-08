package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.team

import android.view.View
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseAdapter
import com.anangkur.kotlinexpertsubmission.data.model.Team
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.util.setImageUrl
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val actionListener: MatchActionListener): BaseAdapter<Team>(){
    override val layout: Int
        get() = R.layout.item_team

    override fun bind(data: Team, itemView: View, position: Int) {
        itemView.iv_team.setImageUrl(data.strTeamBadge?:"")
        itemView.setOnClickListener { actionListener.onClickTeam(data) }
    }

}