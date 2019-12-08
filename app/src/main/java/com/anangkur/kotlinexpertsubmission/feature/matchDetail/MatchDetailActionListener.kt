package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Team

interface MatchDetailActionListener {
    fun onClickFavourite(data: Event){}
    fun onClickFavourite(data: Team){}
}