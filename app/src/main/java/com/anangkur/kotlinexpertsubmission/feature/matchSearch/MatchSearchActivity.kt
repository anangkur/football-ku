package com.anangkur.kotlinexpertsubmission.feature.matchSearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.data.model.Result
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.MatchActionListener
import com.anangkur.kotlinexpertsubmission.feature.leagueDetail.nextMatch.NextMatchAdapter
import com.anangkur.kotlinexpertsubmission.feature.matchDetail.MatchDetailActivity
import com.anangkur.kotlinexpertsubmission.util.*
import kotlinx.android.synthetic.main.activity_match_search.*

class MatchSearchActivity: BaseActivity<MatchSearchViewModel>(), MatchActionListener {

    override val mLayout: Int
        get() = R.layout.activity_match_search
    override val mViewModel: MatchSearchViewModel
        get() = obtainViewModel(MatchSearchViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = ""

    private lateinit var mAdapter: NextMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearchView()
        setupAdapter()
        observeViewModel()
    }

    private fun setupSearchView(){
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mViewModel.refreshData(it)
                }
                hideSoftKeyboard(this@MatchSearchActivity)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupAdapter(){
        mAdapter = NextMatchAdapter(this)
        recycler_match_search.adapter = mAdapter
        recycler_match_search.setupRecyclerViewLinear(this)
    }

    private fun observeViewModel(){
        mViewModel.getSearchMatch().observe(this, Observer {result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    pb_search_match.gone()
                    recycler_match_search.visible()
                    val data = result.data?.events
                    data?.let { mAdapter.setRecyclerData(it) }
                }
                Result.Status.LOADING -> {
                    pb_search_match.visible()
                    recycler_match_search.gone()
                }
                Result.Status.ERROR -> {
                    pb_search_match.gone()
                    showSnackbarShort(result.message?:"")
                }
            }
        })
    }

    override fun onClickMatch(data: Event) {
        MatchDetailActivity.startActivity(this, data)
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, MatchSearchActivity::class.java))
        }
    }
}
