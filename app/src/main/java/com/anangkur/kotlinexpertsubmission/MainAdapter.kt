package com.anangkur.kotlinexpertsubmission

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.kotlinexpertsubmission.model.Teams
import org.jetbrains.anko.*

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
        }
    }
}