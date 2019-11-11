package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
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
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.anangkur.kotlinexpertsubmission.util.*

class LeagueDetailActivity: BaseActivity<LeagueDetailViewModel>(), LeagueDetailActionListener {

    override val mLayout: Int
        get() = R.layout.activity_league_detail
    override val mViewModel: LeagueDetailViewModel
        get() = obtainViewModel(LeagueDetailViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    private lateinit var pagerAdapter: LeagueSliderTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewPagerSlider()
        getDataFromIntent()
        observeViewModel()
        setupCollapsingToolbar()
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
                            pb_detail_league.gone()
                            pb_slider_league.gone()
                            layout_content.visible()
                            vp_slider.visible()
                            fab_logo.visible()
                            btn_facebook.visible()
                            btn_twitter.visible()
                            btn_website.visible()
                            val data = result.data?.leagues?.get(0)
                            createListSlider(data?.strFanart1, data?.strFanart2, data?.strFanart3, data?.strFanart4, data?.strBadge)
                            setupDataToView(data?.strLeague?:"", data?.strDescriptionEN?:"", data?.strBadge?:"")
                            btn_facebook.setOnClickListener { this@LeagueDetailActivity.onClickWebsite(data?.strFacebook?:"") }
                            btn_twitter.setOnClickListener { this@LeagueDetailActivity.onClickWebsite(data?.strTwitter?:"") }
                            btn_website.setOnClickListener { this@LeagueDetailActivity.onClickWebsite(data?.strWebsite?:"") }
                        }
                        Result.Status.LOADING -> {
                            pb_slider_league.visible()
                            pb_detail_league.visible()
                            layout_content.gone()
                            vp_slider.gone()
                            fab_logo.gone()
                            btn_facebook.gone()
                            btn_twitter.gone()
                            btn_website.gone()
                        }
                        Result.Status.ERROR -> {
                            pb_detail_league.gone()
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

    private fun setupDataToView(title: String, description: String, image: String){
        tv_title_league.text = title
        tv_desc_league.text = description
        fab_logo.setImageUrl(image)
    }

    override fun onClickWebsite(url: String) {
        try{
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }catch (e: Exception){
            showSnackbarLong(e.message?:"")
        }
    }

    companion object{

        private const val EXTRA_DETAIL_LEAGUE = "EXTRA_DETAIL_LEAGUE"

        fun startActivity(context: Context, data: League){
            context.startActivity(Intent(context, LeagueDetailActivity::class.java)
                .putExtra(EXTRA_DETAIL_LEAGUE, data))
        }
    }
}
