package com.example.androidseminar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import com.example.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding= ActivityHomeBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.user=User("윤현지","24","isfp")

        binding.homeGithubiconIv.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hyunji24"))
            startActivity(intent)
        }



        setContentView(binding.root)
    }
}