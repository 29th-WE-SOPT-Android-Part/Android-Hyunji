package com.example.androidseminar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidseminar.R
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivitySettingBinding
import com.example.androidseminar.util.BaseActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>({ ActivitySettingBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}