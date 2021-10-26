package com.example.androidseminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.databinding.ItemFollowerBinding
import com.example.androidseminar.databinding.ItemRepositoryBinding

class RepositoryRecyclerViewAdapter : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.MyViewHolder>() {

    var repoList=mutableListOf<RepoInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding=ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int =repoList.size

    class MyViewHolder(private val binding: ItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(repoinfo:RepoInfo){
            binding.repositoryNameTv.text=repoinfo.repoName
            binding.repositoryExplainTv.text=repoinfo.repoExplain
        }
    }


}