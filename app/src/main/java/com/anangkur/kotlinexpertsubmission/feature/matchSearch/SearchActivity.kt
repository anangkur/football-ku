package com.anangkur.kotlinexpertsubmission.feature.matchSearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.base.BaseErrorView
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.custom.DefaultTabAdapter
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchAdapter
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.match.SearchMatchFragment
import com.anangkur.kotlinexpertsubmission.feature.matchSearch.team.SearchTeamFragment
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_match_search.*

class SearchActivity: BaseActivity<SearchViewModel>(), MatchActionListener {

    override val mLayout: Int
        get() = R.layout.activity_match_search
    override val mViewModel: SearchViewModel
        get() = obtainViewModel(SearchViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    private lateinit var tabAdapter: DefaultTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearchView()
        setupTabAdapter()
        setupDataToView()
    }

    private fun setupSearchView(){
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mViewModel.refreshData(it)
                }
                hideSoftKeyboard(this@SearchActivity)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupTabAdapter(){
        tabAdapter = DefaultTabAdapter(supportFragmentManager)
    }

    private fun setupDataToView(){
        tabAdapter.addFragment(SearchMatchFragment.newInstance(), getString(R.string.tab_match))
        tabAdapter.addFragment(SearchTeamFragment.newInstance(), getString(R.string.tab_team))
        vp_search.adapter = tabAdapter
        tab_search.setupWithViewPager(vp_search)
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }
}
