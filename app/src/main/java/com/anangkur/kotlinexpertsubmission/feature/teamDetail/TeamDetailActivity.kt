package com.anangkur.kotlinexpertsubmission.feature.teamDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.data.model.Team
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderFragment
import com.anangkur.kotlinexpertsubmission.feature.custom.LeagueSliderTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.detail.DetailLeagueActionListener
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActionListener
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity: BaseActivity<TeamDetailViewModel>(), DetailLeagueActionListener, MatchDetailActionListener {

    override val mLayout: Int
        get() = R.layout.activity_team_detail
    override val mViewModel: TeamDetailViewModel
        get() = obtainViewModel(TeamDetailViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    private var menu: Menu? = null
    private lateinit var pagerAdapter: LeagueSliderTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewPagerSlider()
        getDataFromIntent()
        observeViewModel()
        setupCollapsingToolbar()
        mViewModel.refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favourite, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_favourite -> {
                mViewModel.dataFromIntent?.let { this.onClickFavourite(it) }
                true
            }
            else -> false
        }
    }

    private fun setupCollapsingToolbar(){
        toolbar.title = mViewModel.dataFromIntent?.strTeam
        collapsing_toolbar.apply {
            setCollapsedTitleTypeface(ResourcesCompat.getFont(this@TeamDetailActivity, R.font.comfortaa_bold))
            setExpandedTitleTypeface(ResourcesCompat.getFont(this@TeamDetailActivity, R.font.comfortaa_bold))
        }
    }

    private fun observeViewModel(){
        mViewModel.apply {
            dataFromIntent?.let {
                getTeamDetail().observe(this@TeamDetailActivity, Observer { result ->
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            error_slider.endProgress()
                            error_slider.gone()
                            error_content.endProgress()
                            error_content.gone()
                            collapsing_toolbar.visible()
                            content.visible()
                            setupDataToView(result.data?.teams?.get(0))
                        }
                        Result.Status.LOADING -> {
                            error_slider.showProgress()
                            error_slider.visible()
                            error_content.showProgress()
                            error_content.visible()
                            collapsing_toolbar.gone()
                            content.gone()
                        }
                        Result.Status.ERROR -> {
                            error_slider.invisible()
                            error_content.showError(result.message?:"",
                                errorType = BaseErrorView.ERROR_GENERAL)
                            error_content.setRetryClickListener {
                                mViewModel.refreshData()
                            }
                            supportActionBar?.title = ""
                        }
                    }
                })
                mViewModel.selectTeamById().observe(this@TeamDetailActivity, Observer {
                    when(it.status){
                        Result.Status.LOADING -> {

                        }
                        Result.Status.SUCCESS -> {
                            mViewModel.isFavourite = true
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_white_24dp) }
                        }
                        Result.Status.ERROR -> {
                            mViewModel.isFavourite = false
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_border_white_24dp) }
                        }
                    }
                })
                mViewModel.insertEvent().observe(this@TeamDetailActivity, Observer {
                    when(it.status){
                        Result.Status.LOADING -> {

                        }
                        Result.Status.SUCCESS -> {
                            showSnackbarLong(getString(R.string.message_add_fav_success))
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_white_24dp) }
                        }
                        Result.Status.ERROR -> {
                            showSnackbarLong(getString(R.string.message_add_fav_fail))
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_border_white_24dp) }
                        }
                    }
                })
                mViewModel.deleteEvent().observe(this@TeamDetailActivity, Observer {
                    when(it.status){
                        Result.Status.LOADING -> {

                        }
                        Result.Status.SUCCESS -> {
                            showSnackbarLong(getString(R.string.message_delete_fav_success))
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_border_white_24dp) }
                        }
                        Result.Status.ERROR -> {
                            showSnackbarLong(getString(R.string.message_delete_fav_fail))
                            menu?.let { menu -> menu.getItem(0).icon = ContextCompat.getDrawable(this@TeamDetailActivity, R.drawable.ic_favorite_white_24dp) }
                        }
                    }
                })
            }
            listSliderLive.observe(this@TeamDetailActivity, Observer {
                for (data in it){
                    pagerAdapter.addFragment(LeagueSliderFragment.getInstance(data))
                    setupSliderPage(pagerAdapter)
                }
            })
        }
    }

    private fun getDataFromIntent(){
        if (intent.hasExtra(EXTRA_DETAIL_TEAM)){
            mViewModel.dataFromIntent = intent.getParcelableExtra(EXTRA_DETAIL_TEAM)
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

    private fun setupDataToView(data: Team?){
        mViewModel.createListSlider(
            data?.strTeamFanart1,
            data?.strTeamFanart2,
            data?.strTeamFanart3,
            data?.strTeamFanart4,
            data?.strTeamBadge
        )
        iv_league.setImageUrl(data?.strTeamBadge?:"")
        tv_title_league.text = data?.strTeam
        tv_desc_league.text = data?.strDescriptionEN
        btn_facebook.setOnClickListener { this.onClickWebsite(data?.strFacebook?:"") }
        btn_twitter.setOnClickListener { this.onClickWebsite(data?.strTwitter?:"") }
        btn_website.setOnClickListener { this.onClickWebsite(data?.strWebsite?:"") }
    }

    companion object{

        private const val EXTRA_DETAIL_TEAM = "EXTRA_DETAIL_TEAM"

        fun startActivity(context: Context, data: Team){
            context.startActivity(Intent(context, TeamDetailActivity::class.java)
                .putExtra(EXTRA_DETAIL_TEAM, data))
        }
    }

    override fun onClickWebsite(url: String) {
        try{
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://$url")
            startActivity(i)
        }catch (e: Exception){
            showSnackbarLong(e.message?:"")
        }
    }

    override fun onClickFavourite(data: Team) {
        if (mViewModel.isFavourite){
             mViewModel.deleteTeamData(data.toTeamFavourite())
            mViewModel.isFavourite = false
        }else{
             mViewModel.insertTeamData(data.toTeamFavourite())
            mViewModel.isFavourite = true
        }
    }
}
