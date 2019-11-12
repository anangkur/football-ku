package com.anangkur.kotlinexpertsubmission.feature.matchDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.data.model.Event
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
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
    }

    private fun getDataFromIntent(){
        mViewModel.dataFromIntent = intent.getParcelableExtra(EXTRA_EVENT)
    }

    companion object{

        private const val EXTRA_EVENT = "EXTRA_EVENT"

        fun startActivity(context: Context, data: Event){
            context.startActivity(Intent(context, MatchDetailActivity::class.java).putExtra(EXTRA_EVENT, data))
        }
    }
}
