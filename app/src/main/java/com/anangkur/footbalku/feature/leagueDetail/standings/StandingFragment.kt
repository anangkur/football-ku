package com.anangkur.footbalku.feature.leagueDetail.standings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseErrorView
import com.anangkur.footbalku.base.BaseFragment
import com.anangkur.footbalku.data.model.League
import com.anangkur.footbalku.data.model.Result
import com.anangkur.footbalku.util.Const.ARGS_LEAGUE
import com.anangkur.footbalku.util.gone
import com.anangkur.footbalku.util.obtainViewModel
import com.anangkur.footbalku.util.setupRecyclerViewLinear
import com.anangkur.footbalku.util.visible
import kotlinx.android.synthetic.main.fragment_standing.*

class StandingFragment: BaseFragment<StandingViewModel>(){
    override val mLayout: Int
        get() = R.layout.fragment_standing
    override val mViewModel: StandingViewModel
        get() = obtainViewModel(StandingViewModel::class.java)

    private lateinit var mAdapter: StandingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.dataFromArgs = arguments?.getParcelable(ARGS_LEAGUE)
        setupAdapter()
        observeViewModel()
        mViewModel.refreshData()
        swipe_standing.setOnRefreshListener {
            mViewModel.refreshData()
        }
    }

    private fun setupAdapter(){
        mAdapter = StandingAdapter()
        recycler_standing.adapter = mAdapter
        recycler_standing.setupRecyclerViewLinear(requireContext())
    }

    private fun observeViewModel(){
        mViewModel.getPrevMatch().observe(this@StandingFragment, Observer {result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    swipe_standing.isRefreshing = false
                    error_standing.endProgress()
                    error_standing.gone()
                    swipe_standing.visible()
                    val data = result.data?.table
                    data?.let { mAdapter.setRecyclerData(it) }
                }
                Result.Status.LOADING -> {
                    error_standing.showProgress()
                    error_standing.visible()
                    swipe_standing.gone()
                }
                Result.Status.ERROR -> {
                    swipe_standing.isRefreshing = false
                    error_standing.showError(result.message?:"", errorType = BaseErrorView.ERROR_GENERAL)
                    error_standing.setRetryClickListener { mViewModel.refreshData() }
                }
            }
        })
    }

    companion object{

        fun newInstance(data: League?) = StandingFragment().apply {
            arguments = Bundle(1).apply {
                putParcelable(ARGS_LEAGUE, data)
            }
        }
    }
}