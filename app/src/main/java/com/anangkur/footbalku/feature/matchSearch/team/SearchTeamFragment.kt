package com.anangkur.footbalku.feature.matchSearch.team

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anangkur.footbalku.R
import com.anangkur.footbalku.base.BaseErrorView
import com.anangkur.footbalku.base.BaseFragment
import com.anangkur.footbalku.feature.leagueDetail.MatchActionListener
import com.anangkur.footbalku.feature.matchSearch.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import com.anangkur.footbalku.data.model.Result
import com.anangkur.footbalku.data.model.Team
import com.anangkur.footbalku.feature.leagueDetail.team.TeamAdapter
import com.anangkur.footbalku.feature.matchSearch.SearchActivity
import com.anangkur.footbalku.feature.teamDetail.TeamDetailActivity
import com.anangkur.footbalku.util.*

class SearchTeamFragment: BaseFragment<SearchViewModel>(), MatchActionListener{

    override val mLayout: Int
        get() = R.layout.fragment_search
    override val mViewModel: SearchViewModel
        get() = (requireActivity() as SearchActivity).mViewModel

    private lateinit var mAdapter: TeamAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        observeViewModel()
    }

    private fun setupAdapter(){
        mAdapter = TeamAdapter(this)
        recycler_search.adapter = mAdapter
        recycler_search.setupRecyclerViewGrid(requireContext(), 3)
    }

    private fun observeViewModel(){
        mViewModel.getSearchTeam().observe(this, Observer {result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    val data = result.data?.teams
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

    override fun onClickTeam(data: Team) {
        super.onClickTeam(data)
        TeamDetailActivity.startActivity(requireContext(), data)
    }

    companion object{
        fun newInstance() = SearchTeamFragment()
    }
}