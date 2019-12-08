package com.anangkur.kotlinexpertsubmission.feature.matchSearch.match

import com.anangkur.kotlinexpertsubmission.data.model.Result
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.base.BaseFragment
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchAdapter
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.SearchActivity
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.SearchViewModel
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchMatchFragment: BaseFragment<SearchViewModel>(), MatchActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_search
    override val mViewModel: SearchViewModel
        get() = (requireActivity() as SearchActivity).mViewModel

    private lateinit var mAdapter: NextMatchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
    }

    private fun setupAdapter(){
        mAdapter = NextMatchAdapter(this)
        recycler_search.adapter = mAdapter
        recycler_search.setupRecyclerViewLinear(requireContext())
    }

    private fun observeViewModel(){
        mViewModel.getSearchMatch().observe(this, Observer {result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    val data = result.data?.events
                    val dataFiltered = data?.filter { it.strSport == Const.SPORT_SOCCER }
                    if (dataFiltered.isNullOrEmpty()){
                        error_search.showError(getString(R.string.error_data_null), errorType = BaseErrorView.ERROR_NULL_DATA)
                    }else{
                        error_search.endProgress()
                        error_search.gone()
                        recycler_search.visible()
                        mAdapter.setRecyclerData(data)
                    }
                }
                Result.Status.LOADING -> {
                    error_search.showProgress()
                    error_search.visible()
                    recycler_search.gone()
                }
                Result.Status.ERROR -> {
                    error_search.showError(result.message?:"", errorType = BaseErrorView.ERROR_NULL_DATA)
                }
            }
        })
    }

    override fun onClickMatch(data: Event) {
        super.onClickMatch(data)
        MatchDetailActivity.startActivity(requireContext(), data)
    }

    companion object{
        fun newInstance() = SearchMatchFragment()
    }
}