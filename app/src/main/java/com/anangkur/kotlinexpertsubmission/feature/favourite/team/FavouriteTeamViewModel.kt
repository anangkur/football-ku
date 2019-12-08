package com.anangkur.kotlinexpertsubmission.feature.favourite.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.TeamFavourite
import com.anangkur.kotlinexpertsubmission.data.model.Result

class FavouriteTeamViewModel(private val repository: Repository): ViewModel(){
    private val reloadTrigger = MutableLiveData<Boolean>()
    private val listEventLive: LiveData<Result<List<TeamFavourite>>> = Transformations.switchMap(reloadTrigger){
        repository.selectAllTeamFav()
    }
    fun selectAllEvent() = listEventLive
    fun refreshData(){
        reloadTrigger.value = true
    }
}