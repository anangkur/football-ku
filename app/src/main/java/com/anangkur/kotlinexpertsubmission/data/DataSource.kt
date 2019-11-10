package com.anangkur.kotlinexpertsubmission.data

import androidx.lifecycle.LiveData
import com.anangkur.kotlinexpertsubmission.data.model.League

interface DataSource {
    fun createDummyLeague(): LiveData<List<League>>{
        throw Exception()
    }
}