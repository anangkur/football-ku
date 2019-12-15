package com.anangkur.footbalku.feature.leagueDetail.team

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseErrorView
import com.anangkur.footbalku.base.BaseFragment
import com.anangkur.footbalku.data.model.League
import kotlinx.android.synthetic.main.fragment_match.*
import com.anangkur.footbalku.data.model.Result
import com.anangkur.footbalku.data.model.Team
import com.anangkur.footbalku.feature.leagueDetail.MatchActionListener
import com.anangkur.footbalku.feature.teamDetail.TeamDetailActivity
import com.anangkur.footbalku.util.*
import com.anangkur.footbalku.util.Const.ARGS_LEAGUE

class TeamFragment: BaseFragment<TeamViewModel>(), MatchActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_match
    override val mViewModel: TeamViewModel
        get() = obtainViewModel(TeamViewModel::class.java)

    private lateinit var mAdapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.dataFromArgs = arguments?.getParcelable(ARGS_LEAGUE)
        setupAdapter()
        observeViewModel()
        mViewModel.refreshData()
        swipe_match.setOnRefreshListener {
            mViewModel.refreshData()
        }
    }

    private fun observeViewModel(){
        mViewModel.getTeamList().observe(this@TeamFragment, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    swipe_match.isRefreshing = false
                    error_match.endProgress()
                    error_match.gone()
                    swipe_match.visible()
                    val data = result.data?.teams
                    data?.let { mAdapter.setRecyclerData(it) }
                }
                Result.Status.LOADING -> {
                    error_match.showProgress()
                    error_match.visible()
                    swipe_match.gone()
                }
                Result.Status.ERROR -> {
                    swipe_match.isRefreshing = false
                    error_match.showError(result.message?:"", errorType = BaseErrorView.ERROR_GENERAL)
                    error_match.setRetryClickListener { mViewModel.refreshData() }
                }
            }
        })
    }

    private fun setupAdapter(){
        mAdapter = TeamAdapter(this)
        recycler_match.adapter = mAdapter
        recycler_match.setupRecyclerViewGrid(requireContext(), 3)
    }

    override fun onClickTeam(data: Team) {
        TeamDetailActivity.startActivity(requireContext(), data)
    }

    companion object{

        fun newInstance(data: League?) = TeamFragment().apply {
            arguments = Bundle(1).apply {
                putParcelable(ARGS_LEAGUE, data)
            }
        }
    }
}