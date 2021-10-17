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
    ): RepositoryRecyclerViewAdapter.MyViewHolder {
        val binding=ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int =repoList.size

    inner class MyViewHolder(private val binding: ItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(repoinfo:RepoInfo){
            binding.repositoryNameTv.text=repoinfo.repo_name
            binding.repositoryExplainTv.text=repoinfo.repo_explain
        }
    }


}