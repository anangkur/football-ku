package com.anangkur.kotlinexpertsubmission.data.local

import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.EventFavourite
import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.data.DataSource
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.MyDatabaseOpenHelper
import com.anangkur.kotlinexpertsubmission.data.local.ankoSqlite.TeamFavourite
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.Result
import org.jetbrains.anko.db.*

class LocalRepository(private val context: Context, private val database: MyDatabaseOpenHelper): DataSource {

    override suspend fun insertTeamFav(data: TeamFavourite): Result<Long> {
        Result.loading(null)
        return try {
            database.use {
                insert(
                    TeamFavourite.TABLE_TEAM,
                    TeamFavourite.COLUMN_ID_TEAM to data.idTeam,
                    TeamFavourite.COLUMN_NAME_TEAM to data.strTeam,
                    TeamFavourite.COLUMN_WEBSITE_TEAM to data.strWebsite,
                    TeamFavourite.COLUMN_FACEBOOK_TEAM to data.strFacebook,
                    TeamFavourite.COLUMN_TWITER_TEAM to data.strTwitter,
                    TeamFavourite.COLUMN_INSTAGRAM_TEAM to data.strInstagram,
                    TeamFavourite.COLUMN_YOUTUBE_TEAM to data.strYoutube,
                    TeamFavourite.COLUMN_DESC_TEAM to data.strDescriptionEN,
                    TeamFavourite.COLUMN_FANART_1_TEAM to data.strTeamFanart1,
                    TeamFavourite.COLUMN_FANART_2_TEAM to data.strTeamFanart2,
                    TeamFavourite.COLUMN_FANART_3_TEAM to data.strTeamFanart3,
                    TeamFavourite.COLUMN_FANART_4_TEAM to data.strTeamFanart4,
                    TeamFavourite.COLUMN_BANNER_TEAM to data.strTeamBanner,
                    TeamFavourite.COLUMN_BADGE_TEAM to data.strTeamBadge
                )
            }
            Result.success(0)
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun selectAllTeamFav(): Result<List<TeamFavourite>> {
        Result.loading(null)
        return try{
            val returnData = ArrayList<TeamFavourite>()
            database.use {
                val result = select(TeamFavourite.TABLE_TEAM)
                returnData.addAll(result.parseList(classParser()))
            }
            Result.success(returnData)
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun deleteTeamFav(id: String): Result<Long> {
        Result.loading(null)
        return try {
            database.use {
                delete(TeamFavourite.TABLE_TEAM, "(${TeamFavourite.COLUMN_ID_TEAM} = {id})", "id" to id)
                Result.success(0)
            }
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun selectTeamById(id: String): Result<TeamFavourite> {
        Result.loading(null)
        return try {
            database.use {
                val result = select(TeamFavourite.TABLE_TEAM)
                    .whereArgs("(${TeamFavourite.COLUMN_ID_TEAM} = {id})", "id" to id)
                val returnResult = result.parseList(classParser<TeamFavourite>())
                if (!returnResult.isNullOrEmpty()){
                    Result.success(returnResult[0])
                }else{
                    Result.error("belum favorit")
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            return Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun selectEventById(id: String): Result<EventFavourite> {
        Result.loading(null)
        return try {
            database.use {
                val result = select(EventFavourite.TABLE_EVENT)
                    .whereArgs("(${EventFavourite.COLUMN_ID} = {id})", "id" to id)
                val returnResult = result.parseList(classParser<EventFavourite>())
                if (!returnResult.isNullOrEmpty()){
                    Result.success(returnResult[0])
                }else{
                    Result.error("belum favorit")
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            return Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun deleteEventFav(id: String): Result<Long> {
        Result.loading(null)
        return try {
            database.use {
                delete(EventFavourite.TABLE_EVENT, "(${EventFavourite.COLUMN_ID} = {id})", "id" to id)
                Result.success(0)
            }
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun selectAllEventFav(): Result<List<EventFavourite>> {
        Result.loading(null)
        return try{
            val returnData = ArrayList<EventFavourite>()
            database.use {
                val result = select(EventFavourite.TABLE_EVENT)
                returnData.addAll(result.parseList(classParser()))
            }
            Result.success(returnData)
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override suspend fun insertEventFav(data: EventFavourite): Result<Long> {
        Result.loading(null)
        return try {
             database.use {
                insert(
                    EventFavourite.TABLE_EVENT,
                    EventFavourite.COLUMN_ID to data.idEvent,
                    EventFavourite.COLUMN_EVENT_NAME to data.strEvent,
                    EventFavourite.COLUMN_ID_HOME_TEAM to data.idHomeTeam,
                    EventFavourite.COLUMN_ID_AWAY_TEAM to data.idAwayTeam,
                    EventFavourite.COLUMN_HOME_SCORE to data.intHomeScore,
                    EventFavourite.COLUMN_AWAY_SCORE to data.intAwayScore,
                    EventFavourite.COLUMN_HOME_TEAM to data.strHomeTeam,
                    EventFavourite.COLUMN_AWAY_TEAM to data.strAwayTeam,
                    EventFavourite.COLUMN_TIME to data.strTime,
                    EventFavourite.COLUMN_DATE to data.dateEvent,
                    EventFavourite.COLUMN_HOME_GOAL to data.strHomeGoalDetails,
                    EventFavourite.COLUMN_AWAY_GOAL to data.strAwayGoalDetails,
                    EventFavourite.COLUMN_HOME_SHOOT to data.intHomeShots,
                    EventFavourite.COLUMN_AWAY_SHOOT to data.intAwayShots,
                    EventFavourite.COLUMN_HOME_FORMATION to data.strHomeFormation,
                    EventFavourite.COLUMN_AWAY_FORMATION to data.strAwayFormation,
                    EventFavourite.COLUMN_HOME_YELLOW to data.strHomeYellowCards,
                    EventFavourite.COLUMN_AWAY_YELLOW to data.strAwayYellowCards,
                    EventFavourite.COLUMN_HOME_RED to data.strHomeRedCards,
                    EventFavourite.COLUMN_AWAY_RED to data.strAwayRedCards,
                    EventFavourite.COLUMN_HOME_KEEPER to data.strHomeLineupGoalkeeper,
                    EventFavourite.COLUMN_AWAY_KEEPER to data.strAwayLineupGoalkeeper,
                    EventFavourite.COLUMN_HOME_DEFENSE to data.strHomeLineupDefense,
                    EventFavourite.COLUMN_AWAY_DEFENSE to data.strAwayLineupDefense,
                    EventFavourite.COLUMN_HOME_MIDFIELD to data.strHomeLineupMidfield,
                    EventFavourite.COLUMN_AWAY_MIDFIELD to data.strAwayLineupMidfield,
                    EventFavourite.COLUMN_HOME_FORWARD to data.strHomeLineupForward,
                    EventFavourite.COLUMN_AWAY_FORWARD to data.strAwayLineupForward,
                    EventFavourite.COLUMN_HOME_SUBTITUTE to data.strHomeLineupSubstitutes,
                    EventFavourite.COLUMN_AWAY_SUBTITUTE to data.strAwayLineupSubstitutes)
            }
            Result.success(0)
        }catch (e: Exception){
            e.printStackTrace()
            Result.error(e.message?:"Terjadi kesalahan")
        }
    }

    override fun createDummyLeague(): LiveData<List<League>> {
        val leagueListLiveData = MutableLiveData<List<League>>()
        val leagueList = ArrayList<League>()
        leagueList.add(League(
            context.getString(R.string.id_league_spanish),
            context.getString(R.string.title_league_spanish),
            context.getString(R.string.desc_league_spanish),
            R.drawable.img_spanish_la_liga,
            R.color.light_greenish_blue
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_scotish),
            context.getString(R.string.title_league_scotish),
            context.getString(R.string.desc_league_scotish),
            R.drawable.img_scotish_premier_league,
            R.color.faded_poster
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_portuguese),
            context.getString(R.string.title_league_portuguese),
            context.getString(R.string.desc_league_portuguese),
            R.drawable.img_portugeuese_premiera_liga,
            R.color.green_darner_tail
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_italy),
            context.getString(R.string.title_league_italy),
            context.getString(R.string.desc_league_italy),
            R.drawable.img_italian_serie_a,
            R.color.shy_moment
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_germany),
            context.getString(R.string.title_league_germany),
            context.getString(R.string.desc_league_germany),
            R.drawable.img_german_bundesliga,
            R.color.city_light
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_french),
            context.getString(R.string.title_league_french),
            context.getString(R.string.desc_league_french),
            R.drawable.img_french_ligue_1,
            R.color.shooting_breeze
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_english_2),
            context.getString(R.string.title_league_english_2),
            context.getString(R.string.desc_league_english_2),
            R.drawable.img_english_league_1,
            R.color.sour_lemon
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_australian),
            context.getString(R.string.title_league_australian),
            context.getString(R.string.desc_league_australian),
            R.drawable.img_australian_a_league,
            R.color.first_date
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_american),
            context.getString(R.string.title_league_american),
            context.getString(R.string.desc_league_american),
            R.drawable.img_american_mayor_league,
            R.color.pico_8_pink
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_english),
            context.getString(R.string.title_league_english),
            context.getString(R.string.desc_league_english),
            R.drawable.img_english_premier_league,
            R.color.pink_glamour
        ))
        leagueListLiveData.value = leagueList
        return leagueListLiveData
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: LocalRepository? = null
        fun getInstance(context: Context, database: MyDatabaseOpenHelper) = INSTANCE ?: LocalRepository(context, database)
    }
}