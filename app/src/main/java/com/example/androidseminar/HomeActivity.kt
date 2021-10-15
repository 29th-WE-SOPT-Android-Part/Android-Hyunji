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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.user=User("윤현지","24","isfp")

       // initTransactionEvent()

        binding.homeFollowerBtn.setOnClickListener {
            val fragment1=FollowerFragment()
            supportFragmentManager.beginTransaction().add(R.id.container_home,fragment1).commit()
            Log.d("HomeActivity","fragment1 added")
        }




        setContentView(binding.root)
    }


}