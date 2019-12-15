package com.anangkur.footbalku.feature.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anangkur.footbalku.data.Repository
import com.anangkur.footbalku.data.model.League

class LeagueViewModel(private val repository: Repository): ViewModel() {
    fun getDummyData(): LiveData<List<League>> = repository.createDummyLeague()
}