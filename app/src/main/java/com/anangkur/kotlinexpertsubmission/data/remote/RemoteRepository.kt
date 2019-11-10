package com.anangkur.kotlinexpertsubmission.data.remote

import com.anangkur.kotlinexpertsubmission.data.DataSource

class RemoteRepository: DataSource {
    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}