package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.data.model.League
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderFragment
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderTabAdapter
import kotlinx.android.synthetic.main.activity_league_detail.*
import androidx.core.content.res.ResourcesCompat
import com.anangkur.kotlinexpertsubmission.feature.custom.DefaultTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.detail.DetailLeagueFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.prevMatch.PrevMatchFragment
import com.anangkur.kotlinexpertsubmission.util.*

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
                getLeagueDetail(it.id).observe(this@LeagueDetailActivity, Observer {result ->
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            pb_slider_league.gone()
                            vp_slider.visible()
                            val data = result.data?.leagues?.get(0)
                            createListSlider(data?.strFanart1, data?.strFanart2, data?.strFanart3, data?.strFanart4, data?.strBadge)
                            tabAdapter.addFragment(DetailLeagueFragment.newInstance(data), getString(R.string.tab_description))
                            tabAdapter.addFragment(NextMatchFragment.newInstance(dataFromIntent), getString(R.string.tab_next_match))
                            tabAdapter.addFragment(PrevMatchFragment.newInstance(dataFromIntent), getString(R.string.tab_prev_match))
                            vp_layout.adapter = tabAdapter
                            tab_detail.setupWithViewPager(vp_layout)
                        }
                        Result.Status.LOADING -> {
                            pb_slider_league.visible()
                            vp_slider.gone()
                        }
                        Result.Status.ERROR -> {
                            pb_slider_league.gone()
                            showSnackbarLong(result.message?:"")
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

    companion object{

        private const val EXTRA_DETAIL_LEAGUE = "EXTRA_DETAIL_LEAGUE"

        fun startActivity(context: Context, data: League){
            context.startActivity(Intent(context, LeagueDetailActivity::class.java)
                .putExtra(EXTRA_DETAIL_LEAGUE, data))
        }
    }
}
