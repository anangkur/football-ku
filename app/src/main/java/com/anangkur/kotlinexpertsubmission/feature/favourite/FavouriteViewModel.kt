package com.anangkur.kotlinexpertsubmission.feature.favourite

import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import androidx.lifecycle.*
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.Result

class FavouriteViewModel(private val repository: Repository): ViewModel(){
    private val reloadTrigger = MutableLiveData<Boolean>()
    private val listEventLive: LiveData<Result<List<EventFavourite>>> = Transformations.switchMap(reloadTrigger){
        repository.selectAllEventFav()
    }
    fun selectAllEvent() = listEventLive
    fun refreshData(){
        reloadTrigger.value = true
    }
}