package com.example.androidseminar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import com.example.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    val followerFragment=FollowerFragment()
    val repositoryFragment=RepositoryFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.user=User("윤현지","24","ISFP")

        initTransactionEvent()

        setContentView(binding.root)
    }


    private fun initTransactionEvent(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_home,followerFragment)
            .commit()


        binding.homeFollowerBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_home,followerFragment)
                .commit()
        }

        binding.homeRepositoryBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_home,repositoryFragment)
                .commit()
        }

    }
}