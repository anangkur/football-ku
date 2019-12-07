package com.anangkur.kotlinexpertsubmission.data.remote

import com.anangkur.kotlinexpertsubmission.base.BaseDataSource
import com.anangkur.kotlinexpertsubmission.data.DataSource
import com.anangkur.kotlinexpertsubmission.data.model.*

class RemoteRepository: DataSource, BaseDataSource() {

    override suspend fun getDetailLeague(id: String): Result<ResponseLeagueDetail> {
        return getResult { ApiService.getApiService.getLeagueDetail(id) }
    }

    override suspend fun getNextMatch(id: String): Result<ResponseMatch> {
        return getResult { ApiService.getApiService.getNextMatch(id) }
    }

    override suspend fun getPrevMatch(id: String): Result<ResponseMatch> {
        return getResult { ApiService.getApiService.getPrevMatch(id) }
    }

    override suspend fun getDetailMatch(id: String): Result<ResponseMatch> {
        return getResult { ApiService.getApiService.getDetailMatch(id) }
    }

    override suspend fun getSearchMatch(e: String): Result<ResponseSearchMatch> {
        return getResult { ApiService.getApiService.getSearchMatch(e) }
    }

    override suspend fun getTeamDetail(id: String): Result<ResponseTeamDetail> {
        return getResult { ApiService.getApiService.getDetailTeam(id) }
    }

    override suspend fun getStanding(id: String): Result<ResponseStanding> {
        return getResult { ApiService.getApiService.getStanding(id) }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}