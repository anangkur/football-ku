package com.anangkur.kotlinexpertsubmission.main

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.model.Teams
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainAdapter(val actionListener: MainActionListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = ArrayList<Teams>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewUI().createView(AnkoContext.create(parent.context)))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setRecyclerData(listData: List<Teams>){
        data.clear()
        data.addAll(listData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
        private val ivTeams = itemView.findViewById<ImageView>(R.id.iv_teams)
        fun bind(data: Teams){
            tvTitle.text = data.name
            tvDesc.text = data.desc
            ivTeams.setImageDrawable(ContextCompat.getDrawable(itemView.context, data.image))
            itemView.setOnClickListener { actionListener.onClickItem(data) }
        }
    }

    inner class ItemViewUI : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>) = with(ui){
            verticalLayout {
                lparams(matchParent, wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
                imageView {
                    id = R.id.iv_teams
                    layoutParams = LinearLayout.LayoutParams(dip(80), dip(80))
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                verticalLayout {
                    lparams(matchParent, wrapContent){
                        marginStart = dip(20)
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
}