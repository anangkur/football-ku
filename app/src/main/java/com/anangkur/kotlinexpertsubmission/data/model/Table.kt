package com.anangkur.kotlinexpertsubmission.data.model

import com.google.gson.annotations.SerializedName

data class Table(
    
    @SerializedName("name")
    val name: String = "",
    
    @SerializedName("teamid")
    val teamid: String = "",
    
    @SerializedName("played")
    val played: Int = 0,
    
    @SerializedName("goalsfor")
    val goalsfor: Int = 0,
    
    @SerializedName("goalsagainst")
    val goalsagainst: Int = 0,
    
    @SerializedName("goalsdifference")
    val goalsdifference: Int = 0,
    
    @SerializedName("win")
    val win: Int = 0,
    
    @SerializedName("draw")
    val draw: Int = 0,
    
    @SerializedName("loss")
    val loss: Int = 0,
    
    @SerializedName("total")
    val total: Int = 0
)