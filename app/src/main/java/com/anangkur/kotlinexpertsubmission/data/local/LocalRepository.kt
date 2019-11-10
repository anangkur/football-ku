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
            R.drawable.img_spanish_la_liga
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_scotish),
            context.getString(R.string.title_league_scotish),
            context.getString(R.string.desc_league_scotish),
            R.drawable.img_scotish_premier_league
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_portuguese),
            context.getString(R.string.title_league_portuguese),
            context.getString(R.string.desc_league_portuguese),
            R.drawable.img_portugeuese_premiera_liga
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_italy),
            context.getString(R.string.title_league_italy),
            context.getString(R.string.desc_league_italy),
            R.drawable.img_italian_serie_a
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_germany),
            context.getString(R.string.title_league_germany),
            context.getString(R.string.desc_league_germany),
            R.drawable.img_german_bundesliga
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_french),
            context.getString(R.string.title_league_french),
            context.getString(R.string.desc_league_french),
            R.drawable.img_french_ligue_1
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_english_2),
            context.getString(R.string.title_league_english_2),
            context.getString(R.string.desc_league_english_2),
            R.drawable.img_english_league_1
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_australian),
            context.getString(R.string.title_league_australian),
            context.getString(R.string.desc_league_australian),
            R.drawable.img_australian_a_league
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_american),
            context.getString(R.string.title_league_american),
            context.getString(R.string.desc_league_american),
            R.drawable.img_american_mayor_league
        ))
        leagueList.add(League(
            context.getString(R.string.id_league_english),
            context.getString(R.string.title_league_english),
            context.getString(R.string.desc_league_english),
            R.drawable.img_english_premier_league
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