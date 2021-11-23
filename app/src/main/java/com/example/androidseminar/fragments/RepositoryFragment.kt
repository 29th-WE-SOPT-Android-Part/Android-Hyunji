package com.example.androidseminar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidseminar.R
import com.example.androidseminar.adapter.RepositoryRecyclerViewAdapter
import com.example.androidseminar.data.RepoInfo

import com.example.androidseminar.databinding.FragmentRepositoryBinding
import com.example.androidseminar.util.HorizontalItemDecoration


class RepositoryFragment : Fragment() {

    private lateinit var adapter: RepositoryRecyclerViewAdapter
    private lateinit var binding: FragmentRepositoryBinding

    val repoData = mutableListOf(
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제"),
        RepoInfo("안드로이드 과제 레포지토리", "안드로이드 파트 과제")

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRepositoryBinding.inflate(inflater, container, false)

        initRepoRecyclerView()
        return binding.root
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