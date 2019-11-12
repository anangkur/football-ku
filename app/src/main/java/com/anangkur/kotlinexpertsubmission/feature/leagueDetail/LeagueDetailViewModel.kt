package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.ResponseLeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result

class LeagueDetailViewModel(private val repository: Repository): ViewModel(){

    var dataFromIntent: League? = null

    val listSliderLive = MutableLiveData<List<String>>()

    fun getLeagueDetail(id: String): LiveData<Result<ResponseLeagueDetail>> = repository.getDetailLeague(id)

    fun createListSlider(link1: String?, link2: String?, link3: String?, link4: String?, link5: String?){
        val listSlider = ArrayList<String>()
        link1?.let { listSlider.add(it) }
        link2?.let { listSlider.add(it) }
        link3?.let { listSlider.add(it) }
        link4?.let { listSlider.add(it) }
        link5?.let { listSlider.add(it) }
        listSliderLive.value = listSlider
    }
}