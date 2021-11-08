package com.example.androidseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidseminar.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeFollowViewPagerAdapter: HomeFollowViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        initTabLayout()
    }

    private fun initAdapter(){
        val fragmentList=listOf(HomeFollowerFragment(),HomeFollowingFragment())

        homeFollowViewPagerAdapter=HomeFollowViewPagerAdapter(this)
        homeFollowViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homefragVp.adapter=homeFollowViewPagerAdapter

    }

    private fun initTabLayout(){
        val tabLabel=listOf("팔로잉","팔로워")

        TabLayoutMediator(binding.homeTablayout,binding.homefragVp){tab,position->
            tab.text=tabLabel[position]
        }.attach()
    }

}