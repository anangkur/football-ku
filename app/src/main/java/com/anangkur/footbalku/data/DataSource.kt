package com.anangkur.footbalku.data

import com.anangkur.footbalku.data.local.ankoSqlite.EventFavourite
import androidx.lifecycle.LiveData
import com.anangkur.footbalku.data.local.ankoSqlite.TeamFavourite
import com.anangkur.footbalku.data.model.*

interface DataSource {
    fun createDummyLeague(): LiveData<List<League>>{throw Exception()}
    suspend fun insertEventFav(data: EventFavourite): Result<Long>{throw Exception()}
    suspend fun selectAllEventFav(): Result<List<EventFavourite>>{throw Exception()}
    suspend fun deleteEventFav(id: String): Result<Long>{throw Exception()}
    suspend fun selectEventById(id: String): Result<EventFavourite>{throw Exception()}
    suspend fun insertTeamFav(data: TeamFavourite): Result<Long>{throw Exception()}
    suspend fun selectAllTeamFav(): Result<List<TeamFavourite>>{throw Exception()}
    suspend fun deleteTeamFav(id: String): Result<Long>{throw Exception()}
    suspend fun selectTeamById(id: String): Result<TeamFavourite>{throw Exception()}

    suspend fun getDetailLeague(id: String): Result<ResponseLeagueDetail>{throw Exception()}
    suspend fun getPrevMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getNextMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getDetailMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getSearchMatch(e: String): Result<ResponseSearchMatch>{throw Exception()}
    suspend fun getTeamDetail(id: String): Result<ResponseTeamDetail>{throw Exception()}
    suspend fun getStanding(id: String): Result<ResponseStanding>{throw Exception()}
    suspend fun getTeamList(id: String): Result<ResponseTeamDetail>{throw Exception()}
    suspend fun getSearchTeam(t: String): Result<ResponseTeamDetail>{throw Exception()}
}