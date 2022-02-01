package com.example.androidseminar.activity

import android.os.Bundle
import com.example.androidseminar.databinding.ActivitySettingBinding
import com.example.androidseminar.util.BaseActivity
import com.example.androidseminar.util.SOPTSharedPreferences

class SettingActivity : BaseActivity<ActivitySettingBinding>({ ActivitySettingBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvAutoRemove.setOnClickListener {
            binding.tvAutoRemove.text="자동로그인이 해제됨"

            SOPTSharedPreferences.removeAutoLogin(this)
            SOPTSharedPreferences.clearStorage(this)
        }
    }
}