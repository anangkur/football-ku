package com.anangkur.kotlinexpertsubmission.feature.favourite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.feature.custom.DefaultTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.favourite.match.FavouriteMatchFragment
import com.anangkur.kotlinexpertsubmission.feature.favourite.team.FavouriteTeamFragment
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.android.synthetic.main.layout_toolbar.toolbar

class FavouriteActivity: BaseActivity<FavouriteViewModel>(), MatchActionListener {

    override val mLayout: Int
        get() = R.layout.activity_favourite
    override val mViewModel: FavouriteViewModel
        get() = obtainViewModel(FavouriteViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = getString(R.string.toolbar_favourite)

    private lateinit var tabAdapter: DefaultTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupTabAdapter()
        setupDataToView()
    }

    private fun setupTabAdapter(){
        tabAdapter = DefaultTabAdapter(supportFragmentManager)
    }

    private fun setupDataToView(){
        tabAdapter.addFragment(FavouriteTeamFragment.newInstance(), getString(R.string.tab_team))
        tabAdapter.addFragment(FavouriteMatchFragment.newInstance(), getString(R.string.tab_match))
        vp_favourite.adapter = tabAdapter
        tab_favourite.setupWithViewPager(vp_favourite)
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
