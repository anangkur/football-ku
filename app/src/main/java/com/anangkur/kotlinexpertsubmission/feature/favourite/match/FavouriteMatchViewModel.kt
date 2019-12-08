package com.anangkur.kotlinexpertsubmission.feature.favourite.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import com.anangkur.kotlinexpertsubmission.data.model.Result

class FavouriteMatchViewModel(private val repository: Repository): ViewModel(){
    private val reloadTrigger = MutableLiveData<Boolean>()
    private val listEventLive: LiveData<Result<List<EventFavourite>>> = Transformations.switchMap(reloadTrigger){
        repository.selectAllEventFav()
    }
    fun selectAllEvent() = listEventLive
    fun refreshData(){
        reloadTrigger.value = true
    }
}