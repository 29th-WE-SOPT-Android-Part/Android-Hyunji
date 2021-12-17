package com.example.androidseminar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidseminar.R
import com.example.androidseminar.adapter.RepositoryRecyclerViewAdapter
import com.example.androidseminar.data.RepoInfo

import com.example.androidseminar.databinding.FragmentRepositoryBinding
import com.example.androidseminar.util.BaseFragment
import com.example.androidseminar.util.function.HorizontalItemDecoration


class RepositoryFragment : BaseFragment<FragmentRepositoryBinding>() {

    private lateinit var adapter: RepositoryRecyclerViewAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepositoryBinding {
        return FragmentRepositoryBinding.inflate(inflater,container,false)
    }

    val repoData = mutableListOf(
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제")

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRepoRecyclerView()
    }

    private fun initRepoRecyclerView() {
        adapter = RepositoryRecyclerViewAdapter()
        adapter.repoList = repoData
        binding.repositoryRecyclerview.adapter = adapter
        binding.repositoryRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.repositoryRecyclerview.addItemDecoration(
            HorizontalItemDecoration(
                5f,
                10f,
                20,
                ContextCompat.getColor(requireContext(), R.color.divider_gray)
            )
        )

    }

}