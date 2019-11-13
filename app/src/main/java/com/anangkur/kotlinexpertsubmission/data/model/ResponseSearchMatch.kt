package com.anangkur.kotlinexpertsubmission.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSearchMatch(
    @SerializedName("event") val events: List<Event> = listOf()
)