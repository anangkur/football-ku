package com.anangkur.kotlinexpertsubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.anangkur.kotlinexpertsubmission.data.local.LocalRepository
import com.anangkur.kotlinexpertsubmission.data.model.*
import com.anangkur.kotlinexpertsubmission.data.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) {

    fun createDummyLeague(): LiveData<List<League>> = localRepository.createDummyLeague()

    fun getDetailLeague(id: String): LiveData<Result<ResponseLeagueDetail>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getDetailLeague(id)
            val responseLive = MutableLiveData<Result<ResponseLeagueDetail>>()
            if (response.status == Result.Status.SUCCESS) {
                withContext(Dispatchers.Main){
                    responseLive.value = response
                    emitSource(responseLive)
                }
            } else if (response.status == Result.Status.ERROR) {
                withContext(Dispatchers.Main){
                    emit(Result.error(response.message?:""))
                    emitSource(responseLive)
                }
            }
        }
    }

    fun getNextMatch(id: String): LiveData<Result<ResponseMatch>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getNextMatch(id)
            val responseLive = MutableLiveData<Result<ResponseMatch>>()
            if (response.status == Result.Status.SUCCESS){
                withContext(Dispatchers.Main){
                    responseLive.value = response
                    emitSource(responseLive)
                }
            }else if (response.status == Result.Status.ERROR){
                withContext(Dispatchers.Main){
                    emit(Result.error(response.message?:""))
                    emitSource(responseLive)
                }
            }
        }
    }

    fun getPrevMatch(id: String): LiveData<Result<ResponseMatch>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getPrevMatch(id)
            val responseLive = MutableLiveData<Result<ResponseMatch>>()
            if (response.status == Result.Status.SUCCESS){
                withContext(Dispatchers.Main){
                    responseLive.value = response
                    emitSource(responseLive)
                }
            }else if (response.status == Result.Status.ERROR){
                withContext(Dispatchers.Main){
                    emit(Result.error(response.message?:""))
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