package com.example.androidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivitySignInBinding
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