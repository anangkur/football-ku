package com.anangkur.kotlinexpertsubmission.feature.matchSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.ResponseMatch
import com.anangkur.kotlinexpertsubmission.data.model.ResponseSearchMatch
import com.anangkur.kotlinexpertsubmission.data.model.Result

class MatchSearchViewModel(private val repository: Repository): ViewModel(){

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
}