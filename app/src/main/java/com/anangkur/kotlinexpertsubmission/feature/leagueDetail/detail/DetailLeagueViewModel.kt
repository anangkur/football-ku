package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.detail

import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.LeagueDetail

class DetailLeagueViewModel(private val repository: Repository): ViewModel(){
    var dataFromArgs: LeagueDetail? = null
}