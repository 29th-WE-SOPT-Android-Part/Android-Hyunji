package com.example.androidseminar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.viewpager2.widget.ViewPager2
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
//    val followerFragment=FollowerFragment()
//    val repositoryFragment=RepositoryFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityHomeBinding.inflate(layoutInflater)
        //binding.user=User("윤현지","24","ISFP")

       // initTransactionEvent()
        initViewPagerAdapter()
        initBottomNavigation()

        setContentView(binding.root)
    }

    private fun initViewPagerAdapter(){
        val fragmentList=listOf(ProfileFragment(),HomeFragment(),ImageFragment())

        homeViewPagerAdapter= HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.homeVp.adapter=homeViewPagerAdapter
    }

    private fun initBottomNavigation(){
        binding.homeVp.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked=true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile->{
                    binding.homeVp.currentItem=FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home->{
                    binding.homeVp.currentItem=SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else->{
                    binding.homeVp.currentItem=THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object{
        const val FIRST_FRAGMENT=0
        const val SECOND_FRAGMENT=1
        const val THIRD_FRAGMENT=2

    }

//    private fun initTransactionEvent(){
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.container_home,followerFragment)
//            .commit()
//
//
//        binding.profileFollowerBtn.setOnClickListener {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container_home,followerFragment)
//                .commit()
//        }
//
//        binding.profileRepositoryBtn.setOnClickListener {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container_home,repositoryFragment)
//                .commit()
//        }
//
//    }
}