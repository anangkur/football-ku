package com.anangkur.kotlinexpertsubmission.feature.leagueDetail.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.kotlinexpertsubmission.R
import com.anangkur.kotlinexpertsubmission.data.model.Table
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val listItem: ArrayList<Table> = ArrayList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_standing){
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false))
        }else{
            HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_header_standing, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return listItem.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0){
            R.layout.item_header_standing
        }else{
            R.layout.item_standing
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            holder.bind(listItem[position-1])
        }
    }

    fun setRecyclerData(data: List<Table>){
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(data: Table){
            itemView.tv_position.text = "${position}"
            itemView.tv_name_team.text = data.name
            itemView.tv_played.text = data.played.toString()
            itemView.tv_goal.text = data.goalsdifference.toString()
            itemView.tv_win.text = data.win.toString()
            itemView.tv_draw.text = data.draw.toString()
            itemView.tv_lose.text = data.loss.toString()
            itemView.tv_point.text = data.total.toString()
        }
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view)

}