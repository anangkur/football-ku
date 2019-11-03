package com.anangkur.kotlinexpertsubmission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    val name: String,
    val id: String,
    val desc: String,
    val image: Int
) : Parcelable