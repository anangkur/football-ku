package com.anangkur.kotlinexpertsubmission.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.anangkur.kotlinexpertsubmission.model.Teams
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getParcelableExtra<Teams>(EXTRA_DETAIL)
        DetailActivityUI(data).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = data.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
        }
    }

    companion object{
        private const val EXTRA_DETAIL = "EXTRA_DETAIL"
        fun startActivity(context: Context, data: Teams){
            context.startActivity(Intent(context, DetailActivity::class.java).putExtra(EXTRA_DETAIL, data))
        }
    }

    inner class DetailActivityUI(private val data: Teams): AnkoComponent<DetailActivity>{
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui){
            scrollView {
                verticalLayout {
                    imageView(data.image).lparams(matchParent, dip(200))
                    textView(data.name)
                        .lparams(wrapContent, wrapContent){
                            margin = dip(10)
                        }
                        .textSize = 24f
                    textView(data.desc)
                        .lparams(wrapContent, wrapContent){
                            margin = dip(10)
                        }
                        .textSize = 14f
                }
            }
        }
    }
}
