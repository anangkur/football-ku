package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.standings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.base.BaseFragment
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.util.Const.ARGS_LEAGUE
import com.anangkur.kotlinexpertsubmission.util.gone
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import com.anangkur.kotlinexpertsubmission.util.setupRecyclerViewLinear
import com.anangkur.kotlinexpertsubmission.util.visible
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