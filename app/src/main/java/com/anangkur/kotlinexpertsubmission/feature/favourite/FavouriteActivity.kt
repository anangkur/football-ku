package com.anangkur.kotlinexpertsubmission.feature.favourite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchAdapter
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.util.gone
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import com.anangkur.kotlinexpertsubmission.util.setupRecyclerViewLinear
import com.anangkur.kotlinexpertsubmission.util.visible
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class FavouriteActivity: BaseActivity<FavouriteViewModel>(), MatchActionListener {

    override val mLayout: Int
        get() = R.layout.activity_favourite
    override val mViewModel: FavouriteViewModel
        get() = obtainViewModel(FavouriteViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = getString(R.string.toolbar_favourite)

    private lateinit var mAdapter: NextMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
        setupAdapter()
        mViewModel.refreshData()
        swipe_favourite.setOnRefreshListener {
            mViewModel.refreshData()
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            selectAllEvent().observe(this@FavouriteActivity, Observer {
                when(it.status){
                    Result.Status.SUCCESS -> {
                        error_event_fav.endProgress()
                        error_event_fav.gone()
                        swipe_favourite.visible()
                        val data = it.data?.map { event -> Event(
                            idEvent = event.idEvent,
                            dateEvent = event.dateEvent,
                            intAwayScore = event.intAwayScore,
                            intAwayShots = event.intAwayShots,
                            intHomeScore = event.intHomeScore,
                            intHomeShots = event.intHomeShots,
                            strAwayFormation = event.strAwayFormation,
                            strAwayGoalDetails = event.strAwayGoalDetails,
                            strAwayLineupDefense = event.strAwayLineupDefense,
                            strAwayLineupForward = event.strAwayLineupForward,
                            strAwayLineupGoalkeeper = event.strAwayLineupGoalkeeper,
                            strAwayLineupMidfield = event.strAwayLineupMidfield,
                            strAwayLineupSubstitutes = event.strAwayLineupSubstitutes,
                            strAwayRedCards = event.strAwayRedCards,
                            strAwayTeam = event.strAwayTeam,
                            strAwayYellowCards = event.strAwayYellowCards,
                            strHomeFormation = event.strHomeFormation,
                            strHomeGoalDetails = event.strHomeGoalDetails,
                            strHomeLineupDefense = event.strHomeLineupDefense,
                            strHomeLineupForward = event.strHomeLineupForward,
                            strHomeLineupGoalkeeper = event.strHomeLineupGoalkeeper,
                            strHomeLineupMidfield = event.strHomeLineupMidfield,
                            strHomeLineupSubstitutes = event.strHomeLineupSubstitutes,
                            strHomeRedCards = event.strHomeRedCards,
                            strHomeTeam = event.strHomeTeam,
                            strHomeYellowCards = event.strHomeYellowCards,
                            strTime = event.strTime
                        ) }
                        data?.let { it1 -> mAdapter.setRecyclerData(it1) }
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
        recycler_favourite.setupRecyclerViewLinear(this)
    }

    override fun onClickMatch(data: Event) {
        MatchDetailActivity.startActivity(this, data)
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, FavouriteActivity::class.java))
        }
    }
}
