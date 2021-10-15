package com.example.androidseminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.databinding.ItemFollowerBinding

class FollowerRecyclerViewAdapter : RecyclerView.Adapter<FollowerRecyclerViewAdapter.MyViewHolder>() {

    var infoList=mutableListOf<Info>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerRecyclerViewAdapter.MyViewHolder {
        val binding=ItemFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int =infoList.size

    inner class MyViewHolder(private val binding: ItemFollowerBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(info:Info){
            binding.nameTv.text=info.follower_name
            binding.partNameTv.text=info.follower_part
        }
    }


}