package com.anangkur.footbalku.feature.leagueDetail

import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.data.model.Team

interface MatchActionListener {
    fun onClickMatch(data: Event){}
    fun onClickTeam(data: Team){}
}