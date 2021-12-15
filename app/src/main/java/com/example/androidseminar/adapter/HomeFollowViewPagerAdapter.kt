package com.example.androidseminar.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeFollowViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int) = fragments[position]


}