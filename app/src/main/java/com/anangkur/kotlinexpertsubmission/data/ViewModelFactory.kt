package com.anangkur.kotlinexpertsubmission.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.kotlinexpertsubmission.feature.league.LeagueViewModel
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.LeagueDetailViewModel
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.detail.DetailLeagueViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(LeagueViewModel::class.java) -> LeagueViewModel(repository)
                isAssignableFrom(LeagueDetailViewModel::class.java) -> LeagueDetailViewModel(repository)
                isAssignableFrom(DetailLeagueViewModel::class.java) -> DetailLeagueViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(Injection.provideRepository(context)).also { INSTANCE = it }
        }
    }
}