package com.anangkur.kotlinexpertsubmission.feature.league

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import com.anangkur.kotlinexpertsubmission.util.setupRecyclerViewGrid
import kotlinx.android.synthetic.main.activity_league.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class LeagueActivity: BaseActivity<LeagueViewModel>() {
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

        setupAdapter()
    }

    override fun onStart() {
        super.onStart()
        observeViewModel()
    }

    private fun observeViewModel(){
        mViewModel.getDummyData().observe(this, Observer {
            mAdapter.setRecyclerData(it)
        })
    }

    private fun setupAdapter(){
        mAdapter = LeagueAdapter()
        recycler_league.apply {
            adapter = mAdapter
            setupRecyclerViewGrid(this@LeagueActivity)
        }
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, LeagueActivity::class.java))
        }
    }
}
