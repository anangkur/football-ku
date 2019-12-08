package com.anangkur.kotlinexpertsubmission.feature.favourite.team

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_fav.*
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.data.model.Team
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.team.TeamAdapter
import com.anangkur.kotlinexpertsubmission.feature.teamDetail.TeamDetailActivity
import com.anangkur.kotlinexpertsubmission.util.*

class FavouriteTeamFragment: BaseFragment<FavouriteTeamViewModel>(), MatchActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_fav
    override val mViewModel: FavouriteTeamViewModel
        get() = obtainViewModel(FavouriteTeamViewModel::class.java)

    private lateinit var mAdapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupAdapter()
        swipe_favourite.setOnRefreshListener {
            mViewModel.refreshData()
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.refreshData()
    }

    private fun observeViewModel(){
        mViewModel.apply {
            selectAllEvent().observe(this@FavouriteTeamFragment, Observer {
                when(it.status){
                    Result.Status.SUCCESS -> {
                        error_event_fav.endProgress()
                        error_event_fav.gone()
                        swipe_favourite.visible()
                        val data = it.data
                        if (data.isNullOrEmpty()){
                            error_event_fav.visible()
                            swipe_favourite.gone()
                            error_event_fav.showError(getString(R.string.error_favourite_null), errorType = BaseErrorView.ERROR_NULL_DATA)
                        }else{
                            mAdapter.setRecyclerData(data.map { team -> team.toTeam() })
                        }
                    }
                    Result.Status.LOADING -> {
                        error_event_fav.showProgress()
                        error_event_fav.visible()
                        swipe_favourite.gone()
                    }
                    Result.Status.ERROR -> {
                        swipe_favourite.isRefreshing = false
                        error_event_fav.showError(it.message?:"", errorType = BaseErrorView.ERROR_GENERAL)
                        error_event_fav.setRetryClickListener { mViewModel.refreshData() }
                    }
                }
            })
        }
    }

    private fun setupAdapter(){
        mAdapter = TeamAdapter(this)
        recycler_favourite.adapter = mAdapter
        recycler_favourite.setupRecyclerViewGrid(requireContext(), 3)
    }

    override fun onClickTeam(data: Team) {
        super.onClickTeam(data)
        TeamDetailActivity.startActivity(requireContext(), data)
    }

    companion object{
        fun newInstance() = FavouriteTeamFragment()
    }
}