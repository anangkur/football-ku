package com.anangkur.kotlinexpertsubmission

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*

class ItemViewUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams(matchParent, wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            imageView {
                id = R.id.iv_teams
                layoutParams = LinearLayout.LayoutParams(100, 100)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
            verticalLayout {
                lparams(matchParent, wrapContent){
                    marginStart = 40
                }
                textView {
                    id = R.id.tv_title
                    layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                    textSize = 16f
                }
                textView {
                    id = R.id.tv_desc
                    layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                    textSize = 14f
                    maxLines = 3
                    ellipsize = TextUtils.TruncateAt.END
                }
            }
        }
    }
}