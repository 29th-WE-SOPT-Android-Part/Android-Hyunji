package com.example.androidseminar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragmentActivity: FragmentActivity):
FragmentStateAdapter(fragmentActivity){
    val fragments=mutableListOf<Fragment>()

    override fun getItemCount()=fragments.size //TODO :Int 생략

    override fun createFragment(position: Int): Fragment =fragments[position]
}