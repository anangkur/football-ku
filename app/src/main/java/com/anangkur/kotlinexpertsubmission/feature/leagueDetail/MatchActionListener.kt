package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Team

interface MatchActionListener {
    fun onClickMatch(data: Event){}
    fun onClickTeam(data: Team){}
}