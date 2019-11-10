package com.anangkur.kotlinexpertsubmission.data.model


import com.google.gson.annotations.SerializedName

data class ResponseLeagueDetail(
    @SerializedName("leagues") val leagues: List<LeagueDetail> = listOf()
)