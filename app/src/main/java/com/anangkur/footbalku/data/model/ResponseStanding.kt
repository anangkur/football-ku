package com.anangkur.footbalku.data.model

import com.google.gson.annotations.SerializedName

data class ResponseStanding(
    @SerializedName("table")
    val table: List<Table> = listOf()
)