package com.anangkur.kotlinexpertsubmission.data.remote

import com.anangkur.kotlinexpertsubmission.base.BaseDataSource
import com.anangkur.kotlinexpertsubmission.data.DataSource
import com.anangkur.kotlinexpertsubmission.data.model.ResponseLeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result

class RemoteRepository: DataSource, BaseDataSource() {

    override suspend fun getDetailLeague(id: String): Result<ResponseLeagueDetail> {
        return getResult { ApiService.getApiService.getLeagueDetail(id) }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}