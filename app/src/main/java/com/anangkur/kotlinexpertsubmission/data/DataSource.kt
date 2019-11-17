package com.anangkur.kotlinexpertsubmission.data

import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import androidx.lifecycle.LiveData
import com.anangkur.kotlinexpertsubmission.data.model.*

interface DataSource {
    fun createDummyLeague(): LiveData<List<League>>{throw Exception()}
    suspend fun insertEventFav(data: EventFavourite): Result<Long>{throw Exception()}
    suspend fun selectAllEventFav(): Result<List<EventFavourite>>{throw Exception()}
    suspend fun deleteEventFav(id: String): Result<Long>{throw Exception()}
    suspend fun selectEventById(id: String): Result<EventFavourite>{throw Exception()}

    suspend fun getDetailLeague(id: String): Result<ResponseLeagueDetail>{throw Exception()}
    suspend fun getPrevMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getNextMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getDetailMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getSearchMatch(e: String): Result<ResponseSearchMatch>{throw Exception()}
    suspend fun getTeamDetail(id: String): Result<ResponseTeamDetail>{throw Exception()}
}