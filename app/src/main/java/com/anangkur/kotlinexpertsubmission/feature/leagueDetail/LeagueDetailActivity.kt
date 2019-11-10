package com.anangkur.kotlinexpertsubmission.feature.leagueDetail

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*

class LeagueDetailActivity: BaseActivity<LeagueViewModel>() {

    override val mLayout: Int
        get() = R.layout.activity_league_detail
    override val mViewModel: LeagueViewModel
        get() = obtainViewModel(LeagueViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = getString(R.string.toolbar_league_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
