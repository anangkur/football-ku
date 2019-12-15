package com.anangkur.footbalku.data.model

import com.google.gson.annotations.SerializedName

data class ResponseMatch(
    @SerializedName("events") val events: List<Event> = listOf()
)