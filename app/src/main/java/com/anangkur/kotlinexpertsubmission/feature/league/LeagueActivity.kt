package com.anangkur.kotlinexpertsubmission.feature.league

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.feature.favourite.FavouriteActivity
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.LeagueDetailActivity
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.MatchSearchActivity
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import com.anangkur.kotlinexpertsubmission.util.setupRecyclerViewGrid
import com.anangkur.kotlinexpertsubmission.util.showSnackbarLong
import kotlinx.android.synthetic.main.activity_league.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class LeagueActivity: BaseActivity<LeagueViewModel>(), LeagueActionListener {

    override val mLayout: Int
        get() = R.layout.activity_league
    override val mViewModel: LeagueViewModel
        get() = obtainViewModel(LeagueViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = getString(R.string.toolbar_league)

    private lateinit var mAdapter: LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar.navigationIcon = null
        setupAdapter()
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_favourite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_search -> {
                MatchSearchActivity.startActivity(this)
                true
            }
            R.id.menu_favourite -> {
                FavouriteActivity.startActivity(this)
                true
            }
            else -> false
        }
    }

    private fun observeViewModel(){
        mViewModel.getDummyData().observe(this, Observer {
            mAdapter.setRecyclerData(it)
        })
    }

    private fun setupAdapter(){
        mAdapter = LeagueAdapter(this)
        recycler_league.apply {
            adapter = mAdapter
            setupRecyclerViewGrid(this@LeagueActivity)
        }
    }

    override fun onClickItem(data: League) {
        LeagueDetailActivity.startActivity(this, data)
    }

    companion object
}
