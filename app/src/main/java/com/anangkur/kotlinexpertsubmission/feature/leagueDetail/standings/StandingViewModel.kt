package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.ResponseStanding
import com.anangkur.kotlinexpertsubmission.data.model.Result

class StandingViewModel(private val repository: Repository): ViewModel(){
    var dataFromArgs: League? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseStanding>> = Transformations.switchMap(reloadTrigger) {
        repository.getStanding(dataFromArgs?.id?:"")
    }
    fun getPrevMatch() = matchLiveData
    fun refreshData() {
        reloadTrigger.value = true
    }
}