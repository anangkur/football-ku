package com.anangkur.kotlinexpertsubmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.kotlinexpertsubmission.model.Teams
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = MainAdapter()
        MainActivityUI().setContentView(this)
        mAdapter.setRecyclerData(prepareData())
    }

    inner class MainActivityUI: AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui){
            verticalLayout {
                recyclerView {
                    lparams (width = matchParent, height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = mAdapter
                }
            }
        }
    }

    private fun prepareData(): List<Teams>{
        val data = ArrayList<Teams>()
        data.add(Teams(
            getString(R.string.title_league_american),
            getString(R.string.id_league_american),
            getString(R.string.desc_league_american),
            R.drawable.american_mayor_league
        ))
        data.add(Teams(
            getString(R.string.title_league_australian),
            getString(R.string.id_league_australian),
            getString(R.string.desc_league_australian),
            R.drawable.australian_a_league
        ))
        data.add(Teams(
            getString(R.string.title_league_english_2),
            getString(R.string.id_league_english_2),
            getString(R.string.desc_league_english_2),
            R.drawable.english_league_1
        ))
        data.add(Teams(
            getString(R.string.title_league_english),
            getString(R.string.id_league_english),
            getString(R.string.desc_league_english),
            R.drawable.english_premier_league
        ))
        data.add(Teams(
            getString(R.string.title_league_french),
            getString(R.string.id_league_french),
            getString(R.string.desc_league_french),
            R.drawable.french_ligue_1
        ))
        data.add(Teams(
            getString(R.string.title_league_germany),
            getString(R.string.id_league_germany),
            getString(R.string.desc_league_germany),
            R.drawable.german_bundesliga
        ))
        data.add(Teams(
            getString(R.string.title_league_italy),
            getString(R.string.id_league_italy),
            getString(R.string.desc_league_italy),
            R.drawable.italian_serie_a
        ))
        data.add(Teams(
            getString(R.string.title_league_portuguese),
            getString(R.string.id_league_portuguese),
            getString(R.string.desc_league_portuguese),
            R.drawable.portugeuese_premiera_liga
        ))
        data.add(Teams(
            getString(R.string.title_league_scotish),
            getString(R.string.id_league_scotish),
            getString(R.string.desc_league_scotish),
            R.drawable.scotish_premier_league
        ))
        data.add(Teams(
            getString(R.string.title_league_spanish),
            getString(R.string.id_league_spanish),
            getString(R.string.desc_league_spanish),
            R.drawable.spanish_la_liga
        ))
        return data
    }
}
