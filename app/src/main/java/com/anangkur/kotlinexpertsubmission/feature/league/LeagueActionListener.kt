package com.anangkur.kotlinexpertsubmission.feature.league

import com.anangkur.kotlinexpertsubmission.data.model.League

interface LeagueActionListener {
    fun onClickItem(data: League)
}