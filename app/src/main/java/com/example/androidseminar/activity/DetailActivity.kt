package com.example.androidseminar.activity

import android.os.Bundle
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.util.BaseActivity

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it)}) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name=intent.getStringExtra("name")
        val picture=intent.getIntExtra("picture",0)

        binding.detailNameTv.setText(name)
        binding.detailProfileIv.setImageResource(picture)
    }
}