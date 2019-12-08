package com.anangkur.kotlinexpertsubmission.data

import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.anangkur.kotlinexpertsubmission.data.local.LocalRepository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.TeamFavourite
import com.anangkur.kotlinexpertsubmission.data.model.*
import com.anangkur.kotlinexpertsubmission.data.remote.RemoteRepository
import com.anangkur.kotlinexpertsubmission.util.EspressoIdlingResource
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

    fun getDetailMatch(id: String): LiveData<Result<ResponseMatch>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response =  remoteRepository.getDetailMatch(id)
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

    fun getSearchMatch(e: String): LiveData<Result<ResponseSearchMatch>>{
        EspressoIdlingResource.increment()
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getSearchMatch(e)
            val responseLive = MutableLiveData<Result<ResponseSearchMatch>>()
            if (response.status == Result.Status.SUCCESS){
                withContext(Dispatchers.Main){
                    responseLive.value = response
                    emitSource(responseLive)
                    EspressoIdlingResource.decrement()
                }
            }else if (response.status == Result.Status.ERROR){
                withContext(Dispatchers.Main){
                    emit(Result.error(response.message?:""))
                    emitSource(responseLive)
                    EspressoIdlingResource.decrement()
                }
            }
        }
    }

    fun getTeamDetail(id: String): LiveData<Result<ResponseTeamDetail>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getTeamDetail(id)
            val responseLive = MutableLiveData<Result<ResponseTeamDetail>>()
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

    fun insertEventFav(data: EventFavourite): LiveData<Result<Long>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.insertEventFav(data)
            val responseLive = MutableLiveData<Result<Long>>()
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

    fun selectAllEventFav(): LiveData<Result<List<EventFavourite>>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.selectAllEventFav()
            val responseLive = MutableLiveData<Result<List<EventFavourite>>>()
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

    fun selectEventFavById(id: String): LiveData<Result<EventFavourite>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.selectEventById(id)
            val responseLive = MutableLiveData<Result<EventFavourite>>()
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

    fun deleteEventFav(id: String): LiveData<Result<Long>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.deleteEventFav(id)
            val responseLive = MutableLiveData<Result<Long>>()
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

    fun getStanding(l: String): LiveData<Result<ResponseStanding>>{
        return liveData {
            emit(Result.loading())
            val response = remoteRepository.getStanding(l)
            val responseLive = MutableLiveData<Result<ResponseStanding>>()
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

    fun getTeamList(id: String): LiveData<Result<ResponseTeamDetail>>{
        return liveData {
            emit(Result.loading())
            val response = remoteRepository.getTeamList(id)
            val responseLive = MutableLiveData<Result<ResponseTeamDetail>>()
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

    fun insertTeamFav(data: TeamFavourite): LiveData<Result<Long>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.insertTeamFav(data)
            val responseLive = MutableLiveData<Result<Long>>()
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

    fun selectAllTeamFav(): LiveData<Result<List<TeamFavourite>>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.selectAllTeamFav()
            val responseLive = MutableLiveData<Result<List<TeamFavourite>>>()
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

    fun selectTeamFavById(id: String): LiveData<Result<TeamFavourite>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.selectTeamById(id)
            val responseLive = MutableLiveData<Result<TeamFavourite>>()
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

    fun deleteTeamFav(id: String): LiveData<Result<Long>>{
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = localRepository.deleteTeamFav(id)
            val responseLive = MutableLiveData<Result<Long>>()
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

    fun getSearchTeam(t: String): LiveData<Result<ResponseTeamDetail>>{
        EspressoIdlingResource.increment()
        return liveData(Dispatchers.IO){
            emit(Result.loading())
            val response = remoteRepository.getSearchTeam(t)
            val responseLive = MutableLiveData<Result<ResponseTeamDetail>>()
            if (response.status == Result.Status.SUCCESS){
                withContext(Dispatchers.Main){
                    responseLive.value = response
                    emitSource(responseLive)
                    EspressoIdlingResource.decrement()
                }
            }else if (response.status == Result.Status.ERROR){
                withContext(Dispatchers.Main){
                    emit(Result.error(response.message?:""))
                    emitSource(responseLive)
                    EspressoIdlingResource.decrement()
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