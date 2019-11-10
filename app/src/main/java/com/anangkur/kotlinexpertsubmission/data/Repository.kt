package com.anangkur.kotlinexpertsubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.anangkur.kotlinexpertsubmission.data.local.LocalRepository
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.LeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.ResponseLeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) {

    fun createDummyLeague(): LiveData<List<League>> = localRepository.createDummyLeague()

    fun getDetailLeague(id: String): LiveData<Result<ResponseLeagueDetail>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val responseStatus = remoteRepository.getDetailLeague(id)
            val responseLive = MutableLiveData<Result<ResponseLeagueDetail>>()
            if (responseStatus.status == Result.Status.SUCCESS) {
                withContext(Dispatchers.Main){
                    responseLive.value = responseStatus
                    emitSource(responseLive)
                }
            } else if (responseStatus.status == Result.Status.ERROR) {
                withContext(Dispatchers.Main){
                    emit(Result.error(responseStatus.message!!))
                    emitSource(responseLive)
                }
            }
        }
    }

    companion object{
        @Volatile private var INSTANCE: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository, localRepository: LocalRepository) = INSTANCE ?: synchronized(Repository::class.java){
            INSTANCE ?: Repository(remoteRepository, localRepository).also { INSTANCE = it }
        }
    }
}