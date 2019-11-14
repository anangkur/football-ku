package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.ResponseMatch
import com.anangkur.kotlinexpertsubmission.data.model.ResponseTeamDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result

class MatchDetailViewModel(private val repository: Repository): ViewModel(){
    var dataFromIntent: Event? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseMatch>> = Transformations.switchMap(reloadTrigger){
        repository.getDetailMatch(dataFromIntent?.idEvent?:"")
    }
    private val homeTeamLiveData: LiveData<Result<ResponseTeamDetail>> = Transformations.switchMap(reloadTrigger){
        repository.getTeamDetail(dataFromIntent?.idHomeTeam?:"")
    }
    private val awayTeamLiveData: LiveData<Result<ResponseTeamDetail>> = Transformations.switchMap(reloadTrigger){
        repository.getTeamDetail(dataFromIntent?.idAwayTeam?:"")
    }

    fun getDetailMatch() = matchLiveData
    fun getHomeTeam() = homeTeamLiveData
    fun getAwayTeam() = awayTeamLiveData
    fun refreshData(){
        reloadTrigger.value = true
    }
}