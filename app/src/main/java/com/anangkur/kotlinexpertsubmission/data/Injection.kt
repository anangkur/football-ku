package com.anangkur.kotlinexpertsubmission.data

import android.content.Context
import com.anangkur.kotlinexpertsubmission.data.local.LocalRepository
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.MyDatabaseOpenHelper
import com.anangkur.kotlinexpertsubmission.data.remote.RemoteRepository
import com.anangkur.kotlinexpertsubmission.util.database

object Injection {
    fun provideRepository(context: Context) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(context, context.database)
    )
}