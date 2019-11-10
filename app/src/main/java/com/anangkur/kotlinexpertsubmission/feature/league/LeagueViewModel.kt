package com.anangkur.kotlinexpertsubmission.feature.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.League

class LeagueViewModel(private val repository: Repository): ViewModel() {
    fun getDummyData(): LiveData<List<League>> = repository.createDummyLeague()
}