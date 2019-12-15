package com.anangkur.footbalku.data.model

import com.google.gson.annotations.SerializedName

data class ResponseTeamDetail(
    @SerializedName("teams") val teams: List<Team> = listOf()
)