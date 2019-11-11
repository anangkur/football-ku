package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.ResponseMatch
import com.anangkur.kotlinexpertsubmission.data.model.Result

class NextMatchViewModel(private val repository: Repository): ViewModel(){

    var dataFromArgs: League? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseMatch>> = Transformations.switchMap(reloadTrigger) {
        repository.getNextMatch(dataFromArgs?.id?:"")
    }

    fun getNextMatch() = matchLiveData
    fun refreshData() {
        reloadTrigger.value = true
    }
}