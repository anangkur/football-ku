package com.anangkur.footbalku.feature.matchDetail

import com.anangkur.footbalku.data.local.ankoSqlite.EventFavourite
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.Event
import com.anangkur.footbalku.data.model.ResponseMatch
import com.anangkur.footbalku.data.model.ResponseTeamDetail
import com.anangkur.footbalku.data.model.Result

class MatchDetailViewModel(private val repository: Repository): ViewModel(){
    var dataFromIntent: Event? = null
    var isFavourite: Boolean = false

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

    fun selectEventById() = repository.selectEventFavById(dataFromIntent?.idEvent?:"")

    private val triggerInsertEventLive = MutableLiveData<EventFavourite>()
    private val insertEventLiveData:LiveData<Result<Long>> = Transformations.switchMap(triggerInsertEventLive){
        repository.insertEventFav(it)
    }
    fun insertEventData(data: EventFavourite){
        triggerInsertEventLive.value = data
    }
    fun insertEvent() = insertEventLiveData

    private val triggerDeleteEventLive = MutableLiveData<EventFavourite>()
    private val deleteEventLiveData:LiveData<Result<Long>> = Transformations.switchMap(triggerDeleteEventLive){
        repository.deleteEventFav(it.idEvent?:"")
    }
    fun deleteEventData(data: EventFavourite){
        triggerDeleteEventLive.value = data
    }
    fun deleteEvent() = deleteEventLiveData
}