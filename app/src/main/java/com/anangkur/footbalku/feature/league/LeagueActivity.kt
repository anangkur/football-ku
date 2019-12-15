package com.anangkur.footbalku.feature.league

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseActivity
import com.anangkur.footbalku.data.model.League
import com.anangkur.footbalku.feature.favourite.FavouriteActivity
import com.anangkur.footbalku.feature.leagueDetail.LeagueDetailActivity
import com.anangkur.footbalku.feature.matchSearch.SearchActivity
import com.anangkur.footbalku.util.obtainViewModel
import com.anangkur.footbalku.util.setupRecyclerViewGrid
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
                SearchActivity.startActivity(this)
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
            setupRecyclerViewGrid(this@LeagueActivity, 2)
        }
    }

    override fun onClickItem(data: League) {
        LeagueDetailActivity.startActivity(this, data)
    }

    companion object
}
