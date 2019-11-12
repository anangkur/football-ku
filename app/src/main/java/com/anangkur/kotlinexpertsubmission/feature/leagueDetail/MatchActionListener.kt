package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import com.anangkur.kotlinexpertsubmission.data.model.Event

interface MatchActionListener {
    fun onClickMatch(data: Event)
}