package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import com.anangkur.kotlinexpertsubmission.data.model.Event

interface MatchDetailActionListener {
    fun onClickFavourite(data: Event)
}