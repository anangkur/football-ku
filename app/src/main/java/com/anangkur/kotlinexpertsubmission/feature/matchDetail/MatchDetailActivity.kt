package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.MatchSearchActivity
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity: BaseActivity<MatchDetailViewModel>() {
    override val mLayout: Int
        get() = R.layout.activity_match_detail
    override val mViewModel: MatchDetailViewModel
        get() = obtainViewModel(MatchDetailViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataFromIntent()
        toolbar.setNavigationOnClickListener { onBackPressed() }
        observeViewModel()
        mViewModel.refreshData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favourite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_favourite -> {
                showSnackbarLong("test fav")
                true
            }
            else -> false
        }
    }

    private fun getDataFromIntent(){
        mViewModel.dataFromIntent = intent.getParcelableExtra(EXTRA_EVENT)
    }

    private fun observeViewModel(){
        mViewModel.getDetailMatch().observe(this, Observer {
            when(it.status){
                Result.Status.LOADING -> {
                    error_match_detail_2.visible()
                    error_match_detail_2.showProgress()
                    layout_content_body.gone()
                }
                Result.Status.SUCCESS -> {
                    error_match_detail_2.endProgress()
                    error_match_detail_2.gone()
                    layout_content_body.visible()
                    it.data?.events?.get(0)?.let { it1 -> setupDataToView(it1) }
                }
                Result.Status.ERROR -> {
                    error_match_detail_2.showError(it.message?:"", errorType = BaseErrorView.ERROR_GENERAL)
                    error_match_detail_2.setRetryClickListener { mViewModel.refreshData() }
                }
            }
        })
        mViewModel.getHomeTeam().observe(this, Observer {
            when(it.status){
                Result.Status.LOADING -> {
                    error_match_detail_1.visible()
                    error_match_detail_1.showProgress()
                    collapsing_toolbar.gone()
                }
                Result.Status.SUCCESS -> {
                    error_match_detail_1.endProgress()
                    error_match_detail_1.gone()
                    collapsing_toolbar.visible()
                    it.data?.teams?.get(0)?.let { team -> iv_home.setImageUrlDarkBg(team.strTeamBadge?:"") }
                }
                Result.Status.ERROR -> {
                    error_match_detail_1.endProgress()
                }
            }
        })
        mViewModel.getAwayTeam().observe(this, Observer {
            when(it.status){
                Result.Status.LOADING -> {
                    error_match_detail_1.visible()
                    error_match_detail_1.showProgress()
                    collapsing_toolbar.gone()
                }
                Result.Status.SUCCESS -> {
                    error_match_detail_1.endProgress()
                    error_match_detail_1.gone()
                    collapsing_toolbar.visible()
                    it.data?.teams?.get(0)?.let { team -> iv_away.setImageUrlDarkBg(team.strTeamBadge?:"") }
                }
                Result.Status.ERROR -> {
                    error_match_detail_1.endProgress()
                }
            }
        })
    }

    private fun setupDataToView(data: Event){
        tv_score_home.text = data.intHomeScore
        tv_score_away.text = data.intAwayScore

        tv_team_home.text = data.strHomeTeam
        tv_team_away.text = data.strAwayTeam

        tv_date_match.text = convertStringToDate(data.dateEvent?:"", data.strTime?:"")
        tv_time_match.text = convertStringToTime(data.dateEvent?:"", data.strTime?:"")

        tv_goal_home.text = data.strHomeGoalDetails
        tv_goal_away.text = data.strAwayGoalDetails

        tv_shoot_home.text = data.intHomeShots.toString()
        tv_shoot_away.text = data.intAwayShots.toString()

        tv_formation_home.text = data.strHomeFormation
        tv_formation_away.text = data.strAwayFormation

        tv_yellow_home.text = data.strHomeYellowCards
        tv_yellow_away.text = data.strAwayYellowCards

        tv_red_home.text = data.strHomeRedCards
        tv_red_away.text = data.strAwayRedCards

        tv_keeper_home.text = data.strHomeLineupGoalkeeper
        tv_keeper_away.text = data.strAwayLineupGoalkeeper

        tv_back_home.text = data.strHomeLineupDefense
        tv_back_away.text = data.strAwayLineupDefense

        tv_mid_home.text = data.strHomeLineupMidfield
        tv_mid_away.text = data.strAwayLineupMidfield

        tv_front_home.text = data.strHomeLineupForward
        tv_front_away.text = data.strAwayLineupForward

        tv_subtitute_home.text = data.strHomeLineupSubstitutes
        tv_subtitute_away.text = data.strAwayLineupSubstitutes
    }

    companion object{

        private const val EXTRA_EVENT = "EXTRA_EVENT"

        fun startActivity(context: Context, data: Event){
            context.startActivity(Intent(context, MatchDetailActivity::class.java).putExtra(EXTRA_EVENT, data))
        }
    }
}
