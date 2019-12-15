package com.anangkur.footbalku.feature.league

import com.anangkur.footbalku.data.model.League

interface LeagueActionListener {
    fun onClickItem(data: League)
}