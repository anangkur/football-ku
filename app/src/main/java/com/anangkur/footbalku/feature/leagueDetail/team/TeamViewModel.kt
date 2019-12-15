package com.anangkur.footbalku.feature.leagueDetail.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.League
import com.anangkur.footbalku.data.model.ResponseTeamDetail
import com.anangkur.footbalku.data.model.Result

class TeamViewModel(private val repository: Repository): ViewModel(){
    var dataFromArgs: League? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseTeamDetail>> = Transformations.switchMap(reloadTrigger) {
        repository.getTeamList(dataFromArgs?.id?:"")
    }
    fun getTeamList() = matchLiveData
    fun refreshData() {
        reloadTrigger.value = true
    }
}