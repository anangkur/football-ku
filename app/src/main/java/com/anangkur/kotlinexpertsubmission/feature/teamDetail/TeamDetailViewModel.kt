package com.anangkur.kotlinexpertsubmission.feature.teamDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.TeamFavourite
import com.anangkur.kotlinexpertsubmission.data.model.ResponseTeamDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.data.model.Team

class TeamDetailViewModel(private val repository: Repository): ViewModel(){
    var dataFromIntent: Team? = null
    var isFavourite: Boolean = false

    val listSliderLive = MutableLiveData<List<String>>()
    private val reloadTrigger = MutableLiveData<Boolean>()
    private val teamLiveData: LiveData<Result<ResponseTeamDetail>> = Transformations.switchMap(reloadTrigger){
        repository.getTeamDetail(dataFromIntent?.idTeam?:"")
    }
    fun getTeamDetail() = teamLiveData

    fun createListSlider(link1: String?, link2: String?, link3: String?, link4: String?, link5: String?){
        val listSlider = ArrayList<String>()
        link1?.let { listSlider.add(it) }
        link2?.let { listSlider.add(it) }
        link3?.let { listSlider.add(it) }
        link4?.let { listSlider.add(it) }
        link5?.let { listSlider.add(it) }
        listSliderLive.value = listSlider
    }

    fun refreshData(){
        reloadTrigger.value = true
    }

    fun selectTeamById() = repository.selectTeamFavById(dataFromIntent?.idTeam?:"")

    private val triggerInsertTeamLive = MutableLiveData<TeamFavourite>()
    private val insertTeamLiveData:LiveData<Result<Long>> = Transformations.switchMap(triggerInsertTeamLive){
        repository.insertTeamFav(it)
    }
    fun insertTeamData(data: TeamFavourite){
        triggerInsertTeamLive.value = data
    }
    fun insertEvent() = insertTeamLiveData

    private val triggerDeleteTeamLive = MutableLiveData<TeamFavourite>()
    private val deleteTeamLiveData:LiveData<Result<Long>> = Transformations.switchMap(triggerDeleteTeamLive){
        repository.deleteTeamFav(it.idTeam?:"")
    }
    fun deleteTeamData(data: TeamFavourite){
        triggerDeleteTeamLive.value = data
    }
    fun deleteEvent() = deleteTeamLiveData
}