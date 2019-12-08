package com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamFavourite(
    val idTeam: String? = "",
    val strTeam: String? = "",
    val strTeamBadge: String? = "",
    val strWebsite: String? = "",
    val strFacebook: String? = "",
    val strTwitter: String? = "",
    val strInstagram: String? = "",
    val strYoutube: String? = "",
    val strDescriptionEN: String? = "",
    val strTeamFanart1: String? = "",
    val strTeamFanart2: String? = "",
    val strTeamFanart3: String? = "",
    val strTeamFanart4: String? = "",
    val strTeamBanner: String? = ""
): Parcelable{
    companion object{
        const val TABLE_TEAM = "TABLE_TEAM"
        const val COLUMN_ID_TEAM = "COLUMN_ID_TEAM"
        const val COLUMN_NAME_TEAM = "COLUMN_NAME_TEAM"
        const val COLUMN_BADGE_TEAM = "COLUMN_BADGE_TEAM"
        const val COLUMN_WEBSITE_TEAM = "COLUMN_WEBSITE_TEAM"
        const val COLUMN_FACEBOOK_TEAM = "COLUMN_FACEBOOK_TEAM"
        const val COLUMN_TWITER_TEAM = "COLUMN_TWITER_TEAM"
        const val COLUMN_INSTAGRAM_TEAM = "COLUMN_INSTAGRAM_TEAM"
        const val COLUMN_YOUTUBE_TEAM = "COLUMN_YOUTUBE_TEAM"
        const val COLUMN_DESC_TEAM = "COLUMN_DESC_TEAM"
        const val COLUMN_FANART_1_TEAM = "COLUMN_FANART_1_TEAM"
        const val COLUMN_FANART_2_TEAM = "COLUMN_FANART_2_TEAM"
        const val COLUMN_FANART_3_TEAM = "COLUMN_FANART_3_TEAM"
        const val COLUMN_FANART_4_TEAM = "COLUMN_FANART_4_TEAM"
        const val COLUMN_BANNER_TEAM = "COLUMN_BANNER_TEAM"
    }
}