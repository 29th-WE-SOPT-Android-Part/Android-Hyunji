package com.example.androidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.*
import androidx.viewpager2.widget.ViewPager2
import com.example.androidseminar.*
import com.example.androidseminar.adapter.HomeViewPagerAdapter
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivityHomeBinding
import com.example.androidseminar.fragments.HomeFragment
import com.example.androidseminar.fragments.ImageFragment
import com.example.androidseminar.fragments.ProfileFragment
import com.example.androidseminar.util.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it)}) {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPagerAdapter()
        initBottomNavigation()
    }

    private fun initViewPagerAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), ImageFragment())

        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homeVp.adapter = homeViewPagerAdapter
    }

    private fun initBottomNavigation() {
        binding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            binding.homeVp.currentItem=
                when(it.itemId){
                    R.id.menu_profile -> FIRST_FRAGMENT
                    R.id.menu_home -> SECOND_FRAGMENT
                    else -> THIRD_FRAGMENT
                }
            return@setOnItemSelectedListener true
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2

    }

}