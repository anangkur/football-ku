package com.anangkur.kotlinexpertsubmission.data.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.data.DataSource
import com.anangkur.kotlinexpertsubmission.data.model.League

class LocalRepository(private val context: Context): DataSource {

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
        fun getInstance(context: Context) = INSTANCE ?: LocalRepository(context)
    }
}