package com.anangkur.kotlinexpertsubmission.feature.favourite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.base.BaseActivity
import com.anangkur.kotlinexpertsubmission.util.obtainViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*

class FavouriteActivity: BaseActivity<FavouriteViewModel>() {
    override val mLayout: Int
        get() = R.layout.activity_favourite
    override val mViewModel: FavouriteViewModel
        get() = obtainViewModel(FavouriteViewModel::class.java)
    override val mToolbar: Toolbar
        get() = toolbar
    override val mTitleToolbar: String
        get() = getString(R.string.toolbar_favourite)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, FavouriteActivity::class.java))
        }
    }
}
