package com.anangkur.footbalku.feature.matchDetail

import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.data.model.Team

interface MatchDetailActionListener {
    fun onClickFavourite(data: Event){}
    fun onClickFavourite(data: Team){}
}