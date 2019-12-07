package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.core.content.res.ResourcesCompat
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.LeagueDetail
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderFragment
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.custom.DefaultTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.detail.DetailLeagueFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.prevMatch.PrevMatchFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.standings.StandingFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.team.TeamFragment
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity: BaseActivity<LeagueDetailViewModel>(){

    override val mLayout: Int
        get() = R.layout.activity_league_detail
    override val mViewModel: LeagueDetailViewModel
        get() = obtainViewModel(LeagueDetailViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    private lateinit var pagerAdapter: LeagueSliderTabAdapter
    private lateinit var tabAdapter: DefaultTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewPagerSlider()
        getDataFromIntent()
        observeViewModel()
        setupCollapsingToolbar()
        setupTabAdapter()
        mViewModel.refreshData()
    }

    private fun setupCollapsingToolbar(){
        toolbar.title = mViewModel.dataFromIntent?.title
        collapsing_toolbar.apply {
            setCollapsedTitleTypeface(ResourcesCompat.getFont(this@LeagueDetailActivity, R.font.comfortaa_bold))
            setExpandedTitleTypeface(ResourcesCompat.getFont(this@LeagueDetailActivity, R.font.comfortaa_bold))
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            dataFromIntent?.let {
                getLeagueDetail().observe(this@LeagueDetailActivity, Observer {result ->
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            error_slider.endProgress()
                            collapsing_toolbar.visible()
                            error_slider.gone()
                            setupDataToView(result.data?.leagues?.get(0))
                        }
                        Result.Status.LOADING -> {
                            error_slider.showProgress()
                            collapsing_toolbar.gone()
                            error_slider.visible()
                        }
                        Result.Status.ERROR -> {
                            error_slider.showError(result.message?:"", errorType = BaseErrorView.ERROR_GENERAL)
                            error_slider.setRetryClickListener {
                                mViewModel.refreshData()
                            }
                            supportActionBar?.title = ""
                        }
                    }
                })
            }
            listSliderLive.observe(this@LeagueDetailActivity, Observer {
                for (data in it){
                    pagerAdapter.addFragment(LeagueSliderFragment.getInstance(data))
                    setupSliderPage(pagerAdapter)
                }
            })
        }
    }

    private fun getDataFromIntent(){
        if (intent.hasExtra(EXTRA_DETAIL_LEAGUE)){
            mViewModel.dataFromIntent = intent.getParcelableExtra(EXTRA_DETAIL_LEAGUE)
        }
    }

    private fun setupViewPagerSlider(){
        pagerAdapter = LeagueSliderTabAdapter(supportFragmentManager)
    }

    private fun setupSliderPage(pagerAdapter: LeagueSliderTabAdapter){
        vp_slider.adapter = pagerAdapter
        tab_slider.setupWithViewPager(vp_slider, true)
        tab_slider.disableClickTablayout()
    }

    private fun setupTabAdapter(){
        tabAdapter = DefaultTabAdapter(supportFragmentManager)
    }

    private fun setupDataToView(data: LeagueDetail?){
        mViewModel.createListSlider(data?.strFanart1, data?.strFanart2, data?.strFanart3, data?.strFanart4, data?.strBadge)
        tabAdapter.addFragment(DetailLeagueFragment.newInstance(data), getString(R.string.tab_description))
        tabAdapter.addFragment(NextMatchFragment.newInstance(mViewModel.dataFromIntent), getString(R.string.tab_next_match))
        tabAdapter.addFragment(PrevMatchFragment.newInstance(mViewModel.dataFromIntent), getString(R.string.tab_prev_match))
        tabAdapter.addFragment(StandingFragment.newInstance(mViewModel.dataFromIntent), getString(R.string.tab_clasement))
        tabAdapter.addFragment(TeamFragment.newInstance(mViewModel.dataFromIntent), getString(R.string.tab_team))
        vp_layout.adapter = tabAdapter
        tab_detail.setupWithViewPager(vp_layout)
    }

    companion object{

        private const val EXTRA_DETAIL_LEAGUE = "EXTRA_DETAIL_LEAGUE"

        fun startActivity(context: Context, data: League){
            context.startActivity(Intent(context, LeagueDetailActivity::class.java)
                .putExtra(EXTRA_DETAIL_LEAGUE, data))
        }
    }
}
