package com.anangkur.footbalku.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.footbalku.feature.favourite.FavouriteViewModel
import com.anangkur.footbalku.feature.favourite.match.FavouriteMatchViewModel
import com.anangkur.footbalku.feature.favourite.team.FavouriteTeamViewModel
import com.anangkur.footbalku.feature.league.LeagueViewModel
import com.anangkur.footbalku.feature.leagueDetail.LeagueDetailViewModel
import com.anangkur.footbalku.feature.leagueDetail.detail.DetailLeagueViewModel
import com.anangkur.footbalku.feature.leagueDetail.nextMatch.NextMatchViewModel
import com.anangkur.footbalku.feature.leagueDetail.prevMatch.PrevMatchViewModel
import com.anangkur.footbalku.feature.leagueDetail.team.TeamViewModel
import com.anangkur.footbalku.feature.leagueDetail.standings.StandingViewModel
import com.anangkur.footbalku.feature.matchDetail.MatchDetailViewModel
import com.anangkur.footbalku.feature.matchSearch.SearchViewModel
import com.anangkur.footbalku.feature.teamDetail.TeamDetailViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(LeagueViewModel::class.java) -> LeagueViewModel(repository)
                isAssignableFrom(LeagueDetailViewModel::class.java) -> LeagueDetailViewModel(repository)
                isAssignableFrom(DetailLeagueViewModel::class.java) -> DetailLeagueViewModel()
                isAssignableFrom(NextMatchViewModel::class.java) -> NextMatchViewModel(repository)
                isAssignableFrom(PrevMatchViewModel::class.java) -> PrevMatchViewModel(repository)
                isAssignableFrom(MatchDetailViewModel::class.java) -> MatchDetailViewModel(repository)
                isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository)
                isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(repository)
                isAssignableFrom(StandingViewModel::class.java) -> StandingViewModel(repository)
                isAssignableFrom(TeamViewModel::class.java) -> TeamViewModel(repository)
                isAssignableFrom(TeamDetailViewModel::class.java) -> TeamDetailViewModel(repository)
                isAssignableFrom(FavouriteMatchViewModel::class.java) -> FavouriteMatchViewModel(repository)
                isAssignableFrom(FavouriteTeamViewModel::class.java) -> FavouriteTeamViewModel(repository)
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