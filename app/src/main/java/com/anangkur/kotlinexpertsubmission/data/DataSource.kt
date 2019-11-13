package com.anangkur.kotlinexpertsubmission.data

import androidx.lifecycle.LiveData
import com.anangkur.kotlinexpertsubmission.data.model.*

interface DataSource {
    fun createDummyLeague(): LiveData<List<League>>{ throw Exception() }

    suspend fun getDetailLeague(id: String): Result<ResponseLeagueDetail>{throw Exception()}
    suspend fun getPrevMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getNextMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getDetailMatch(id: String): Result<ResponseMatch>{throw Exception()}
    suspend fun getSearchMatch(e: String): Result<ResponseSearchMatch>{throw Exception()}
}