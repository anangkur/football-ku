package com.anangkur.footbalku.data

import android.content.Context
import com.anangkur.footbalku.data.local.LocalRepository
import com.anangkur.footbalku.data.remote.RemoteRepository
import com.anangkur.footbalku.util.database

object Injection {
    fun provideRepository(context: Context) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(context, context.database)
    )
}