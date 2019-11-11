package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.prevMatch

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseFragment
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import com.anangkur.kotlinexpertsubmission.util.setupRecyclerViewLinear
import kotlinx.android.synthetic.main.fragment_match.*
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.util.showSnackbarShort

class PrevMatchFragment: BaseFragment<PrevMatchViewModel>(){
    override val mLayout: Int
        get() = R.layout.fragment_match
    override val mViewModel: PrevMatchViewModel
        get() = obtainViewModel(PrevMatchViewModel::class.java)

    private lateinit var mAdapter: PrevMatchAdapter

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
        mViewModel.getPrevMatch().observe(this@PrevMatchFragment, Observer {result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    swipe_match.isRefreshing = false
                    val data = result.data?.events
                    data?.let { mAdapter.setRecyclerData(it) }
                }
                Result.Status.LOADING -> {
                    swipe_match.isRefreshing = true
                }
                Result.Status.ERROR -> {
                    swipe_match.isRefreshing = false
                    requireActivity().showSnackbarShort(result.message?:"")
                }
            }
        })
    }

    private fun setupAdapter(){
        mAdapter = PrevMatchAdapter()
        recycler_match.adapter = mAdapter
        recycler_match.setupRecyclerViewLinear(requireContext())
    }

    companion object{

        private const val ARGS_LEAGUE = "ARGS_LEAGUE"

        fun newInstance(data: League?) = PrevMatchFragment().apply {
            arguments = Bundle(1).apply {
                putParcelable(ARGS_LEAGUE, data)
            }
        }
    }
}