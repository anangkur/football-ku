package com.anangkur.footbalku.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val id: String,
    val title: String,
    val description: String,
    val image: Int,
    val backgroundColor: Int
): Parcelable