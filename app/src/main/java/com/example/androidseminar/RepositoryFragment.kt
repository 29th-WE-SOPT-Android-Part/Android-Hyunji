package com.example.androidseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.androidseminar.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {

    private lateinit var adapter:RepositoryRecyclerViewAdapter
    private lateinit var binding: FragmentRepositoryBinding

    val repoData=mutableListOf<RepoInfo>(
        RepoInfo("repository1","첫번째 레포지토리"),
        RepoInfo("repository1","첫번째 레포지토리"),
        RepoInfo("repository1","첫번째 레포지토리"),
        RepoInfo("repository1","첫번째 레포지토리")



    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding= FragmentRepositoryBinding.inflate(inflater,container,false)



        //addData()
        initRepoRecyclerView()
        return binding.root
    }

//    private fun addData(){
//        repoData.add(RepoInfo("repository1","첫번째 레포지토리"))
//        repoData.add(RepoInfo("repository2","두번째 레포지토리"))
//        repoData.add(RepoInfo("repository3","세번째 레포지토리"))
//        repoData.add(RepoInfo("repository4","네번째 레포지토리"))
//
//    }

    private fun initRepoRecyclerView(){
        adapter= RepositoryRecyclerViewAdapter()
        adapter.repoList=repoData
        binding.repositoryRecyclerview.adapter=adapter
        val gridLayoutManager=GridLayoutManager(requireContext(),2)
        binding.repositoryRecyclerview.layoutManager=gridLayoutManager
        //binding.repositoryRecyclerview.layoutManager= LinearLayoutManager(requireContext()) //TODO gridLayout으로 바꾸기

    }


}