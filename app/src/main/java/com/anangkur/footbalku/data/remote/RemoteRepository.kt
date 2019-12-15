package com.anangkur.footbalku.data.remote

import com.anangkur.footbalku.base.BaseDataSource
import com.anangkur.footbalku.data.DataSource
import com.anangkur.footbalku.data.model.*

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

    override suspend fun getTeamList(id: String): Result<ResponseTeamDetail> {
        return getResult { ApiService.getApiService.getTeamList(id) }
    }

    override suspend fun getSearchTeam(t: String): Result<ResponseTeamDetail> {
        return getResult { ApiService.getApiService.getSearchTeam(t) }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}