package com.anangkur.kotlinexpertsubmission.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMatch(
    @SerializedName("events") val events: List<Event> = listOf()
)