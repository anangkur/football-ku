package com.anangkur.kotlinexpertsubmission.feature.favourite.match

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.base.BaseFragment
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchAdapter
import kotlinx.android.synthetic.main.fragment_fav.*
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.data.model.Team
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.feature.teamDetail.TeamDetailActivity
import com.anangkur.kotlinexpertsubmission.util.*

class FavouriteMatchFragment: BaseFragment<FavouriteMatchViewModel>(), MatchActionListener{
    override val mLayout: Int
        get() = R.layout.fragment_fav
    override val mViewModel: FavouriteMatchViewModel
        get() = obtainViewModel(FavouriteMatchViewModel::class.java)

    private lateinit var mAdapter: NextMatchAdapter

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
            selectAllEvent().observe(this@FavouriteMatchFragment, Observer {
                when(it.status){
                    Result.Status.SUCCESS -> {
                        error_event_fav.endProgress()
                        error_event_fav.gone()
                        swipe_favourite.visible()
                        val data = it.data
                        if (data.isNullOrEmpty()){
                            error_event_fav.visible()
                            swipe_favourite.gone()
                            error_event_fav.showError(getString(R.string.error_favourite_null),
                                errorType = BaseErrorView.ERROR_NULL_DATA)
                        }else{
                            mAdapter.setRecyclerData(data.map { event -> event.toEvent() })
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
        mAdapter = NextMatchAdapter(this)
        recycler_favourite.adapter = mAdapter
        recycler_favourite.setupRecyclerViewLinear(requireContext())
    }

    override fun onClickMatch(data: Event) {
        super.onClickMatch(data)
        MatchDetailActivity.startActivity(requireContext(), data)
    }

    companion object{
        fun newInstance() = FavouriteMatchFragment()
    }
}