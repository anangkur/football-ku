package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import androidx.lifecycle.ViewModel
import com.anangkur.kotlinexpertsubmission.data.Repository
import com.anangkur.kotlinexpertsubmission.data.model.Event

class MatchDetailViewModel(private val repository: Repository): ViewModel(){
    var dataFromIntent: Event? = null
}