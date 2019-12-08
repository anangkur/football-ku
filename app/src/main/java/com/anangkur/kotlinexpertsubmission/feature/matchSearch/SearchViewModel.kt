package com.anangkur.kotlinexpertsubmission.feature.matchSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.ResponseSearchMatch
import com.anangkur.kotlinexpertsubmission.data.model.ResponseTeamDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result

class SearchViewModel(private val repository: Repository): ViewModel(){

    private var e: String? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseSearchMatch>> = Transformations.switchMap(reloadTrigger){
        repository.getSearchMatch(e?:"")
    }

    fun getSearchMatch() = matchLiveData
    fun refreshData(query: String){
        e = query
        reloadTrigger.value = true
    }

    private val reloadTriggerTeam = MutableLiveData<Boolean>()
    private val teamLiveData: LiveData<Result<ResponseTeamDetail>> = Transformations.switchMap(reloadTrigger){
        repository.getSearchTeam(e?:"")
    }

    fun getSearchTeam() = teamLiveData
    fun refreshDataTeam(query: String){
        e = query
        reloadTriggerTeam.value = true
    }
}