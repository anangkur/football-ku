package com.anangkur.footbalku.feature.leagueDetail.prevMatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.League
import com.anangkur.footbalku.data.model.ResponseMatch
import com.anangkur.footbalku.data.model.Result

class PrevMatchViewModel(private val repository: Repository): ViewModel(){
    var dataFromArgs: League? = null

    private val reloadTrigger = MutableLiveData<Boolean>()
    private val matchLiveData: LiveData<Result<ResponseMatch>> = Transformations.switchMap(reloadTrigger) {
        repository.getPrevMatch(dataFromArgs?.id?:"")
    }
    fun getPrevMatch() = matchLiveData
    fun refreshData() {
        reloadTrigger.value = true
    }
}