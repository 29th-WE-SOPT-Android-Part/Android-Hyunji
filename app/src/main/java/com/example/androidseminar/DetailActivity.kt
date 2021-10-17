package com.example.androidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivitySignInBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDetailBinding.inflate(layoutInflater)


        setContentView(binding.root)

    }
}